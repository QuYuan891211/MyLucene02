package cn.qy.javaee.lucene.dao;

import cn.qy.javaee.lucene.util.LuceneUtil;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;

public class ArticleDao {
    public Integer getIndexNum(String words) throws Exception {
        String keyWords = words;
        IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
        QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
    }

}
