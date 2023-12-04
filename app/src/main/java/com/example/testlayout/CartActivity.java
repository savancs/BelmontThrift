package com.example.testlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CartActivity extends AppCompatActivity {

    ImageView homeIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        homeIm =findViewById(R.id.chomeIV);
        homeIm.setOnClickListener(homeListener);
    }

    private final View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}