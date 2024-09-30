package solution.Output;

import solution.Input.User;
import solution.SearchBarCommands.Select;
import com.fasterxml.jackson.annotation.JsonInclude;
import fileio.input.EpisodeInput;
import fileio.input.PlaylistInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public class Output {
    private String command;
    private String user;
    private int timestamp;
    private String message;
    private ArrayList<String> results;
    private Stats stats;
    private ArrayList<Object> result;

    /* Constructor */
    public Output() {
    }

    /* Getters and Setters */
    public final String getCommand() {
        return command;
    }

    public final void setCommand(final String command) {
        this.command = command;
    }

    public final String getUser() {
        return user;
    }

    public final void setUser(final String user) {
        this.user = user;
    }

    public final int getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    public final ArrayList<String> getResults() {
        return results;
    }

    public final void setResults(final ArrayList<String> results) {
        this.results = results;
    }

    public final Stats getStats() {
        return stats;
    }

    public final void setStats(final Stats stats) {
        this.stats = stats;
    }

    public final ArrayList<Object> getResult() {
        return result;
    }

    public final void setResult(final ArrayList<Object> result) {
        this.result = result;
    }

    /**
     *
     * method to set Stats
     *
     * @param time the time moment
     * @param myUser myUser
     */
    public final void setStatistics(final int time, final User myUser) {
        this.stats = new Stats();

        if (Select.getSong() != null) {
            SongInput song = Select.getSong();
            stats.setName(song.getName());

            if (song.getDuration() < myUser.getPassedTime()) {
                stats.setName("");
                stats.setRemainedTime(0);
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(true);
                return;
            }

            // check if it's paused or playing
            if (myUser.isPlayPauseStatus()) {
                stats.setRemainedTime(song.getDuration() - myUser.getPassedTime());
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(false);
            } else {
                stats.setRemainedTime(song.getDuration() - myUser.getPassedTime());
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(true);
            }

        } else if (Select.getPodcast() != null) {

            // iterate through episodes
            EpisodeInput currentEpisode = new EpisodeInput();
            int copy = myUser.getTimestampLastCommand() - myUser.getPassedTime();
            int copy2 = copy;
            int found = 0;
            for (EpisodeInput iter : myUser.getLoadedPodcast().getEpisodes()) {
                copy += iter.getDuration();
                if (copy >= getTimestamp()) {
                    currentEpisode = iter;
                    found = 1;
                    break;
                }
            }
            stats.setName(currentEpisode.getName());

            if (found == 0) {
                stats.setName("");
                stats.setRemainedTime(0);
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(true);
                return;
            }

            // check if it's paused or playing
            if (myUser.isPlayPauseStatus()) {
                stats.setRemainedTime(copy - copy2 - myUser.getPassedTime());
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(false);
            } else {
                stats.setRemainedTime(copy - copy2 - myUser.getPassedTime());
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(true);
            }
        } else if (Select.getPlaylist() != null) {
            SongInput currentSong = new SongInput();
            int copy = myUser.getTimestampLastCommand() - myUser.getPassedTime();
            int copy2 = copy;
            if (myUser.getLoadedPlaylist() != null) {
                for (SongInput iter : myUser.getLoadedPlaylist().getPlaylistSongs()) {
                    copy += iter.getDuration();
                    if (copy >= getTimestamp()) {
                        currentSong = iter;
                        break;
                    }
                }
            }

            stats.setName(currentSong.getName());

            if (copy <= getTimestamp()) {
                stats.setName("");
                stats.setRemainedTime(0);
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(true);
                return;
            }

            // check if it's paused or playing
            if (myUser.isPlayPauseStatus()) {
                stats.setRemainedTime(copy - copy2 - myUser.getPassedTime());
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(false);
            } else {
                stats.setRemainedTime(copy - copy2 - myUser.getPassedTime());
                stats.setRepeat("No Repeat");
                stats.setShuffle(false);
                stats.setPaused(true);
            }
        }
    }
}
