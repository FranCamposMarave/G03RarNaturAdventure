package model.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

/**
 * Created by oscar on 27/11/14.
 */
@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name="Activity.getAll", query = "SELECT a FROM Activity a"),
        @NamedQuery(name="Activity.get", query = "SELECT a FROM Activity a WHERE a.id = :id"),
        @NamedQuery(name="Activity.deleteById", query = "DELETE FROM Activity a WHERE a.id = :id")
})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlTransient
    private Long id;

    private String title;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private float price;

    private String picture;


    public Activity() {
        super();
    }

    public Activity(Long id, String title, String description, Date date, float price, String picture) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                '}';
    }
}