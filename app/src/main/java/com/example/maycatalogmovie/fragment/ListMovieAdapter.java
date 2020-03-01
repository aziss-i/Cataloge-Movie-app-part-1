package com.example.maycatalogmovie.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maycatalogmovie.R;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> implements Filterable {

    private ArrayList<ListMovie> listMovie;
    private ArrayList<ListMovie> listMoviefull;

    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    public void setData(ArrayList<ListMovie> items){
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }

    public ListMovieAdapter(ArrayList<ListMovie> list){
        this.listMovie = list;
        listMoviefull = new ArrayList<>(listMovie);
    }

    private OnItemClickCallback onItemClickCallback;
    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListMovieAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListMovieAdapter.ListViewHolder holder, int position) {
        holder.bind(listMovie.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    @Override
    public Filter getFilter() {
        return searchMovie;
    }

    private Filter searchMovie = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<ListMovie> filteredlist = new ArrayList<>();
            if (charSequence == null || charSequence.length()==0){
                filteredlist.addAll(listMoviefull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (ListMovie item : listMoviefull){
                    if (item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredlist.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listMovie.clear();
            listMovie.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView poster_path;
        TextView title, overview, release_date;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            poster_path = itemView.findViewById(R.id.poster_path_favorite);
            overview = itemView.findViewById(R.id.overview);
            title = itemView.findViewById(R.id.title);
            release_date = itemView.findViewById(R.id.release_date);
        }

        void bind(ListMovie movie_list){
            title.setText(movie_list.getTitle());
            release_date.setText(movie_list.getRelease_date());
            overview.setText(movie_list.getOverview());
            Glide.with(itemView).load(IMAGE_BASE_URL+movie_list.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster_path);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ListMovie data);
    }
}
