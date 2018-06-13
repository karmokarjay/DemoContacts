package com.example.sif.democontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    private TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        this.txt_login = (TextView) findViewById(R.id.txt_login);
    }
}
