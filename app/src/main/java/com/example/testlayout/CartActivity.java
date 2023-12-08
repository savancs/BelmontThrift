package com.example.testlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ImageView homeIm;
    private DatabaseReference mDatabase;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        homeIm =findViewById(R.id.chomeIV);
        homeIm.setOnClickListener(homeListener);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("cart");



        mDatabase.orderByChild("cart").addListenerForSingleValueEvent(new ValueEventListener() {

// I like apple piecc
            //pie
                                                                          @Override
                                                                          public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                                              Iterable<DataSnapshot> data = snapshot.getChildren();
//                                                                              int lastElement = (int) snapshot.getChildrenCount();
//                                                                              Iterator<DataSnapshot> it = data.iterator();
//                                                                              for (int i = 0; i < lastElement - 1; i++) {
//                                                                                  String nextValue = (String) it.next().getValue();
//                                                                                  // retrieveListFromFB.add(nextValue);
//                                                                              }
//                                                                              int random = rand.nextInt(retrieveListFromFB.size());
//                                                                              String word = retrieveListFromFB.get(random);
//                                                                              // Toast.makeText(MainActivity.this, "The word is: " + word, Toast.LENGTH_SHORT).show();
                                                                          }

                                                                          @Override
                                                                          public void onCancelled(@NonNull DatabaseError error) {

                                                                          }
                                                                      }
        );
    }

    private final View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}