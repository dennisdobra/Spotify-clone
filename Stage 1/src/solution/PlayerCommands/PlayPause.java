package solution.PlayerCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;

public class PlayPause extends ExecuteCommand {
    private String message;
    private static boolean playStatus;

    /* Constructors */
    public PlayPause() {
        super();
    }

    public PlayPause(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    /* Getter and Setter */
    public static boolean isSongStatus() {
        return playStatus;
    }

    public static void setSongStatus(final boolean songStatus) {
        PlayPause.playStatus = songStatus;
    }

    /**
     *
     * method to play or pause an audio
     *
     * @return output of type Output
     */
    public final Output playPause() {
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());

        int copyTimestamp = user.getTimestampLastCommand();
        user.setTimestampLastCommand(getTimestamp());

        if (user.isLoaded()) {
            if (playStatus) {
                user.setPassedTime(user.getPassedTime() + getTimestamp() - copyTimestamp);
                playStatus = false; // pause the playback
                user.setPlayPauseStatus(false);
                Output output = new Output();
                output.setCommand("playPause");
                output.setUser(getUsername());
                output.setTimestamp(getTimestamp());
                this.message = "Playback paused successfully.";
                output.setMessage(this.message);
                return output;
            } else {
                playStatus = true; // resume playback
                user.setPlayPauseStatus(true);
                Output output = new Output();
                output.setCommand("playPause");
                output.setUser(getUsername());
                output.setTimestamp(getTimestamp());
                this.message = "Playback resumed successfully.";
                output.setMessage(this.message);
                return output;
            }
        } else {
            Output output = new Output();
            output.setCommand("playPause");
            output.setUser(getUsername());
            output.setTimestamp(getTimestamp());
            this.message = "Please load a source before attempting to pause or resume playback.";
            output.setMessage(this.message);
            return output;
        }
    }
}
