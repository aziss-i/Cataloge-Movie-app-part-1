package com.example.maycatalogmovie.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class ColoumnMovie implements BaseColumns{
        public static final String ID = "id";
        public static final String MOVIE_NAME = "movie";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String POSTER_PATH = "posterPath";
    }

    public static final class ColumnTv implements BaseColumns {
        public static final String TV_NAME = "tv";
        public static final String TV_ID = "id";
        public static final String TV_TITLE = "name";
        public static final String TV_OVERVIEW = "overview";
        public static final String TV_DATE = "first_air_date";
        public static final String TV_POSTER_PATH = "poster_path";

    }
}
