package solution.PlaylistCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import fileio.input.PlaylistInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public class CreatePlaylist extends ExecuteCommand {
    private String playlistName;

    /* Constructor */
    public CreatePlaylist(final String command, final String username, final int timestamp,
                          final String playlistName) {
        super(command, username, timestamp);
        this.playlistName = playlistName;
    }

    /* Getter and Setter */
    public final String getPlaylistName() {
        return playlistName;
    }

    public final void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     *
     * method to create a playlist, taking into account
     * the specified restrictions
     *
     * @return corresponding output of type Output
     */
    public final Output createPlaylist() {
        User user = LibrarySingleton.getInstance().searchUser(getUsername());

        Output output = new Output();
        output.setCommand("createPlaylist");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        // Check if a playlist with the same name already exists
        for (PlaylistInput playlist : user.getPlaylists()) {
            if (playlist.getName().equals(playlistName)) {
                output.setMessage("A playlist with the same name already exists.");
                return output;
            }
        }

        // Otherwise, create a new one
        ArrayList<SongInput> songs = new ArrayList<>();
        user.getPlaylists().add(new PlaylistInput(playlistName, user.getUserName(),
                songs, "public"));
        LibrarySingleton.getInstance().getPlaylists().add(user.getPlaylists().
                get(user.getPlaylists().size() - 1));

        output.setMessage("Playlist created successfully.");
        return output;
    }
}
