package com.lujiatao.ims.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页器
 *
 * @author lujiatao
 */
public class Paginationer {

    private int pageSize;

    public Paginationer() {
        this(10);
    }

    public Paginationer(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取目标页数据
     *
     * @param list       总数据
     * @param targetPage 目标页码
     * @return 当前页码、总页码和目标页数据
     */
    public Object[] getTargetPageData(List<?> list, int targetPage) {
        if (list == null || list.size() == 0) {
            return new Object[]{0, 0, new ArrayList<>()};
        }
        int currentPage = 0;
        int totalPage = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
        List<?> pagelist;
        if (targetPage < 1) {
            currentPage = 1;
            pagelist = list.subList(0, currentPage == totalPage ? list.size() : pageSize);
        } else if (targetPage > totalPage) {
            currentPage = totalPage;
            pagelist = list.subList((currentPage - 1) * pageSize, list.size());
        } else {
            currentPage = targetPage;
            pagelist = list.subList((currentPage - 1) * pageSize, currentPage == totalPage ? list.size() : currentPage * pageSize);
        }
        return new Object[]{currentPage, totalPage, pagelist};
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
