package fileio.input;

import java.util.ArrayList;

public class PlaylistInput {
    private String name;
    private String owner;
    private ArrayList<SongInput> playlistSongs;
    private String visibilty;
    private int followers = 0;

    public PlaylistInput(final String name, final String owner,
                          final ArrayList<SongInput> playlistSongs, final String visibility) {
        this.name = name;
        this.owner = owner;
        this.playlistSongs = playlistSongs;
        this.visibilty = visibility;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getOwner() {
        return owner;
    }

    public final void setOwner(final String owner) {
        this.owner = owner;
    }

    public final ArrayList<SongInput> getPlaylistSongs() {
        return playlistSongs;
    }

    public final String getVisibilty() {
        return visibilty;
    }

    public final void setVisibilty(final String visibilty) {
        this.visibilty = visibilty;
    }

    public final int getFollowers() {
        return followers;
    }

    public final void setFollowers(final int followers) {
        this.followers = followers;
    }
}
