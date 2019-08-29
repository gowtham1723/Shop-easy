package com.example.supermaket_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class showshortestpath extends AppCompatActivity {
    ArrayList<Integer> locationid;
    floydwarshall fw;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showshortestpath);
        Intent secintent=getIntent();
        locationid=secintent.getIntegerArrayListExtra("array");
        String z="";
        String y=Integer.toString(locationid.size());
        int[] array=new int[locationid.size()];
        int j=0;
        for(Integer i:locationid)
        {
            array[j]=i;
            j++;
        }
        TextView s=(TextView) findViewById(R.id.s);
        floydwarshall a = new floydwarshall();
        int[][] dist=a.floydWarshall(a.graph);
        int[]x=a.multi(dist,array,locationid.size());
        for(int i=x.length-1;i>=0;i--)
        {
            x[i]=x[i]+96;
            char c=(char)x[i];
            if(z=="")
            {
                z=z+c;
            }
            else
            {
                z=z+" -> "+c;
            }

        }
        s.setText(z);

    }
}

