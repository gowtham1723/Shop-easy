package com.example.supermaket_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Itemslist extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemslist);
        lv=(ListView) findViewById(R.id.listview);
        Query query= FirebaseDatabase.getInstance().getReference().child("Items");
        FirebaseListOptions<Items> options=new FirebaseListOptions.Builder<Items>()
                .setLayout(R.layout.items)
                .setQuery(query,Items.class)
                .setLifecycleOwner(Itemslist.this)
                .build();
        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Itemname=v.findViewById(R.id.Itemname);
                ImageView imageView=v.findViewById(R.id.image);
                Items item=(Items) model;
                Itemname.setText(item.getItemname().toString()+" : Rs."+item.getPrice().toString());
                Picasso.with(Itemslist.this).load(item.getImage().toString()).into(imageView);

            }
        };
        lv.setAdapter(adapter);
    }

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
