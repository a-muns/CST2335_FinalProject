package com.example.cst2335_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchActivity extends BaseActivity {

    /**
     * Global variables
     */
    NASAItem nasaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search    (1.0.0)");

        // Drawer
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //Navigation View
        NavigationView navigation = findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(this);

        /**
         * Initialize NasaAPI AsyncTask with date chosen
         */
        NasaAPI nasaAPI = new NasaAPI();
        nasaAPI.execute("https://api.nasa.gov/planetary/apod?api_key=DgPLcIlnmN0Cwrzcg3e9NraFaYLIDI68Ysc6Zh3d&date=2020-02-01");
    }

    /**
     * Queries NASA API in the background
     */
    private class NasaAPI extends AsyncTask <String, Integer, String> {

        @Override
        protected String doInBackground(String... args) {

            try {
                URL url = new URL(args[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream response = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                String result = sb.toString();

                // Create JSON object from result
                JSONObject nasaJSON = new JSONObject(result);

                // Cast attributes into a new NASAItem object
                String title = nasaJSON.getString("title");
                String explanation = nasaJSON.getString("explanation");
                URL imageURL = new URL(nasaJSON.getString("hdurl"));
                nasaItem = new NASAItem(title, explanation, imageURL);

                publishProgress(1);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Done";
        }

        /**
         * Update TextViews with nasaItem attributes
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView nasaItemTitle = findViewById(R.id.nasaItemTitle);
            TextView nasaItemExplanation = findViewById(R.id.nasaItemExplanation);
            TextView nasaItemImageURL = findViewById(R.id.nasaItemImageURL);
            nasaItemTitle.setText(nasaItem.getTitle());
            nasaItemExplanation.setText(nasaItem.getExplanation());
            nasaItemImageURL.setText((nasaItem.getImageURL().toString()));
        }
    }

    /**
     * Alert Dialog on clicking help button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Search Help")
                .setMessage("Select a date in the calendar to search for the image associated with that date." +
                        " You can save a date by clicking the star icon.")
                .setPositiveButton("Dismiss", (click, arg) -> { });
        alertDialogBuilder.create().show();

        return true;
    }
}