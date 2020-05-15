package com.shree.intergration.vo.result.layui;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shree.intergration.vo.pagination.BasePagination;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class LayPagination<T> extends BasePagination<T> {

    public static final String PAGE_SIZE_NAME = "limit";
    public static final String PAGE_INDEX_NAME = "page";
    public static final String ORDER_FIELD_NAME = "orderField";
    public static final String ORDER_SORT_NAME = "orderSort";

    public LayPagination() {
        super();
    }

    public LayPagination(HttpServletRequest request) {
        try {
            if (request.getParameterMap().containsKey(PAGE_SIZE_NAME) && request.getParameterMap().containsKey(PAGE_INDEX_NAME)) {
                this.setPageSize(Long.parseLong(request.getParameter(PAGE_SIZE_NAME)));
                this.setPageIndex(Long.parseLong(request.getParameter(PAGE_INDEX_NAME)) - 1);
                this.setStartRow(this.getPageIndex() * this.getPageSize());
                this.setOrderField(request.getParameter(ORDER_FIELD_NAME));
                this.setOrderSort(request.getParameter(ORDER_SORT_NAME));
            }
        } catch (Exception ex) {
            log.error("LayPagination通过构造时发生了错误！", ex);
        }
        this.setVerified(this.paramsVerify());
    }

    @SuppressWarnings("unchecked")
    public LayPageResult getLayPageResult(IPage<T> mybatisPage) {
        LayPageResult layPageResult = new LayPageResult();
        layPageResult.setData(mybatisPage.getRecords());
        layPageResult.setCount((int) mybatisPage.getTotal());
        layPageResult.setStart((int) ((mybatisPage.getCurrent() - 1) * mybatisPage.getSize()));
        return layPageResult;
    }
}
