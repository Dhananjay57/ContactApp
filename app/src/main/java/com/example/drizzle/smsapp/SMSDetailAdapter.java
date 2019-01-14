package com.example.drizzle.smsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SMSDetailAdapter extends RecyclerView.Adapter<SMSDetailAdapter.ViewHolder>{
private Context mcontext;
private List<String> smsDetailsList;

public SMSDetailAdapter(Context mcontext, List<String> smsDetailsList){
        this.mcontext=mcontext;
        this.smsDetailsList=smsDetailsList;
        }


    @NonNull
    @Override
    public SMSDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.item_sms_details, viewGroup, false);
        return new SMSDetailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SMSDetailAdapter.ViewHolder viewHolder, int i) {
    viewHolder.tvMobileNumber.setText(smsDetailsList.get(i).toString());


    }

    @Override
    public int getItemCount() {
        return smsDetailsList.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvMobileNumber;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMobileNumber = itemView.findViewById(R.id.tv_mobileNumber);
    }
}
}
