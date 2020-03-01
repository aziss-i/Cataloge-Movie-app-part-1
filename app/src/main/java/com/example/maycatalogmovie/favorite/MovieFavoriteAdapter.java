package com.example.maycatalogmovie.favorite;

import android.content.Context;
import android.content.Intent;
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
import com.example.maycatalogmovie.detail.MovieDetail;
import com.example.maycatalogmovie.fragment.ListMovie;

import java.util.ArrayList;

public class MovieFavoriteAdapter extends  RecyclerView.Adapter<MovieFavoriteAdapter.FavoriteViewHolder>{

    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ArrayList<ListMovie> listMovie = new ArrayList<>();
    private Context mcontext;

    public ArrayList<ListMovie> getListFavorite(){
        return listMovie;
    }

    public void setListFavorite(ArrayList<ListMovie> movies){
        this.listMovie = movies;
    }

    public MovieFavoriteAdapter(Context context){
        this.mcontext = context;
    }

    public void setData(ArrayList<ListMovie> items){
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieFavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_favorite,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieFavoriteAdapter.FavoriteViewHolder holder, final int position) {
        holder.bind(listMovie.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, MovieDetail.class);
                intent.putExtra(MovieDetail.EXTRA_MOVIE,getListFavorite().get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView title, overview, release_Date;
        ImageView poster_path;

        FavoriteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_favorite_movie);
            overview = itemView.findViewById(R.id.overview_favorite_movie);
            release_Date = itemView.findViewById(R.id.release_date_favorite_movie);
            poster_path = itemView.findViewById(R.id.poster_path_favorite_movie);
        }
        void bind(ListMovie itemMovie){
            title.setText(itemMovie.getTitle());
            overview.setText(itemMovie.getOverview());
            release_Date.setText(itemMovie.getRelease_date());
            Glide.with(itemView).load(IMAGE_BASE_URL+itemMovie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster_path);
        }
    }
}
