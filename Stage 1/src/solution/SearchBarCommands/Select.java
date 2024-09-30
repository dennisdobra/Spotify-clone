package solution.SearchBarCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import fileio.input.PlaylistInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public class Select extends ExecuteCommand {
    private int itemNumber;
    private String message;
    public static SongInput song;
    public static PodcastInput podcast;
    public static PlaylistInput playlist;

    private final LibrarySingleton library = LibrarySingleton.getInstance();

    /* Constructor */
    public Select(final String command, final String username, final int timestamp,
                  final int itemNumber) {
        super(command, username, timestamp);
        this.itemNumber = itemNumber;
    }

    /* Getters and Setters */
    public final int getItemNumber() {
        return itemNumber;
    }

    public final void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public final void setSong(final SongInput song) {
        Select.song = song;
    }

    public static SongInput getSong() {
        return song;
    }

    public static void setPodcast(final PodcastInput podcast) {
        Select.podcast = podcast;
    }

    public static PodcastInput getPodcast() {
        return podcast;
    }

    public static PlaylistInput getPlaylist() {
        return playlist;
    }

    public static void setPlaylist(final PlaylistInput playlist) {
        Select.playlist = playlist;
    }


    /**
     *
     * With this method, I select the audio for which I conducted a search, 
     * in case a search has been made.
     *
     * @param results
     * @param type
     * @return
     */
    public final Output selectSomething(final ArrayList<String> results, final String type) {
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());

        // Check if a search has been conducted before
        if (!user.isSearched()) {
            Output output = new Output();
            output.setCommand("select");
            output.setUser(getUsername());
            output.setTimestamp(getTimestamp());
            this.message = "Please conduct a search before making a selection.";
            output.setMessage(this.message);
            return output;
        } else {
            if (itemNumber < 1 || itemNumber > results.size()) {
                Output output = new Output();
                output.setCommand("select");
                output.setUser(getUsername());
                output.setTimestamp(getTimestamp());
                this.message = "The selected ID is too high.";
                output.setMessage(this.message);
                return output;
            }

            String name = results.get(itemNumber - 1);
            if (type.equals("song")) {
                user.setSelectedSong(song);
                for (SongInput iter : library.getSongs()) {
                    if (iter.getName().equals(name)) {
                        Select.song = iter;
                        user.setSelectedSong(song);
                        break;
                    }
                }
                podcast = null;
                playlist = null;
            } else if (type.equals("podcast")) {
                this.setPodcast(podcast);
                for (PodcastInput iter : library.getPodcasts()) {
                    if (iter.getName().equals(name)) {
                        Select.podcast = iter;
                        user.setSelectedPodcast(podcast);
                        break;
                    }
                }
                song = null;
                playlist = null;
            } else if (type.equals("playlist")) {
                setPlaylist(playlist);
                for (PlaylistInput iter : LibrarySingleton.getInstance().getPlaylists()) {
                    if (iter.getName().equals(name)) {
                        Select.playlist = iter;
                        user.setSelectedPlaylist(playlist);
                        break;
                    }
                }
                song = null;
                podcast = null;
            }

            // Indicate that something has been selected
            user.setSelected(true);
            user.setSearched(false);

            Output output = new Output();
            output.setCommand("select");
            output.setUser(getUsername());
            output.setTimestamp(getTimestamp());
            this.message = "Successfully selected " + name + ".";
            output.setMessage(this.message);
            return output;
        }
    }

    /**
     *
     * With this method, I simply call the selectSomething function.
     *
     * @param type
     * @param results
     * @return
     */
    public final Output select(final String type, final ArrayList<String> results) {
        return selectSomething(results, type);
    }
}
