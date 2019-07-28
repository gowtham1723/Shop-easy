package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.adminbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadminlogin();
            }
        });
        button1 = (Button) findViewById(R.id.customerbtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencustomeractivity();
            }
        });
    }
    public void openadminlogin() {
        Intent intent = new Intent(this, adminlogin.class);
        startActivity(intent);
    }
    public void opencustomeractivity() {
        Intent intent = new Intent(this, customeractivity.class);
        startActivity(intent);
    }
}
