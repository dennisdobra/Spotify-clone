package solution.StatsUser;

import fileio.input.PodcastInput;

public class PodcastInfo {
    private int listenedTime;
    private PodcastInput podcast;

    public PodcastInfo() {
    }

    public final int getListenedTime() {
        return listenedTime;
    }

    public final void setListenedTime(final int listenedTime) {
        this.listenedTime = listenedTime;
    }

    public final PodcastInput getPodcast() {
        return podcast;
    }

    public final void setPodcast(final PodcastInput podcast) {
        this.podcast = podcast;
    }

}
