package com.riats.bettertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Locale;

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
        Button search = findViewById(R.id.search);
        TextView textView = findViewById(R.id.confirmNum);
        EditText searchBar = findViewById(R.id.searchBar);
        ListView listView = findViewById(R.id.listView);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("Bruh");
                String uh = (String) parent.getItemAtPosition(position);
                int addingStuff = Integer.valueOf(calDict.get(uh).trim());
                dailyCalories += addingStuff;
                textView.setText(String.valueOf(dailyCalories));
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(searchUpdate(foodNames, searchBar.getText().toString().toLowerCase(Locale.ROOT)));
            }
        });


    }
    public void switchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Daily Calories", dailyCalories);
        startActivity(intent);
    }
    public ArrayAdapter<String> searchUpdate(String[] foodNames, String desired){
        String[] searchNames = new String[foodNames.length];

        int x = 0;
        for (String food : foodNames){
            if (food.toLowerCase(Locale.ROOT).contains(desired)){
                searchNames[x] = food;
                x += 1;
            }
        }
        ArrayAdapter<String> arr;
        if(searchNames[0] != null){
            arr = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, searchNames);
        }
        else{
            arr = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, foodNames);
        }

        return(arr);
    }
}