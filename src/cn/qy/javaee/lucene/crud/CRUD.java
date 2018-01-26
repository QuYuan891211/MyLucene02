package cn.qy.javaee.lucene.crud;

import cn.qy.javaee.lucene.entity.Article;
import cn.qy.javaee.lucene.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.search;

public class CRUD {
    @Test
    public void add() throws Exception{
        Article article = new Article(1,"腾讯","腾讯是一家上市公司",9);
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(), IndexWriter.MaxFieldLength.LIMITED);
        Document document = LuceneUtil.javaBean2Document(article);
        indexWriter.addDocument(document);
        indexWriter.close();
    }
    @Test
    public void addAll() throws Exception{
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        Article article1 = new Article(1,"腾讯","腾讯是一家上市公司",10);
        indexWriter.addDocument(LuceneUtil.javaBean2Document(article1));
        Article article2 = new Article(2,"腾讯","腾讯的董事会主席是马化腾",20);
        indexWriter.addDocument(LuceneUtil.javaBean2Document(article2));
        Article article3 = new Article(3,"腾讯","腾讯的总市值超过3000亿美元",20);
        indexWriter.addDocument(LuceneUtil.javaBean2Document(article3));
        Article article4 = new Article(4,"腾讯","腾讯的游戏很赚钱",30);
        indexWriter.addDocument(LuceneUtil.javaBean2Document(article4));
        Article article5 = new Article(5,"腾讯","腾讯在港股的股价为800港币",5);
        indexWriter.addDocument(LuceneUtil.javaBean2Document(article5));
        indexWriter.close();
    }
    @Test
    public void delete() throws Exception{
        Integer id = 1;
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        Term term = new Term("id",id.toString());
        indexWriter.deleteDocuments(term);
        indexWriter.close();
    }
    @Test
    public void deleteAll() throws Exception{
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        indexWriter.deleteAll();
        indexWriter.close();
    }
    @Test
    public void update() throws Exception{
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
        Integer id = 3;
        Article article = new Article(5,"腾讯","腾讯的股价是900",50);
        Term term = new Term("id",id.toString());
        Document document = LuceneUtil.javaBean2Document(article);
        indexWriter.updateDocument(term,document);
        indexWriter.close();

    }
    @Test
    public void findAll() throws Exception{
        List<Article> list = new ArrayList<Article>();
        IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
        QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
        String keywords = "腾";
        Query query = queryParser.parse(keywords);
        TopDocs topDocs = indexSearcher.search(query,1000000);
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            Integer doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = (Article) LuceneUtil.document2JavaBean(document,Article.class);
            list.add(article);
        }
        for(Article article:list){
            System.out.println(article);
        }
        indexSearcher.close();

    }
}
