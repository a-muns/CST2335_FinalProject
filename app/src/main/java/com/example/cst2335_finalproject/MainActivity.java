package com.example.cst2335_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /**
     * Global SharedPreferences
     */
    SharedPreferences namePreferences = null;
    SharedPreferences bodyPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets edit values to saved SharedPreferences values if they exist
        setEditTexts();

        EditText nameEdit = findViewById(R.id.nameInputEdit);
        EditText bodyEdit = findViewById(R.id.bodyInputEdit);
        Button titleButton = findViewById(R.id.titleButton);

        // Start HomeActivity with name and body values
        titleButton.setOnClickListener( click -> {
            String nameValue = nameEdit.getText().toString();
            String bodyValue = bodyEdit.getText().toString();
            Log.i("onclick", nameValue);
            Log.i("onclick", bodyValue);
            saveSharedPrefs(nameValue, bodyValue);
            Intent homePage = new Intent(this, HomeActivity.class)
                    .putExtra("name", nameValue)
                    .putExtra("body", bodyValue);
            startActivity(homePage);
        });
    }

    /**
     * Grab name and body from SharedPreferences if they exist
     */
    private void setEditTexts() {

        EditText nameEdit = findViewById(R.id.nameInputEdit);
        EditText bodyEdit = findViewById(R.id.bodyInputEdit);

        namePreferences = getSharedPreferences("Name", Context.MODE_PRIVATE);
        bodyPreferences = getSharedPreferences("Body", Context.MODE_PRIVATE);
        String savedNameString = namePreferences.getString("Name", "Name not found");
        String savedBodyString = bodyPreferences.getString("Body", "Body not found");
        if (!(savedNameString.contains("Name not found"))) {
            nameEdit.setText(savedNameString);
        }
        if (!(savedBodyString.contains("Body not found"))) {
            bodyEdit.setText(savedBodyString);
        }
    }

    /**
     * Save EditText inputs to SharedPreferences
     *
     * @param nameString
     * @param bodyString
     */
    private void saveSharedPrefs(String nameString, String bodyString) {
        SharedPreferences.Editor nameEditor = namePreferences.edit();
        SharedPreferences.Editor bodyEditor = bodyPreferences.edit();
        nameEditor.putString("Name", nameString);
        bodyEditor.putString("Body", bodyString);
        nameEditor.commit();
        bodyEditor.commit();
    }
}