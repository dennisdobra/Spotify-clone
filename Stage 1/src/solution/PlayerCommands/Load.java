package solution.PlayerCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import solution.SearchBarCommands.Select;
import solution.StatsUser.PodcastInfo;

public class Load extends ExecuteCommand {
    private String message;

    /* Constructor */
    public Load(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    /**
     *
     * method to load the audio that has been selected
     *
     * @return an output of type Output for the load command
     */
    public final Output load() {
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());

        // check if something has been selected
        if (user.getSelectedSong() != null || user.getSelectedPodcast() != null
                || user.getSelectedPlaylist() != null) {
            user.setPassedTime(0);
            user.setTimestampLastCommand(getTimestamp());

            if (Select.getSong() != null) {
                user.setLoadedSong(user.getSelectedSong());
            } else if (Select.getPodcast() != null) {
                // check if I need to set the time from where it was
                if (user.getRunningPodcast() != null) {
                    for (PodcastInfo currPodcast : user.getRunningPodcast()) {
                        if (currPodcast.getPodcast() == user.getSelectedPodcast()) {
                            user.setPassedTime(currPodcast.getListenedTime());
                        }
                    }
                }
                user.setLoadedPodcast(user.getSelectedPodcast());
            } else if (Select.getPlaylist() != null) {
                user.setLoadedPlaylist(user.getSelectedPlaylist());
            }

            /* for both Song, Podcast, and Playlist */
            user.setLoadTime(this.getTimestamp());  // set the moment when loading occurs
            user.setTimestampLastCommand(this.getTimestamp());
            user.setLoaded(true);                   // mark that loading has occurred
            user.setPlayPauseStatus(true);
            PlayPause.setSongStatus(true);

            Output output = new Output();
            output.setCommand("load");
            output.setUser(getUsername());
            output.setTimestamp(getTimestamp());
            this.message = "Playback loaded successfully.";
            user.setSelected(false);
            user.setSelectedSong(null);
            user.setSelectedPlaylist(null);
            user.setSelectedPodcast(null);
            output.setMessage(this.message);
            return output;
        } else {
            PlayPause.setSongStatus(false);

            Output output = new Output();
            output.setCommand("load");
            output.setUser(getUsername());
            output.setTimestamp(getTimestamp());
            this.message = "Please select a source before attempting to load.";
            output.setMessage(this.message);
            return output;
        }
    }
}
