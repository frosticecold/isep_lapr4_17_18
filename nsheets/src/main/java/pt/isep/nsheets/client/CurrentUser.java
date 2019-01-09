package pt.isep.nsheets.client;

/**
 * Class that represents the current logged in user
 *
 * @Author Rui Almeida <1160818>
 */
public class CurrentUser {

    /**
     * Boolean that represents the login state
     */
    private static boolean isLogged = false;

    /**
     * Current user username
     */
    private static String username = "";

    private static String picture = "";

    private static String email;

    /**
     * Returns the current status of the user
     *
     * @return true if its logged in, false if not
     */
    public static boolean isLogged() {
        return isLogged;
    }

    /**
     * Logs out the user and clears the username
     */
    public static void setLoggedOut() {
        isLogged = false;
        username = "";
    }

    /**
     * Logs the user in and sets the username
     */
    public static void setLoggedIn(String u) {
        isLogged = true;
        username = u;
    }

    /**
     * @return the current user username, empty string if there is no user
     * logged in
     */
    public static String username() {
        return username;
    }

    public static String picture() {
        return picture;
    }

    public static String email() {
        return email;
    }

    public static void setPicture(String picture) {
        CurrentUser.picture = picture;
    }

    public static void setEmail(String whatEmail) {
        CurrentUser.email = whatEmail;
    }

}
