package com.example.maycatalogmovie.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.maycatalogmovie.BuildConfig;
import com.example.maycatalogmovie.fragment.ListTv;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewModelTv extends ViewModel {
    private MutableLiveData<ArrayList<ListTv>> listMutableLiveData = new MutableLiveData<>();
    private static final String Api_Key = BuildConfig.TMDB_API_KEY;

    public void setApiKey(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ListTv> listTv = new ArrayList<>();
        String url ="https://api.themoviedb.org/3/tv/popular?api_key="+Api_Key+"&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject tv = jsonArray.getJSONObject(i);
                        ListTv listTv1 = new ListTv();
                        listTv1.setId(tv.getInt("id"));
                        listTv1.setName(tv.getString("name"));
                        listTv1.setPosterPath(tv.getString("poster_path"));
                        listTv1.setO_language(tv.getString("original_language"));
                        listTv1.setFa_date(tv.getString("first_air_date"));
                        listTv1.setOverview(tv.getString("overview"));
                        listTv.add(listTv1);
                    }listMutableLiveData.postValue(listTv);
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

    public LiveData<ArrayList<ListTv>> getApiKey(){
        return listMutableLiveData;
    }

}
