package solution.Output;

import java.util.ArrayList;

public class Result {
    private String name;
    private ArrayList<String> songs;
    private String visibility;
    private int followers;

    /* Constructor */
    public Result() {
    }

    /* Getters and Setters */
    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final ArrayList<String> getSongs() {
        return songs;
    }

    public final void setSongs(final ArrayList<String> songs) {
        this.songs = songs;
    }

    public final String getVisibility() {
        return visibility;
    }

    public final void setVisibility(final String visibility) {
        this.visibility = visibility;
    }

    public final int getFollowers() {
        return followers;
    }

    public final void setFollowers(final int followers) {
        this.followers = followers;
    }
}
