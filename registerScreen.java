package com.example.noiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        final DataBaseHelper mydb = new DataBaseHelper(this);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        Button register = findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if(!uname.isEmpty() && !pass.isEmpty()){
                    if(mydb.insertData(uname,pass)){
                        Toast.makeText(registerScreen.this , "Registered",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(registerScreen.this , showOptions.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(registerScreen.this , "Some Error Occured",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(registerScreen.this , "Fields are empty",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}