package cn.qy.javaee.lucene.entity;

import java.util.List;

public class Pagination<T> {
    private Integer currPageNum;
    private Integer totalPageNum;
    private Integer pageSize = 2;
    private Integer totalNum;
    private List<T> list;


    public Pagination(Integer currPageNum, Integer totalPageNum, Integer pageSize, Integer totalNum, List<T> list) {
        this.currPageNum = currPageNum;
        this.totalPageNum = totalPageNum;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.list = list;
    }

    public Pagination(){

    }

    public Integer getCurrPageNum() {
        return currPageNum;
    }

    public void setCurrPageNum(Integer currPageNum) {
        this.currPageNum = currPageNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer allObjNum) {
        this.totalNum = allObjNum;
        if(this.totalNum % this.pageSize == 0){
            this.totalPageNum = this.totalNum / this.pageSize;
        }else{
            this.totalPageNum = this.totalNum / this.pageSize + 1;
        }
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
