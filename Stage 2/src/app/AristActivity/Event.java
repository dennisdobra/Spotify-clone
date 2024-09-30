package app.AristActivity;

public final class Event {
    private String artistName;
    private String eventName;
    private String description;
    private String date;

    public Event(final String artistName, final String eventName,
                 final String description, final String date) {
        this.artistName = artistName;
        this.eventName = eventName;
        this.description = description;
        this.date = date;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(final String artistName) {
        this.artistName = artistName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(final String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }
}
