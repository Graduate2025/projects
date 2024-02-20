package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

public class signUp extends AppCompatActivity {

    Button sign_up2;
    Context context;
    EditText email_in_sign_up, password_in_sign_up, password2_in_sign_up, first_name_in, last_name_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = getApplicationContext();

        sign_up2 = findViewById(R.id.sign_up2);
        sign_up2.setOnClickListener(view -> {
            email_in_sign_up = findViewById(R.id.email_in_sign_up);
            password_in_sign_up = findViewById(R.id.password_in_sign_up);
            password2_in_sign_up = findViewById(R.id.password2_in_sign_up);
            first_name_in = findViewById(R.id.first_name_in);
            last_name_in = findViewById(R.id.last_name_in);

            String email = email_in_sign_up.getText().toString();
            String pass1 = password_in_sign_up.getText().toString();
            String pass2 = password2_in_sign_up.getText().toString();
            String first = first_name_in.getText().toString();
            String last = last_name_in.getText().toString();
            boolean valid = true;

            if(!validEmail(email)){
                valid = false;
                Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show();
            }

            if(!validPassword(pass1) | !validPassword(pass2)){
                valid = false;
                Toast.makeText(context, "password should be at least 8 characters", Toast.LENGTH_LONG).show();
            }


            if(pass1.equals(pass2) & valid) {
                makeNewUser(email,pass1,first,last);
            }else if(!pass1.equals(pass2)){
                Toast.makeText(context, "passwords do not match", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void makeNewUser(String email,String password, String first, String last){
        if(new accountsDB(context).getName(email).equals(null + " " + null)) {
            new accountsDB(context).createNewUser(email, password, first, last);
            finish();
        }else{
            Toast.makeText(context, "User already exist", Toast.LENGTH_LONG).show();
        }
    }

    public Boolean validEmail(String email){
        Pattern p = Pattern.compile("^[a-z0-9]+@[a-z]+");
        Matcher m = p.matcher(email);
        return m.find();
    }

    public Boolean validPassword(String pass){
        return pass.length() >= 8;
    }
}