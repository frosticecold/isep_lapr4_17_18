package pt.isep.nsheets.shared.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Magalhães<1160763>
 */
public class UserDTO implements Serializable {

    private String username;
    private String password;
    private String email;
    private String role;
    private String name;
    private String pictureUrl;
    private List<String> contacts;
    private List<String> invitations;
    private List<String> blockedUsers;
    private List<WorkbookDescriptionDTO> list;
    private boolean active;

    public UserDTO(String username, String password, String email, String role, String name, String path, List<WorkbookDescriptionDTO> list, boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.pictureUrl = path;
        this.contacts = new ArrayList<>();
        this.invitations = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
        this.list = list;
        this.active = active;
    }

    public UserDTO() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return pictureUrl;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public List<String> getblockedUsers() {
        return blockedUsers;
    }

    public List<WorkbookDescriptionDTO> getList() {
        return list;
    }

    public boolean isActive() {
        return active;
    }
}
