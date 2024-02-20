package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editProfile extends AppCompatActivity {

    EditText first_name,last_name;
    String name,first,last;
    Button save;
    TextView forgot_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = MainActivity.name;
        first = name.substring(0,name.indexOf(' '));
        last = name.substring(name.indexOf(' ')+1);

        first_name = findViewById(R.id.first_name_edit);
        first_name.setText(first);

        last_name = findViewById(R.id.last_name_edit);
        last_name.setText(last);

        save = findViewById(R.id.save_changes);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new accountsDB(getApplicationContext()).updateName(MainActivity.email,first_name.getText().toString(),last_name.getText().toString());
                MainActivity.name = first_name.getText().toString() + " " + last_name.getText().toString();
                Toast.makeText(editProfile.this,first_name.getText().toString() + " " + last_name.getText().toString() , Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        forgot_pass = findViewById(R.id.forgot_password_edit);
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),forgotPassword.class));
            }
        });
    }
}