package com.example.maycatalogmovie.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maycatalogmovie.R;
import com.example.maycatalogmovie.fragment.ListUpcoming;

public class UpcomingDetail extends AppCompatActivity {
    public static final String EXTRA_UPCOMING = "extra_upcoming";
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ListUpcoming listUpcoming;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_detail);

        TextView Title = findViewById(R.id.title_in_detail_upcoming);
        TextView Language = findViewById(R.id.language_in_detail_upcoming);
        TextView Overview = findViewById(R.id.overview_in_detail_upcoming);
        TextView Release_date = findViewById(R.id.release_date_in_detail_upcoming);
        ImageView Poster_path = findViewById(R.id.poster_path_in_detail_upcoming);
        Button Button_back = findViewById(R.id.button_back_in_detail_upcoming);

        listUpcoming = getIntent().getParcelableExtra(EXTRA_UPCOMING);
        assert listUpcoming !=null;

        String Title_upcoming = listUpcoming.getTitle();
        Title.setText(Title_upcoming);

        String Language_upcoming = listUpcoming.getOriginal_language();
        Language.setText(Language_upcoming);

        String Overview_upcoming = listUpcoming.getOverview();
        Overview.setText(Overview_upcoming);

        String Release_date_upcoming = listUpcoming.getRelease_date();
        Release_date.setText(Release_date_upcoming);

        String Poster_path_upcoming = listUpcoming.getBackdrop_path();
        Glide.with(this).load(IMAGE_BASE_URL+Poster_path_upcoming)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(Poster_path);
        Button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
