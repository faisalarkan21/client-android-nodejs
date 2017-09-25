package com.example.arkan.bootcampmobile;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                view.getContext().startActivity(Intent);
            }
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

                final String username = txtUsername.getText().toString();
                final String password = txtPassword.getText().toString();

                // Check if username, password is filled
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    // For testing puspose username, password is checked with sample data

                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    //this is the url where you want to send the request
                    //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
//                    String urlAPI = "http://192.168.56.1:3000/mobile-login";
                    String urlAPI = "http://192.168.1.100:3000/mobile-login";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlAPI,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the response string.
                                    getCallback(response);


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.toString(),
                                    Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                        //adding parameters to the request
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.clear();
                            params.put("email", username);
                            params.put("password", password);
                            return params;
                        }
                    };

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            0,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(stringRequest);


                } else {
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    errorLogin.setText("Masukan Username dan Password.");
                }

            }
        });
    }


    public void getCallback(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            String id_pembeli = jsonObject.getString("id_pembeli");
            String nm_pembeli = jsonObject.getString("nm_pembeli");
            String email_pembeli = jsonObject.getString("email_pembeli");
            boolean isValid = Boolean.parseBoolean(jsonObject.getString("isValid"));

//            Toast.makeText(LoginActivity.this, isValid, Toast.LENGTH_LONG).show();
            if (isValid) {
                // Creating user login session
                // For testing i am stroing name, email as follow
                // Use user real data
                session = new SessionManager(this);
                session.setSession(nm_pembeli, email_pembeli, id_pembeli);

                // Buat Intent baru
                Intent dashboard = new Intent(LoginActivity.this, MainActivity.class);


                // Staring MainActivity
                startActivity(dashboard);

            } else {
                // username / password doesn't match
                Toast.makeText(LoginActivity.this, "Password / Username Salah",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


    }


}
