package com.example.barbersgold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView Registrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registrate = (TextView) findViewById(R.id.textRegistrate);

        Registrate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                     Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                     startActivity(intent);

                }


        });

    }
}