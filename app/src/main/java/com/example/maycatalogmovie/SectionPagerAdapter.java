package com.example.maycatalogmovie;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.maycatalogmovie.favorite.MovieFavorite;
import com.example.maycatalogmovie.favorite.TvFavorite;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private final Context Dummy_context;

    public SectionPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        Dummy_context = context;
    }

    @StringRes
    private final int[] Tab_Title = new int[]{
            R.string.Movie,
            R.string.TVshow
    };


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MovieFavorite();
                break;
            case 1:
                fragment = new TvFavorite();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return Dummy_context.getResources().getString(Tab_Title[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
