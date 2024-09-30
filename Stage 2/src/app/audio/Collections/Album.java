package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import java.util.ArrayList;

public final class Album extends AudioCollection {
    private int releaseYear;
    private String description;
    private ArrayList<Song> songs;
    private int albumTotalLikes = 0;

    public Album(final String artistName, final String albumName, final int releaseYear,
                 final String description, final ArrayList<Song> albumSongs) {
        super(albumName, artistName);
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = albumSongs;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     *
     * Gets index of song
     *
     * @param index the index
     * @return index
     */
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    public int getAlbumTotalLikes() {
        return albumTotalLikes;
    }

    public void setAlbumTotalLikes(final int albumTotalLikes) {
        this.albumTotalLikes = albumTotalLikes;
    }
}
