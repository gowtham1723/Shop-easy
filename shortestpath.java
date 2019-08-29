package com.example.supermaket_user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class shortestpath extends AppCompatActivity {
    ArrayList<String> selectedItems,locationlist;
    Intent find;
    ArrayList<Integer>locations;
    DatabaseReference ref,ref2;
    Button button1,button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortestpath);
        find=new Intent(this,showshortestpath.class);
        Intent secintent=getIntent();
        selectedItems=secintent.getStringArrayListExtra("itemselectedsp");
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        TextView textView=(TextView)findViewById(R.id.finale);
        textView.setText(selItems);
        selectedItems=secintent.getStringArrayListExtra("key");
        selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        button = (Button) findViewById(R.id.confirm);

        btnchange();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(find);
            }
        });


    }


    public void print(ArrayList<String> l)
    {
        String selItems="";
        for(String item:l){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        locations=new ArrayList<>();
        for(String item:l){
            locations.add(Integer.parseInt(item));
        }
        find.putExtra("array",locations);
    }
    private void btnchange()
    {
        for(String item:selectedItems){
            readdata(new Firebasecallback() {
                @Override
                public void onCallback(ArrayList<String> list) {
                    print(list);
                }
            },item);
        }

    }
    private void readdata(final Firebasecallback firebasecallback,String item)
    {
        locationlist=new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference().child("Items").child(item);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            String a;
            String out;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                a=dataSnapshot.child("Location").getValue().toString();
                char x=a.charAt(0);
                int ascii=(int)x;
                ascii=ascii-96;
                out=Integer.toString(ascii);
                locationlist.add(out);
                firebasecallback.onCallback(locationlist);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private interface Firebasecallback
    {
        void onCallback(ArrayList<String> list);
    }
}
