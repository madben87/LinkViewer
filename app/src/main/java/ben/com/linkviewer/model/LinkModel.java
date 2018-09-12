package ben.com.linkviewer.model;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LinkModel extends RealmObject {

    @PrimaryKey
    private long id;
    private String link;
    private String date;
    private int status;

    public LinkModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkModel linkModel = (LinkModel) o;
        return Objects.equals(link, linkModel.link) &&
                Objects.equals(date, linkModel.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(link, date);
    }

    @Override
    public String toString() {
        return "LinkModel{" +
                "link='" + link + '\'' +
                '}';
    }
}
