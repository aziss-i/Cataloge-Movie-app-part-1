package com.example.maycatalogmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {

    Button guestbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        guestbutton = findViewById(R.id.button_guest);
        guestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gohomescreen = new Intent(GetStarted.this,HomeScreen.class);
                startActivity(gohomescreen);
                finish();
            }
        });
    }

}
