package com.example.testlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    ImageView homeIm;
    ImageView cartIm;
    ImageView likeIm;
    ImageView redLikeIm;
    TextView  numLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        homeIm = findViewById(R.id.ihomeIV);
        cartIm =findViewById(R.id.icartIV);
        likeIm = findViewById(R.id.likeIV);
        numLikes = findViewById(R.id.numLikesTV);
        redLikeIm = findViewById(R.id.redLikeIV);

        homeIm.setOnClickListener(homeListener);
        cartIm.setOnClickListener(cartListener);
        likeIm.setOnClickListener(likeListener);
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
}