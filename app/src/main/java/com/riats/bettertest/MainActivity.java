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

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView testText = (TextView) findViewById(R.id.testText);
        EditText editText = (EditText) findViewById(R.id.editNum);
        Button button = (Button) findViewById(R.id.button);

        testText.setText("Testing");

        String string = "";
        try {
            InputStream inputStream = getAssets().open("food.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
            string = string.replace("\n",",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] foodList = string.split(",");
        testText.setText(String.valueOf(foodList.length));
        testText.setText(foodList[0]);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int userNum = Integer.parseInt(editText.getText().toString());
                if(userNum <= foodList.length){
                    testText.setText(foodList[userNum]);
                }
                else{
                    testText.setText("Invalid Selection");
                }

            }
        });

        }


    }