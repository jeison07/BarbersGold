package com.example.barbersgold.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.barbersgold.MenuPrincipal;
import com.example.barbersgold.Util.Util;
import com.example.barbersgold.activity.MainActivity;

public class splashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("preferences",Context.MODE_PRIVATE);

        Intent intentLogin =  new Intent(this, MainActivity.class);
        Intent intentMain = new Intent(this, MenuPrincipal.class);

        if(!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) && !TextUtils.isEmpty(Util.getUserPassPrefs(prefs))){
            startActivity(intentMain);
        }else{
            startActivity(intentLogin);
        }
        finish();



    }

}