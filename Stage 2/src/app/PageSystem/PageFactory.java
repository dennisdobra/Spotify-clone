package app.PageSystem;

public final class PageFactory {

    /**
     *
     * @param type type of next page
     * @return new page depending on type
     */
    public Page createPage(final String type) {
        switch (type) {
            case "Home":
                return new HomePage();
            case "LikedContent":
                return new LikedContentPage();
            case "Host":
                return new HostPage();
            case "Artist":
                return new ArtistPage();
            default:
                return null;
        }
    }
}
