package com.example.cst2335_finalproject;

import java.net.URL;

/**
 * Represents a single item returned from the NASA API
 */
public class NASAItem {

    /**
     * Class variables
     */
    private String title;
    private String explanation;
    private URL imageURL;

    /**
     * Constructor
     * @param title
     * @param explanation
     * @param imageURL
     */
    public NASAItem(String title, String explanation, URL imageURL) {
        this.title = title;
        this.explanation = explanation;
        this.imageURL = imageURL;
    }

    /**
     * Getters and setters
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public URL getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

}
