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

public class ListUpcomingAdapter extends RecyclerView.Adapter<ListUpcomingAdapter.ListViewHolder> {
    private ArrayList<ListUpcoming> listUpcoming = new ArrayList<>();
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    public void setData(ArrayList<ListUpcoming> items){
        listUpcoming.clear();
        listUpcoming.addAll(items);
        notifyDataSetChanged();
    }

    private OnItemClickCallback onItemClickCallback;
    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListUpcomingAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_upcoming,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListUpcomingAdapter.ListViewHolder holder, int position) {
        holder.bind(listUpcoming.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listUpcoming.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUpcoming.size();
    }


    public interface OnItemClickCallback {
        void onItemClicked(ListUpcoming data);
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView poster_path;
        TextView title, overview, release_date;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            poster_path = itemView.findViewById(R.id.poster_path_upcoming);
            overview = itemView.findViewById(R.id.overview_upcoming);
            title = itemView.findViewById(R.id.title_upcoming);
            release_date = itemView.findViewById(R.id.release_date_upcoming);
        }
        void bind(ListUpcoming movie_list){
            title.setText(movie_list.getTitle());
            release_date.setText(movie_list.getRelease_date());
            overview.setText(movie_list.getOverview());
            Glide.with(itemView).load(IMAGE_BASE_URL+movie_list.getBackdrop_path())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster_path);
        }
    }
}
