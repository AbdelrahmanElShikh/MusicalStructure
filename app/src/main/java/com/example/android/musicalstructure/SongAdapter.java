package com.example.android.musicalstructure;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Song currentSong = getItem(position);
        if (convertView == null)
            //layoutInflater which turns the Xml layout file into actual view objects.
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);

        TextView songName = convertView.findViewById(R.id.songName);
        songName.setText(currentSong.getSongName());
        TextView songAlbum = convertView.findViewById(R.id.songAlbum);
        songAlbum.setText(currentSong.getSongAlbum());
        TextView songArtist = convertView.findViewById(R.id.songArtist);
        songArtist.setText(currentSong.getSongArtist());
        ImageView songImage = convertView.findViewById(R.id.songImage);
        songImage.setImageResource(currentSong.getSongImageResourceID());
        return convertView;
    }
}
