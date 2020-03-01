package com.example.maycatalogmovie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maycatalogmovie.fragment.ListMovie;

import java.util.ArrayList;

import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.ID;
import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.MOVIE_NAME;
import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.OVERVIEW;
import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.POSTER_PATH;
import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.RELEASE_DATE;
import static com.example.maycatalogmovie.database.DatabaseContract.ColoumnMovie.TITLE;

public class MovieFavoriteHelper {
    private static final String Database_Table = MOVIE_NAME;
    private static MovieDatabaseHelper databaseHelper;
    private static MovieFavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    private MovieFavoriteHelper(Context context){
        databaseHelper = new MovieDatabaseHelper(context);
    }

    public static MovieFavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieFavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<ListMovie> getAllMovie(){
        ArrayList<ListMovie> favoriteList = new ArrayList<>();
        Cursor cursor = database.query(Database_Table,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null
                );
        cursor.moveToFirst();
        ListMovie listMovie;
        if (cursor.getCount()>0){
            do {
                listMovie = new ListMovie();
                listMovie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                listMovie.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                listMovie.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
                listMovie.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
                listMovie.setPosterPath(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
                favoriteList.add(listMovie);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return favoriteList;
    }

    public long addFavorite(ListMovie listMovie){
        ContentValues values = new ContentValues();
        values.put(ID,listMovie.getId());
        values.put(TITLE,listMovie.getTitle());
        values.put(OVERVIEW,listMovie.getOverview());
        values.put(RELEASE_DATE,listMovie.getRelease_date());
        values.put(POSTER_PATH,listMovie.getPosterPath());
        return database.insert(Database_Table,null,values);
    }

    public int deleteFavorite(int id){
        return database.delete(MOVIE_NAME,ID+" = '"+ id +"'",null);
    }

}
