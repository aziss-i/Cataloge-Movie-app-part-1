package com.example.maycatalogmovie.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.maycatalogmovie.BuildConfig;
import com.example.maycatalogmovie.fragment.ListMovie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewModelMovies extends ViewModel {

    private MutableLiveData<ArrayList<ListMovie>> listMutableLiveData = new MutableLiveData<>();
    private static final String Api_Key = BuildConfig.TMDB_API_KEY;


    public void setApiKey(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ListMovie> listMovie = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/popular?api_key="+Api_Key+"&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject movie = jsonArray.getJSONObject(i);
                        ListMovie listMovie1 = new ListMovie();
                        listMovie1.setId(movie.getInt("id"));
                        listMovie1.setTitle(movie.getString("title"));
                        listMovie1.setPosterPath(movie.getString("poster_path"));
                        listMovie1.setO_language(movie.getString("original_language"));
                        listMovie1.setRelease_date(movie.getString("release_date"));
                        listMovie1.setOverview(movie.getString("overview"));
                        listMovie.add(listMovie1);
                    }listMutableLiveData.postValue(listMovie);
                } catch (Exception e) {
                    Log.d("Exception",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure",error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<ListMovie>> getApiKey(){
        return listMutableLiveData;
    }

}
