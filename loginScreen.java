package com.example.noiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class loginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final DataBaseHelper mydb = new DataBaseHelper(this);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if(!uname.isEmpty() && !pass.isEmpty()){
                    if(mydb.login(uname, pass)){
                        Toast.makeText(loginScreen.this, "Logged In ",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(loginScreen.this , showOptions.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(loginScreen.this, "Invalid Login",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(loginScreen.this, "Fields are Empty",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}