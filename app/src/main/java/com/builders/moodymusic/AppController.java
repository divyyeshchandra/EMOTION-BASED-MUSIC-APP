package com.builders.moodymusic;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.builders.moodymusic.constants.MoodConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AppController extends Application {


    public static Context context;
    public static MoodConstants.MOOD currentMood;

    static private HashMap<Integer, String> sadSongHashMap = new HashMap();
    static private HashMap<Integer, String> happySongHashMap = new HashMap();
    static private Random random;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initSadSongHashMap();
        initHappySongHashMap();
        random = new Random();
    }

    private void initHappySongHashMap() {
        happySongHashMap.put(0, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Chhote%20Chhote%20Peg%20Video%20%20Yo%20Yo%20Honey%20Singh%20%20Neha%20Kakkar%20%20Navraj%20Hans%20%20Sonu%20Ke%20Titu%20Ki%20Sweety.mp3?alt=media&token=2fe3c966-15f0-48d8-bc2b-9e54d582ffca");
        happySongHashMap.put(1, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Harrdy%20Sandhu%20%20Kya%20Baat%20Ay%20%20Jaani%20%20B%20Praak%20%20%20Arvindr%20Khaira%20%20Official%20Music%20Video.mp3?alt=media&token=19237068-5eb2-4403-96fd-cc9a15c7f3b2");
        happySongHashMap.put(2, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Kya%20Karu%20Full%20Song%20Millind%20Gaba%20Feat%20Ashnoor%20K%20%20Parampara%20T%20%20Asli%20Gold%20%20Shabby%20%20Bhushan%20Kumar.mp3?alt=media&token=dd12587c-9fc4-4526-a644-2ff59a482ef3");
        happySongHashMap.put(3, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Lungi%20Dance%20Chennai%20Express%20New%20Video%20Feat%20Honey%20Singh%20Shahrukh%20Khan%20Deepika.mp3?alt=media&token=32331ed9-6207-49b7-9b4b-953e05fe2d23");
        happySongHashMap.put(4, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20One%20Bottle%20Down%20FULL%20VIDEO%20SONG%20%20Yo%20Yo%20Honey%20Singh%20%20TSERIES.mp3?alt=media&token=1c2394d0-80f0-4743-8b3b-7235bf78c285");
        happySongHashMap.put(5, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Party%20With%20The%20Bhoothnath%20Song%20Official%20%20Bhoothnath%20Returns%20%20Amitabh%20Bachchan%20Yo%20Yo%20Honey%20Singh.mp3?alt=media&token=4116d042-197f-4463-a02e-a6a5ee86c95e");
    }

    private void initSadSongHashMap() {
        sadSongHashMap.put(0, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/jag%20soona%20lage.mp3?alt=media&token=23158f8f-f376-4244-a09d-a85500675ebb");
        sadSongHashMap.put(1, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/tujhe%20bhula%20diya.mp3?alt=media&token=b9063733-08fa-4134-adc3-cdba117e4ad0");
        sadSongHashMap.put(2, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Arijit%20Singh%20Pachtaoge%20%20Vicky%20Kaushal%20Nora%20Fatehi%20Jaani%20B%20Praak%20Arvindr%20Khaira%20%20Bhushan%20Kumar.mp3?alt=media&token=aa5ed7e6-561a-4244-add7-b53e3b60d6fe");
        sadSongHashMap.put(3, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Atif%20A%20Dekhte%20Dekhte%20Lyrical%20%20Batti%20Gul%20Meter%20Chalu%20%20Shahid%20K%20Shraddha%20%20Nusrat%20Saab%20Rochak%20Manoj.mp3?alt=media&token=d19b5985-3231-4c42-aa59-fdaab3f27f53");
        sadSongHashMap.put(4, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Baarish%20%20Full%20Video%20%20Half%20Girlfriend%20%20Arjun%20Kapoor%20%20Shraddha%20Kapoor%20Ash%20King%20%20Sashaa%20%20Tanishk.mp3?alt=media&token=584c3202-942e-45b6-91a1-fb799143ca3a");
        sadSongHashMap.put(5, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20FILHALL%20%20Akshay%20Kumar%20Ft%20Nupur%20Sanon%20%20BPraak%20%20Jaani%20%20Arvindr%20Khaira%20%20Ammy%20Virk%20%20Official%20Video.mp3?alt=media&token=9491226b-5e77-479d-825d-0c6b707d3969");
        sadSongHashMap.put(6, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Lyrical%20Jag%20Soona%20Soona%20Lage%20%20Om%20Shanti%20Om%20%20Shahrukh%20Khan%20Deepika%20Padukon.mp3?alt=media&token=d3aed940-82ec-4012-8c29-caa631684bbe");
        sadSongHashMap.put(7, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Qismat%20Full%20Video%20%20Ammy%20Virk%20%20Sargun%20Mehta%20%20Jaani%20%20B%20Praak%20%20Arvindr%20Khaira%20%20Punjabi%20Songs.mp3?alt=media&token=93dfc07a-ef2f-4d79-9ae4-b51b0d7f8e72");
        sadSongHashMap.put(8, "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/Yeh%20Duniya%20yeh%20mehfil%20mere%20kaam%20ki%20nahi.mp3?alt=media&token=ce6c8ec0-796d-4f99-be6c-4434639b424c");
    }

    public static String getSadSong() {
        int number = random.nextInt(9);
        return sadSongHashMap.get(number);
    }

    public static String getHappySong() {
        int number = random.nextInt(6);
        return happySongHashMap.get(number);
    }

    public static void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
