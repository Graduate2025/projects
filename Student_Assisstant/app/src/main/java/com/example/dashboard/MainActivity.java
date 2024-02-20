package com.example.dashboard;
// 2022/12/15  9:55

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Intent GpaCalculator;
    Bundle bundle;
    public static String name;
    public static String email;
    TextView display_name;
    ImageView schedule,listbut,settings;
    Context context;
    public static Activity theMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        theMain = this;

        GpaCalculator = new Intent(this, Gpa_Calculator.class);
        bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");

        display_name = findViewById(R.id.display_name);
        display_name.setText(name);

        listbut=findViewById(R.id.listbut);
        listbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,list.class);
                startActivity(intent);
            }
        });

        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Settings.class));
            }
        });

        schedule = findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ScheduleJava.class);
                startActivity(intent);
            }
        });

    }



    public void GoToCalculator(View view){
        startActivity(GpaCalculator);
    }

    public void onStart(){
        super.onStart();
        display_name.setText(name);
    }

}