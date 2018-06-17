package com.example.sif.democontacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_dablela,tv_sync_now,tv_delete_ac,tv_show_list;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tv_dablela = (TextView) findViewById(R.id.tv_dablela);
        this.tv_delete_ac=findViewById(R.id.tv_delete_ac);
        this.tv_sync_now=findViewById(R.id.tv_sync_now);
        this.tv_show_list=findViewById(R.id.tv_show_list);

        tv_dablela.setOnClickListener(this);
        tv_delete_ac.setOnClickListener(this);
        tv_sync_now.setOnClickListener(this);
        tv_show_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dablela:
                Toast.makeText(MainActivity.this, "Banavla re babba", Toast.LENGTH_SHORT).show();
                AccountManagerHelper.createAccount(MainActivity.this, "jaykarmokar@cardinbox.com");
                break;
            case R.id.tv_delete_ac:
                Toast.makeText(this, "Udvun takla....!!", Toast.LENGTH_SHORT).show();
                AccountManagerHelper.deleteAllAccounts(MainActivity.this);
                break;
            case R.id.tv_sync_now:
                Toast.makeText(this, "Sync Now..!!", Toast.LENGTH_SHORT).show();
                AccountManagerHelper.syncImmediately(MainActivity.this);
                break;
            case R.id.tv_show_list:
                Intent intent=new Intent(MainActivity.this,MyContactsList.class);
                startActivity(intent);
                Toast.makeText(this, "Dakhavto re baba..!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
