package solution.StatsUser;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import fileio.input.SongInput;

import java.util.ArrayList;

public class ShowPrefferedSongs extends ExecuteCommand {
    // in result o sa tin minte song-urile la care dau like
    private ArrayList<String> result = new ArrayList<>();
    private LibrarySingleton myLibrary = LibrarySingleton.getInstance();

    public ShowPrefferedSongs(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    public final ArrayList<String> getResult() {
        return result;
    }

    public final void setResult(final ArrayList<String> result) {
        this.result = result;
    }


    /**
     *
     * metoda pentru a afisa melodiile la cade userul a dat like
     *
     * @return
     */
    public final Output showPrefferedSongs() {
        User user = LibrarySingleton.getInstance().searchUser(getUsername());

        Output output = new Output();
        output.setResult(new ArrayList<>());
        for (SongInput song : user.getLikedSongs()) {
            output.getResult().add(song.getName());
        }


        output.setCommand("showPreferredSongs");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());
        return output;
    }
}
