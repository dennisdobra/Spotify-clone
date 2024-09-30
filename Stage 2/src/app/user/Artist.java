package app.user;

import app.Admin;
import app.AristActivity.Merch;
import app.audio.Collections.Album;
import app.audio.Files.Song;
import lombok.Getter;
import app.AristActivity.Event;
import lombok.Setter;

import java.util.ArrayList;

public class Artist extends User {
    @Getter
    private ArrayList<Album> artistAlbums = new ArrayList<>();
    @Getter
    private ArrayList<Event> artistEvents = new ArrayList<>();
    @Getter
    private ArrayList<Merch> artistMerch = new ArrayList<>();
    @Getter @Setter
    private int artistTotalLikes = 0;

    public Artist(final String username, final int age, final String city, final String type) {
        super(username, age, city, type);
    }

    /**
     *
     * @param albumName album name
     * @param artistName artist name
     * @param releaseYear release year of album
     * @param description description of album
     * @param albumSongs songs in the album
     * @return message depending on whether the album was added or not
     */
    public String addAlbum(final String albumName, final String artistName, final int releaseYear,
                           final String description, final ArrayList<Song> albumSongs) {
        if (Admin.getInstance().getArtists().stream().anyMatch(artists -> artists.getUsername().
                equals(artistName))) {
            if (artistAlbums.stream().anyMatch(album -> album.getName().equals(albumName))) {
                return artistName + " has another album with the same name.";
            }

            // check if the same song exists twice in the album
            int numberOfSongs = albumSongs.size();
            for (int i = 0; i < numberOfSongs - 1; i++) {
                for (int j = i + 1; j < numberOfSongs; j++) {
                    Song song1 = albumSongs.get(i);
                    Song song2 = albumSongs.get(j);

                    if (song1.getName().equals(song2.getName())) {
                        return artistName + " has the same song at least twice in this album.";
                    }
                }
            }
            for (Song song : albumSongs) {
                Admin.getInstance().addSong(song);
            }
            // add the album
            artistAlbums.add(new Album(artistName, albumName, releaseYear,
                    description, albumSongs));
            return artistName + " has added new album successfully.";
        } else {
            return "The username " + artistName + "doesn't exist.";
        }
    }

    /**
     *
     * @param eventName name of the event
     * @param artistName artist name
     * @param description description of the event
     * @param date date of the event
     * @return message depending on whether the event was added or not
     */
    public String addEvent(final String eventName, final String artistName,
                           final String description, final String date) {
        // search through the artist's events
        if (artistEvents.stream().anyMatch(event -> event.getEventName()
                .equals(eventName))) {
            return artistName + " has another event with the same name.";
        }

        // check if the event has a valid date
        String[] dateParts = date.split("-");

        int day, month, year;

        year = Integer.parseInt(dateParts[2]);
        if (year < 1900 || year > 2023) {
            return "Event for " + artistName + " does not have a valid date.";
        }

        month = Integer.parseInt(dateParts[1]);
        if (month < 1 || month > 12) {
            return "Event for " + artistName + " does not have a valid date.";
        }

        day = Integer.parseInt(dateParts[0]);
        if (day < 1 || day > 31) {
            return "Event for " + artistName + " does not have a valid date.";
        }

        // Check February
        if (month == 2 && day > 28) {
            return "Event for " + artistName + " does not have a valid date.";
        }

        // Check months with 30 days
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return "Event for " + artistName + " does not have a valid date.";
        }

        // add the event
        artistEvents.add(new Event(artistName, eventName, description, date));
        return artistName + " has added new event successfully.";
    }

    /**
     *
     * @param merchName merch name
     * @param artistName artist name
     * @param description description of merch
     * @param price price of merch
     * @return message depending on whether merch was added or not
     */
    public String addMerch(final String merchName, final String artistName,
                           final String description, final String price) {
        if (artistMerch.stream().anyMatch(merch -> merch.getMerchName().equals(merchName))) {
            return artistName + " has merchandise with the same name.";
        }

        // check if the price of merch is valid
        double priceCast = Double.parseDouble(price);
        if (priceCast < 0) {
            return "Price for merchandise can not be negative.";
        }

        artistMerch.add(new Merch(artistName, merchName, description, price));
        return artistName + " has added new merchandise successfully.";
    }

    /**
     *
     * @param eventName event name
     * @param artistName artist name
     * @return message depending on whether the event was removed or not
     */
    public String removeEvent(final String eventName, final String artistName) {
        int found = 0;
        if (artistEvents.stream().anyMatch(event -> event.getEventName().equals(eventName))) {
            found = 1;
        }

        if (found == 0) {
            return artistName + " doesn't have an event with the given name.";
        } else {
            // remove the event
            for (Event event : artistEvents) {
                if (event.getEventName().equals(eventName)) {
                    artistEvents.remove(event);
                    break;
                }
            }
        }
        return artistName + " deleted the event successfully.";
    }

    /**
     *
     * @param albumName album name
     * @param username username
     * @return message depending on whether the album was removed or not
     */
    public String removeAlbum(final String albumName, final String username) {
        int found = 0;
        if (artistAlbums.stream().anyMatch(album -> album.getName().equals(albumName))) {
            found = 1;
        }

        if (found == 0) {
            return username + " doesn't have an album with the given name.";
        } else {
            for (User user : Admin.getInstance().getUsers()) {
                if (user.getPage().getUser().equals(Admin.getInstance().getArtist(getUsername()))) {
                    return username + " can't delete this album.";
                }

                if (user.getPlayer().getSource() != null) {   // if listening to something
                    if (user.getPlayer().getSource().getAudioCollection() != null) {
                        if (user.getPlayer().getSource().getAudioCollection().
                                matchesOwner(getUsername())) {
                            return username + " can't delete this album.";
                        }
                    } else if (user.getPlayer().getSource().getAudioFile().
                            matchesArtist(getUsername())) {
                        return username + " can't delete this album.";
                    }
                }
            }

            // remove the album
            for (Album album : artistAlbums) {
                if (album.getName().equals(albumName)) {
                    artistAlbums.remove(album);
                    return username + " deleted the album successfully.";
                }
            }

        }
        return null;
    }
}
