package app;

import app.PageSystem.ArtistPage;
import app.PageSystem.HomePage;
import app.PageSystem.HostPage;
import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
    @Getter
    private List<User> users = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();
    private List<Podcast> podcasts = new ArrayList<>();
    private int timestamp = 0;
    private final int LIMIT = 5;

    @Getter
    private ArrayList<User> artists = new ArrayList<>();
    @Getter
    private List<User> hosts = new ArrayList<>();

    private static Admin instance = new Admin();

    public static Admin getInstance() {
        return  instance;
    }

    private Admin() {
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            User user = new User(userInput.getUsername(), userInput.getAge(),
                    userInput.getCity(), userInput.getType());
            user.setPage(new HomePage());
            user.getPage().setUser(user);
            users.add(user);
        }
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     *
     * Adds Song
     *
     * @param song
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     *
     * Removes Podcast
     *
     * @param podcast
     */
    public void removePodcast(final Podcast podcast) {
        podcasts.remove(podcast);
    }

    /**
     *
     * Adds Podcast
     *
     * @param podcast
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * Gets artist
     *
     * @param username artist's username
     * @return the artist
     */
    public User getArtist(final String username) {
        for (User artist : artists) {
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    /**
     *
     * Gets host
     *
     * @param username host's username
     * @return the host
     */
    public User getHost(final String username) {
        for (User host : hosts) {
            if (host.getUsername().equals(username)) {
                return host;
            }
        }
        return null;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * Reset.
     */
    public void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        timestamp = 0;
    }

    /**
     *
     * @return all users that are online
     */
    public List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();

        for (User user : users) {
            if (user.isOnline()) {
                onlineUsers.add(user.getUsername());
            }
        }
        return onlineUsers;
    }

    /**
     *
     * @param username username of new user
     * @param age age of new user
     * @param city city of new user
     * @param type type of new user
     * @return new user
     */
    public String addUser(final String username, final int age,
                                 final String city, final String type) {
        User newUser = new User(username, age, city, type);
        String message;

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                message = "The username " + username + " is already taken.";
                return message;
            }
        }

        for (User artist : artists) {
            if (artist.getUsername().equals(username)) {
                message = "The username " + username + " is already taken.";
                return message;
            }
        }

        for (User host : hosts) {
            if (host.getUsername().equals(username)) {
                message = "The username " + username + " is already taken.";
                return message;
            }
        }

        if ("user".equals(type)) {
            newUser = new User(username, age, city, "user");
            newUser.setPage(new HomePage());
            newUser.getPage().setUser(newUser);
            users.add(newUser);
        } else if ("artist".equals(type)) {
            newUser = new Artist(username, age, city, "artist");
            newUser.setPage(new ArtistPage());
            newUser.getPage().setUser(newUser);
            artists.add(newUser);
        } else if ("host".equals(type)) {
            newUser = new Host(username, age, city, "host");
            newUser.setPage(new HostPage());
            newUser.getPage().setUser(newUser);
            hosts.add(newUser);
        }

        message = "The username " + username + " has been added successfully.";
        return message;
    }

    /**
     *
     * @param username for which I want to show albums
     * @return albums for the given username
     */
    public List<AlbumOutput> showAlbums(final String username) {
        List<AlbumOutput> albums = new ArrayList<>();

        for (User artist : artists) {
            if (artist.getUsername().equals(username)) {
                // found the artist for which I want
                // to display the albums

                for (Album album : ((Artist) artist).getArtistAlbums()) {
                    AlbumOutput albumOutput = new AlbumOutput();
                    albumOutput.setName(album.getName());

                    for (Song song : album.getSongs()) {
                        albumOutput.getSongs().add(song.getName());
                    }
                    albums.add(albumOutput);
                }
            }
        }

        return albums;
    }

    /**
     *
     * @return All existing albums
     */
    public List<Album> getAllAlbums() {
        List<Album> albumList = new ArrayList<>();

        for (User artist : artists) {
            for (Album album : ((Artist) artist).getArtistAlbums()) {
                albumList.add(album);
            }
        }

        return albumList;
    }

    /**
     *
     * @return Top 5 Albums considering the number of likes
     */
    public List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(getAllAlbums());

        for (Album album : sortedAlbums) {
            for (Song song : album.getSongs()) {
                album.setAlbumTotalLikes(album.getAlbumTotalLikes() + song.getLikes());
            }
        }

        // sort the albums based on the number of likes they have
        Collections.sort(sortedAlbums,
                Comparator.comparingInt(Album::getAlbumTotalLikes).reversed()
                        .thenComparing(Album::getName, String.CASE_INSENSITIVE_ORDER));

        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     *
     * @return All users
     */
    public ArrayList<String> getAllUsers() {
        ArrayList<String> allUsers = new ArrayList<>();

        for (User user : users) {
            allUsers.add(user.getUsername());
        }

        for (User artist : artists) {
            allUsers.add(artist.getUsername());
        }

        for (User host : hosts) {
            allUsers.add(host.getUsername());
        }

        return allUsers;
    }

    /**
     *
     * @param deletedUser user to be deleted
     * @return message depending on whether user can be deleted
     */
    public String deleteUser(final String deletedUser) {
        User user = getUser(deletedUser);
        User artist = getArtist(deletedUser);
        User host = getHost(deletedUser);

        for (User iter : users) {
            if (artist != null && iter.getPage().getUser().equals(artist)) {
                return deletedUser + " can't be deleted.";
            }
            if (host != null && iter.getPage().getUser().equals(host)) {
                return deletedUser + " can't be deleted.";
            }

            if (iter.getPlayer().getSource() != null) {   // check if it is listening to something
                if (iter.getPlayer().getSource().getAudioCollection() != null) {
                    if (iter.getPlayer().getSource().getAudioCollection().
                            matchesOwner(deletedUser)) {
                        return deletedUser + " can't be deleted.";
                    }
                    for (int i = 0; i < iter.getPlayer().getSource().getAudioCollection().
                            getNumberOfTracks(); i++) {
                        if (iter.getPlayer().getSource().getAudioCollection().getTrackByIndex(i).
                                matchesArtist(deletedUser)) {
                            return deletedUser + " can't be deleted.";
                        }
                    }
                } else if (iter.getPlayer().getSource().getAudioFile().matchesArtist(deletedUser)) {
                    return deletedUser + " can't be deleted.";
                }
            }
        }

        if (user != null) {
            // delete his playlists
            for (User iter : users) {
                for (Playlist playlist : user.getPlaylists()) {
                    if (iter.getFollowedPlaylists().contains(playlist)) {
                        iter.getFollowedPlaylists().remove(playlist);
                    }
                }
            }
            for (Playlist playlist : user.getFollowedPlaylists()) {
                playlist.decreaseFollowers();
            }
            user.getPlaylists().clear();
            users.remove(user);
        } else if (artist != null) {
            for (Album album : ((Artist) artist).getArtistAlbums()) {
                for (Song song : album.getSongs()) {
                    songs.remove(song);
                }
            }

            for (User myuser : users) {
                for (Album album : ((Artist) artist).getArtistAlbums()) {
                    for (Song song : album.getSongs()) {
                        myuser.getLikedSongs().remove(song);
                    }
                }
            }
            artists.remove(artist);
        } else if (host != null) {
            hosts.remove(host);
        }
        return deletedUser + " was successfully deleted.";
    }

    /**
     *
     * Gets top 5 artists.
     *
     * @return Top 5 artists
     */
    public ArrayList<String> getTop5Artists() {
        ArrayList<User> sortedArtists = new ArrayList<>();
        for (User artist : artists) {
            sortedArtists.add(artist);
        }

        for (User artist : sortedArtists) {
            for (Album album : ((Artist) artist).getArtistAlbums()) {
                for (Song song : album.getSongs()) {
                    album.setAlbumTotalLikes(album.getAlbumTotalLikes() + song.getLikes());
                    ((Artist) artist).setArtistTotalLikes(((Artist) artist).getArtistTotalLikes()
                            + song.getLikes());
                }
            }
        }

        ArrayList<Artist> sortedArtistsList = new ArrayList<>();
        for (User artist : sortedArtists) {
            sortedArtistsList.add((Artist) artist);
        }
        // sort the albums based on the number of likes they have
        Collections.sort(sortedArtistsList,
                Comparator.comparingInt(Artist::getArtistTotalLikes).reversed()
                        .thenComparing(Artist::getUsername, String.CASE_INSENSITIVE_ORDER));

        ArrayList<String> topArtists = new ArrayList<>();
        int count = 0;
        for (User artist : sortedArtistsList) {
            if (count >= LIMIT) {
                break;
            }
            topArtists.add(artist.getUsername());
            count++;
        }
        return topArtists;
    }
}
