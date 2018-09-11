package ben.com.linkviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class LinkModel implements Parcelable {

    private long id;
    private String link;
    private String date;
    private int status;

    public LinkModel() {
    }

    protected LinkModel(Parcel in) {
        id = in.readLong();
        link = in.readString();
        date = in.readString();
        status = in.readInt();
        //status = Status.values()[in.readInt()].getValue();
    }

    public static final Creator<LinkModel> CREATOR = new Creator<LinkModel>() {
        @Override
        public LinkModel createFromParcel(Parcel in) {
            return new LinkModel(in);
        }

        @Override
        public LinkModel[] newArray(int size) {
            return new LinkModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(link);
        parcel.writeString(date);
        parcel.writeInt(status);
    }
}
