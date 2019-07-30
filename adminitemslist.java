package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class adminitemslist extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminitemslist);
        myintent=new Intent(this,changeprice.class);
        lv=(ListView) findViewById(R.id.adminlistview);
        Query query= FirebaseDatabase.getInstance().getReference().child("Items");
        FirebaseListOptions<Items> options=new FirebaseListOptions.Builder<Items>()
                .setLayout(R.layout.items)
                .setQuery(query,Items.class)
                .setLifecycleOwner(adminitemslist.this)
                .build();
        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Itemname=v.findViewById(R.id.Itemname);

                Items item=(Items) model;
                Itemname.setText(item.getItemname().toString()+" : Rs."+item.getPrice().toString());


            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listclick);
    }
    private AdapterView.OnItemClickListener listclick=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Items itemname=(Items) lv.getItemAtPosition(i);
            String key= new Integer(i+1).toString();
            myintent.putExtra("ItemSelected",itemname.getItemname());
            myintent.putExtra("price",itemname.getPrice());
            myintent.putExtra("key",key);
            startActivity(myintent);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}