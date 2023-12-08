package com.example.testlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    ListView historyLv;

    ImageView cartIm, searchButton;
    EditText searchEt;
    ImageView itemIm1, itemIm2, itemIm3, itemIm4;

    ArrayList<String> historyList;
    ArrayAdapter<String> adapter;
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        historyList = new ArrayList<>();
        cartIm = findViewById(R.id.hcartIV);
        searchButton = findViewById(R.id.searchIV);
        searchEt = findViewById(R.id.searchET);
        itemIm1 = findViewById(R.id.fsIV1);
        itemIm2 = findViewById(R.id.fsIV2);
        itemIm3 = findViewById(R.id.fsIV3);
        itemIm4 = findViewById(R.id.fsIV4);
        historyLv = findViewById(R.id.historyLV);

        cartIm.setOnClickListener(cartListener);
        searchButton.setOnClickListener(searchListener);
        itemIm1.setOnClickListener(itemListener);
        itemIm2.setOnClickListener(itemListener);
        itemIm3.setOnClickListener(itemListener);
        itemIm4.setOnClickListener(itemListener);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        historyLv.setAdapter(adapter);

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
    private final View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String searchValue = searchEt.getText().toString();
            if(searchValue.isEmpty()){
                Toast.makeText(MainActivity.this, "No search results.", Toast.LENGTH_LONG).show();
            } else {

                historyList.add(searchValue);
                adapter.add(searchValue);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Search added to history.", Toast.LENGTH_LONG).show();
            }

        }
    };

}