package com.example.maycatalogmovie.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.maycatalogmovie.HomeScreen;
import com.example.maycatalogmovie.R;
import com.example.maycatalogmovie.detail.UpcomingDetail;
import com.example.maycatalogmovie.view_model.ViewModelUpcoming;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upcoming extends Fragment {
    private RecyclerView recyclerView;
    private ListUpcomingAdapter listUpcomingAdapter;
    private ProgressBar progressBar;
    private ViewModelUpcoming viewModelUpcoming;

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public Upcoming() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.fragment_upcoming);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listUpcomingAdapter = new ListUpcomingAdapter();
        listUpcomingAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listUpcomingAdapter);

        viewModelUpcoming = new ViewModelProvider(getActivity(),new ViewModelProvider.NewInstanceFactory()).get(ViewModelUpcoming.class);
        viewModelUpcoming.setApi_Key();
        showLoading(true);
        viewModelUpcoming.getApiKey().observe(getActivity(), new Observer<ArrayList<ListUpcoming>>() {
            @Override
            public void onChanged(ArrayList<ListUpcoming> list) {
                if (list!=null){
                    listUpcomingAdapter.setData(list);
                    showLoading(false);
                }
            }
        });
        listUpcomingAdapter.setOnItemClickCallback(new ListUpcomingAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(ListUpcoming data) {
                showSelectedUpcoming(data);
            }
        });
        showRecyclerList();
    }
    private void showSelectedUpcoming(ListUpcoming data) {
        Intent intent = new Intent(getActivity(), UpcomingDetail.class);
        data.getBackdrop_path();
        data.getTitle();
        data.getOverview();
        data.getRelease_date();
        data.getOriginal_language();
        intent.putExtra(UpcomingDetail.EXTRA_UPCOMING,data);
        startActivity(intent);
    }

    private void showRecyclerList() {

    }
}
