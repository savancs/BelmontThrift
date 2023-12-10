package com.example.testlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ImageView homeIm;

    private DatabaseReference ref;
    private FirebaseDatabase database;
    private List<CartItem> CartItemList;

    private ArrayList<CartItem> cartList;
    //private adapter adapter;

    ArrayAdapter<CartItem> adapter;
    ListView cartLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        homeIm = findViewById(R.id.chomeIV);
        homeIm.setOnClickListener(homeListener);
        cartLV = (ListView) findViewById(R.id.cartLV);
        //cartList = new ArrayList<>();

        //new stuff just added
        CartItemList = new ArrayList<>();
        adapter = new adapter(this,CartItemList);
        cartLV.setAdapter(adapter);

        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //cartLV.setAdapter(adapter);


         database = FirebaseDatabase.getInstance();
        //ref = database.getReference("cart");
        //ref = database.getReference("cart");
        //database = FirebaseDatabase.getInstance();

         ref = FirebaseDatabase.getInstance().getReference().child("cart");

        FirebaseListOptions<CartItem> options = new FirebaseListOptions.Builder<CartItem>()
                .setLayout(android.R.layout.simple_list_item_1)
                .setQuery(ref, CartItem.class)
                .build();
//        cartLV.setAdapter(new FirebaseListAdapter<CartItem>(options) {
//
//            @Override
//            protected void populateView(@NonNull View v, @NonNull CartItem model, int position) {
//                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getItemName());
//            }
//        });








        ref.orderByChild("cart").addListenerForSingleValueEvent(new ValueEventListener() {


                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        CartItemList.clear();
                                                                        for (DataSnapshot snap : snapshot.getChildren()) {
                                                                            CartItem cartItem = snap.getValue(CartItem.class);
                                                                            CartItemList.add(cartItem);
                                                                        }
                                                                        adapter.notifyDataSetChanged();
                                                                    }


                                                                    //
//                                                                              Iterable<DataSnapshot> data = snapshot.getChildren();
//                                                                              int lastElement = (int) snapshot.getChildrenCount();
//                                                                             Iterator<DataSnapshot> it = data.iterator();
//                                                                              for (int i = 0; i < lastElement - 1; i++) {
//                                                                                  String nextValue = (String) it.next().getValue();
//                                                                                  // retrieveListFromFB.add(nextValue);
//                                                                              }
//                                                                             int random = rand.nextInt(retrieveListFromFB.size());
//                                                                             String word = retrieveListFromFB.get(random);
                                                                              // Toast.makeText(MainActivity.this, "The word is: " + word, Toast.LENGTH_SHORT).show();
                                                                    // }

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

    public void populateLV() {
        ref = database.getReference("cart");
        cartList = new ArrayList<CartItem>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CartItemList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    CartItem cartItem = snap.getValue(CartItem.class);
                    CartItemList.add(cartItem);
                }
                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

