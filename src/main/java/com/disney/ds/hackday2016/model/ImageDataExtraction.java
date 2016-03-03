package com.disney.ds.hackday2016.model;

import java.util.List;

/**
 * Created by peichen on 3/2/16.
 */
public class ImageDataExtraction {
    String url;
    List<FaceDetectionUnit> faceDetection;
    List<ImageDataUnit> labelDetection;
    List<String> textDetection;
    List<ImageDataUnit> landmarkDetection;
    List<ImageDataUnit> logoDetection;

    public ImageDataExtraction(String url, List<FaceDetectionUnit> faceDetection, List<ImageDataUnit> labelDetection, List<String> textDetection, List<ImageDataUnit> landmarkDetection, List<ImageDataUnit> logoDetection) {
        this.url = url;
        this.faceDetection = faceDetection;
        this.labelDetection = labelDetection;
        this.textDetection = textDetection;
        this.landmarkDetection = landmarkDetection;
        this.logoDetection = logoDetection;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<FaceDetectionUnit> getFaceDetection() {
        return faceDetection;
    }

    public void setFaceDetection(List<FaceDetectionUnit> faceDetection) {
        this.faceDetection = faceDetection;
    }

    public List<ImageDataUnit> getLabelDetection() {
        return labelDetection;
    }

    public void setLabelDetection(List<ImageDataUnit> labelDetection) {
        this.labelDetection = labelDetection;
    }

    public List<String> getTextDetection() {
        return textDetection;
    }

    public void setTextDetection(List<String> textDetection) {
        this.textDetection = textDetection;
    }

    public List<ImageDataUnit> getLandmarkDetection() {
        return landmarkDetection;
    }

    public void setLandmarkDetection(List<ImageDataUnit> landmarkDetection) {
        this.landmarkDetection = landmarkDetection;
    }

    public List<ImageDataUnit> getLogoDetection() {
        return logoDetection;
    }

    public void setLogoDetection(List<ImageDataUnit> logoDetection) {
        this.logoDetection = logoDetection;
    }
}
