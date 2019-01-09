package com.example.sifan.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonBMI_Clicked(View v) {
        EditText editTextHeight = findViewById(R.id.userHeight);
        EditText editTextWeight = findViewById(R.id.userWeight);
        TextView textViewResult = findViewById(R.id.userBMI);

        double userHeight = Double.parseDouble(editTextHeight.getText().toString());
        double userWeight = Double.parseDouble(editTextWeight.getText().toString());
        double userBMI = userWeight/(userHeight*userHeight);

        textViewResult.setText(Double.toString(userBMI));
    }
}
