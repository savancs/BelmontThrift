package com.example.testlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class ItemActivity extends AppCompatActivity {

    ImageView homeIm;
    ImageView cartIm;
    ImageView likeIm;
    ImageView redLikeIm;
    TextView  numLikes;

    Button addToCart;
    DatabaseReference ref;
    CartItem item;
    //FirebaseDatabase cartDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference ref = database.getReference("Cart");
        ref = database.getReference("Cart");
        //cartDB = FirebaseDatabase.getInstance();

        item = new CartItem("Blouse", 5);

        homeIm = findViewById(R.id.ihomeIV);
        cartIm =findViewById(R.id.icartIV);
        likeIm = findViewById(R.id.likeIV);
        numLikes = findViewById(R.id.numLikesTV);
        redLikeIm = findViewById(R.id.redLikeIV);

        homeIm.setOnClickListener(homeListener);
        cartIm.setOnClickListener(cartListener);
        likeIm.setOnClickListener(likeListener);
        addToCart = findViewById(R.id.addButton);
        addToCart.setOnClickListener(addListener);


    }

    private final View.OnClickListener cartListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ItemActivity.this, CartActivity.class);
            startActivity(intent);
        }
    };
    private final View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ItemActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
    private final View.OnClickListener likeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int number = Integer.parseInt(numLikes.getText().toString());
            //change image to red heart
            if (redLikeIm.getVisibility() == View.VISIBLE){
                redLikeIm.setVisibility(View.INVISIBLE);
                number -= 1;
                numLikes.setText(Integer.toString(number));
            } else {
                redLikeIm.setVisibility(View.VISIBLE);
                //update counter
                number += 1;
                numLikes.setText(Integer.toString(number));

            }

        }
    };
    private final View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           //ref.push().setValue(item.getItemName());
            ref.child(ref.push().getKey()).setValue(item);
            //ref.setValue(item);
            //int price = 5;
            //addToCart(item);
            Toast.makeText(ItemActivity.this, "Item successfully added to cart.", Toast.LENGTH_SHORT).show();
        }
    };

 /*   public void addToCart(CartItem clothing){
        ref.child(ref.push().getKey()).setValue(item);

    }*/
}