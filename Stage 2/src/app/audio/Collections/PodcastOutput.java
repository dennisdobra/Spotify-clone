package app.audio.Collections;

import app.audio.Files.Episode;

import java.util.ArrayList;

public final class PodcastOutput {
    private String name;
    private ArrayList<String> episodes = new ArrayList<>();

    public PodcastOutput(final Podcast podcast) {
        name = podcast.getName();
        for (Episode episode : podcast.getEpisodes()) {
            episodes.add(episode.getName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(final ArrayList<String> episodes) {
        this.episodes = episodes;
    }
}
