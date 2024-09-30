package solution.PlayerCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import fileio.input.SongInput;

import java.util.ArrayList;

public class Like extends ExecuteCommand {
    private boolean likeStatus = false;
    private ArrayList<SongInput> likedSongs = new ArrayList<>();

    public Like(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    public final boolean isLikeStatus() {
        return likeStatus;
    }

    public final void setLikeStatus(final boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public final ArrayList<SongInput> getLikedSongs() {
        return likedSongs;
    }

    public final void setLikedSongs(final ArrayList<SongInput> likedSongs) {
        this.likedSongs = likedSongs;
    }

    /**
     *
     * method called when I need to like
     * an audio based on the given criteria
     *
     * @return output of type Output with the corresponding result
     */
    public final Output like() {
        User user = LibrarySingleton.getInstance().searchUser(getUsername());

        Output output = new Output();
        output.setCommand("like");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        // check if something has been loaded
        if (!user.isLoaded()) {
            output.setMessage("Please load a source before liking or unliking.");
            return output;
        }

        // check if a song has been loaded
        if (user.getLoadedSong() == null && user.getLoadedPlaylist() == null)  {
            output.setMessage("The loaded source is not a song.");
            return output;
        }

        SongInput currentSong = new SongInput();
        if (user.getLoadedSong() != null) {
            currentSong = user.getLoadedSong();
        } else if (user.getLoadedPlaylist() != null) {
            int copy = user.getTimestampLastCommand();
            for (SongInput iter : user.getLoadedPlaylist().getPlaylistSongs()) {
                copy += iter.getDuration();
                if (copy >= getTimestamp()) {
                    currentSong = iter;
                }
            }
        }

        // check if the song has been liked or not
        // check if it's in my ArrayList: likedSongs
        if (user.getLikedSongs().isEmpty()) {
            user.getLikedSongs().add(currentSong);
            output.setMessage("Like registered successfully.");
            return output;
        }

        // otherwise, search through the list
        for (SongInput song : user.getLikedSongs()) {
            if (currentSong.equals(song)) {
                // means it has been liked => remove it
                user.getLikedSongs().remove(song);
                output.setMessage("Unlike registered successfully.");
                user.getLikedSongs().remove(currentSong);
                return output;
            }
        }
        output.setMessage("Like registered successfully.");
        user.getLikedSongs().add(currentSong);
        return output;
    }
}
