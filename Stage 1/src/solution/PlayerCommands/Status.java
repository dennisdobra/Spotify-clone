package solution.PlayerCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import solution.Output.Stats;
import solution.SearchBarCommands.Select;

public class Status extends ExecuteCommand {
    private Stats stats = new Stats();

    /* Constructors */
    public Status(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    /* Getter and Setter */
    public final Stats getStats() {
        return stats;
    }

    public final void setStats(final Stats stats) {
        this.stats = stats;
    }

    /**
     *
     * method to see the status of the current audio
     *
     * @return output of type Output
     */
    public final Output status() {
        User myUser = LibrarySingleton.getInstance().searchUser(getUsername());

        // Update the passed time if the audio is currently playing
        if (myUser.isPlayPauseStatus()) {
            myUser.setPassedTime(myUser.getPassedTime() + getTimestamp()
                    - myUser.getTimestampLastCommand());
        }
        myUser.setTimestampLastCommand(getTimestamp());

        Output output = new Output();
        output.setCommand("status");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        // Set statistics based on what is currently selected
        if (Select.song != null && Select.podcast == null && Select.playlist == null) {
            output.setStatistics(this.getTimestamp(), myUser);
        } else if (Select.song == null && Select.podcast != null && Select.playlist == null) {
            output.setStatistics(this.getTimestamp(), myUser);
        } else if (Select.song == null && Select.podcast == null && Select.playlist != null) {
            output.setStatistics(this.getTimestamp(), myUser);
        }
        
        return output;
    }
}
