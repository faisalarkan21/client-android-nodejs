package com.example.arkan.bootcampmobile;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    // Email, password edittext
    EditText txtUsername, txtPassword;

    TextView errorLogin;

    // login button
    Button btnLogin;

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Medium.ttf");

        TextView logoLogin = (TextView) findViewById(R.id.logoLogin);
        logoLogin.setTypeface(typeface);

        final TextView errorLogin = (TextView) findViewById(R.id.errorLogin);
        errorLogin.setTypeface(typeface);

        Button btnDaftar = (Button) findViewById(R.id.btn_daftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), DaftarActivity.class);
                view.getContext().startActivity(Intent);}
        });

        btnDaftar.setTypeface(typeface);

        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.input_email);
        txtPassword = (EditText) findViewById(R.id.input_password);


        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setTypeface(typeface);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                // Check if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    // For testing puspose username, password is checked with sample data
                    // username = test
                    // password = test
                    if(username.equals("test") && password.equals("test")){

                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
                        session.createLoginSession("Faisal Arkan", "faisalarkan21@gmail.com");



                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);


                    }else{
                        // username / password doesn't match
                        errorLogin.setText("Username atau password salah.");

                    }
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    errorLogin.setText("Masukan Username dan Password.");
                }

            }
        });





    }
}
