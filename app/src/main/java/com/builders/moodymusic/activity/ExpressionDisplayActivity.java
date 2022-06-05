package com.builders.moodymusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.builders.moodymusic.AppController;
import com.builders.moodymusic.R;
import com.builders.moodymusic.constants.MoodConstants;

import java.io.IOException;

public class ExpressionDisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout expressionBackgroundRL;
    ImageView emojiPlaceholderIV;
    Handler handler;
    Runnable runnable;
    MediaPlayer mediaPlayer;
    String musicUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_expression_display);
        recyclerView=findViewById(R.id.recyclerView);
        init();
        setMood();
        startTheMusic();
    }

    private void startTheMusic() {
        Uri myUri = Uri.parse(musicUrl);
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(this, myUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare(); //don't use prepareAsync for mp3 playback
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        expressionBackgroundRL = findViewById(R.id.expression_background_ll);
        emojiPlaceholderIV = findViewById(R.id.emoji_placeholder_iv);
    }

    private void setMood() {

        int emoji = R.drawable.happy;
        int background = R.drawable.happy_bg;

        if (AppController.currentMood == MoodConstants.MOOD.SAD) {
            emoji = R.drawable.sad;
            background = R.drawable.sad_bg;
            musicUrl = AppController.getSadSong();
        } else if (AppController.currentMood == MoodConstants.MOOD.HAPPY) {
            emoji = R.drawable.happy;
            background = R.drawable.happy_bg;
            musicUrl = AppController.getHappySong();
        } else if (AppController.currentMood == MoodConstants.MOOD.MIDDLE) {
            emoji = R.drawable.calm;
            background = R.drawable.calm_bg;
            musicUrl = AppController.getHappySong();
        }

        emojiPlaceholderIV.setImageResource(emoji);
        expressionBackgroundRL.setBackground(getResources().getDrawable(background));
        setRippleEffect();
    }

    private void setRippleEffect() {
        runnable = new Runnable() {
            @Override
            public void run() {
                forceRippleAnimation(expressionBackgroundRL);
                handler.postDelayed(this, 500);
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);
    }

    protected void forceRippleAnimation(View view) {
        Drawable background = view.getBackground();

        if (Build.VERSION.SDK_INT >= 21 && background instanceof RippleDrawable) {
            final RippleDrawable rippleDrawable = (RippleDrawable) background;

            rippleDrawable.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});

            Handler handler = new Handler();

            handler.postDelayed(() -> rippleDrawable.setState(new int[]{}), 200);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        destroyMediaPlayer();
    }

    private void destroyMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}