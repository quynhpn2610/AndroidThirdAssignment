package com.example.mythirdassign;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

/**
  Quynh Nguyen
  Prof. Thomas
  CS 255A Android Programming
  Assignment 3
  02/22/2022
  This activity has an add/subtract function and 2 ways to get to the Random Activity
  One through a menu, this passed the count value to random
  The other is through a button that calls random for a result
 */

public class RandomActivity extends AppCompatActivity {

    static final String COUNT = "count";
    static final String RANDOM = "random";
    TextView mainView2;
    TextView mainNum2;
    Button buttonRdn;
    Button buttonSave;
    Random rand = new Random();
    int randomNumber;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_layout);

        // Make the random button work

        mainView2 = (TextView) (findViewById(R.id.text_random));
        mainNum2 = (TextView) (findViewById(R.id.text_random_num));
        buttonRdn = (Button) (findViewById(R.id.butRandom));
        buttonSave = (Button) findViewById(R.id.butSave);

        // Get the value passed in by the other activity
        Intent intent = getIntent();
        int count = intent.getIntExtra(COUNT, 0);

        // Set the text field to the received value
        mainNum2.setText(String.valueOf(count));


        // Listener for the Random button
        buttonRdn.setOnClickListener(v1 -> {
            randomNumber = rand.nextInt(99 - 0) + 0;
            mainNum2.setText(String.valueOf(randomNumber));
        });

        // Listener for the Save button
        buttonSave.setOnClickListener(v2 -> {
            Intent intent1 = new Intent();
            intent1.putExtra(RANDOM, randomNumber);
            setResult(RESULT_OK, intent1);
            finish();
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.addSubtract:
                Intent intent = new Intent(getApplicationContext(), AddSubActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }

    }


}
