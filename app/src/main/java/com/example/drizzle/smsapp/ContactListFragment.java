package com.example.drizzle.smsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public  class ContactListFragment extends Fragment implements ContactListAdapter.DataClickListener{
    private RecyclerView recyclerView;
    private ContactListAdapter contactListAdapter;
    private List<Contact> contactList = new ArrayList<>();

    public static Fragment newInstance() {
        return new ContactListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list,container, false);
        recyclerView = view.findViewById(R.id.rv_contact_list);
        String JSON_STRING="{\"contacts\":[{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"},{\"first_name\":\"XYZ\",\"last_name\":\"ABCD\",\"mobile\":\"+91 0000000000\"}]}";
        init(JSON_STRING);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void init(String _STRING) {
        try {

           if(_STRING!= null){
               JSONObject jsonObj = new JSONObject(_STRING);
               JSONArray contacts = jsonObj.getJSONArray("contacts");

               for (int i = 0; i < contacts.length(); i++) {
                   JSONObject c = contacts.getJSONObject(i);
                   String firstName = c.getString("first_name");
                   String lastName = c.getString("last_name");
                   String mobile = c.getString("mobile");
                   contactList.add(new Contact(firstName,lastName, mobile));
               }
               contactListAdapter = new ContactListAdapter(getActivity(), contactList, this);
               recyclerView.setAdapter(contactListAdapter);
           }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onViewDataClicked(View view, int position, Contact contactList) {
       if(getActivity()!= null){
           Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
           intent.putExtra("firstName", contactList.getFirstName());
           intent.putExtra("mobileNumber", contactList.getMobile());
           getActivity().startActivity(intent);
       }

    }
}
