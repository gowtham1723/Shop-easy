package com.example.supermaket_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.view.View;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Itemselection extends AppCompatActivity {

    FirebaseListAdapter adapter;
    ArrayList<String> selectedItems,selectedItemskey;
    Intent next;
    ListView chl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        next=new Intent(this,shortestpath.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemselection);
        selectedItems=new ArrayList<String>();
        selectedItemskey=new ArrayList<String>();
    }

    public void onStart(){
        super.onStart();
        //create an instance of ListView
        chl=(ListView) findViewById(R.id.checkable_list);
        //set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Query query= FirebaseDatabase.getInstance().getReference().child("Items");
        FirebaseListOptions<Items> options=new FirebaseListOptions.Builder<Items>()
                .setLayout(R.layout.items2)
                .setQuery(query,Items.class)
                .setLifecycleOwner(Itemselection.this)
                .build();
        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Itemname=v.findViewById(R.id.Itemname2);
                Items item=(Items) model;
                Itemname.setText(item.getItemname().toString());

            }
        };
        chl.setAdapter(adapter);
        //set OnItemClickListener
        chl.setOnItemClickListener(listclick);
    }
    private AdapterView.OnItemClickListener listclick=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Items itemname=(Items) chl.getItemAtPosition(i);
            String key= new Integer(i+1).toString();
            if(selectedItemskey.contains(key))
                selectedItemskey.remove(key); //remove deselected item from the list of selected items
            else
                selectedItemskey.add(key);
            String selectedItem = ((TextView) view.findViewById(R.id.Itemname2)).getText().toString();
            if(selectedItems.contains(selectedItem))
                selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
            else
                selectedItems.add(selectedItem); //add selected item to the list of selected items
        }
    };

    public void showSelectedItems(View view){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
        next.putExtra("itemselectedsp",selectedItems);
        next.putExtra("key",selectedItemskey);
        startActivity(next);
    }

}

