package com.riats.bettertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class FoodSelector extends AppCompatActivity {
    int dailyCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            dailyCalories = extras.getInt("Daily Calories");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selector);
        Button goBack = findViewById(R.id.goBack);
        ListView listView = findViewById(R.id.listView);
        TextView textView = findViewById(R.id.confirmNum);
        textView.setText(String.valueOf(dailyCalories));

        String string = "Test";
        try {
            InputStream inputStream = getAssets().open("nutrition.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
            string = string.replace("\"", "");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Hashtable<String, String> calDict = new Hashtable<String, String>();
        String[] foodLines = string.split("\n");
        String[] foodNames = new String[foodLines.length];

        int x = 0;
        for (String line : foodLines){
            int index = 0;
            char check = ',';
            char replace = ']';
            for (int i = 0; i < line.length(); i++){
                if(line.charAt(i) == check) {
                    index = Math.max(index, i);
                }
            }
            StringBuilder bruh = new StringBuilder(line);
            bruh.setCharAt(index, replace);
            line = bruh.toString();
            System.out.println(bruh);
            String[] sep = line.split("]");
            calDict.put(sep[0],sep[1]);
            foodNames[x] = sep[0];
            x += 1;
        }

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, foodNames);
        listView.setAdapter(arr);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity();
            }
        });

    }
    public void switchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Daily Calories", dailyCalories);
        startActivity(intent);
    }
}