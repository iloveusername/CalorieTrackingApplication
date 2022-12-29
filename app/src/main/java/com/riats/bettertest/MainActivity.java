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
    int dailyCalories = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.addButton);
        TextView textView = findViewById(R.id.textView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity();
            }
        });

//        TextView testText = (TextView) findViewById(R.id.testText);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        Button button = (Button) findViewById(R.id.button);
//        SeekBar seekBar = findViewById(R.id.seekBar);
//
//        testText.setText("Testing");
//
//        String string = "Test";
//        try {
//            InputStream inputStream = getAssets().open("nutrition.txt");
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            string = new String(buffer);
//            //string = string.replace("\",","]");
//            string = string.replace("\"", "");
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        testText.setText(string);
//
//        Hashtable<String, String> calDict = new Hashtable<String, String>();
//        String[] foodLines = string.split("\n");
//        String[] foodNames = new String[foodLines.length];
//
//        int x = 0;
//        for (String line : foodLines){
//            int index = 0;
//            char check = ',';
//            char replace = ']';
//            for (int i = 0; i < line.length(); i++){
//                if(line.charAt(i) == check) {
//                    index = Math.max(index, i);
//                }
//            }
//            StringBuilder bruh = new StringBuilder(line);
//            bruh.setCharAt(index, replace);
//            line = bruh.toString();
//            System.out.println(bruh);
//            String[] sep = line.split("]");
//            calDict.put(sep[0],sep[1]);
//            foodNames[x] = sep[0];
//            x += 1;
//        }
//        testText.setText(String.valueOf(foodNames.length));
//
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v){
//                int layer = seekBar.getProgress();
//                String userText = editText.getText().toString();
//                testText.setText(calDict.get(userText));
//            }
//        });
//
//        }

    }
    public void switchActivity(){
        Intent intent = new Intent(this, FoodSelector.class);
        startActivity(intent);
    }
}