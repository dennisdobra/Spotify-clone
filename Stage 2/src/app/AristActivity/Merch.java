package app.AristActivity;

public final class Merch {
    private String artistName;
    private String merchName;
    private String description;
    private String price;

    public Merch(final String artistName, final String merchName,
                 final String description, final String price) {
        this.artistName = artistName;
        this.merchName = merchName;
        this.description = description;
        this.price = price;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(final String artistName) {
        this.artistName = artistName;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(final String merchName) {
        this.merchName = merchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }
}
