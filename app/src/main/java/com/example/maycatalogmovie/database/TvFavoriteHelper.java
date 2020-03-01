package com.example.maycatalogmovie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maycatalogmovie.fragment.ListTv;

import java.util.ArrayList;

import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_DATE;
import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_ID;
import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_NAME;
import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_OVERVIEW;
import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_POSTER_PATH;
import static com.example.maycatalogmovie.database.DatabaseContract.ColumnTv.TV_TITLE;

public class TvFavoriteHelper {
    private static final String Database_Table = TV_NAME;
    private static TvDatabaseHelper databaseHelper;
    private static TvFavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    private TvFavoriteHelper(Context context){
        databaseHelper = new TvDatabaseHelper(context);
    }

    public static TvFavoriteHelper getInstance(Context context){
        if (INSTANCE==null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE==null){
                    INSTANCE=new TvFavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open()throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<ListTv> getAllTv(){
        ArrayList<ListTv> favoriteList = new ArrayList<>();
        Cursor cursor = database.query(Database_Table,
                null,
                null,
                null,
                null,
                null,
                TV_ID + " ASC",
                null
        );
        cursor.moveToFirst();
        ListTv listTv;
        if (cursor.getCount()>0){
            do {
                listTv = new ListTv();
                listTv.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(TV_ID))));
                listTv.setName(cursor.getString(cursor.getColumnIndex(TV_TITLE)));
                listTv.setOverview(cursor.getString(cursor.getColumnIndex(TV_OVERVIEW)));
                listTv.setFa_date(cursor.getString(cursor.getColumnIndex(TV_DATE)));
                listTv.setPosterPath(cursor.getString(cursor.getColumnIndex(TV_POSTER_PATH)));
                favoriteList.add(listTv);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return favoriteList;
    }

    public long addFavorite(ListTv listTv){
        ContentValues values = new ContentValues();
        values.put(TV_ID,listTv.getId());
        values.put(TV_TITLE,listTv.getName());
        values.put(TV_OVERVIEW,listTv.getOverview());
        values.put(TV_DATE,listTv.getFa_date());
        values.put(TV_POSTER_PATH,listTv.getPosterPath());
        return database.insert(Database_Table,null,values);
    }

    public int deleteFavorite(int id){
        return database.delete(TV_NAME,TV_ID+" = '"+ id +"'",null);
    }
}
