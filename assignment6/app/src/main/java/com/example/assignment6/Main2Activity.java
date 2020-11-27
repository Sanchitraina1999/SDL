package com.example.assignment6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Button next, prev, stop;
    TextView songtextview;
    SeekBar seekbar;


    static MediaPlayer mediaplayer;
    int position;

    String sname;

    ArrayList<File> mySongs;
    Thread updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.previous);
        stop = findViewById(R.id.pause);

        songtextview = findViewById(R.id.stextview);

        seekbar = findViewById(R.id.seekBar);
        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        updateSeekBar = new Thread() {
            @Override
            public void run() {
                int runtime = mediaplayer.getDuration();
                int currentPosition = 0;
                int adv = 0;
                while ((adv = ((adv = runtime - currentPosition) < 500)?adv:500) > 2) {
                    try {
                        currentPosition = mediaplayer.getCurrentPosition();
                        if (seekbar != null) {
                            seekbar.setProgress(currentPosition);
                        }
                        sleep(adv);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        seekbar.setProgress(runtime);
                        break;
                    }

                }
            }
        };

        if(mediaplayer != null) {

            mediaplayer.stop();
            mediaplayer.release();

        }


        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");

        sname = mySongs.get(position).getName();

        String songName = i.getStringExtra("songName");

        songtextview.setText(songName);
        songtextview.setSelected(true);

        position = bundle.getInt("pos",0);

        Uri u = Uri.parse(mySongs.get(position).toString());

        mediaplayer = MediaPlayer.create(getApplicationContext(), u);
        mediaplayer.start();
        seekbar.setMax(mediaplayer.getDuration());

        updateSeekBar.start();

        seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        seekbar.getThumb().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mediaplayer.seekTo(seekBar.getProgress());

            }
        });

//    mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            mediaplayer.stop();
//            mediaplayer.release();
//            position = ((position+1)%mySongs.size());
//            stop.setBackgroundResource(R.drawable.icon_pause);
//            Uri u = Uri.parse(mySongs.get(position).toString());
//
//            mediaplayer = MediaPlayer.create(getApplicationContext(), u);
//
//            sname = mySongs.get(position).getName();
//
//            songtextview.setText(sname);
//
//            mediaplayer.start();
//        }
//    });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                seekbar.setMax(mediaplayer.getDuration());

                if(mediaplayer.isPlaying()) {

                    stop.setBackgroundResource(R.drawable.icon_play);
                    mediaplayer.pause();

                }
                else {

                    stop.setBackgroundResource(R.drawable.icon_pause);
                    mediaplayer.start();

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaplayer.stop();
                mediaplayer.release();
                position = ((position+1)%mySongs.size());
                stop.setBackgroundResource(R.drawable.icon_pause);
                Uri u = Uri.parse(mySongs.get(position).toString());

                mediaplayer = MediaPlayer.create(getApplicationContext(), u);

                sname = mySongs.get(position).getName();

                songtextview.setText(sname);

                mediaplayer.start();

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaplayer.stop();
                mediaplayer.release();
                stop.setBackgroundResource(R.drawable.icon_pause);
                position = ((position-1)<0)?(mySongs.size()-1):(position-1);

                Uri u = Uri.parse(mySongs.get(position).toString());

                mediaplayer = MediaPlayer.create(getApplicationContext(), u);

                sname = mySongs.get(position).getName();

                songtextview.setText(sname);

                mediaplayer.start();

            }
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) {

            onBackPressed();

        }

        return super.onOptionsItemSelected(item);
    }
}
