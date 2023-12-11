package com.example.testlayout;

public class CartItem {
    private String itemName;
    private double itemPrice;

    public CartItem() {
        // Empty constructor for Firebase
    }

    public CartItem(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String toString(){
        return this.itemName + " --- $" + String.valueOf(itemPrice);
    }
}
