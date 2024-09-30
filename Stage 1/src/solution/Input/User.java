package solution.Input;

import solution.StatsUser.PodcastInfo;
import fileio.input.PlaylistInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public class User {
    private String username;
    private int loadTime;       // the moment when load will be executed
    private int passedTime;
    private int timestampLastCommand;   // the moment when the last command is executed
    private SongInput song;
    private PodcastInput podcast;
    private PlaylistInput playlist;


    private boolean searched = false;
    private boolean selected = false;
    private boolean loaded = false;
    private boolean playPauseStatus;

    /* for songs */
    private ArrayList<SongInput> searchedSongs;      // searched songs
    private SongInput selectedSong;                  // selected song
    private SongInput loadedSong;                    // loaded song

    /* for podcasts */
    private ArrayList<PodcastInput> searchedPodcasts;
    private PodcastInput selectedPodcast;
    private PodcastInput loadedPodcast;
    private ArrayList<PodcastInfo> runningPodcast = new ArrayList<>();

    /* for playlists */
    private ArrayList<PlaylistInput> searchedPlaylists;
    private PlaylistInput selectedPlaylist;
    private PlaylistInput loadedPlaylist;

    private ArrayList<PlaylistInput> playlists = new ArrayList<>();
    private ArrayList<SongInput> likedSongs = new ArrayList<>();

    private ArrayList<PlaylistInput> followedPlaylists = new ArrayList<>();

    public  User() {
    }

    /* Getters and Setters */
    public final String getUserName() {
        return username;
    }

    public final void setUserName(final String userName) {
        this.username = userName;
    }

    public final int getLoadTime() {
        return loadTime;
    }

    public final void setLoadTime(final int loadTime) {
        this.loadTime = loadTime;
    }

    public final SongInput getSong() {
        return song;
    }

    public final void setSong(final SongInput song) {
        this.song = song;
    }

    public final PodcastInput getPodcast() {
        return podcast;
    }

    public final void setPodcast(final PodcastInput podcast) {
        this.podcast = podcast;
    }

    public final boolean isSearched() {
        return searched;
    }

    public final void setSearched(final boolean searched) {
        this.searched = searched;
    }

    public final ArrayList<SongInput> getSearchedSongs() {
        return searchedSongs;
    }

    public final void setSearchedSongs(final ArrayList<SongInput> searchedSongs) {
        this.searchedSongs = searchedSongs;
    }

    public final SongInput getSelectedSong() {
        return selectedSong;
    }

    public final void setSelectedSong(final SongInput selectedSong) {
        this.selectedSong = selectedSong;
    }

    public final boolean isLoaded() {
        return loaded;
    }

    public final void setLoaded(final boolean loaded) {
        this.loaded = loaded;
    }

    public final ArrayList<PodcastInput> getSearchedPodcasts() {
        return searchedPodcasts;
    }

    public final void setSearchedPodcasts(final ArrayList<PodcastInput> searchedPodcasts) {
        this.searchedPodcasts = searchedPodcasts;
    }

    public final PodcastInput getSelectedPodcast() {
        return selectedPodcast;
    }

    public final void setSelectedPodcast(final PodcastInput selectedPodcast) {
        this.selectedPodcast = selectedPodcast;
    }

    public final boolean isSelected() {
        return selected;
    }

    public final void setSelected(final boolean selected) {
        this.selected = selected;
    }

    public final SongInput getLoadedSong() {
        return loadedSong;
    }

    public final void setLoadedSong(final SongInput loadedSong) {
        this.loadedSong = loadedSong;
    }

    public final PodcastInput getLoadedPodcast() {
        return loadedPodcast;
    }

    public final void setLoadedPodcast(final PodcastInput loadedPodcast) {
        this.loadedPodcast = loadedPodcast;
    }

    public final int getPassedTime() {
        return passedTime;
    }

    public final void setPassedTime(final int passedTime) {
        this.passedTime = passedTime;
    }

    public final boolean isPlayPauseStatus() {
        return playPauseStatus;
    }

    public final void setPlayPauseStatus(final boolean playPauseStatus) {
        this.playPauseStatus = playPauseStatus;
    }

    public final int getTimestampLastCommand() {
        return timestampLastCommand;
    }

    public final void setTimestampLastCommand(final int timestampLastCommand) {
        this.timestampLastCommand = timestampLastCommand;
    }

    public final ArrayList<PlaylistInput> getPlaylists() {
        return playlists;
    }

    public final void setPlaylists(final ArrayList<PlaylistInput> playlists) {
        this.playlists = playlists;
    }

    public final ArrayList<SongInput> getLikedSongs() {
        return likedSongs;
    }

    public final void setLikedSongs(final ArrayList<SongInput> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public final PlaylistInput getPlaylist() {
        return playlist;
    }

    public final void setPlaylist(final PlaylistInput playlist) {
        this.playlist = playlist;
    }

    public final ArrayList<PlaylistInput> getSearchedPlaylists() {
        return searchedPlaylists;
    }

    public final void setSearchedPlaylists(final ArrayList<PlaylistInput> searchedPlaylists) {
        this.searchedPlaylists = searchedPlaylists;
    }

    public final PlaylistInput getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public final void setSelectedPlaylist(final PlaylistInput selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    public final PlaylistInput getLoadedPlaylist() {
        return loadedPlaylist;
    }

    public final void setLoadedPlaylist(final PlaylistInput loadedPlaylist) {
        this.loadedPlaylist = loadedPlaylist;
    }

    public final ArrayList<PodcastInfo> getRunningPodcast() {
        return runningPodcast;
    }

    public final void setRunningPodcast(final ArrayList<PodcastInfo> runningPodcast) {
        this.runningPodcast = runningPodcast;
    }

    public final ArrayList<PlaylistInput> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public final void setFollowedPlaylists(final ArrayList<PlaylistInput> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    /**
     *
     * this method returns the type of audio
     * that is playing at a certain moment in time
     *
     * @return the current audio type
     */
    public final String verifyRunningAudioType() {
        if (loadedSong != null) {
            return "song";
        } else if (loadedPlaylist != null) {
            return "playlist";
        } else if (loadedPodcast != null) {
            return "podcast";
        }
        return "Issue deciding current audio source";
    }


    public final void setUsername(final String username) {
        this.username = username;
    }
}
