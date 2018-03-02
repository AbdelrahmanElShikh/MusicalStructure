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

public class AlbumActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView descreption = (TextView) findViewById(R.id.descreption);
        descreption.setText(R.string.albumsDescreption);
        // linking the navigation buttons in the xml.
        final Button buttonAlbums = (Button) findViewById(R.id.buttonAlbums);
        Button buttonSongs = (Button) findViewById(R.id.buttonSongs);
        Button buttonArtists = (Button) findViewById(R.id.buttonArtists);
        //setting the active button a different color and others are white.
        buttonArtists.setTextColor((Color.parseColor("#ffffff")));
        buttonSongs.setTextColor(Color.parseColor("#ffffff"));
        buttonAlbums.setTextColor(Color.parseColor("#2095F2"));
        //setting onClickListener to each button to go to specified activity.
        buttonSongs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(AlbumActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        buttonAlbums.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast toast = Toast.makeText(AlbumActivity.this, R.string.albumsToast, Toast.LENGTH_SHORT);
                toast.show();

            }
        });
        buttonArtists.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(AlbumActivity.this, ArtistActivity.class);
                startActivity(i);

            }
        });
        // creating arraylist of type String to hold the names of albums in our songs.
        final ArrayList<String> albums = Song.getAlbums(songs);
        //creating object of the default arrayAdapter to show the artists names.
        ArrayAdapter albumAdapter = new ArrayAdapter<String>(this, R.layout.activity_artist_album, R.id.artist_album_Name, albums);
        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(albumAdapter);
        //listening to each item in the albums adapter because if the user clicked any album that mean he want album's songs.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                descreption.setText(R.string.songsDescreption);
                //geting the clicked album to show his songs.
                String clickedAlbum = albums.get(position);
                //creating arrayList to hold the album songs from Song class.
                final ArrayList<Song> commonAlbumSongs = Song.getCommonAlbumSongs(songs, clickedAlbum);
                //displaying the album songs in the custom adapter.
                SongAdapter songAdapter = new SongAdapter(AlbumActivity.this, commonAlbumSongs);
                // Get a reference to the ListView, and attach the adapter to the listView.
                final ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(songAdapter);
                //listening to the albums button as if the user clicked it while the songs of a specified album is shown,
                //the list of all albums show up again.
                buttonAlbums.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        Intent intent = new Intent(AlbumActivity.this, AlbumActivity.class);
                        startActivity(intent);

                    }
                });
                //listening to each song of the album's songs so if the user clicked it ,, the app play the song.
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String clickedSong = commonAlbumSongs.get(position).getSongName();
                        Intent i = new Intent(AlbumActivity.this, NowPlayingActivity.class);
                        //sending the songName with the intent to the NowPlaying activity to display it.
                        i.putExtra("clickedSong", clickedSong);
                        startActivity(i);

                    }
                });
            }
        });

    }
}
