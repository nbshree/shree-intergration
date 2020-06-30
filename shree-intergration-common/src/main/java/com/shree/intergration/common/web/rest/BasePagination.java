package com.shree.intergration.common.web.rest;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shree.intergration.common.util.FieldUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Data
public abstract class BasePagination<T> {

    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";
    public static final List<String> SORT_TYPE = Arrays.asList(SORT_ASC, SORT_DESC);

    /**
     * 分页参数校验是否通过
     */
    protected boolean pageVerified;
    /**
     * 排序参数校验是否通过
     */
    protected boolean orderVerified;

    /**
     * 分页大小
     */
    protected long pageSize;

    /**
     * 分页页码
     */
    protected long pageIndex;

    /**
     * 起始行数
     */
    protected long startRow;

    /**
     * 数据总数
     */
    protected long totalCount;

    /**
     * 过滤后数据总数
     */
    protected long fieldCount;

    /**
     * 排序列名
     */
    protected String orderField;

    /**
     * 列序列的方式（asc、desc）
     */
    protected String orderSort;

    /**
     * 错误消息
     */
    protected String errMsg;

    public BasePagination() {
        this.pageSize = 0;
        this.pageIndex = -1;
        this.pageVerified = false;
        this.orderVerified = false;
    }

    /**
     * 分页参数检测
     *
     * @return 检测结果（true | false）
     */
    public boolean verifyPageParams() {
        // 检测分页大小以及起始页数字是否存在
        return this.pageSize > 0 && this.pageIndex >= 0;
    }

    public boolean verifyOrderParams() {
        // 检测排序字段是否为空
        return StringUtils.isNotEmpty(this.orderField) && StringUtils.isNotEmpty(this.orderSort) && SORT_TYPE.contains(this.orderSort.toLowerCase());
    }

    /**
     * 构建 MyBatis Plus 分页对象
     *
     * @param classType 分页对象泛型
     * @return MybatisPlus的分页对象
     */
    public Page<T> generatePageParams(Class<T> classType) {
        if (this.isPageVerified()) {
            // 由于MybatisPlus分页默认以1开始，之前取的index需加1
            Page<T> page = new Page<>(this.getPageIndex() + 1, this.getPageSize());
            String orderField = this.getOrderField();

            if (this.isOrderVerified()) {
                try {
                    // 查询是否有对应field，然后获取对应的数据库字段名
                    Field field = FieldUtils.getField(classType, orderField);
                    TableField tableField = field.getAnnotation(TableField.class);
                    orderField = tableField.value();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
                if (SORT_ASC.equals(this.getOrderSort().toLowerCase())) {
                    page.getOrders().add(OrderItem.asc(orderField));
                } else {
                    page.getOrders().add(OrderItem.desc(orderField));
                }
            }
            return page;
        } else {
            return null;
        }
    }

    /**
     * 构建 MyBatis Plus 分页对象, 优先采用 Request 传入的, 如果没 Request 分页参数, 则采用当前参数传入的
     *
     * @param classType  分页对象泛型
     * @param orderField 排序字段, 默认 DESC 排序
     * @return
     */
    public Page<T> generatePageParams(Class<T> classType, String orderField) {
        return generatePageParams(classType, orderField, "DESC");
    }

    /**
     * 构建 MyBatis Plus 分页对象, 优先采用 Request 传入的, 如果没 Request 分页参数, 则采用当前参数传入的
     *
     * @param classType  分页对象泛型
     * @param orderField 排序字段, 默认 DESC 排序
     * @param orderSort  排序方向: DESC, ASC
     * @return
     */
    public Page<T> generatePageParams(Class<T> classType, String orderField, String orderSort) {
        return generatePageParams(classType, 10, 0, orderField, orderSort);
    }

    /**
     * 构建 MyBatis Plus 分页对象, 优先采用 Request 传入的, 如果没 Request 分页参数, 则采用当前参数传入的
     *
     * @param classType  分页对象泛型
     * @param pageSize   每页数量
     * @param pageIndex  页索引: >=1
     * @param orderField 排序字段
     * @param orderSort  排序方向: DESC, ASC
     * @return
     */
    public Page<T> generatePageParams(Class<T> classType, int pageSize, int pageIndex, String orderField, String orderSort) {
        if (!this.isPageVerified()) {
            this.setPageSize(pageSize);
            this.setPageIndex(pageIndex);
            this.setStartRow(this.getPageIndex() * this.getPageSize());

            this.setPageVerified(this.verifyPageParams());
        }
        // 检测排序字段是否为空
        if (StringUtils.isBlank(this.orderField)) {
            this.orderField = orderField.toLowerCase();
        }
        if (StringUtils.isBlank(this.orderSort)) {
            this.orderSort = orderSort.toLowerCase();
        }

        boolean orderVerify = this.verifyOrderParams();
        this.setOrderVerified(orderVerify);

        return generatePageParams(classType);
    }
}