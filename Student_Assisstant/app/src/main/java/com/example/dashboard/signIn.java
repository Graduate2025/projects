package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signIn extends AppCompatActivity {
    Button sign_in,sign_up;
    Context context;
    EditText email_in_sign_in,password_in_sign_in;
    CheckBox stay_signed;
    TextView forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        context = getApplicationContext();

        forgot_password = findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),forgotPassword.class));
            }
        });

        try {
            if (new lastSignedDB(context).isStaySigned()) {
                login(new lastSignedDB(context).lastSigned());
            }
        }catch (Exception e){

        }

        stay_signed = findViewById(R.id.stay_signed);


        sign_in = findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_in_sign_in = findViewById(R.id.email_in_sign_in);
                password_in_sign_in = findViewById(R.id.password_in_sign_in);

                String email = email_in_sign_in.getText().toString();
                String pass = password_in_sign_in.getText().toString();

                try {

                    if (new accountsDB(context).checkIfPasswordIsRight(email, pass)) {
                        new lastSignedDB(context).userSigned(email, stay_signed.isChecked());
                        login(email);
                    } else {
                        Toast.makeText(context, "Wrong email address or password", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(context, "User does not exist", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });
    }

    public void login(String email) {
        Intent i = new Intent(context,MainActivity.class);
        i.putExtra("name",new accountsDB(context).getName(email));
        i.putExtra("email", email);
        finish();
        startActivity(i);
    }

    public void goToSignUp(){
        Intent i = new Intent(context,signUp.class);
        startActivity(i);
    }
}

