package com.example.supermaket_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class customeractivity extends AppCompatActivity {
    private Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customeractivity);
        button = (Button) findViewById(R.id.btnviewprice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openitemlist();
            }
        });
        button1=(Button) findViewById(R.id.viewlocation);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openitemslistforlocation();
            }
        });
    }
    public void openitemlist() {
        Intent intent = new Intent(this, Itemslist.class);
        startActivity(intent);
    }
    public void openitemslistforlocation() {
        Intent intent = new Intent(this, ItemslistLocation.class);
        startActivity(intent);
    }
}
