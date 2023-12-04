package com.example.testlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ItemActivity extends AppCompatActivity {

    ImageView homeIm;
    ImageView cartIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        homeIm = findViewById(R.id.ihomeIV);
        cartIm =findViewById(R.id.icartIV);

        homeIm.setOnClickListener(homeListener);
        cartIm.setOnClickListener(cartListener);
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
}