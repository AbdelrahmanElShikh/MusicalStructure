package com.example.android.musicalstructure;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArtistActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView descreption = (TextView) findViewById(R.id.descreption);
        descreption.setText(R.string.artistDescreption);
        // linking the buttons in the xml.
        Button buttonAlbums = (Button) findViewById(R.id.buttonAlbums);
        Button buttonSongs = (Button) findViewById(R.id.buttonSongs);
        final Button buttonArtists = (Button) findViewById(R.id.buttonArtists);
        //setting the active button a different color and others are white.
        buttonArtists.setTextColor((Color.parseColor("#2095F2")));
        buttonSongs.setTextColor(Color.parseColor("#ffffff"));
        buttonAlbums.setTextColor(Color.parseColor("#ffffff"));
        //setting onClickListener to each button to go to specified activity.
        buttonAlbums.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(ArtistActivity.this, AlbumActivity.class);
                startActivity(i);

            }
        });
        buttonSongs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(ArtistActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        buttonArtists.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast toast = Toast.makeText(ArtistActivity.this, R.string.artistToast, Toast.LENGTH_SHORT);
                toast.show();

            }
        });
        // creating arraylist of type String to hold the names of artists in our songs.
        final ArrayList<String> artists = Song.getArtists(songs);
        //creating object of the default arrayAdapter to show the artists names.
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_artist_album, R.id.artist_album_Name, artists);
        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        //listening to each item in the artists adapter because if the user clicked any artist that mean he want artist's songs.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                descreption.setText(R.string.songsDescreption);
                //geting the clicked artist to show his songs.
                String clickedArtist = artists.get(position);
                //creating arrayList to hold the artist songs from Song class.
                final ArrayList<Song> commonArtistSongs = Song.getCommonArtistSongs(songs, clickedArtist);
                //displaying the artist songs in the custom adapter.
                SongAdapter songAdapter = new SongAdapter(ArtistActivity.this, commonArtistSongs);
                // Get a reference to the ListView, and attach the adapter to the listView.
                final ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(songAdapter);
                //listening to the artists button as if the user clicked it while the songs of a specified artist is shown,
                //the list of all artists show up again.
                buttonArtists.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        Intent intent = new Intent(ArtistActivity.this, ArtistActivity.class);
                        startActivity(intent);

                    }
                });
                //listening to each song of the artist's songs so if the user clicked it ,, the app play the song.
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String clickedSong = commonArtistSongs.get(position).getSongName();
                        Intent i = new Intent(ArtistActivity.this, NowPlayingActivity.class);
                        //sending the songName with the intent to the NowPlaying activity to display it.
                        i.putExtra("clickedSong", clickedSong);
                        startActivity(i);

                    }
                });

            }
        });

    }
}
