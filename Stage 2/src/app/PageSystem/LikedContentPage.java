package app.PageSystem;

import app.user.User;

public class LikedContentPage extends Page {
    public LikedContentPage() {
    }

    @Override
    public final String printPage(final User user) {
        StringBuilder result = new StringBuilder();
        result.append("Liked songs:\n\t[");

        for (int i = 0; i < user.getLikedSongs().size() - 1; i++) {
            result.append(user.getLikedSongs().get(i).getName());
            result.append(" - ");
            result.append(user.getLikedSongs().get(i).getArtist());
            result.append(", ");
        }
        if (!user.getLikedSongs().isEmpty()) {
            result.append(user.getLikedSongs().get(user.getLikedSongs().size() - 1).getName());
            result.append(" - ");
            result.append(user.getLikedSongs().get(user.getLikedSongs().size() - 1).getArtist());
            result.append("]\n\n");
        } else {
            result.append("]\n\n");
        }


        result.append("Followed playlists:\n\t[");
        for (int i = 0; i < user.getFollowedPlaylists().size() - 1; i++) {
            result.append(user.getFollowedPlaylists().get(i).getName());
            result.append(" - ");
            result.append(user.getFollowedPlaylists().get(i).getOwner());
            result.append(", ");
        }
        if (!user.getFollowedPlaylists().isEmpty()) {
            result.append(user.getFollowedPlaylists().get(user.
                    getFollowedPlaylists().size() - 1).getName());
            result.append(" - ");
            result.append(user.getFollowedPlaylists().get(user.
                    getFollowedPlaylists().size() - 1).getOwner());
            result.append("]");
        } else {
            result.append("]");
        }

        return result.toString();
    }
}
