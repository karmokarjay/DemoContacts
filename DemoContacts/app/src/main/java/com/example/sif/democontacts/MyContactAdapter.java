package com.example.sif.democontacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyContactAdapter extends RecyclerView.Adapter<MyContactAdapter.ContactsViewHolder> {

    Context context;
    ArrayList<String> mIDS = new ArrayList<>();
    ArrayList<String> contactList = new ArrayList<>();

    MyContactAdapter(Context context, ArrayList<String> mIDS, ArrayList<String> contactList) {
        this.context = context;
        this.mIDS = mIDS;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_contact_item, parent);
        return new ContactsViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_contact_id, tv_contact_number;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            tv_contact_id = itemView.findViewById(R.id.contact_id);
            tv_contact_number = itemView.findViewById(R.id.contact_no);
        }
    }
}
