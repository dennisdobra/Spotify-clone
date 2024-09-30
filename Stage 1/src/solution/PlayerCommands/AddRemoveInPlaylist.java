package solution.PlayerCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import fileio.input.SongInput;

public class AddRemoveInPlaylist extends ExecuteCommand {
    private int playlistId;

    /* Constructor */
    public AddRemoveInPlaylist(final String command, final String username,
                               final int timestamp, final int playlistId) {
        super(command, username, timestamp);
        this.playlistId = playlistId;
    }

    /* Getter and Setter */
    public final int getPlaylistId() {
        return playlistId;
    }

    public final void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

    /**
     *
     * method to add or remove a song
     * in the playlist based on the given criteria
     *
     * @return output
     */
    public final Output addRemoveInPlaylist() {
        User user = LibrarySingleton.getInstance().searchUser(getUsername());

        Output output = new Output();
        output.setCommand("addRemoveInPlaylist");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        // check if the playlist with playlistId exists
        if (playlistId < 1 || playlistId > user.getPlaylists().size()) {
            output.setMessage("The specified playlist does not exist.");
            return output;
        }

        playlistId = playlistId - 1;
        // check if the user has loaded something before
        if (!user.isLoaded()) {
            output.setMessage("Please load a source before adding to"
                    + " or removing from the playlist.");
            return output;
        }

        // check if a song or something else is loaded -> it must be a song
        if (user.getLoadedSong() == null)  {
            output.setMessage("The loaded source is not a song.");
            return output;
        }

        SongInput currentSong = user.getLoadedSong();
        // check if the playlist is empty
        if (user.getPlaylists().get(playlistId).getPlaylistSongs().isEmpty()) {
            user.getPlaylists().get(playlistId).getPlaylistSongs().add(currentSong);
            output.setMessage("Successfully added to playlist.");
            return output;
        }

        // check if the song already exists
        // if it exists, remove it
        // if it doesn't exist, add it
        for (SongInput song : user.getPlaylists().get(playlistId).getPlaylistSongs()) {
            if (currentSong.equals(song)) {
                // means I found it => remove it
                user.getPlaylists().get(playlistId).getPlaylistSongs().remove(song);
                output.setMessage("Successfully removed from playlist.");
                return output;
            }
        }
        // means I didn't find it => add it
        user.getPlaylists().get(playlistId).getPlaylistSongs().add(currentSong);
        output.setMessage("Successfully added to playlist.");
        return output;
    }
}
