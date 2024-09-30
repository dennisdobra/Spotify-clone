package app.PageSystem;

import app.audio.Files.Episode;
import app.user.Host;
import app.user.User;

public class HostPage extends Page {
    public HostPage() {
    }

    @Override
    public final String printPage(final User user) {
        StringBuilder result = new StringBuilder();
        result.append("Podcasts:\n\t[");

        // for podcasturi
        for (int i = 0; i < ((Host) user).getHostPodcasts().size() - 1; i++) {
            result.append(((Host) user).getHostPodcasts().get(i).getName());
            result.append(":\n\t[");
            // display the episodes
            for (int j = 0; j < ((Host) user).getHostPodcasts().get(i).
                    getNumberOfTracks() - 1; j++) {
                result.append(((Host) user).getHostPodcasts().get(i).getTrackByIndex(j).getName());
                result.append(" - ");
                result.append(((Episode) ((Host) user).getHostPodcasts().get(i).getTrackByIndex(j)).
                        getDescription());
                result.append(", ");
            }
            if (((Host) user).getHostPodcasts().get(i).getNumberOfTracks() != 0) {
                result.append(((Host) user).getHostPodcasts().get(i).getTrackByIndex(((Host) user).
                        getHostPodcasts().get(i).getNumberOfTracks() - 1).getName());
                result.append(" - ");
                result.append(((Episode) ((Host) user).getHostPodcasts().get(i).
                        getTrackByIndex(((Host) user).getHostPodcasts().get(i).
                                getNumberOfTracks() - 1)).getDescription());
                result.append("]\n");
                result.append(", ");
            } else {
                result.append("]");
            }
        }
        if (((Host) user).getHostPodcasts().size() != 0) {
            result.append(((Host) user).getHostPodcasts().get(((Host) user).
                    getHostPodcasts().size() - 1).getName());
            result.append(":\n\t[");

            // display the episodes
            for (int j = 0; j < ((Host) user).getHostPodcasts().get(((Host) user).
                    getHostPodcasts().size() - 1).getNumberOfTracks() - 1; j++) {
                result.append(((Host) user).getHostPodcasts().get(((Host) user).
                        getHostPodcasts().size() - 1).getTrackByIndex(j).getName());
                result.append(" - ");
                result.append(((Episode) ((Host) user).getHostPodcasts().get(((Host) user).
                        getHostPodcasts().size() - 1).getTrackByIndex(j)).getDescription());
                result.append(", ");
            }
            if (((Host) user).getHostPodcasts().get(((Host) user).getHostPodcasts().size() - 1).
                    getNumberOfTracks() != 0) {
                result.append(((Host) user).getHostPodcasts().get(((Host) user).
                        getHostPodcasts().size() - 1).getTrackByIndex(((Host) user).
                        getHostPodcasts().get(((Host) user).getHostPodcasts().size() - 1).
                        getNumberOfTracks() - 1).getName());
                result.append(" - ");
                result.append(((Episode) ((Host) user).getHostPodcasts().get(((Host) user).
                        getHostPodcasts().size() - 1).getTrackByIndex(((Host) user).
                        getHostPodcasts().get(((Host) user).getHostPodcasts().size() - 1).
                        getNumberOfTracks() - 1)).getDescription());
                result.append("]\n");
            } else {
                result.append("]\n");
            }
        }
        result.append("]\n\n");

        // for announcements
        result.append("Announcements:\n\t[");
        for (int i = 0; i < ((Host) user).getHostAnnouncements().size() - 1; i++) {
            result.append(((Host) user).getHostAnnouncements().get(i).getAnnouncementName());
            result.append(":");
            result.append("\n\t");
            result.append(((Host) user).getHostAnnouncements().get(i).getDescription());
            result.append(", ");
        }
        if (!((Host) user).getHostAnnouncements().isEmpty()) {
            result.append(((Host) user).getHostAnnouncements().get(((Host) user).
                    getHostAnnouncements().size() - 1).getAnnouncementName());
            result.append(":");
            result.append("\n\t");
            result.append(((Host) user).getHostAnnouncements().get(((Host) user).
                    getHostAnnouncements().size() - 1).getDescription());
        }
        result.append("\n]");

        return result.toString();
    }
}
