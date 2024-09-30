package app.PageSystem;

import app.user.Artist;
import app.user.User;

public final class ArtistPage extends Page {
    public ArtistPage() {
    }

    @Override
    public String printPage(final User user) {
        StringBuilder result = new StringBuilder();
        result.append("Albums:\n\t[");

        // for albums
        for (int i = 0; i < ((Artist) user).getArtistAlbums().size() - 1; i++) {
            result.append(((Artist) user).getArtistAlbums().get(i).getName());
            result.append(", ");
        }
        if (!((Artist) user).getArtistAlbums().isEmpty()) {
            result.append(((Artist) user).getArtistAlbums().get(((Artist) user).
                    getArtistAlbums().size() - 1).getName());
            result.append("]\n\n");
        } else {
            result.append("]");
        }

        // for merch
        result.append("Merch:\n\t[");
        for (int i = 0; i < ((Artist) user).getArtistMerch().size() - 1; i++) {
            result.append(((Artist) user).getArtistMerch().get(i).getMerchName());
            result.append(" - ");
            result.append(((Artist) user).getArtistMerch().get(i).getPrice());
            result.append(":\n\t");
            result.append(((Artist) user).getArtistMerch().get(i).getDescription());
            result.append(", ");
        }
        if (!((Artist) user).getArtistMerch().isEmpty()) {
            result.append(((Artist) user).getArtistMerch().get(((Artist) user).
                    getArtistMerch().size() - 1).getMerchName());
            result.append(" - ");
            result.append(((Artist) user).getArtistMerch().get(((Artist) user).
                    getArtistMerch().size() - 1).getPrice());
            result.append(":\n\t");
            result.append(((Artist) user).getArtistMerch().get(((Artist) user).
                    getArtistMerch().size() - 1).getDescription());
            result.append("]\n\n");
        } else {
            result.append("]\n\n");
        }

        // for events
        result.append("Events:\n\t[");
        for (int i = 0; i < ((Artist) user).getArtistEvents().size() - 1; i++) {
            result.append(((Artist) user).getArtistEvents().get(i).getEventName());
            result.append(" - ");
            result.append(((Artist) user).getArtistEvents().get(i).getDate());
            result.append(":\n\t");
            result.append(((Artist) user).getArtistEvents().get(i).getDescription());
            result.append(", ");
        }
        if (!((Artist) user).getArtistEvents().isEmpty()) {
            result.append(((Artist) user).getArtistEvents().get(((Artist) user).
                    getArtistEvents().size() - 1).getEventName());
            result.append(" - ");
            result.append(((Artist) user).getArtistEvents().get(((Artist) user).
                    getArtistEvents().size() - 1).getDate());
            result.append(":\n\t");
            result.append(((Artist) user).getArtistEvents().get(((Artist) user).
                    getArtistEvents().size() - 1).getDescription());
            result.append("]");
        } else {
            result.append("]");
        }

        return result.toString();
    }
}
