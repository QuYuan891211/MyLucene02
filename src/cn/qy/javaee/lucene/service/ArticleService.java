package cn.qy.javaee.lucene.service;

import cn.qy.javaee.lucene.dao.ArticleDao;
import cn.qy.javaee.lucene.entity.Article;
import cn.qy.javaee.lucene.entity.Pagination;

public class ArticleService {
    private ArticleDao articleDao = new ArticleDao();
    public Pagination<Article> fy(String words, int currentNo) throws Exception{
        Pagination<Article> pagination = new Pagination<>();
        pagination.setCurrPageNum(currentNo);
        Integer start = (pagination.getCurrPageNum()-1)*pagination.getPageSize();
        pagination.setTotalNum(articleDao.getIndexNum(words));
        pagination.setTotalPageNum(pagination.getTotalNum());
        pagination.setList(articleDao.getAllRecordWithPagination(words,start,pagination.getPageSize()));
        return pagination;
    }
}
