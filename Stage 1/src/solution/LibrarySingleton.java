package solution;

import solution.Input.User;
import fileio.input.SongInput;
import fileio.input.PodcastInput;
import fileio.input.PlaylistInput;
import fileio.input.UserInput;
import fileio.input.LibraryInput;

import java.util.ArrayList;

// Singleton class for LibraryInput
public final class LibrarySingleton {
    // Private static instance
    private static LibrarySingleton instance = null;

    private ArrayList<SongInput> songs;
    private ArrayList<PodcastInput> podcasts;
    private ArrayList<PlaylistInput> playlists;
    private ArrayList<UserInput> users;
    private ArrayList<User> activeUsers;

    // Private constructor
    private LibrarySingleton() {
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        users = new ArrayList<>();
        activeUsers = new ArrayList<>();
        playlists = new ArrayList<>();
    }

    /**
     *
     * A public static method to get the instance
     *
     * @return instance
     */
    public static LibrarySingleton getInstance() {
        if (instance == null) {
            instance = new LibrarySingleton();
        }
        return instance;
    }

    /**
     *
     * Initialization method
     *
     * @param myLibrary my library
     */
    public void init(final LibraryInput myLibrary) {
        activeUsers.clear();
        this.users = myLibrary.getUsers();
        this.podcasts = myLibrary.getPodcasts();
        this.songs = myLibrary.getSongs();
        this.playlists.clear();
    }

    // Getters and Setters
    public ArrayList<SongInput> getSongs() {
        return songs;
    }

    public void setSongs(final ArrayList<SongInput> songs) {
        this.songs = songs;
    }

    public ArrayList<PodcastInput> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(final ArrayList<PodcastInput> podcasts) {
        this.podcasts = podcasts;
    }

    public ArrayList<UserInput> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<UserInput> users) {
        this.users = users;
    }

    public ArrayList<PlaylistInput> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(final ArrayList<PlaylistInput> playlists) {
        this.playlists = playlists;
    }

    /**
     *
     * I check if the user with the given username has issued
     * any commands before and I return the user
     *
     * @param userName the username of the user
     * @return user
     */
    public User searchUser(final String userName) {
        for (User i : activeUsers) {
            if (i.getUserName().equals(userName)) {
                return i;
            }
        }
        User user = new User();
        user.setUserName(userName);
        activeUsers.add(user);
        return user;
    }
}
