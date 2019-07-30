package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Changefloorplan extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changefloorplan);
        for(int i=1;i<21;i++)
        {
            final int j=i;
            btn=(Button) findViewById(getResources().getIdentifier("flp"+i,"id",this.getPackageName()));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changefloorplan(j);
                }
            });
        }


    }
    public void changefloorplan(int i) {
        Intent intent = new Intent(this, changefp.class);
        String key= new Integer(i).toString();
        intent.putExtra("floorplanid",key);
        startActivity(intent);
    }
}
