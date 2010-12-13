package com.cannonmatthews.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;

public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // click-handlers for buttons
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);

        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);

    }
    
    
    public void onClick(View v) {


        switch (v.getId()) {
        case R.id.new_button:
            Intent gridIntent = new Intent(this, GridActivity.class);
            startActivity(gridIntent);
            break;
        case R.id.about_button:
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
            break;
        }
    }
}