package com.example.maycatalogmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Pindah otomatis ke get started
                Intent gogetstarted = new Intent(MainActivity.this,GetStarted.class);
                startActivity(gogetstarted);
                finish();
            }
        },1500);

    }

}
