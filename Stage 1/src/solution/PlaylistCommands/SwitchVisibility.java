package solution.PlaylistCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;

public class SwitchVisibility extends ExecuteCommand {
    private int playlistId;
    private String message;

    /* Constructor */
    public SwitchVisibility(final String command, final String username,
                            final int timestamp, final int playlistId) {
        super(command, username, timestamp);
        this.playlistId = playlistId;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    /**
     *
     * method to switch the privacy status of a selected playlist
     *
     * @return output of type Output
     */
    public final Output switchVisibility() {
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());

        Output output = new Output();
        output.setCommand("switchVisibility");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        // Check if the specified playlist exists
        if (playlistId < 1 || playlistId > user.getPlaylists().size()) {
            output.setMessage("The specified playlist does not exist.");
            return output;
        }

        // Adjust for zero-based index
        playlistId = playlistId - 1;

        // Toggle the visibility of the playlist
        if (user.getPlaylists().get(playlistId).getVisibilty().equals("public")) {
            user.getPlaylists().get(playlistId).setVisibilty("private");
            this.message = "Visibility status updated successfully to private.";
            output.setMessage(this.message);
            return output;
        } else {
            user.getPlaylists().get(playlistId).setVisibilty("public");
            this.message = "Visibility status updated successfully to public.";
            output.setMessage(this.message);
            return output;
        }
    }
}
