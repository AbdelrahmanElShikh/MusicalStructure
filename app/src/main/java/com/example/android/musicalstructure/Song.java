package com.example.android.musicalstructure;

import java.util.ArrayList;


public class Song {
    // class variables
    private String songName;
    private String songAlbum;
    private String songArtist;
    private int songImageResourceID;

    // constructor
    public Song(String songName, String songAlbum, String songArtist, int songImageResourceID) {
        this.songName = songName;
        this.songAlbum = songAlbum;
        this.songImageResourceID = songImageResourceID;
        this.songArtist = songArtist;
    }

    //methods
    public String getSongName() {
        return songName;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public int getSongImageResourceID() {
        return songImageResourceID;
    }

    public String getSongArtist() {
        return songArtist;
    }

    /**
     * this method take arraylist of songs (as parameters) and loop through the songs and
     * return an arraylist of all Artists in the songs.
     *
     * @param songs is the list of songs.
     * @return an array list of artists in the songs list.
     */
    public static ArrayList<String> getArtists(ArrayList<Song> songs) {
        ArrayList<String> artists = new ArrayList<String>();
        for (int i = 0; i < songs.size(); i++) {
            if (!artists.contains(songs.get(i).getSongArtist()))
                artists.add(songs.get(i).getSongArtist());
        }
        return artists;
    }

    /**
     * this method take arraylist of songs (as parameters) and loop through the songs and
     * return an arraylist of all albums in the songs.
     *
     * @param songs is the list of songs.
     * @return an array list of albums on the songs list.
     */
    public static ArrayList<String> getAlbums(ArrayList<Song> songs) {
        ArrayList<String> albums = new ArrayList<String>();
        for (int i = 0; i < songs.size(); i++) {

            if (!albums.contains(songs.get(i).getSongAlbum()))
                albums.add(songs.get(i).getSongAlbum());
        }
        return albums;
    }

    /**
     * this method take arrayList of Songs and artist Name (as parameter) and loop through ,
     * this list of songs  to return common Songs of this artist
     *
     * @param songs      is the list of songs.
     * @param artistName is the name of the artist to get his songs in the list.
     * @return arraylist of artist common songs in the list.
     */

    public static ArrayList<Song> getCommonArtistSongs(ArrayList<Song> songs, String artistName) {
        ArrayList<Song> commonSongs = new ArrayList<Song>();
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).songArtist.equals(artistName))
                commonSongs.add(songs.get(i));
        }
        return commonSongs;
    }

    /**
     * this method take arrayList of Songs and album Name (as parameter) and loop through ,
     * this list of songs  to return common Songs of this album.
     *
     * @param songs     is the list of songs.
     * @param albumName is the name of the album to get it's songs in the list.
     * @return arraylist of album common songs in the list.
     */
    public static ArrayList<Song> getCommonAlbumSongs(ArrayList<Song> songs, String albumName) {
        ArrayList<Song> commonSongs = new ArrayList<Song>();
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).songAlbum.equals(albumName))
                commonSongs.add(songs.get(i));
        }
        return commonSongs;
    }
}
