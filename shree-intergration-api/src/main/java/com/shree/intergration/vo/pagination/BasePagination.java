package com.shree.intergration.vo.pagination;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    protected boolean verified;

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
     * 列序列的方式（ASC、DESC）
     */
    protected String orderSort;

    /**
     * 错误消息
     */
    protected String errMsg;

    public BasePagination() {
        this.pageSize = 0;
        this.pageIndex = -1;
        this.verified = false;
    }

    /**
     * 分页参数检测
     *
     * @return 检测结果（true | false）
     */
    public boolean paramsVerify() {
        // 检测分页大小以及起始页数字是否存在
        if (this.pageSize > 0 && this.pageIndex >= 0) {
            // 检测排序字段是否为空
            if (StringUtils.isNotEmpty(this.orderField) && StringUtils.isNotEmpty(this.orderSort)) {
                // 检测排序类型是否正确
                return SORT_TYPE.contains(this.orderSort.toLowerCase());
            }
        }
        return false;
    }

    /**
     * 构建 MyBatis Plus 分页对象
     *
     * @param classType 分页对象泛型
     * @return
     */
    public Page<T> generatePageParams(Class<T> classType) {
        if (this.isVerified()) {
            // 由于MybatisPlus分页默认以1开始，之前取的index需加1
            Page<T> page = new Page<T>(this.getPageIndex() + 1, this.getPageSize());
            String orderField = this.getOrderField();
            try {
                Field field = classType.getDeclaredField(this.getOrderField());
                if (field != null) {
                    TableField tableField = field.getAnnotation(TableField.class);
                    orderField = tableField.value();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (SORT_ASC.equals(this.getOrderSort().toLowerCase())) {
                page.getOrders().add(OrderItem.asc(orderField));
            } else {
                page.getOrders().add(OrderItem.desc(orderField));
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
        if (!this.isVerified()) {
            this.setOrderField(orderField);
            this.setOrderSort(orderSort);
            this.setPageSize(pageSize);
            this.setPageIndex(pageIndex);
            this.setStartRow(this.getPageIndex() * this.getPageSize());
            this.setVerified(this.paramsVerify());
        }
        return generatePageParams(classType);
    }
}
