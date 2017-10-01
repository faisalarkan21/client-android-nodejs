package com.example.arkan.bootcampmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class ValidasiTiket extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView nameSideBar, emailSideBar, name, email, hp, harga_tiket, pilihan_bank;
    Button btn_validasi;
    SessionManager session;
    String nm_pembeli, email_pembeli, id_pembeli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_tiket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        session = new SessionManager(this);
        nm_pembeli =  session.getNm_pembeli();
        email_pembeli =  session.getEmail_pembeli();

        nameSideBar = (TextView)header.findViewById(R.id.txt_nm_pembeli);
        emailSideBar = (TextView)header.findViewById(R.id.txt_email_pembeli);
        nameSideBar.setText(nm_pembeli);
        emailSideBar.setText(email_pembeli);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        session = new SessionManager(this);


        name = (TextView) findViewById(R.id.txt_nama);
        email = (TextView) findViewById(R.id.txt_email);
        hp = (TextView) findViewById(R.id.txt_hp);
        harga_tiket = (TextView) findViewById(R.id.txt_harga);
        pilihan_bank = (TextView) findViewById(R.id.txt_pilihan_bank);

        String url = "http://192.168.56.1:3000/data-user-validasi/" + session.getId_pembeli();

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObjectData = new JSONObject(response);
                            JSONObject result = jsonObjectData.getJSONObject("result");

                            JSONObject dataPembeli = result.getJSONObject("dataPembeli");
                            JSONObject detailPembeli = result.getJSONObject("detailPembeli");
                            boolean statusValidasi = Boolean.parseBoolean(result.getString("statusValidasi"));

                            btn_validasi.setEnabled(!statusValidasi);

                            name.setText(dataPembeli.getString("nm_pembeli"));
                            email.setText(dataPembeli.getString("email_pembeli"));
                            hp.setText(dataPembeli.getString("hp_pembeli"));

                            harga_tiket.setText(detailPembeli.getString("harga_tiket"));
                            pilihan_bank.setText(detailPembeli.getString("pilihan_bank"));



                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ValidasiTiket.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        btn_validasi = (Button) findViewById(R.id.btn_validasi);
        btn_validasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(ValidasiTiket.this);
                //this is the url where you want to send the request
                //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
                String url = "http://192.168.56.1:3000/data-user-validasi/" + session.getId_pembeli();

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response string.
                                getCallback(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ValidasiTiket.this, error.toString(),
                                Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.clear();
                        params.put("pilihan_bank", pilihan_bank.getText().toString());
                        return params;
                    }
                };

                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(stringRequest);

            }
        });


    }

    public void getCallback(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean status = Boolean.parseBoolean(jsonObject.getString("success"));

            String result = status ? "Tiket Terkirim" : "Tiket Gagal Terkirim!";
            Toast.makeText(ValidasiTiket.this, result, Toast.LENGTH_SHORT).show();

            finish();
            startActivity(getIntent());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pembeli_data) {

            Intent dataPembeli = new Intent(ValidasiTiket.this, MainActivity.class);


            // Staring MainActivity
            startActivity(dataPembeli);
            // Handle the camera action
        } else if (id == R.id.nav_pembeli_validasi) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}




