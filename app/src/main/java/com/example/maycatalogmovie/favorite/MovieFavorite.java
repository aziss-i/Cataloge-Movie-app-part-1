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
import com.example.maycatalogmovie.database.MovieFavoriteHelper;
import com.example.maycatalogmovie.fragment.ListMovie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavorite extends Fragment {
    private ArrayList<ListMovie> listmovie = new ArrayList<>();
    private MovieFavoriteAdapter movieFavoriteAdapter;
    private ProgressBar progressBar;
    private MovieFavoriteHelper movieFavoriteHelper;
    private RecyclerView recyclerView;

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }


    public MovieFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieFavoriteHelper = MovieFavoriteHelper.getInstance(view.getContext());
        movieFavoriteHelper.open();

        recyclerView = view.findViewById(R.id.fragment_movie_Favorite);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        movieFavoriteAdapter = new MovieFavoriteAdapter(view.getContext());
        movieFavoriteAdapter.setListFavorite(listmovie);
        recyclerView.setAdapter(movieFavoriteAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        listmovie = movieFavoriteHelper.getAllMovie();
        movieFavoriteAdapter.setData(listmovie);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieFavoriteHelper.close();
    }
}
