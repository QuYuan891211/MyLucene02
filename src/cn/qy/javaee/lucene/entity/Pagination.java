package cn.qy.javaee.lucene.entity;

import java.util.List;

public class Pagination<T> {
    private Integer currPageNum;
    private Integer totalPageNum;
    private Integer pageSize;
    private Integer totalNum;
    private List<T> list;


    public Integer getCurrPageNum() {
        return currPageNum;
    }

    public void setCurrPageNum(Integer currPageNum) {
        this.currPageNum = currPageNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
