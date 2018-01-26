package cn.qy.javaee.lucene.entity;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer count;
    public Article(){}

    public Article(Integer id, String title, String content, Integer count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return this.id + " " + this.title + " " + this.content + " " + this.count;
    }
}
