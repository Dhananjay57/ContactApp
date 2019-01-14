package com.example.drizzle.smsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private Context mcontext;
    private List<Contact> contactList;
    private ContactListAdapter.DataClickListener dataClickListener;

    public ContactListAdapter(Context mcontext, List<Contact> contactList, DataClickListener dataClickListener){
        this.mcontext = mcontext;
        this.contactList = contactList;
        this.dataClickListener = dataClickListener;
    }

    public interface DataClickListener {
        void onViewDataClicked(View view, int position, Contact contact);

    }
    @NonNull
    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.item_contact_list, viewGroup, false);
        return new ContactListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactListAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tvContactName.setText(contactList.get(i).getFirstName() +" "+
                contactList.get(i).getLastName());
        viewHolder.itemView.setOnClickListener(v -> {
            if(dataClickListener!= null){
                int adapterPosition = viewHolder.getAdapterPosition();
                dataClickListener.onViewDataClicked(v, adapterPosition, contactList.get(adapterPosition));
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvContactName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactName = itemView.findViewById(R.id.tv_contact_name);
        }
    }
}
