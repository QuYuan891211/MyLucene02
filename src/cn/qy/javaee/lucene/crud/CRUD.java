package cn.qy.javaee.lucene.crud;

import cn.qy.javaee.lucene.entity.Article;
import cn.qy.javaee.lucene.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.search;

public class CRUD {
    @Test
    public void add() throws Exception{
        Article article = new Article(1,"腾讯","腾讯是一家上市公司",9);
        Directory RAMdirectory = new RAMDirectory(LuceneUtil.getDirectory());

        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
        IndexWriter RAMIndexWriter = new IndexWriter(RAMdirectory,LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());

        Document document = LuceneUtil.javaBean2Document(article);
        RAMIndexWriter.addDocument(document);
        RAMIndexWriter.close();
        indexWriter.addIndexesNoOptimize(RAMdirectory);
        indexWriter.close();
    }
    @Test
    public void addAll() throws Exception{
        //进行索引库优化
        Directory RAMdirectory = new RAMDirectory(LuceneUtil.getDirectory());
        IndexWriter RAMindexWriter = new IndexWriter(RAMdirectory,LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        Article article1 = new Article(1,"腾讯","腾讯是一家上市公司",10);
        RAMindexWriter.addDocument(LuceneUtil.javaBean2Document(article1));
        Article article2 = new Article(2,"腾讯","腾讯的董事会主席是马化腾",20);
        RAMindexWriter.addDocument(LuceneUtil.javaBean2Document(article2));
        Article article3 = new Article(3,"腾讯","腾讯的总市值超过3000亿美元",20);
        RAMindexWriter.addDocument(LuceneUtil.javaBean2Document(article3));
        Article article4 = new Article(4,"腾讯","腾讯的游戏很赚钱",30);
        RAMindexWriter.addDocument(LuceneUtil.javaBean2Document(article4));
        Article article5 = new Article(5,"腾讯上","腾讯在港股的股价为800港币",5);
        RAMindexWriter.addDocument(LuceneUtil.javaBean2Document(article5));

        RAMindexWriter.close();
        indexWriter.addIndexesNoOptimize(RAMdirectory);
        indexWriter.setMergeFactor(3);
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
        /*多条件搜索
        与结果排序*/
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtil.getVersion(),new String[]{"content","title"},LuceneUtil.getAnalyzer());
        String keywords = "腾";
        Sort sort = new Sort(new SortField("count",SortField.INT,true),new SortField("id",SortField.INT,true));
        Query query = queryParser.parse(keywords);
        TopDocs topDocs = indexSearcher.search(query,null,1000000,sort);
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
