package com.builders.moodymusic.utils;


import com.builders.moodymusic.constants.MoodConstants;
import com.builders.moodymusic.interfaces.FacialExpressionCallBack;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;


public class FaceDetectionUtils {

    private static final String TAG = "FaceDetectionUtils";
    private FaceDetector faceDetector;
    private InputImageUtils inputImageUtils;

    public FaceDetectionUtils() {
        FaceDetectorOptions highAccuracyOpts =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();

        faceDetector = FaceDetection.getClient(highAccuracyOpts);
        inputImageUtils = new InputImageUtils();
    }

    public void processMood(FacialExpressionCallBack facialExpressionCallBack, byte[] bytes, int rotation) {
        InputImage image = inputImageUtils.getInputImageFromBitmap(bytes, rotation);
        faceDetector.process(image)
                .addOnSuccessListener(
                        faces -> {
                            if (faces.size() > 1) {
                                facialExpressionCallBack.currentMood(MoodConstants.MOOD.NO_GROUP);
                                return;
                            } else if (faces.size() > 0) {
                                facialExpressionCallBack.currentMood(calculateSmile(faces.get(0).getSmilingProbability()));
                            } else {
                                facialExpressionCallBack.currentMood(MoodConstants.MOOD.NO_FACE);
                            }
                        })
                .addOnFailureListener(
                        e -> {
                            facialExpressionCallBack.currentMood(MoodConstants.MOOD.NO_FACE);
                        });

    }

    private MoodConstants.MOOD calculateSmile(Float smilingProbability) {
        MoodConstants.MOOD mood = MoodConstants.MOOD.MIDDLE;
        if (smilingProbability > 0.0 && smilingProbability < 0.33) {
            mood = MoodConstants.MOOD.SAD;
        } else if (smilingProbability > 0.33 && smilingProbability < 0.6) {
            mood = MoodConstants.MOOD.MIDDLE;
        } else if (smilingProbability > 0.6 && smilingProbability < 1.0) {
            mood = MoodConstants.MOOD.HAPPY;
        }
        return mood;
    }
}
