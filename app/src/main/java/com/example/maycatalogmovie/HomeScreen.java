package com.example.maycatalogmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.maycatalogmovie.fragment.Favorite;
import com.example.maycatalogmovie.fragment.ListMovie;
import com.example.maycatalogmovie.fragment.ListMovieAdapter;
import com.example.maycatalogmovie.fragment.Movie;
import com.example.maycatalogmovie.fragment.Tv;
import com.example.maycatalogmovie.fragment.Upcoming;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout nav_drawer;
    private ActionBarDrawerToggle toggle;
    private ArrayList<ListMovie> listmovie = new ArrayList<>();
    private ListMovieAdapter listMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        nav_drawer = findViewById(R.id.nav_drawer);
        toggle = new ActionBarDrawerToggle(this,nav_drawer,R.string.open,R.string.close);
        nav_drawer.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new Movie());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        listMovieAdapter = new ListMovieAdapter(listmovie);

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.movie:
                fragment = new Movie();
                break;
            case R.id.tv:
                fragment = new Tv();
                break;
            case R.id.favorite:
                fragment = new Favorite();
                break;
            case R.id.upcoming:
                fragment = new Upcoming();
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }

        /*if (toggle.onOptionsItemSelected(item)){
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


}
