package com.example.maycatalogmovie.fragment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMovie implements Parcelable {
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setO_language(String o_language) {
        this.o_language = o_language;
    }

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("original_language")
    @Expose
    private String o_language;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public String getOverview(){
        return overview;
    }

    public String getO_language(){
        return o_language;
    }
    public ListMovie() {
    }

    private ListMovie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.release_date = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.o_language = in.readString();
    }

    public static final Creator<ListMovie> CREATOR = new Creator<ListMovie>() {
        @Override
        public ListMovie createFromParcel(Parcel in) {
            return new ListMovie(in);
        }

        @Override
        public ListMovie[] newArray(int size) {
            return new ListMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.release_date);
        parcel.writeString(this.posterPath);
        parcel.writeString(this.overview);
        parcel.writeString(this.o_language);
    }


}
