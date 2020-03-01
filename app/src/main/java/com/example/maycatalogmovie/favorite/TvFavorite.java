package com.example.maycatalogmovie.favorite;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.maycatalogmovie.R;
import com.example.maycatalogmovie.database.TvFavoriteHelper;
import com.example.maycatalogmovie.fragment.ListTv;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavorite extends Fragment {
    private ArrayList<ListTv> listtv = new ArrayList<>();
    private TvFavoriteAdapter tvFavoriteAdapter;
    private ProgressBar progressBar;
    private TvFavoriteHelper tvFavoriteHelper;
    private RecyclerView recyclerView;

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public TvFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvFavoriteHelper = TvFavoriteHelper.getInstance(view.getContext());
        tvFavoriteHelper.open();

        recyclerView = view.findViewById(R.id.fragment_tv_Favorite);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        tvFavoriteAdapter = new TvFavoriteAdapter(view.getContext());
        tvFavoriteAdapter.setListFavorite(listtv);
        recyclerView.setAdapter(tvFavoriteAdapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        listtv = tvFavoriteHelper.getAllTv();
        tvFavoriteAdapter.setData(listtv);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvFavoriteHelper.close();
    }
}
