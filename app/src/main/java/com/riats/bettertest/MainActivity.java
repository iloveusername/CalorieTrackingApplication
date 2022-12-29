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
        SeekBar seekBar = findViewById(R.id.seekBar);

        testText.setText("Testing");

        String string = "";
        try {
//            InputStream inputStream = getAssets().open("food.txt");
            InputStream inputStream = getAssets().open("nutrition.txt");
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
        int x = 0;
        int y = 0;
        String[][] foodArray = new String[foodList.length][foodList.length];
        for (String food : foodList){
            foodArray[x][y] = food;
            x += 1;
            if (x > 76){
                x = 0;
                y += 1;
            }
        }


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int layer = seekBar.getProgress();
                int userNum = Integer.parseInt(editText.getText().toString());
                if(userNum <= foodList.length){
                    if(layer > 76 || layer < 0){
                        testText.setText("Invalid Layer");
                    }
                    else{
//                        testText.setText(foodList[userNum]);
                        testText.setText(foodArray[layer][userNum]);
                    }

                }
                else{
                    testText.setText("Invalid Selection");
                }

            }
        });


        }


    }