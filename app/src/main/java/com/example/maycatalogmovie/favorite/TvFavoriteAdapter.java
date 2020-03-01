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
import com.example.maycatalogmovie.detail.TvDetail;
import com.example.maycatalogmovie.fragment.ListTv;

import java.util.ArrayList;

public class TvFavoriteAdapter extends RecyclerView.Adapter<TvFavoriteAdapter.FavoriteViewHolder>{
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ArrayList<ListTv> listTv = new ArrayList<>();
    private Context mcontext;

    public ArrayList<ListTv> getListFavorite(){
        return listTv;
    }

    public void setListFavorite(ArrayList<ListTv> tvs){
        this.listTv = tvs;
    }

    public TvFavoriteAdapter(Context context){
        this.mcontext = context;
    }

    public void setData(ArrayList<ListTv> items){
        listTv.clear();
        listTv.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvFavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tv_favorite,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvFavoriteAdapter.FavoriteViewHolder holder, final int position) {
        holder.bind(listTv.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, TvDetail.class);
                intent.putExtra(TvDetail.EXTRA_TV,getListFavorite().get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView title, overview, release_Date;
        ImageView poster_path;
        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_favorite_tv);
            overview = itemView.findViewById(R.id.overview_favorite_tv);
            release_Date = itemView.findViewById(R.id.release_date_favorite_tv);
            poster_path = itemView.findViewById(R.id.poster_path_favorite_tv);
        }
        void bind(ListTv itemTv){
            title.setText(itemTv.getName());
            overview.setText(itemTv.getOverview());
            release_Date.setText(itemTv.getFa_date());
            Glide.with(itemView).load(IMAGE_BASE_URL+itemTv.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster_path);
        }
    }
}
