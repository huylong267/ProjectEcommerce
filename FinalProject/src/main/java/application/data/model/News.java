package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_news")
public class News {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "news_id")
    private int news_id;
    @Column(name = "title")
    private String title;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    private Date created_date;
    @Column(name = "updated_date")
    private Date updated_date;

    public News() {
    }

    public News(String title, String image, String content, Date created_date, Date updated_date) {
        this.title = title;
        this.image=image;
        this.content = content;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
}
