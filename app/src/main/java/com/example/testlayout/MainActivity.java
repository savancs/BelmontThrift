package com.example.testlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    ImageView cartIm;
    ImageView itemIm1, itemIm2, itemIm3, itemIm4;
    Random rand = new Random();

    private final List<String> retrieveListFromFB = new ArrayList<>();
    private final List<Character> input = new ArrayList<>();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartIm = findViewById(R.id.hcartIV);
        itemIm1 = findViewById(R.id.fsIV1);
        itemIm2 = findViewById(R.id.fsIV2);
        itemIm3 = findViewById(R.id.fsIV3);
        itemIm4 = findViewById(R.id.fsIV4);

        cartIm.setOnClickListener(cartListener);
        itemIm1.setOnClickListener(itemListener);
        itemIm2.setOnClickListener(itemListener);
        itemIm3.setOnClickListener(itemListener);
        itemIm4.setOnClickListener(itemListener);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("words");

        mDatabase.orderByChild("words").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                int lastElement = (int) snapshot.getChildrenCount();
                Iterator<DataSnapshot> it = data.iterator();
                for (int i = 0; i < lastElement - 1; i++) {
                    String nextValue = (String) it.next().getValue();
                    retrieveListFromFB.add(nextValue);
                }
                int random = rand.nextInt(retrieveListFromFB.size());
                String word = retrieveListFromFB.get(random);
                Toast.makeText(MainActivity.this, "The word is: " + word, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }
        );
}

            private final View.OnClickListener cartListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }
    };
    private final View.OnClickListener itemListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ItemActivity.class);
            startActivity(intent);
        }
    };
}