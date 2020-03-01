package com.example.maycatalogmovie.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maycatalogmovie.R;

import java.util.ArrayList;

public class ListTvAdapter extends RecyclerView.Adapter<ListTvAdapter.ListViewHolder> {

    private ArrayList<ListTv> listTv;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    public void setData(ArrayList<ListTv> items){
        listTv.clear();
        listTv.addAll(items);
        notifyDataSetChanged();
    }

    public ListTvAdapter (ArrayList<ListTv> list){
        this.listTv = list;
    }

    private OnItemClickCallback onItemClickCallback;
    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListTvAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tv,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListTvAdapter.ListViewHolder holder, int position) {
        holder.bind(listTv.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listTv.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView poster_path;
        TextView title, overview, date;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            poster_path=itemView.findViewById(R.id.poster_path_tv);
            overview=itemView.findViewById(R.id.overview_tv);
            title=itemView.findViewById(R.id.title_tv);
            date=itemView.findViewById(R.id.release_tv);
        }
        void bind (ListTv tvList){
            title.setText(tvList.getName());
            date.setText(tvList.getFa_date());
            overview.setText(tvList.getOverview());
            Glide.with(itemView).load(IMAGE_BASE_URL+tvList.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster_path);
        }
    }
    public interface OnItemClickCallback{
        void onItemClicked(ListTv data);
    }
}
