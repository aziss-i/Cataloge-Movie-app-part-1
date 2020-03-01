package com.example.maycatalogmovie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.MOVIE_NAME;
import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie;

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "dbmovieapp";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_FAVORITE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            MOVIE_NAME,
            ColoumnMovie.ID,
            ColoumnMovie.TITLE,
            ColoumnMovie.OVERVIEW,
            ColoumnMovie.RELEASE_DATE,
            ColoumnMovie.POSTER_PATH);


    MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIE_NAME);
        onCreate(sqLiteDatabase);
    }
}
