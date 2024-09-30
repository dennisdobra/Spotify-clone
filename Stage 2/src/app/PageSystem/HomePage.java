package app.PageSystem;

import app.audio.Files.Song;
import app.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomePage extends Page {
    private static final int LIMIT = 5;

    public HomePage() {
    }

    @Override
    public final String printPage(final User user) {
        // sort the songs from likedSongs
        ArrayList<Song> orderedSongs = new ArrayList<>();

        for (Song song : user.getLikedSongs()) {
            orderedSongs.add(song);
        }
        Collections.sort(orderedSongs, Comparator.comparingInt(Song::getLikes).reversed());

        ArrayList<Song> top5SortedSongs = new ArrayList<>();
        int cnt = 0;
        for (Song song : orderedSongs) {
            top5SortedSongs.add(song);
            if (top5SortedSongs.size() == LIMIT) {
                break;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Liked songs:\n\t[");

        for (int i = 0; i < top5SortedSongs.size() - 1; i++) {
            result.append(top5SortedSongs.get(i).getName());
            result.append(", ");
        }
        if (!top5SortedSongs.isEmpty()) {
            result.append(top5SortedSongs.get(top5SortedSongs.size() - 1).getName());
            result.append("]\n\n");
        } else {
            result.append("]\n\n");
        }


        result.append("Followed playlists:\n\t[");
        for (int i = 0; i < user.getFollowedPlaylists().size() - 1; i++) {
            result.append(user.getFollowedPlaylists().get(i).getName());
            result.append(", ");
        }
        if (!user.getFollowedPlaylists().isEmpty()) {
            result.append(user.getFollowedPlaylists().get(user.
                    getFollowedPlaylists().size() - 1).getName());
            result.append("]");
        } else {
            result.append("]");
        }

        return result.toString();
    }
}
