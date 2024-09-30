package solution.Output;

public class Stats {
    private String name;
    private int remainedTime;
    private String repeat;
    private boolean shuffle = false;
    private boolean paused = false;

    /* Constructor */
    public Stats() {
    }

    /* Getters and Setters */
    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final int getRemainedTime() {
        return remainedTime;
    }

    public final void setRemainedTime(final int remainedTime) {
        this.remainedTime = remainedTime;
    }

    public final String getRepeat() {
        return repeat;
    }

    public final void setRepeat(final String repeat) {
        this.repeat = repeat;
    }

    public final boolean getShuffle() {
        return shuffle;
    }

    public final void setShuffle(final boolean shuffle) {
        this.shuffle = shuffle;
    }

    public final boolean getPaused() {
        return paused;
    }

    public final void setPaused(final boolean paused) {
        this.paused = paused;
    }
}
