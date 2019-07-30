package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class changeprice extends AppCompatActivity {
    DatabaseReference ref;
    EditText m;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprice);

        Intent secintent=getIntent();
        String msg="Item Selected: "+secintent.getStringExtra("ItemSelected");
        String p=secintent.getStringExtra("price");
        TextView mytext=(TextView) findViewById(R.id.textView3);

        m=(EditText) findViewById(R.id.editprice);
        m.setText(p);
        mytext.setText(msg);
        String key=secintent.getStringExtra("key");

        ref= FirebaseDatabase.getInstance().getReference().child("Items").child(key);
        button1 = (Button) findViewById(R.id.btnchange);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnchange(v);
            }
        });
    }
    private void btnchange(View view)
    {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("Price").setValue(m.getText().toString());
                Toast.makeText(changeprice.this,"Price changed successfully",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
        Intent my=new Intent(this,adminitemslist.class);
        startActivity(my);
    }
}
