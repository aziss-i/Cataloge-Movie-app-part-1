package com.example.maycatalogmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;

import com.example.maycatalogmovie.HomeScreen;
import com.example.maycatalogmovie.R;
import com.example.maycatalogmovie.detail.MovieDetail;
import com.example.maycatalogmovie.view_model.ViewModelMovies;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Movie extends Fragment {
    private RecyclerView recyclerView;
    private ListMovieAdapter listMovieAdapter;
    private ProgressBar progressBar;
    private ViewModelMovies viewModelMovies;
    private ArrayList<ListMovie> listmovie = new ArrayList<>();

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public Movie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.fragment_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovieAdapter = new ListMovieAdapter(listmovie);
        listMovieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listMovieAdapter);

        viewModelMovies = new ViewModelProvider(getActivity(),new ViewModelProvider.NewInstanceFactory()).get(ViewModelMovies.class);
        viewModelMovies.setApiKey();
        showLoading(true);
        viewModelMovies.getApiKey().observe(getActivity(), new Observer<ArrayList<ListMovie>>() {
            @Override
            public void onChanged(ArrayList<ListMovie> list) {
                if (list!=null){
                    listMovieAdapter.setData(list);
                    showLoading(false);
                }
            }
        });

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(ListMovie data) {
                showSelectedMovie(data);
            }
        });
        showRecyclerList();
    }

    private void showSelectedMovie(ListMovie data) {
        Intent intent = new Intent(getActivity(), MovieDetail.class);
        data.getPosterPath();
        data.getTitle();
        data.getOverview();
        data.getRelease_date();
        data.getO_language();
        intent.putExtra(MovieDetail.EXTRA_MOVIE,data);
        startActivity(intent);
    }

    private void showRecyclerList() {

    }
}
