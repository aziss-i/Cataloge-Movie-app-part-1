package com.example.maycatalogmovie.fragment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTv implements Parcelable {

    public ListTv() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFa_date(String fa_date) {
        this.fa_date = fa_date;
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

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("first_air_date")
    @Expose
    private String fa_date;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("original_language")
    @Expose
    private String o_language;

    protected ListTv(Parcel in) {
        id = in.readInt();
        name = in.readString();
        fa_date = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        o_language = in.readString();
    }

    public static final Creator<ListTv> CREATOR = new Creator<ListTv>() {
        @Override
        public ListTv createFromParcel(Parcel in) {
            return new ListTv(in);
        }

        @Override
        public ListTv[] newArray(int size) {
            return new ListTv[size];
        }
    };

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFa_date() {
        return fa_date;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(fa_date);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(o_language);
    }
}


