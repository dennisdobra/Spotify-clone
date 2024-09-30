package solution.PlaylistCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import solution.Output.Result;
import fileio.input.PlaylistInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public class ShowPlaylists extends ExecuteCommand {
    public ShowPlaylists(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    /**
     *
     * method to display a playlist with all
     * the songs it contains
     *
     * @return output of type Output
     */
    public final Output showPlaylists() {
        User user = LibrarySingleton.getInstance().searchUser(getUsername());

        Output output = new Output();
        output.setCommand("showPlaylists");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        ArrayList<Object> myPlaylists = new ArrayList<>();
        for (PlaylistInput iter : user.getPlaylists()) {
            Result result = new Result();
            result.setName(iter.getName());         // set the name of the playlist

            result.setSongs(new ArrayList<>());     // initialize an ArrayList for the names
                                                    // of the songs in the current playlist
            for (SongInput iterSong : iter.getPlaylistSongs()) {
                result.getSongs().add(iterSong.getName());
            }

            result.setVisibility(iter.getVisibility()); // correct spelling from 'Visibilty' to 'Visibility'
            result.setFollowers(iter.getFollowers());
            myPlaylists.add(result);
        }
        output.setResult(myPlaylists);

        return output;
    }
}
