package com.zhangzhihao.SpringMVCSeedProject.Utils;


import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResults<T> {
    // 下一页
    @Getter
    private int nextPage;

    // 当前页
    @Getter
    private int currentPage;

    // 每页数量
    @Getter
    private int pageSize;

    // 总条数
    @Getter
    @Setter
    private int totalCount;

    // 总页数
    @Getter
    @Setter
    private int pageCount;

    // 记录
    @Getter
    @Setter
    private List<T> results;

    public void setNextPage(int nextPage) {
        if (nextPage <= 0) {
            this.nextPage = 1;
        } else {
            if (nextPage > pageCount && pageCount > 0) {
                this.nextPage = pageCount;
            } else if (nextPage > pageCount && pageCount == 0) {
                this.nextPage = 1;
            } else {
                this.nextPage = nextPage;
            }
        }
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0) {
            this.currentPage = 1;
        } else {
            if (currentPage > pageCount && pageCount > 0) {
                this.currentPage = pageCount;
            } else if (currentPage > pageCount && pageCount == 0) {
                this.currentPage = 1;
            } else {
                this.currentPage = currentPage;
            }
        }
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 1 : pageSize;
    }


//    public void resetPageNumber() {
//        nextPage = currentPage + 1;
//        pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
//                : totalCount / pageSize + 1;
//    }
}
