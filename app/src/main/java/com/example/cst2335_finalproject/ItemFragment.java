package com.example.cst2335_finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class ItemFragment extends Fragment {

    /**
     * Receive itemData and inflate fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_item, container, false);

        // Get bundled data
        Bundle itemData = getArguments();
        try {
            long id = itemData.getLong(DBOpener.COL_ID);
            String date = itemData.getString(DBOpener.COL_DATE);
            String title = itemData.getString(DBOpener.COL_TITLE);
            String explanation = itemData.getString(DBOpener.COL_EXPLANATION);
            URL imageURL = new URL(itemData.getString(DBOpener.COL_IMAGE_URL));

        // Set TextViews with data
        TextView nasaItemDate = result.findViewById(R.id.nasaItemDate_fav);
        TextView nasaItemTitle = result.findViewById(R.id.nasaItemTitle_fav);
        TextView nasaItemExplanation = result.findViewById(R.id.nasaItemExplanation_fav);
        TextView nasaItemImageURL = result.findViewById(R.id.nasaItemImageURL_fav);
        nasaItemDate.setText(date);
        nasaItemTitle.setText(title);
        nasaItemExplanation.setText(explanation);
        nasaItemImageURL.setText(imageURL.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return result;
    }
}