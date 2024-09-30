package solution.Input;

import java.util.ArrayList;

public class Filters {
    /* filters by which a: song, podcast, or playlist can be searched */
    private String name;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private String releaseYear;
    private String artist;
    private String owner;

    /* constructor */
    public Filters() { }

    /* Getters and Setters */
    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getAlbum() {
        return album;
    }

    public final ArrayList<String> getTags() {
        return tags;
    }

    public final void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    public final String getLyrics() {
        return lyrics;
    }

    public final String getGenre() {
        return genre;
    }

    public final String getReleaseYear() {
        return releaseYear;
    }

    public final String getArtist() {
        return artist;
    }

    public final void setArtist(final String artist) {
        this.artist = artist;
    }

    public final String getOwner() {
        return owner;
    }

    public final void setOwner(final String owner) {
        this.owner = owner;
    }
}
