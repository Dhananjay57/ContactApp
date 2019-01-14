package com.example.drizzle.smsapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ContactDetailsActivity  extends AppCompatActivity{
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    TextView tvName, tvMobileNumber;
    String name, mobileNumber, message, OTP;
    Button btnSendSMS;
    SharedPreferences sharedPreferences;
    private boolean sentToSettings = false;
    private static final int SMS_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    List<String> smsDetailsList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        tvName = findViewById(R.id.tv_contact_name);
        tvMobileNumber = findViewById(R.id.tv_mobileNumber);
        btnSendSMS = findViewById(R.id.btnSendSMS);
        name =  getIntent().getStringExtra("firstName");
        mobileNumber= getIntent().getStringExtra("mobileNumber");
        tvName.setText(name);
        tvMobileNumber.setText(mobileNumber);

        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        OTP = String.format("%06d", number);
        message = "Hi your OTP is: "+OTP;

        btnSendSMS.setOnClickListener(v -> {
            SendMessage(mobileNumber, message);

    });

if (ActivityCompat.checkSelfPermission(ContactDetailsActivity.this,
        Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ContactDetailsActivity.this,
                Manifest.permission.SEND_SMS)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ContactDetailsActivity.this);
            builder.setTitle("Need SMS Permission");
            builder.setMessage("This app needs SMS permission to send Messages.");
            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    ActivityCompat.requestPermissions(ContactDetailsActivity.this,
                            new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CONSTANT);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        } else if (sharedPreferences.getBoolean(Manifest.permission.SEND_SMS, false)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ContactDetailsActivity.this);
            builder.setTitle("Need SMS Permission");
            builder.setMessage("This app needs SMS permission to send Messages.");
            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    sentToSettings = true;
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                    Toast.makeText(getBaseContext(),
                            "Go to Permissions to Grant SMS permissions", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        } else {
            ActivityCompat.requestPermissions(ContactDetailsActivity.this,
                    new String[]{Manifest.permission.SEND_SMS}
                    , SMS_PERMISSION_CONSTANT);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Manifest.permission.SEND_SMS, true);
        editor.commit();

    }
}

    public void SendMessage(String strMobileNo, String strMessage) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(strMobileNo, null, strMessage, null, null);

            Date currentTime = Calendar.getInstance().getTime();
//TODO Need to save the sms sent time, name of contact person and number
            Toast.makeText(getApplicationContext(), "Your Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

}
