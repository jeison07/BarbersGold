package com.example.barbersgold.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbersgold.MenuPrincipal;
import com.example.barbersgold.R;
import com.example.barbersgold.Util.Util;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    TextView Registrate;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button  button_login;
    private SwitchMaterial Switch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();

        prefs = getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        setCredentialsIfExist();

        Registrate = (TextView)findViewById(R.id.textRegistrate);

        Registrate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                     Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                     startActivity(intent);

                }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String email = editTextEmail.getText().toString();
                  String password = editTextPassword.getText().toString();
                  if(login(email,password)){
                      gotoMain();
                      saveOnPreferences(email,password);
                  }
              }
        });
    }
    private void bindUI(){
        editTextEmail = (EditText) findViewById(R.id.editextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        button_login = (Button) findViewById(R.id.button_login);
        Switch = (SwitchMaterial) findViewById(R.id.Switch);

    }
    private void setCredentialsIfExist(){
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            Switch.setChecked(true);
        }
    }
    private  boolean login(String email,String password){
        if(!isValidEmail(email)){
            Toast.makeText(this,"Correo incorrecto, intente nuevamente",Toast.LENGTH_LONG).show();
            return false;
        }else if(!isValidPassword(password)){
            Toast.makeText(this,"Contraseña incorrecta,Ingrese mas de 4 caracteres, Intente nuevamente",Toast.LENGTH_LONG).show();
            return false;
        } else{
            Toast.makeText(this, "La correo y contraseña son correctos", Toast.LENGTH_LONG).show();
            return true;
        }
    }
    private void saveOnPreferences(String email,String password){
        if(Switch.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email",email);
            editor.putString("password",password);
            editor.apply();
        }

    }
    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    private boolean isValidPassword(String password){
        return password.length() >4;
    }
    private void gotoMain(){
        Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}