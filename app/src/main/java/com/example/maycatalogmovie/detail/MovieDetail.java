package com.example.maycatalogmovie.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maycatalogmovie.R;
import com.example.maycatalogmovie.database.MovieFavoriteHelper;
import com.example.maycatalogmovie.fragment.ListMovie;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ListMovie listMovie;
    private MovieFavoriteHelper movieFavoriteHelper;
    private boolean isFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView Title = findViewById(R.id.title_in_detail);
        TextView Language = findViewById(R.id.language_in_detail);
        TextView Overview = findViewById(R.id.overview_in_detail);
        TextView Release_date = findViewById(R.id.release_date_in_detail);
        ImageView Poster_path = findViewById(R.id.poster_path_in_detail);
        Button Button_back = findViewById(R.id.button_back_in_detail);
        Button button_favorite = findViewById(R.id.button_favorite);

        listMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        assert listMovie !=null;

        String Title_movie = listMovie.getTitle();
        Title.setText(Title_movie);

        String Language_movie = listMovie.getO_language();
        Language.setText(Language_movie);

        String Overview_movie = listMovie.getOverview();
        Overview.setText(Overview_movie);

        String Release_date_movie = listMovie.getRelease_date();
        Release_date.setText(Release_date_movie);

        String Poster_path_movie = listMovie.getPosterPath();
        Glide.with(this).load(IMAGE_BASE_URL+Poster_path_movie)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(Poster_path);
        Button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        movieFavoriteHelper = MovieFavoriteHelper.getInstance(getApplicationContext());
        movieFavoriteHelper.open();
        isFavorite = false;
        ArrayList<ListMovie> movieInFavorite = movieFavoriteHelper.getAllMovie();
        for (ListMovie item : movieInFavorite){
            if(listMovie.getId() == item.getId()) {
                isFavorite = true;
            }
            if (isFavorite==true){
                break;
            }
        }

        button_favorite.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectedMovie(listMovie);
            }
        });
    }

    private void showSelectedMovie(ListMovie listMovies){
        Button button = findViewById(R.id.button_favorite);
        if (isFavorite){
            button.setBackgroundResource(R.drawable.ic_favorite_border);
            movieFavoriteHelper.deleteFavorite(listMovies.getId());
            Toast.makeText(this,"Dihapus dari MovieFavorite"+listMovie.getTitle(),Toast.LENGTH_SHORT).show();
        }else {
            button.setBackgroundResource(R.drawable.ic_favorite);
            movieFavoriteHelper.addFavorite(listMovies);
            Toast.makeText(this,"Tersimpan di MovieFavorite"+listMovie.getTitle(),Toast.LENGTH_SHORT).show();
        }
    }
}
