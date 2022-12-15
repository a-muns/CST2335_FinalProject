package com.example.cst2335_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         * TextView declarations
         */
        TextView nameWelcome = findViewById(R.id.nameWelcome);
        TextView bodyWelcome = findViewById(R.id.bodyWelcome);

        /**
         * Get previous intent and set TextViews with name and body values
         */
        Intent dataReceived = getIntent();
        String nameReceived = dataReceived.getStringExtra("name");
        String bodyReceived = dataReceived.getStringExtra("body");

        String welcomeName = getString(R.string.welcome);
        String welcomeNameFormatted = String.format(welcomeName, nameReceived);
        String welcomeBody = getString(R.string.bodyWelcome);
        String welcomeBodyFormatted = String.format(welcomeBody, bodyReceived);
        nameWelcome.setText(welcomeNameFormatted);
        bodyWelcome.setText(welcomeBodyFormatted);

        /**
         * Button delcarations
         */
        Button dateButton = findViewById(R.id.dateButton);
        Button favouritesButton = findViewById(R.id.favouritesButton);

        dateButton.setOnClickListener( click -> {
            Intent searchPage = new Intent(this, SearchActivity.class);
            startActivity(searchPage);
        });

        favouritesButton.setOnClickListener( click -> {
            Intent favouritesPage = new Intent(this, FavouritesActivity.class);
            startActivity(favouritesPage);
        });



    }
}