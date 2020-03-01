package com.example.maycatalogmovie.fragment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUpcoming implements Parcelable {
    public ListUpcoming() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
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

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("original_language")
    @Expose
    private String original_language;
    protected ListUpcoming(Parcel in) {
        id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        original_language = in.readString();
    }

    public static final Creator<ListUpcoming> CREATOR = new Creator<ListUpcoming>() {
        @Override
        public ListUpcoming createFromParcel(Parcel in) {
            return new ListUpcoming(in);
        }

        @Override
        public ListUpcoming[] newArray(int size) {
            return new ListUpcoming[size];
        }
    };

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getBackdrop_path(){
        return backdrop_path;
    }

    public String getOverview(){
        return overview;
    }

    public String getOriginal_language(){
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(release_date);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(original_language);
    }
}
