package com.example.sif.democontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MyContactsList extends AppCompatActivity {

    RecyclerView contacts_list;
    MyContactAdapter contactAdapter;
    ArrayList<String> mIds=new ArrayList<>();
    ArrayList<String> contactList=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts_list);
        this.contacts_list=findViewById(R.id.contacts_list);

        layoutManager=new LinearLayoutManager(this);
        contactAdapter=new MyContactAdapter(this,mIds,contactList);
        contacts_list.setAdapter(contactAdapter);
    }
}
