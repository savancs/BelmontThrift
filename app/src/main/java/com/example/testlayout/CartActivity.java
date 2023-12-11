package com.example.testlayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView subTTV, taxTV, totalTV;
    ImageView homeIm;
    DatabaseReference ref;
    ListView cartLV;
    ArrayList<String> cartList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    CartItem item;
    Double price;
    double subtotal = 0.0;
    double tax = 0.0;
    double total = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        subTTV = findViewById(R.id.subtotalVal);
        taxTV = findViewById(R.id.taxVal);
        totalTV = findViewById(R.id.totalVal);
        homeIm = findViewById(R.id.chomeIV);
        homeIm.setOnClickListener(homeListener);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("Cart");

        cartLV = (ListView) findViewById(R.id.cartLV);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cartList);
        cartLV.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(CartItem.class).toString();

                cartList.add(value);

                item = snapshot.getValue(CartItem.class);
                price = item.getItemPrice();
                if(item!=null){
                    subtotal += price;
                }
                tax = subtotal * .07;
                total = subtotal + tax;
                subTTV.setText(String.valueOf(subtotal));
                taxTV.setText(String.valueOf(tax));
                totalTV.setText(String.valueOf(total));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private final View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    //implement total and subtotal


}

