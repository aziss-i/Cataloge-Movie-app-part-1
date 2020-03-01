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
import com.example.maycatalogmovie.database.TvFavoriteHelper;
import com.example.maycatalogmovie.fragment.ListTv;

import java.util.ArrayList;

public class TvDetail extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ListTv listTv;
    private TvFavoriteHelper tvFavoriteHelper;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        TextView Title = findViewById(R.id.title_in_detail_tv);
        TextView Language = findViewById(R.id.language_in_detail_tv);
        TextView Overview = findViewById(R.id.overview_in_detail_tv);
        TextView Release_date = findViewById(R.id.release_date_in_detail_tv);
        ImageView Poster_path = findViewById(R.id.poster_path_in_detail_tv);
        Button Button_back = findViewById(R.id.button_back_in_detail_tv);
        Button button_favorite = findViewById(R.id.button_favorite_tv);

        listTv = getIntent().getParcelableExtra(EXTRA_TV);
        assert listTv !=null;

        String Title_movie = listTv.getName();
        Title.setText(Title_movie);

        String Language_movie = listTv.getO_language();
        Language.setText(Language_movie);

        String Overview_movie = listTv.getOverview();
        Overview.setText(Overview_movie);

        String Release_date_movie = listTv.getFa_date();
        Release_date.setText(Release_date_movie);

        String Poster_path_movie = listTv.getPosterPath();
        Glide.with(this).load(IMAGE_BASE_URL+Poster_path_movie)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(Poster_path);
        Button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tvFavoriteHelper = TvFavoriteHelper.getInstance(getApplicationContext());
        tvFavoriteHelper.open();
        isFavorite = false;
        ArrayList<ListTv> tvInFavorite = tvFavoriteHelper.getAllTv();
        for (ListTv item : tvInFavorite){
            if(listTv.getId() == item.getId()) {
                isFavorite = true;
            }
            if (isFavorite==true){
                break;
            }
        }

        button_favorite.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectedTv(listTv);
            }
        });

    }
    private void showSelectedTv(ListTv listTv){
        Button button = findViewById(R.id.button_favorite_tv);
        if (isFavorite){
            button.setBackgroundResource(R.drawable.ic_favorite_border);
            tvFavoriteHelper.deleteFavorite(listTv.getId());
            Toast.makeText(this,"Dihapus dari TvFavorite"+listTv.getName(),Toast.LENGTH_SHORT).show();
        }else {
            button.setBackgroundResource(R.drawable.ic_favorite);
            tvFavoriteHelper.addFavorite(listTv);
            Toast.makeText(this,"Tersimpan di TvFavorite"+listTv.getName(),Toast.LENGTH_SHORT).show();
        }
    }
}
