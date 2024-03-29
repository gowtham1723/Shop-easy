package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Adminactivity extends AppCompatActivity {
    private Button button;
    Button btn;
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminactivity);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        button = (Button) findViewById(R.id.changepricebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeprice();
            }
        });
        btn = (Button) findViewById(R.id.floorplanbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changefloorplan();
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(Adminactivity.this, adminlogin.class);
                startActivity(I);

            }
        });


    }
    public void changeprice() {
        Intent intent = new Intent(this, adminitemslist.class);
        startActivity(intent);
    }
    public void changefloorplan() {
        Intent intent = new Intent(this, Changefloorplan.class);
        startActivity(intent);
    }
}
