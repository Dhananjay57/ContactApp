package com.example.drizzle.smsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class DetailsFragment extends Fragment {
    public static Fragment newInstance() {
        DetailsFragment detailsFragment = new DetailsFragment();
        return detailsFragment;
    }

    RecyclerView recyclerView;
    SMSDetailAdapter smsDetailAdapter;
    List<String> smsDetailsList = new ArrayList<String>();
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sms_details,container, false);
        recyclerView = view.findViewById(R.id.rv_sms_details);

        init();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void init() {
        //TODO smsDetailslist
        //(need to show name , number and sms sent time )
        smsDetailAdapter = new SMSDetailAdapter(getActivity(), smsDetailsList);
        recyclerView.setAdapter(smsDetailAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
