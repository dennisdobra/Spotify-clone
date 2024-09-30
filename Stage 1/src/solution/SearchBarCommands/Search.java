package solution.SearchBarCommands;

import solution.Input.ExecuteCommand;
import solution.Input.Filters;
import solution.Input.User;
import solution.LibrarySingleton;
import solution.Output.Output;
import solution.StatsUser.PodcastInfo;
import fileio.input.PlaylistInput;
import fileio.input.SongInput;
import fileio.input.PodcastInput;

import java.util.ArrayList;

public class Search extends ExecuteCommand {
    private String type;        // to check if it is a song, playlist, or podcast
    private Filters filters;    // filters for song, playlist, podcast
    private String message;
    // in results, I keep track of what I search: songs, podcasts, playlists
    private ArrayList<String> results = new ArrayList<>();
    private LibrarySingleton myLibrary = LibrarySingleton.getInstance();


    private static final Integer MAX_SIZE = 5;

    /* Constructor */
    public Search(final String command, final String username, final int timestamp,
                  final String type, final Filters filters) {
        super(command, username, timestamp);
        this.type = type;
        this.filters = filters;
    }

    /* Getters and Setters */
    public final void setType(final String type) {
        this.type = type;
    }

    public final String getType() {
        return type;
    }

    public final void setFilters(final Filters filters) {
        this.filters = filters;
    }

    public final Filters getFilters() {
        return filters;
    }

    public final ArrayList<String> getResults() {
        return results;
    }

    public final void setResults(final ArrayList<String> results) {
        this.results = results;
    }

