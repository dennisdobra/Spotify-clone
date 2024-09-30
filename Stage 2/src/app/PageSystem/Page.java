package app.PageSystem;


import app.user.User;
import lombok.Getter;
import lombok.Setter;

public class Page {
    @Setter @Getter
    private User user;

    /**
     *
     * @param user the user
     * @return null
     */
    public String printPage(final User user) {
        return null;
    }
}
