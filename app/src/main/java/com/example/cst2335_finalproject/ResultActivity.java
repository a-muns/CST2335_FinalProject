package com.example.cst2335_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultActivity extends BaseActivity {

    /**
     * Global variables
     */
    NASAItem nasaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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
        // Receive and transform date into URL
        Intent dataReceived = getIntent();
        String date = dataReceived.getStringExtra("date");
        String apiURL = "https://api.nasa.gov/planetary/apod?api_key=DgPLcIlnmN0Cwrzcg3e9NraFaYLIDI68Ysc6Zh3d&date=" + date;

        // Start AsyncTask with formatted URL
        NasaAPI nasaAPI = new NasaAPI();
        nasaAPI.execute(apiURL);
    }

    /**
     * Alert Dialog on clicking help button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Results Help")
                .setMessage("This page displays the result of your chosen date. Click on 'Open Image in Broswer' to load the HD image into your default broswer.")
                .setPositiveButton("Dismiss", (click, arg) -> { });
        alertDialogBuilder.create().show();

        return true;
    }

    /**
     * Queries NASA API in the background
     */
    private class NasaAPI extends AsyncTask<String, Integer, String> {

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
                Log.i("doInBackground", nasaItem.getTitle() + nasaItem.getExplanation() + nasaItem.getImageURL());

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

            // Update imageRedirectButton with imageURL
            Button imageRedirectButton = findViewById(R.id.imageRedirectButton);
            imageRedirectButton.setOnClickListener( click -> {
                        try {
                            Intent browserRedirect = new Intent(Intent.ACTION_VIEW, Uri.parse(nasaItem.getImageURL().toString()));
                            startActivity(browserRedirect);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

            Log.i("onProgressUpdate", nasaItem.getTitle() + nasaItem.getExplanation() + nasaItem.getImageURL());
        }
    }
}