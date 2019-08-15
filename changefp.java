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

public class changefp extends AppCompatActivity {
    DatabaseReference ref;
    DataSnapshot dataSnapshot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changefp);
        Intent secintent=getIntent();
        String msg="Floorplan ID: "+secintent.getStringExtra("floorplanid");
        TextView a=(TextView) findViewById(R.id.flptext);
        a.setText(msg);
        final String key=secintent.getStringExtra("floorplanid");
        final EditText m=(EditText) findViewById(R.id.locid);
        Button button1 = (Button) findViewById(R.id.flpchangebtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnchange(v,m);
            }
        });
        ref=FirebaseDatabase.getInstance().getReference().child("Floorplan").child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String loc = dataSnapshot.child("Location").getValue(String.class);
                m.setText(loc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void btnchange(View view,final EditText m)
    {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("Location").setValue(m.getText().toString());
                Toast.makeText(changefp.this,"Location changed successfully",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
        Intent my=new Intent(this,Changefloorplan.class);
        startActivity(my);
    }

}
