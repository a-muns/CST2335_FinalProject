package com.example.cst2335_finalproject;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Inflate toolbar_menu into Toolbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /**
     * On clicking help button - overridden in each activity for custom message
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;

        //        DrawerLayout drawer = findViewById(R.id.drawer);
//        Snackbar.make(drawer, "Welcome! Fill in the text fields, then click Go!", Snackbar.LENGTH_INDEFINITE)
//                .setAction("Dismiss", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // Do nothing, dismissing the snackbar
//                    }
//                })
//                .show();

        // Make Toast (change to Snackbar displaying help info?)
        // Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * On clicking NavigationDrawer items
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        String message = null;

        return false;
    }


}
