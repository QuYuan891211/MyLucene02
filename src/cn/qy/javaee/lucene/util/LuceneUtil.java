package cn.qy.javaee.lucene.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.lang.reflect.Method;

public class LuceneUtil {
    public static Directory getDirectory() {
        return directory;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    public static Version getVersion() {
        return version;
    }

    public static IndexWriter.MaxFieldLength getMaxFieldLength() {
        return maxFieldLength;
    }

    private static Directory directory;
    private static Analyzer analyzer;
    private static Version version;
    private static IndexWriter.MaxFieldLength maxFieldLength;


    static {
        try{
            directory = FSDirectory.open(new File("D:\\WebProjects\\qy\\IdeaProjects\\MyLuence02\\LuceneDBDB"));
            version = Version.LUCENE_30;
            analyzer = new StandardAnalyzer(version);
            maxFieldLength = IndexWriter.MaxFieldLength.LIMITED;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public static Document javaBean2Document(Object object) throws Exception{
        Document document = new Document();
        Class clazz = object.getClass();
        java.lang.reflect.Field[] declaredFields = clazz.getDeclaredFields();
        for(java.lang.reflect.Field field: declaredFields){
            field.setAccessible(true);
            String name = field.getName();
            String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1,name.length());
            String value = clazz.getMethod(methodName,null).invoke(object,null).toString();
            //后两个属性：1.是否存入词汇表中 2.是否对该记录进行分词
            document.add(new Field(name,value, Field.Store.YES, Field.Index.ANALYZED));
        }
        return document;
    }
    public static Object document2JavaBean(Document document,Class clazz) throws Exception{
        Object object = clazz.newInstance();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field field: fields){
            field.setAccessible(true);
            String name = field.getName();
            String value = document.get(name);
            BeanUtils.setProperty(object,name,value);

        }
        return object;
    }

}
