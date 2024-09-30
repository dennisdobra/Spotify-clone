package app.HostActivity;

public final class Announcement {
    private String hostName;
    private String announcementName;
    private String description;

    public Announcement(final String hostName, final String announcementName,
                        final String description) {
        this.hostName = hostName;
        this.announcementName = announcementName;
        this.description = description;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public void setAnnouncementName(final String announcementName) {
        this.announcementName = announcementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
