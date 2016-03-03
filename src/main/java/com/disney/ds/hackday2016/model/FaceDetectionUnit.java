package com.disney.ds.hackday2016.model;

/**
 * Created by peichen on 3/3/16.
 */
public class FaceDetectionUnit {
    private String detectionConfidence;
    private String landmarkingConfidence;
    private String joyLikelihood;
    private String sorrowLikelihood;
    private String angerLikelihood;
    private String surpriseLikelihood;
    private String underExposedLikelihood;
    private String blurredLikelihood;
    private String headwearLikelihood;

    public FaceDetectionUnit(String detectionConfidence, String landmarkingConfidence, String joyLikelihood, String sorrowLikelihood, String angerLikelihood, String surpriseLikelihood, String underExposedLikelihood, String blurredLikelihood, String headwearLikelihood) {
        this.detectionConfidence = detectionConfidence;
        this.landmarkingConfidence = landmarkingConfidence;
        this.joyLikelihood = joyLikelihood;
        this.sorrowLikelihood = sorrowLikelihood;
        this.angerLikelihood = angerLikelihood;
        this.surpriseLikelihood = surpriseLikelihood;
        this.underExposedLikelihood = underExposedLikelihood;
        this.blurredLikelihood = blurredLikelihood;
        this.headwearLikelihood = headwearLikelihood;
    }

    public String getDetectionConfidence() {

        return detectionConfidence;
    }

    public void setDetectionConfidence(String detectionConfidence) {
        this.detectionConfidence = detectionConfidence;
    }

    public String getLandmarkingConfidence() {
        return landmarkingConfidence;
    }

    public void setLandmarkingConfidence(String landmarkingConfidence) {
        this.landmarkingConfidence = landmarkingConfidence;
    }

    public String getJoyLikelihood() {
        return joyLikelihood;
    }

    public void setJoyLikelihood(String joyLikelihood) {
        this.joyLikelihood = joyLikelihood;
    }

    public String getSorrowLikelihood() {
        return sorrowLikelihood;
    }

    public void setSorrowLikelihood(String sorrowLikelihood) {
        this.sorrowLikelihood = sorrowLikelihood;
    }

    public String getAngerLikelihood() {
        return angerLikelihood;
    }

    public void setAngerLikelihood(String angerLikelihood) {
        this.angerLikelihood = angerLikelihood;
    }

    public String getSurpriseLikelihood() {
        return surpriseLikelihood;
    }

    public void setSurpriseLikelihood(String surpriseLikelihood) {
        this.surpriseLikelihood = surpriseLikelihood;
    }

    public String getUnderExposedLikelihood() {
        return underExposedLikelihood;
    }

    public void setUnderExposedLikelihood(String underExposedLikelihood) {
        this.underExposedLikelihood = underExposedLikelihood;
    }

    public String getBlurredLikelihood() {
        return blurredLikelihood;
    }

    public void setBlurredLikelihood(String blurredLikelihood) {
        this.blurredLikelihood = blurredLikelihood;
    }

    public String getHeadwearLikelihood() {
        return headwearLikelihood;
    }

    public void setHeadwearLikelihood(String headwearLikelihood) {
        this.headwearLikelihood = headwearLikelihood;
    }
}
