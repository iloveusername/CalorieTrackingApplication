package com.riats.bettertest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView testText = (TextView) findViewById(R.id.testText);
        EditText editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        SeekBar seekBar = findViewById(R.id.seekBar);

        testText.setText("Testing");

        String string = "Test";
        try {
            InputStream inputStream = getAssets().open("nutrition.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
            //string = string.replace("\",","]");
            string = string.replace("\"", "");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        testText.setText(string);

        Hashtable<String, String> calDict = new Hashtable<String, String>();
        String[] foodLines = string.split("\n");

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

        }
        testText.setText(calDict.get("Cornstarch"));

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int layer = seekBar.getProgress();
                String userText = editText.getText().toString();
                testText.setText(calDict.get(userText));
            }
        });


//        String string = "";
//        try {
////            InputStream inputStream = getAssets().open("food.txt");
//            InputStream inputStream = getAssets().open("nutrition.txt");
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            string = new String(buffer);
//            string = string.replace("\n",",");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String[] foodList = string.split(",");
//        testText.setText(String.valueOf(foodList.length));
//        testText.setText(foodList[0]);
//        int x = 0;
//        int y = 0;
//        String[][] foodArray = new String[foodList.length][foodList.length];
//        for (String food : foodList){
//            foodArray[x][y] = food;
//            x += 1;
//            if (x > 5){
//                x = 0;
//                y += 1;
//            }
//        }
//
//



        }


    }