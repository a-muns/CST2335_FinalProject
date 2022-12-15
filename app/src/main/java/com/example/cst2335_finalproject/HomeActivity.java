package com.example.cst2335_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home    (1.0.0)");

        // Drawer
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //Navigation View
        NavigationView navigation = findViewById(R.id.navigation);


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

    /**
     * On clicking help button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer);

        Snackbar.make(drawer, "Click Search to search images, or Favourites to see saved dates.", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Do nothing, dismissing the snackbar
                    }
                })
                .show();

        // Make Toast (change to Snackbar displaying help info?)
        // Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show();

        return true;
    }
}