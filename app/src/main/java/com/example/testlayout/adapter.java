package com.example.testlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class adapter extends ArrayAdapter<CartItem> {
    private Context context;
    private List<CartItem> CartItemList;

    public adapter(Context context, List<CartItem> productList) {
        super(context, R.layout.activity_cart, productList);
        this.context = context;
        this.CartItemList = CartItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_cart, parent, false);
        }

        TextView productName = convertView.findViewById(R.id.itemTV);
        //TextView productPrice = convertView.findViewById(R.id.productPriceTextView);
       // ImageView productImage = convertView.findViewById(R.id.productImageView);

        CartItem product = CartItemList.get(position);

        productName.setText(product.getItemName());
       // productPrice.setText(String.valueOf(product.getItemPrice()));

        // Load the image using your preferred image loading library or method
        // Picasso, Glide, or setImageURI can be used

        return convertView;
    }
}
