package com.example.android.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class NowPlayingActivity extends AppCompatActivity {
    boolean playing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing_);
        TextView descreption = (TextView) findViewById(R.id.descreption);
        descreption.setText(R.string.nowPlayingDescreption);
        // linking the navigation buttons in the xml.
        Button buttonAlbums = (Button) findViewById(R.id.buttonAlbums);
        Button buttonSongs = (Button) findViewById(R.id.buttonSongs);
        Button buttonArtists = (Button) findViewById(R.id.buttonArtists);
        // variable to know if the song is on or off , as the start of this activity the song will be on ,
        // so it will be true.
        playing = true;
        //geting refrence of the playButton in the Xml.
        final ImageButton playButton = (ImageButton) findViewById(R.id.buttonplay);
        //geting the songName which will the activity play on from the passed intent.
        final String songName = getIntent().getStringExtra("clickedSong");
        // linkin the textView where the song name will be displayed on from the xml.
        final TextView playingSong = (TextView) findViewById(R.id.nowPlayingText);
        //assigning the text that will be displayed on the screen in case of a song is on to isPlaying String variable.
        final String isPlaying = songName + getText(R.string.playing);
        //assigning the text that will be displayed on the screen in case of a song is off to stopped String variable.
        final String stopped = songName + getText(R.string.stopped);
        //when activity first start the song will be on so we will display isPlaying Text.
        playingSong.setText(isPlaying);

        //listening to the platButton .
        playButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // in case of the song is on and the user clicked the button , the icon is changed to the play icon ,
                // and changing the text to stopped text.
                if (playing) {
                    playButton.setImageResource(R.drawable.play);
                    playing = !playing;
                    playingSong.setText(stopped);
                }
                //in case of the song is off and the user clicked the button , the icon is changed to pause icon,
                // and changing the text to isPlaying text.
                else {
                    playButton.setImageResource(R.drawable.pause);
                    playing = !playing;
                    playingSong.setText(isPlaying);
                }
            }
        });
        //listening to navigation buttons to make the user able to move to any activity
        buttonArtists.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(NowPlayingActivity.this, ArtistActivity.class);
                startActivity(i);

            }
        });
        buttonAlbums.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(NowPlayingActivity.this, AlbumActivity.class);
                startActivity(i);

            }
        });
        buttonSongs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(NowPlayingActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }
}
