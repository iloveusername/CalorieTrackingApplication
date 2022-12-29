package com.riats.bettertest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView testText = (TextView)findViewById(R.id.testText);
        ImageView imageView = (ImageView)findViewById(R.id.bruh);

        String[][] wordArray;

        testText.setText("Testing");

        String string = "";
        try {
            InputStream inputStream = getAssets().open("food.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int x = 0;
        int y = 0;
        for (String str : string.split(",")){
            x += 1;
            wordArray[x][y] = str;
            if(x > 8){
                
            }
        }
        [] = Arrays.asList(string.split(","));
        testText.setText(string);

    }
}