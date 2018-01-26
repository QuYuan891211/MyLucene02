package cn.qy.javaee.lucene.dao;

import cn.qy.javaee.lucene.entity.Article;
import cn.qy.javaee.lucene.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    public Integer getIndexNum(String words) throws Exception {
        String keyWords = words;
        IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
        QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
        Query query = queryParser.parse(words);
        TopDocs docs = indexSearcher.search(query,1000000);
        System.out.println("总数为："+docs.totalHits);
        indexSearcher.close();
        return docs.totalHits;
    }
    public List<Article> getAllRecordWithPagination(String words,Integer start,Integer size) throws Exception{
        List<Article> list = new ArrayList<Article>();
        IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
        QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
        Query query = queryParser.parse(words);
        TopDocs topDocs = indexSearcher.search(query,10000);
        Integer middle = Math.min(topDocs.totalHits,start+size);
        for(int i = start;i<=middle;i++){
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            Document document = indexSearcher.doc(scoreDoc.doc);
            Article article = (Article)LuceneUtil.document2JavaBean(document,Article.class);
            list.add(article);
        }
        return list;
    }

}
