package solution.PlaylistCommands;

import solution.Input.ExecuteCommand;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import fileio.input.PlaylistInput;

public class FollowPlaylist extends ExecuteCommand {
    private String message;

    public FollowPlaylist(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    /**
     *
     * method to follow a playlist,
     * respecting the given conditions
     *
     * @return output of type Output
     */
    public final Output followPlaylist() {
        User user = LibrarySingleton.getInstance().searchUser(getUsername());

        Output output = new Output();
        output.setCommand("follow");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());

        if (!user.isSelected()) {
            this.message = "Please select a source before following or unfollowing.";
            output.setMessage(this.message);
            return output;
        }

        if (user.getSelectedPlaylist() == null) {
            this.message = "The selected source is not a playlist.";
            output.setMessage(this.message);
            return output;
        }

        // Check if it is the user's own playlist
        for (PlaylistInput playlist : user.getPlaylists()) {
            if (playlist == user.getSelectedPlaylist()) {
                this.message = "You cannot follow or unfollow your own playlist.";
                output.setMessage(this.message);
                return output;
            }
        }

        if (user.getSelectedPlaylist().getVisibility().equals("public")) {
            if (user.getFollowedPlaylists().isEmpty()) {
                // User has no followed playlists => add the selected one
                user.getFollowedPlaylists().add(user.getSelectedPlaylist());
                user.getSelectedPlaylist().setFollowers(user.getSelectedPlaylist().getFollowers() + 1);
                this.message = "Playlist followed successfully.";
                output.setMessage(this.message);
                return output;
            }

            for (PlaylistInput playlist : user.getFollowedPlaylists()) {
                if (playlist == user.getSelectedPlaylist()) {
                    // User is already following this playlist => unfollow it
                    user.getFollowedPlaylists().remove(user.getSelectedPlaylist());
                    user.getSelectedPlaylist().setFollowers(user.getSelectedPlaylist().getFollowers() - 1);
                    this.message = "Playlist unfollowed successfully.";
                    output.setMessage(this.message);
                    return output;
                }
            }
            // Automatically add the selected playlist
            user.getFollowedPlaylists().add(user.getSelectedPlaylist());
            user.getSelectedPlaylist().setFollowers(user.getSelectedPlaylist().getFollowers() + 1);
            this.message = "Playlist followed successfully.";
            output.setMessage(this.message);
            return output;
        }
        return null;
    }
}
