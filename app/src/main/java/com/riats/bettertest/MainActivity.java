package com.riats.bettertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    int dailyCalories = 10;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            dailyCalories = extras.getInt("Daily Calories");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.addButton);
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(dailyCalories));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity();
            }
        });

    }
    public void switchActivity(){
        Intent intent = new Intent(this, FoodSelector.class);
        intent.putExtra("Daily Calories", dailyCalories);
        startActivity(intent);
    }
}