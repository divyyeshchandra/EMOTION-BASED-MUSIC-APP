
//Recycler view is not connected with the backend till now.
//It is just a prototype that, how songs gonna look in app.
//Modification will be done later.


package com.builders.moodymusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class recyclerview extends AppCompatActivity {

     RecyclerView recyclerView;
     Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=findViewById(R.id.recyclerView);
        adapter=new Adapter(getSongList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<String> getSongList() {
        List<String> songsList = new ArrayList<>();
        songsList.add("Heer Ranjha");
        songsList.add("Sang Hoon Tere");
        songsList.add("Safar");
        songsList.add("Ghalat Fehmi");
        songsList.add("Jashn-E-Bahaaraa");
        songsList.add("Banjarey");
        songsList.add("Akhiyan");
        songsList.add("Beqaaboo");
        songsList.add("Tumse Hi Tumse");
        songsList.add("Chand baaliyan");
        songsList.add("Mehrama");
        songsList.add("Tere Bina");
        songsList.add("Tera Fitoor");
        songsList.add("Tujhse Naraz Nahi Zindagi");
        songsList.add("Baarish");
        songsList.add("Song17");
        songsList.add("Song18");
        songsList.add("Song19");
        songsList.add("Song20");
        return songsList;
    }
}