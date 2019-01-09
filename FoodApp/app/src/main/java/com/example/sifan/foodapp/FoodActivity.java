package com.example.sifan.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    public static final String EXTRA_FOODNUMBER = "foodno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodNumber = (Integer) getIntent().getExtras().get(EXTRA_FOODNUMBER);
        Food food = Food.foods[foodNumber];

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        ImageView image = findViewById(R.id.image);

        name.setText(food.getName());
        description.setText(food.getDescription());
        image.setImageResource(food.getImageID());
    }
}