    /**
     *
     * Method to search for a song from my library
     * based on certain criteria
     *
     * @return an output corresponding to the requirements
     * with a maximum of 5 results for the search
     */
    public final Output searchSong() {
        // Initialize an ArrayList with all songs
        ArrayList<SongInput> songs = new ArrayList<SongInput>(myLibrary.getSongs());

        // Now remove the songs that do not meet the filters
        if (filters.getName() != null) {
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if (!song.getName().startsWith(filters.getName())) {
                    songs.remove(i);
                }
            }
        }
        if (filters.getAlbum() != null) {
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if (!song.getAlbum().startsWith(filters.getAlbum())) {
                    songs.remove(i);
                }
            }
        }
        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if (!song.getTags().containsAll(filters.getTags())) {
                    songs.remove(i);
                }
            }
        }
        if (filters.getLyrics() != null) {
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if (!song.getLyrics().toLowerCase().contains(filters.getLyrics().toLowerCase())) {
                    songs.remove(i);
                }
            }
        }
        if (filters.getGenre() != null) {
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if (!song.getGenre().toLowerCase().contains(filters.getGenre().toLowerCase())) {
                    songs.remove(i);
                }
            }
        }
        if (filters.getReleaseYear() != null) {
            char sign = this.filters.getReleaseYear().charAt(0); // I remembered < or >
            int filterYear = Integer.parseInt(filters.getReleaseYear().substring(1));
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if ((sign == '<' && song.getReleaseYear() >= filterYear)
                    || (sign == '>' && song.getReleaseYear() <= filterYear)) {
                    songs.remove(i);
                }
            }
        }
        if (filters.getArtist() != null) {
            for (int i = songs.size() - 1; i >= 0; i--) {
                SongInput song = songs.get(i);
                if (!song.getArtist().equals(filters.getArtist())) {
                    songs.remove(i);
                }
            }
        }

        // Store the selected songs in searchedSongs
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());
        user.setLoadedSong(null);
        user.setLoadedPodcast(null);
        user.setLoadedPlaylist(null);
        user.setLoaded(false);

        user.setSearchedSongs(new ArrayList<>());
        for (int i = 0; i < songs.size(); i++) {
            this.results.add(songs.get(i).getName());
            user.getSearchedSongs().add(songs.get(i));
            if (results.size() == MAX_SIZE) {
                break;
            }
        }

        // Mark that I have done a search
        user.setSearched(true);

        Output output = new Output();
        output.setCommand("search");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());
        this.message = "Search returned " + results.size() + " results";
        output.setMessage(this.message);
        output.setResults(this.results);
        return output;
    }

    /**
     *
     * Method to search for a playlist based on the given filters
     *
     * @return an output of type Output containing the searched playlists
     */
    private Output searchPlaylist() {
        // Initialize an ArrayList with all my playlists
        ArrayList<PlaylistInput> playlists = new ArrayList<PlaylistInput>(myLibrary.getPlaylists());

        // Remove playlists that do not meet the filters
        if (filters.getName() != null) {
            for (int i = playlists.size() - 1; i >= 0; i--) {
                PlaylistInput playlist = playlists.get(i);
                if (!playlist.getName().startsWith(filters.getName())) {
                    playlists.remove(i);
                }
            }
        }
        if (filters.getOwner() != null) {
            for (int i = playlists.size() - 1; i >= 0; i--) {
                PlaylistInput playlist = playlists.get(i);
                if (!playlist.getOwner().equals(filters.getOwner())) {
                    playlists.remove(i);
                }
            }
        }
        for (int i = playlists.size() - 1; i >= 0; i--) {
            PlaylistInput playlist = playlists.get(i);
            if (playlist.getVisibilty().equals("private")) {
                playlists.remove(i);
            }
        }
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());
        user.setLoadedSong(null);
        user.setLoadedPodcast(null);
        user.setLoadedPlaylist(null);
        user.setLoaded(false);

        user.setSearchedPlaylists(new ArrayList<>());
        for (int i = 0; i < playlists.size(); i++) {
            this.results.add(playlists.get(i).getName());
            user.getSearchedPlaylists().add(playlists.get(i)); // Retain the entire structure
            if (results.size() == MAX_SIZE) {
                break;
            }
        }

        // Mark that a search has been performed
        user.setSearched(true);

        Output output = new Output();
        output.setCommand("search");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());
        this.message = "Search returned " + results.size() + " results";
        output.setMessage(this.message);
        output.setResults(this.results);
        return output;
    }


    /* Method to search for a podcast */
    private Output searchPodcast() {
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());
        ArrayList<PodcastInput> podcasts = new ArrayList<PodcastInput>(myLibrary.getPodcasts());

        // Remove podcasts that do not meet the filters
        if (filters.getName() != null) {
            for (int i = podcasts.size() - 1; i >= 0; i--) {
                PodcastInput podcast = podcasts.get(i);
                if (!podcast.getName().startsWith(filters.getName())) {
                    podcasts.remove(i);
                }
            }
        }
        if (filters.getOwner() != null) {
            for (int i = podcasts.size() - 1; i >= 0; i--) {
                PodcastInput podcast = podcasts.get(i);
                if (!podcast.getOwner().startsWith(filters.getOwner())) {
                    podcasts.remove(i);
                }
            }
        }

        user.setLoadedSong(null);
        user.setLoadedPodcast(null);
        user.setLoadedPlaylist(null);
        user.setLoaded(false);

        user.setSearchedPodcasts(new ArrayList<>());
        for (int i = 0; i < podcasts.size(); i++) {
            this.results.add(podcasts.get(i).getName());
            user.getSearchedPodcasts().add(podcasts.get(i)); // Retain the entire structure
            if (results.size() == MAX_SIZE) {
                break;
            }
        }

        // Mark that a search has been performed
        user.setSearched(true);

        Output output = new Output();
        output.setCommand("search");
        output.setUser(getUsername());
        output.setTimestamp(getTimestamp());
        this.message = "Search returned " + results.size() + " results";
        output.setMessage(this.message);
        output.setResults(this.results);
        return output;
    }

    /**
     *
     * Method to determine what type of audio I am searching for, 
     * which will then allow me to proceed with the corresponding search 
     * for the audio returned by this method.
     *
     * @return
     */
    public final Output search() {
        User user = LibrarySingleton.getInstance().searchUser(this.getUsername());
        if (user.isLoaded()) {
            if (user.verifyRunningAudioType().equals("podcast")) {
                if (user.isPlayPauseStatus()) {
                    user.setPassedTime(user.getPassedTime() + getTimestamp()
                            - user.getTimestampLastCommand());
                }
            }
        }
        if (user.getLoadedPodcast() != null) {
            PodcastInfo runningPodcast = new PodcastInfo();
            runningPodcast.setPodcast(user.getLoadedPodcast());
            runningPodcast.setListenedTime(user.getPassedTime());
            user.getRunningPodcast().add(runningPodcast);
        }
        return switch (type) {
            case "song" -> searchSong();
            case "podcast" -> searchPodcast();
            case "playlist" -> searchPlaylist();
            default -> null;
        };
    }
}
