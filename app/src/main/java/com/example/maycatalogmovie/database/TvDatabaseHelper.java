package com.example.maycatalogmovie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_NAME;
import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv;

public class TvDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbtvapp";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_FAVORITE =
            String.format("CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL)",
                    TV_NAME,
                    ColumnTv.TV_ID,
                    ColumnTv.TV_TITLE,
                    ColumnTv.TV_OVERVIEW,
                    ColumnTv.TV_DATE,
                    ColumnTv.TV_POSTER_PATH);

    TvDatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAVORITE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TV_NAME);
        onCreate(sqLiteDatabase);

    }
}
