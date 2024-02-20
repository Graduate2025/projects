package com.example.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

    TextView user_name;
    ImageButton log_out,about_us;
    Button edit_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        user_name = findViewById(R.id.user_name);
        user_name.setText(MainActivity.name);

        log_out = findViewById(R.id.log_out);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new lastSignedDB(getApplicationContext()).logOut();
                logOut();
            }
        });

        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AboutUs.class));
            }
        });

        edit_profile = findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),editProfile.class));
            }
        });
    }

    public void logOut(){
        MainActivity.theMain.finish();
        MainActivity.name = null;
        MainActivity.email = null;
        finish();
        startActivity(new Intent(getApplicationContext(),signIn.class));
    }
    public void onStart() {
        super.onStart();
        user_name.setText(MainActivity.name);

    }
}