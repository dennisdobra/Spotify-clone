package solution.Input;

import solution.Output.Output;
import solution.PlayerCommands.Load;
import solution.PlayerCommands.Like;
import solution.PlayerCommands.AddRemoveInPlaylist;
import solution.PlayerCommands.Status;
import solution.PlayerCommands.PlayPause;
import solution.PlaylistCommands.CreatePlaylist;
import solution.PlaylistCommands.FollowPlaylist;
import solution.PlaylistCommands.ShowPlaylists;
import solution.PlaylistCommands.SwitchVisibility;
import solution.SearchBarCommands.Search;
import solution.SearchBarCommands.Select;
import solution.StatsUser.ShowPrefferedSongs;

public class ExecuteCommand {
    private String command;
    private String username;
    private int timestamp;

    private static Search search;

    /* constructors */
    public ExecuteCommand(final String command, final String username, final int timestamp) {
        this.command = command;
        this.username = username;
        this.timestamp = timestamp;
    }

    public ExecuteCommand() {
    }

    /* Getters and Setters */
    public final String getCommand() {
        return command;
    }

    public final void setCommand(final String command) {
        this.command = command;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final int getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    public static Search getSearch() {
        return search;
    }

    public static void setSearch(final Search search) {
        ExecuteCommand.search = search;
    }

    /**
     * In this method, I check which command needs to be executed, then I go to the class 
     * corresponding to the command that needs to be executed and call the appropriate method.
     *
     * @param input an input of the Input class type, where I keep track of the fields given from STDIN
     * @return the method corresponding to the command
     */
    public Output execute(final Input input) {
        if (this.command.equals("search")) {
            search = new Search(input.getCommand(), input.getUsername(), input.getTimestamp(),
                    input.getType(), input.getFilters());
            return search.search();
        } else if (this.command.equals("select")) {
            Select select = new Select(input.getCommand(), input.getUsername(),
                    input.getTimestamp(), input.getItemNumber());
            return select.select(search.getType(), search.getResults());
        } else if (this.command.equals("load")) {
            Load load = new Load(input.getCommand(), input.getUsername(), input.getTimestamp());
            return load.load();
        } else if (this.command.equals("playPause")) {
            PlayPause playPause = new PlayPause(input.getCommand(), input.getUsername(),
                    input.getTimestamp());
            return playPause.playPause();
        } else if (this.command.equals("repeat")) { // not yet implemented
            return null;
        } else if (this.command.equals("shuffle")) { // not yet implemented
            return null;
        } else if (this.command.equals("forward")) { // not yet implemented
            return null;
        } else if (this.command.equals("backward")) { // not yet implemented
            return null;
        } else if (this.command.equals("like")) {
            Like like = new Like(input.getCommand(), input.getUsername(), input.getTimestamp());
            return like.like();
        } else if (this.command.equals("next")) { // not yet implemented
            return null;
        } else if (this.command.equals("prev")) { // not yet implemented
            return null;
        } else if (this.command.equals("addRemoveInPlaylist")) {
            AddRemoveInPlaylist addRemoveInPlaylist = new AddRemoveInPlaylist(input.getCommand(),
                    input.getUsername(), input.getTimestamp(), input.getPlaylistId());
            return addRemoveInPlaylist.addRemoveInPlaylist();
        } else if (this.command.equals("status")) {
            Status status = new Status(input.getCommand(), input.getUsername(),
                    input.getTimestamp());
            return status.status();
        } else if (this.command.equals("createPlaylist")) {
            CreatePlaylist createPlaylist = new CreatePlaylist(input.getCommand(),
                    input.getUsername(), input.getTimestamp(), input.getPlaylistName());
            return createPlaylist.createPlaylist();
        } else if (this.command.equals("switchVisibility")) {
            SwitchVisibility switchVisibility = new SwitchVisibility(input.getCommand(),
                    input.getUsername(), input.getTimestamp(), input.getPlaylistId());
            return switchVisibility.switchVisibility();
        } else if (this.command.equals("follow")) {
            FollowPlaylist followPlaylist = new FollowPlaylist(input.getCommand(),
                    input.getUsername(), input.getTimestamp());
            return followPlaylist.followPlaylist();
        } else if (this.command.equals("showPlaylists")) {
            ShowPlaylists showPlaylists = new ShowPlaylists(input.getCommand(),
                    input.getUsername(), input.getTimestamp());
            return showPlaylists.showPlaylists();
        } else if (this.command.equals("showPreferredSongs")) {
            ShowPrefferedSongs showPrefferedSongs = new ShowPrefferedSongs(input.getCommand(),
                    input.getUsername(), input.getTimestamp());
            return showPrefferedSongs.showPrefferedSongs();
        } else if (this.command.equals("getTop5Songs")) { // not yet implemented
            return null;
        } else if (this.command.equals("getTop5Playlists")) { // not yet implemented
            return null;
        }
        return null;
    }
}
