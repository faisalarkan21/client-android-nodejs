package com.example.arkan.bootcampmobile;

/**
 * Created by arkan on 9/22/17.
 */

import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SessionManager {
    // Shared Preferences
    private SharedPreferences prefs;

    String nm_pembeli, email_pembeli, id_pembeli;

    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setSession(String nm_pembeli, String email_pembeli, String id_pembeli) {
        prefs.edit().putString("nm_pembeli", nm_pembeli).apply();
        prefs.edit().putString("email_pembeli", email_pembeli).apply();
        prefs.edit().putString("id_pembeli", id_pembeli).apply();



    }

    public String getNm_pembeli() {
        String nm_pembeli = prefs.getString("nm_pembeli","");
        return nm_pembeli;
    }

    public String getEmail_pembeli() {
        String email_pembeli = prefs.getString("email_pembeli","");
        return email_pembeli;
    }

    public String getId_pembeli() {
        String id_pembeli = prefs.getString("id_pembeli","");
        return id_pembeli;
    }

}