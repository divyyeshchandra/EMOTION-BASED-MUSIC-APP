package com.builders.moodymusic.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.mlkit.vision.common.InputImage;

public class InputImageUtils {

    public InputImage getInputImage(byte[] byteArray, int rotation) {
        InputImage image = InputImage.fromByteArray(byteArray,
                480,
                360,
                rotation,
                InputImage.IMAGE_FORMAT_NV21);
        return image;
    }

    public InputImage getInputImageFromBitmap(byte[] data, int rotation) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return InputImage.fromBitmap(bmp, rotation);
    }

}
