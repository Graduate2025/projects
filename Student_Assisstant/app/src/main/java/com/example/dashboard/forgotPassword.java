package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgotPassword extends AppCompatActivity {

    Context context;
    Button confirm;
    EditText email_in_forgot,password_in_forgot,password2_in_forgot;
    Boolean valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        context = getApplicationContext();

        email_in_forgot = findViewById(R.id.email_in_forgot);
        email_in_forgot.setText(MainActivity.email);
        password_in_forgot = findViewById(R.id.password_in_forgot);
        password2_in_forgot = findViewById(R.id.password2_in_forgot);

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid = true;
                String email = email_in_forgot.getText().toString();
                String pass1 = password_in_forgot.getText().toString();
                String pass2 = password2_in_forgot.getText().toString();

                if(!new accountsDB(getApplicationContext()).getName(email).equals(null + " " + null)) {

                    if (!validEmail(email)) {
                        valid = false;
                        Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show();
                    }

                    if (!validPassword(pass1) | !validPassword(pass2)) {
                        valid = false;
                        Toast.makeText(context, "password should be at least 8 characters", Toast.LENGTH_LONG).show();
                    }


                    if (pass1.equals(pass2) & valid) {
                        new accountsDB(context).updatePassword(email, pass1);
                        finish();
                    } else if (!pass1.equals(pass2)) {
                        Toast.makeText(context, "passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context, "Email address doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Boolean validPassword(String pass){
        if(pass.length()>=8){
            return true;
        }else{
            return false;
        }
    }

    public Boolean validEmail(String email){
        Pattern p = Pattern.compile("^[a-z0-9]+@[a-z]+");
        Matcher m = p.matcher(email);
        return m.find();
    }
}