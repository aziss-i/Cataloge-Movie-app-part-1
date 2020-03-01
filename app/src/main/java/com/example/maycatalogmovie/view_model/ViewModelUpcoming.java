package com.example.maycatalogmovie.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.maycatalogmovie.BuildConfig;
import com.example.maycatalogmovie.fragment.ListUpcoming;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewModelUpcoming extends ViewModel {

    private MutableLiveData<ArrayList<ListUpcoming>> listMutableLiveData = new MutableLiveData<>();
    private static final String Api_Key = BuildConfig.TMDB_API_KEY;

    public void setApi_Key(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ListUpcoming> listUpcoming = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+Api_Key+"&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject upcoming = jsonArray.getJSONObject(i);
                        ListUpcoming listUpcoming1 = new ListUpcoming();
                        listUpcoming1.setId(upcoming.getInt("id"));
                        listUpcoming1.setTitle(upcoming.getString("title"));
                        listUpcoming1.setBackdrop_path(upcoming.getString("backdrop_path"));
                        listUpcoming1.setOriginal_language(upcoming.getString("original_language"));
                        listUpcoming1.setRelease_date(upcoming.getString("release_date"));
                        listUpcoming1.setOverview(upcoming.getString("overview"));
                        listUpcoming.add(listUpcoming1);
                    }listMutableLiveData.postValue(listUpcoming);
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
    public LiveData<ArrayList<ListUpcoming>> getApiKey(){
        return listMutableLiveData;
    }

}
