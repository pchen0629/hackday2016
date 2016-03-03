package com.disney.ds.hackday2016.model;

import java.util.List;

/**
 * Created by peichen on 3/2/16.
 */
public class ImageDataExtraction {
    List<String> labelDetection;
    List<String> textDetection;
    List<String> landmarkDetection;
    List<String> logoDetection;
    String url;

    public ImageDataExtraction(String url, List<String> labelDetection, List<String> textDetection, List<String> landmarkDetection, List<String> logoDetection) {
        this.url = url;
        this.labelDetection = labelDetection;
        this.textDetection = textDetection;
        this.landmarkDetection = landmarkDetection;
        this.logoDetection = logoDetection;
    }

    public List<String> getLabelDetection() {

        return labelDetection;
    }

    public void setLabelDetection(List<String> labelDetection) {
        this.labelDetection = labelDetection;
    }

    public List<String> getTextDetection() {
        return textDetection;
    }

    public void setTextDetection(List<String> textDetection) {
        this.textDetection = textDetection;
    }

    public List<String> getLandmarkDetection() {
        return landmarkDetection;
    }

    public void setLandmarkDetection(List<String> landmarkDetection) {
        this.landmarkDetection = landmarkDetection;
    }

    public List<String> getLogoDetection() {
        return logoDetection;
    }

    public void setLogoDetection(List<String> logoDetection) {
        this.logoDetection = logoDetection;
    }
}
