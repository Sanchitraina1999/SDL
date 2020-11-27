package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView songlist;
    String[] songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songlist = findViewById(R.id.listView);
        runtimePermissions();
    }
    public void runtimePermissions() {

        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

                display();
                
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

                
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();

    }

    public ArrayList<File> findSong(File file) {

        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for(File singleFile : files) {

            if(singleFile.isDirectory() && !singleFile.isHidden()) {

                arrayList.addAll(findSong(singleFile));

            }
            else {

                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {

                    arrayList.add(singleFile);

                }

            }

        }

        return arrayList;
    }

    void display() {

        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());

        songs = new String[mySongs.size()];

        for(int i = 0; i < mySongs.size(); i++) {

            songs[i] = mySongs.get(i).getName().replace(".mp3","").replace(".wav","");

        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,songs);
        songlist.setAdapter(myAdapter);

        songlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String songName = songlist.getItemAtPosition(i).toString();

                startActivity(new Intent(getApplicationContext(), Main2Activity.class).putExtra("songs",mySongs).putExtra("songName", songName).putExtra("pos",i));

            }
        });

    }
}
