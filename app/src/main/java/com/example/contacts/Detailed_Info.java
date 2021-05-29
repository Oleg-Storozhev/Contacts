package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detailed_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);

        // setting Online

        TextView online = findViewById(R.id.online);
        boolean setOnline = (boolean) getIntent().getExtras().get("online");
        if(setOnline){
            online.setText("online");
            online.setTextColor(Color.parseColor("#00A611"));
        }
        else{
            online.setText("offline");
            online.setTextColor(Color.parseColor("#E91E1E"));
        }

        // setting Full name

        TextView fullName = findViewById(R.id.fullname);
        String setFullName = getIntent().getExtras().get("fullName").toString();
        fullName.setText(setFullName);

        // setting email

        TextView email = findViewById(R.id.mail);
        String setEmail = getIntent().getExtras().get("email").toString();
        email.setText(setEmail);

        // setting gender

        TextView gender = findViewById(R.id.gender);
        String setGender = getIntent().getExtras().get("gender").toString();
        gender.setText(setGender);

        // setting avatar

        ImageView avatar = findViewById(R.id.detailView);
        int setAvatar = (int) getIntent().getExtras().get("image");
        avatar.setImageResource(setAvatar);
    }
}