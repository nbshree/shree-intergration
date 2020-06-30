package com.shree.intergration.common.web.rest.standard;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shree.intergration.common.web.rest.BasePagination;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class StdPagination<T> extends BasePagination<T> {

    public static final String PAGE_SIZE_NAME = "limit";
    public static final String PAGE_INDEX_NAME = "page";
    public static final String ORDER_FIELD_NAME = "orderField";
    public static final String ORDER_SORT_NAME = "orderSort";

    public StdPagination() {
        super();
    }

    public StdPagination(HttpServletRequest request) {
        try {
            if (request.getParameterMap().containsKey(PAGE_SIZE_NAME) && request.getParameterMap().containsKey(PAGE_INDEX_NAME)) {
                this.setPageSize( Long.parseLong(request.getParameter(PAGE_SIZE_NAME)) );
                this.setPageIndex(Long.parseLong(request.getParameter(PAGE_INDEX_NAME)) - 1);
                this.setStartRow(this.getPageIndex() * this.getPageSize());
            }
            if (request.getParameterMap().containsKey(ORDER_FIELD_NAME)) {
                this.setOrderField(request.getParameter(ORDER_FIELD_NAME));
            }
            if (request.getParameterMap().containsKey(ORDER_SORT_NAME)) {
                this.setOrderSort(request.getParameter(ORDER_SORT_NAME));
            }
        } catch (Exception ex) {
            log.error("StdPagination通过构造时发生了错误！", ex);
        }
        // 检查分页参数
        boolean pageResult = this.verifyPageParams();
        this.setPageVerified(pageResult);

        // 检查排序参数
        boolean orderResult = this.verifyOrderParams();
        this.setOrderVerified(orderResult);
    }

    @SuppressWarnings("unchecked")
    public StdPageResult getStdPageResult(IPage<T> mybatisPage) {
        StdPageResult stdPageResult = new StdPageResult();
        stdPageResult.setData(mybatisPage.getRecords());
        stdPageResult.setCount((int) mybatisPage.getTotal());
        stdPageResult.setStart((int) ((mybatisPage.getCurrent()) * mybatisPage.getSize()));
        stdPageResult.setCurrent(mybatisPage.getCurrent());
        return stdPageResult;
    }
}
