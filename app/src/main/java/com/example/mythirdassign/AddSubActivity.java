package com.example.mythirdassign;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class AddSubActivity extends AppCompatActivity implements View.OnClickListener {
    static final String COUNT = "count";
    static final String RANDOM = "random";
    static final int RANDOM_NUMBER_REQUEST = 1;
    TextView mainNum;
    Button buttonAdd;
    Button buttonSub;
    Button buttonGet;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addsub);

        // Make the buttons add, subtract and get
        mainNum = (TextView)(findViewById(R.id.text_total_num));
        buttonSub = (Button)(findViewById(R.id.butSub));
        buttonAdd = (Button)(findViewById(R.id.butAdd));
        buttonGet = (Button)(findViewById(R.id.butGet));

        // Implement View.onClickListener to the buttons above
        buttonAdd.setOnClickListener(this);
        buttonGet.setOnClickListener(this);
        buttonSub.setOnClickListener(this);

    }
    // Handle the three buttons
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
        switch (v.getId()){
            case R.id.butAdd:
                counter++;
                mainNum.setText(String.valueOf(counter));
                break;
            case R.id.butSub:
                counter--;
                mainNum.setText(String.valueOf(counter));
                break;
            case R.id.butGet:
                Intent intent = new Intent(this, RandomActivity.class);
                startActivityForResult(intent, RANDOM_NUMBER_REQUEST);
                break;
        }
    }

    @Override
    // Add a menu bar when the app is started
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    // Get called when a menu item is clicked on
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id) {
            case R.id.random:
                Intent intentRandom = new Intent(getApplicationContext(), RandomActivity.class);
                intentRandom.putExtra(COUNT, counter);
                startActivity(intentRandom);
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RANDOM_NUMBER_REQUEST) {
            if (resultCode == RESULT_OK) {

                // Received back the random value
                counter = data.getIntExtra(RANDOM, 0);
                mainNum.setText(String.valueOf(counter));

            }
        }
    }

}