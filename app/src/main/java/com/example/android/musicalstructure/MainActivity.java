package com.example.android.musicalstructure;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // To save the Songs in.
    ArrayList<Song> songs = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView descreption = (TextView) findViewById(R.id.descreption);
        descreption.setText(R.string.songsDescreption);
        // linking the navigation  buttons in the xml.
        Button buttonAlbums = (Button) findViewById(R.id.buttonAlbums);
        Button buttonSongs = (Button) findViewById(R.id.buttonSongs);
        Button buttonArtists = (Button) findViewById(R.id.buttonArtists);

        //setting the active button a different color and others are white.
        buttonSongs.setTextColor((Color.parseColor("#2095F2")));
        buttonArtists.setTextColor(Color.parseColor("#ffffff"));
        buttonAlbums.setTextColor(Color.parseColor("#ffffff"));

        //setting onClickListener to each button to go to the specified activity.
        buttonArtists.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ArtistActivity.class);
                startActivity(i);
            }
        });
        buttonAlbums.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(i);
            }
        });
        buttonSongs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.songsToast, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // filling the arrayList of songs with the songs.
        songs.add(new Song("Song 1", "Album 1", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 2", "Album 1", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 3", "Album 1", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 4", "Album 1", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 5", "Album 1", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 6", "Album 2", "Mahmoud el Esseli", R.drawable.music));
        songs.add(new Song("Song 7", "Album 2", "Hamaki", R.drawable.music));
        songs.add(new Song("Song 8", "Album 2", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 9", "Album 2", "Amr Diab", R.drawable.music));
        songs.add(new Song("Song 10", "Album 2", "Amr Diab", R.drawable.music));

        //making object of a custom adapter that is made to show the listView as wanted.
        SongAdapter songAdapter = new SongAdapter(this, songs);
        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(songAdapter);

        //listening for each item on the adapter because if item clicked that mean that the user want to
        //listen to the song so we should open the activity which will play the song.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //getting the clicked song name to display it's name on the now_playing activity.
                String clickedSong = songs.get(position).getSongName();
                Intent i = new Intent(MainActivity.this, NowPlayingActivity.class);
                //sending the songName with the intent to the NowPlaying activity to display it.
                i.putExtra("clickedSong", clickedSong);
                startActivity(i);
            }
        });
    }
}
