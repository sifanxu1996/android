package com.example.sifan.foodapp;

public class Food {
    private String name;
    private String description;
    private int imageID;

    public static final Food[] foods = {
            new Food("Pizza", "Thin crust pizza with extra cheese.", R.drawable.pizza),
            new Food("Burger", "Beef patty between sesame buns.", R.drawable.burger),
            new Food("Sandwich", "Whole wheat BLT sammich.", R.drawable.sandwich),
    };

    public Food(String name, String description, int imageID) {
        this.name = name;
        this.description = description;
        this.imageID = imageID;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getImageID() {
        return this.imageID;
    }

    public String toString() {
        return this.name;
    }
}
