package org.mybatis.example.bean;

/**
 * Created by fatkun on 2017/8/13.
 */
public class Blog {
    private int id;
    private String title;
    private String content;
    private BlogStatusType status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public BlogStatusType getStatus() {
        return status;
    }

    public void setStatus(BlogStatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
