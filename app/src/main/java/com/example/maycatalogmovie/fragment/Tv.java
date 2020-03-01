package com.example.maycatalogmovie.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.maycatalogmovie.R;
import com.example.maycatalogmovie.detail.TvDetail;
import com.example.maycatalogmovie.view_model.ViewModelTv;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class Tv extends Fragment {

    private RecyclerView recyclerView2;
    private ListTvAdapter listTvAdapter;
    private ProgressBar progressBar;
    private ViewModelTv viewModelTvs;
    private ArrayList<ListTv> listtv = new ArrayList<>();

    public Tv() {
        // Required empty public constructor
    }

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(View view2, Bundle savedInstanceState) {
        super.onViewCreated(view2, savedInstanceState);

        progressBar = view2.findViewById(R.id.progressBar);
        recyclerView2 = view2.findViewById(R.id.fragment_tv);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        listTvAdapter = new ListTvAdapter(listtv);
        listTvAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(listTvAdapter);

        viewModelTvs = new ViewModelProvider(getActivity(),new ViewModelProvider.NewInstanceFactory()).get(ViewModelTv.class);
        viewModelTvs.setApiKey();
        showLoading(true);
        viewModelTvs.getApiKey().observe(getActivity(), new Observer<ArrayList<ListTv>>() {
            @Override
            public void onChanged(ArrayList<ListTv> list) {
                if (list!=null){
                    listTvAdapter.setData(list);
                    showLoading(false);
                }
            }
        });

        listTvAdapter.setOnItemClickCallback(new ListTvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(ListTv data) {
                showSelectedTv(data);
            }
        });
        showRecyclerList();

    }
    private void showSelectedTv(ListTv data) {
        Intent intent = new Intent(getActivity(), TvDetail.class);
        data.getPosterPath();
        data.getName();
        data.getOverview();
        data.getFa_date();
        data.getO_language();
        intent.putExtra(TvDetail.EXTRA_TV,data);
        startActivity(intent);
    }

    private void showRecyclerList() {

    }
}
