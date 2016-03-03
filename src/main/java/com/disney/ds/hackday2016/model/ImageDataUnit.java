package com.disney.ds.hackday2016.model;

/**
 * Created by peichen on 3/3/16.
 */
public class ImageDataUnit {
    private String description;
    private String score;

    public ImageDataUnit(String description, String score) {
        this.description = description;
        this.score = score;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
