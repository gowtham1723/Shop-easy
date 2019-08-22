package com.example.supermaket_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class showlocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlocation);

        Intent secintent=getIntent();
        String msg="Item Selected: "+secintent.getStringExtra("ItemSelected");
        String p="Location of item: "+secintent.getStringExtra("location");
        TextView mytext=(TextView) findViewById(R.id.item);
        TextView m=(TextView) findViewById(R.id.location);
        m.setText(p);
        mytext.setText(msg);
    }
}
