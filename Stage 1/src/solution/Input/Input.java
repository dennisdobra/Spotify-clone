package solution.Input;

import solution.Output.Output;

public class Input {
    private String command;
    private String username;
    private int timestamp;
    private String type;
    private Filters filters;
    private int itemNumber;
    private int seed;

    private String user;
    private int playlistId;
    private String playlistName;
    private String message;

    /* constructors */
    public Input() {
    }

    /**
     * the method I will call when displaying an
     * Output for a given command
     *
     * @return the Output of the command
     */
    public final Output execute() {
        return null;
    }


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

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final Filters getFilters() {
        return filters;
    }

    public final void setFilters(final Filters filters) {
        this.filters = filters;
    }

    public final int getItemNumber() {
        return itemNumber;
    }

    public final void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public final int getSeed() {
        return seed;
    }

    public final void setSeed(final int seed) {
        this.seed = seed;
    }

    public final String getUser() {
        return user;
    }

    public final void setUser(final String user) {
        this.user = user;
    }

    public final int getPlaylistId() {
        return playlistId;
    }

    public final void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

    public final String getPlaylistName() {
        return playlistName;
    }

    public final void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }
}
