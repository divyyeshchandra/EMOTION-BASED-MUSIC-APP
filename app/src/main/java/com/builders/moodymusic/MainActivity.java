package com.builders.moodymusic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.builders.moodymusic.activity.ExpressionDisplayActivity;
import com.builders.moodymusic.constants.MoodConstants;
import com.builders.moodymusic.interfaces.FacialExpressionCallBack;
import com.builders.moodymusic.utils.FaceDetectionUtils;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Mode;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FacialExpressionCallBack {

    private static final String TAG = "MainActivity";
    int CAMERA_PERMISSION = 1004;
    boolean isCameraFacingFront = true;
    private CameraView cameraView;
    private RelativeLayout clickImageRL;
    private RelativeLayout changeCameraFaceRL;
    FaceDetectionUtils faceDetectionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        checkForPermission();
        faceDetectionUtils = new FaceDetectionUtils();
        AppController.showToast("Please keep your network turn on");
    }

    private void checkForPermission() {
        if (hasPermission()) {
            setUpCamera();
            setOnClicks();
        } else {
            String permission[] = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
            requestPermissions(permission, CAMERA_PERMISSION);
        }
    }

    private boolean hasPermission() {
        boolean isPermissionGranted = false;
        if (
                checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        ) {
            isPermissionGranted = true;
        }
        return isPermissionGranted;
    }

    private void setUpCamera() {
        cameraView = findViewById(R.id.camera_view);
        cameraView.setMode(Mode.PICTURE);
        cameraView.setLifecycleOwner(this);
        cameraView.setFacing(Facing.FRONT);
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull @NotNull PictureResult result) {
                super.onPictureTaken(result);
                faceDetectionUtils.processMood(MainActivity.this, result.getData(), result.getRotation());
            }
        });
    }

    private void setOnClicks() {
        changeCameraFaceRL = findViewById(R.id.change_camera_face_rl);
        clickImageRL = findViewById(R.id.click_image_rl);
        changeCameraFaceRL.setOnClickListener(this);
        clickImageRL.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION) {
            if (hasPermission()) {
                setUpCamera();
                setOnClicks();
            } else {
                AppController.showToast(getResources().getString(R.string.permission_not_allowed));
                finish();
            }
        }
    }

    private void changeCameraFacing() {
        isCameraFacingFront = !isCameraFacingFront;
        cameraView.setFacing(isCameraFacingFront ? Facing.FRONT : Facing.BACK);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.change_camera_face_rl)
            changeCameraFacing();
        if(v.getId()==R.id.click_image_rl)
            cameraView.takePicture();
    }

    @Override
    public void currentMood(MoodConstants.MOOD currentMood) {
        if (currentMood == MoodConstants.MOOD.NO_GROUP) {
            AppController.showToast("Solo pic only");
        } else if (currentMood == MoodConstants.MOOD.NO_FACE) {
            AppController.showToast("face not found , try again");
        } else {
            AppController.currentMood = currentMood;
            startActivity(new Intent(this, ExpressionDisplayActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}