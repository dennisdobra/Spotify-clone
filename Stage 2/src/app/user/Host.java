package app.user;

import app.Admin;
import app.HostActivity.Announcement;
import app.audio.Collections.Podcast;
import app.audio.Collections.PodcastOutput;
import app.audio.Files.Episode;
import lombok.Getter;

import java.util.ArrayList;

public class Host extends User {
    @Getter
    private ArrayList<Podcast> hostPodcasts = new ArrayList<>();
    @Getter
    private ArrayList<Announcement> hostAnnouncements = new ArrayList<>();

    public Host(final String username, final int age, final String city, final String type) {
        super(username, age, city, type);
    }

    /**
     *
     * @param podcastName podcast name
     * @param hostName host's name
     * @param podcastEpisodes podcast episodes
     * @return message depending on whether the podcast was added or not
     */
    public String addPodcast(final String podcastName, final String hostName,
                             final ArrayList<Episode> podcastEpisodes) {
        // Search through the host's podcasts
        if (hostPodcasts.stream().anyMatch(podcast -> podcast.getName().equals(podcastName))) {
            return hostName + " has another podcast with the same name.";
        }

        // Check if the same episode exists twice in the podcast
        int numberOfEpisodes = podcastEpisodes.size();
        for (int i = 0; i < numberOfEpisodes - 1; i++) {
            for (int j = i + 1; j < numberOfEpisodes; j++) {
                Episode episode1 = podcastEpisodes.get(i);
                Episode episode2 = podcastEpisodes.get(j);

                if (episode1.getName().equals(episode2.getName())) {
                    return hostName + " has the same episode in this podcast.";
                }
            }
        }

        Podcast podcast = new Podcast(podcastName, hostName, podcastEpisodes);
        Admin.getInstance().addPodcast(podcast);
        hostPodcasts.add(podcast);
        return hostName + " has added new podcast successfully.";
    }

    /**
     *
     * @param announcementName announcement's name
     * @param hostName host's name
     * @param description announcement's description
     * @return message depending on whether the announcement was added or not
     */
    public String addAnnouncement(final String announcementName, final String hostName,
                                  final String description) {
        // Search through the artist's events
        if (hostAnnouncements.stream().anyMatch(announcement -> announcement.getAnnouncementName()
                .equals(announcementName))) {
            return hostName + " has another announcement with the same name.";
        }

        // Add the event
        hostAnnouncements.add(new Announcement(hostName, announcementName, description));
        return hostName + " has successfully added new announcement.";
    }

    /**
     *
     * @param announcementName announcement name
     * @param hostName host's name
     * @return message depending on whether the announcement was removed or not
     */
    public String removeAnnouncement(final String announcementName, final String hostName) {
        int found = 0;
        if (hostAnnouncements.stream().anyMatch(announcement -> announcement.getAnnouncementName().
                equals(announcementName))) {
            found = 1;
        }

        if (found == 0) {
            return hostName + " has no announcement with the given name.";
        } else {
            // Remove the announcement
            for (Announcement announcement : hostAnnouncements) {
                if (announcement.getAnnouncementName().equals(announcementName)) {
                    hostAnnouncements.remove(announcement);
                    break;
                }
            }
        }
        return hostName + " has successfully deleted the announcement.";
    }

    /**
     *
     * @param podcastName podcast name
     * @param username username
     * @return message depending on whether the podcast was removed or not
     */
    public String removePodcast(final String podcastName, final String username) {
        int found = 0;
        if (hostPodcasts.stream().anyMatch(podcast -> podcast.getName().equals(podcastName))) {
            found = 1;
        }

        if (found == 0) {
            return username + " doesn't have a podcast with the given name.";
        } else {
            // Check if any normal user has the podcast loaded
            for (User user : Admin.getInstance().getUsers()) {
                if (user.getPlayer().getSource() != null) {   // if they are listening to something
                    if (user.getPlayer().getSource().getAudioCollection() != null) {
                        if (user.getPlayer().getSource().getAudioCollection().
                                matchesOwner(getUsername())) {
                            return username + " can't delete this podcast.";
                        }
                    }
                }
            }

            // Remove the podcast
            for (Podcast podcast : hostPodcasts) {
                if (podcast.getName().equals(podcastName)) {
                    hostPodcasts.remove(podcast);
                    Admin.getInstance().removePodcast(podcast);
                    return username + " deleted the podcast successfully.";
                }
            }
        }
        return null;
    }

    /**
     *
     * @param username username for which I want to show podcasts
     * @return podcast names
     */
    public ArrayList<PodcastOutput> showPodcasts(final String username) {
        ArrayList<PodcastOutput> podcastOutputs = new ArrayList<>();
        for (Podcast podcast : hostPodcasts) {
            podcastOutputs.add(new PodcastOutput(podcast));
        }

        return podcastOutputs;
    }
}
