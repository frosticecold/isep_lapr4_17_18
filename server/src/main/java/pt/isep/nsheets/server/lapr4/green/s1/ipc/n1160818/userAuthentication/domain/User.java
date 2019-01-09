package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain;

import eapli.framework.domain.AggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eapli.framework.domain.ValueObject;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1160763.ContactEdition.domain.Contact;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1160763.ContactEdition.domain.Invitation;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.UserDTO;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 * This class represents a general system user.
 *
 * @author Rui Almeida<1160818>
 */
@Entity
public class User implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The user's username
     */
    @OneToOne
    private Username username;

    /**
     * The user's password
     */
    @OneToOne
    private Password password;

    /**
     * The user's email
     */
    @OneToOne
    private Email email;
    
    /**
     * The user's name
     */
    @OneToOne
    private Name name;

    /**
     * User role - default user or admin
     */
    private String role;
    
    private boolean active;
    
    private String picURL;
    
    @OneToMany(cascade=CascadeType.ALL)
    private List<WorkbookDescription>workbooks;

    /**
     * User list of contacts
     */
    @ElementCollection
    private List<Email> contacts;

    /**
     * User list of blocked users
     */
    @ElementCollection
    private List<Email> blockedUsers;

    /**
     * User list of invitations
     */
    @ElementCollection
    private List<Email> invitations;

    /**
     * Protected constructor for ORM
     */
    protected User() {
    }

    /**
     * Default user constructor, using username, password and email. Per
     * default, the user is considered a default user.
     *
     * @param username username of type Username
     * @param password password of type Password
     * @param email email of type Email
     * @author Rui Almeida<1160818>
     */
    public User(String username, String password, String email, String role, String name, String path) {
        if (username == null || password == null || email == null
                || role == null || name == null) {
            throw new IllegalArgumentException();
        }
        this.username = new Username(username);
        this.password = new Password(password);
        this.email = new Email(email);
        this.role = role;
        this.name = new Name(name);
        this.picURL = path;
        this.contacts = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
        this.invitations = new ArrayList<>();
        this.active = true;
    }

    /**
     * Changes an user to administrator role, used to create a power user
     */
    public void setAsAdmin() {
        this.role = RoleType.ADMIN.toString();
    }

    /**
     * Changes an user to default user role
     */
    public void setAsDefaultUser() {
        this.role = RoleType.USER.toString();
    }

    /**
     * @return the user current role
     */
    public RoleType role() {
        for (RoleType role : RoleType.values()) {
            if (role.toString().equals(this.role)) {
                return role;
            }
        }
        return null;
    }

    /**
     * Compares a password with the user's current password.
     *
     * @param password - another password
     * @return true if they match, false if they don't
     */
    public boolean verifyPassword(String password) {
        return this.password.toString().equals(password);
    }

    /**
     * Adds a contact to the user contact list
     *
     * @return true if the contact was added successfully
     */
    public boolean addContact(Email contact) {
        return this.contacts.add(contact);
    }

    /**
     * @return the list user's contacts
     */
    public List<Email> contactsList() {
        return this.contacts;
    }

    public List<WorkbookDescription> workbooksFromUser() {
        return workbooks;
    }
    
    /**
     * Adds an invitation to the user list of invitations
     *
     * @return true if the invitation was added successfully
     */
    public boolean receiveInvitation(Email invitation) { return this.invitations.add(invitation); }

    /**
     * @return the list of user's invitations
     */
    public List<Email> invitations() {
        return this.invitations;
    }

    public Email getEmail() {
        return email;
    }

    public String getPicURL() {
        return picURL;
    }
    
    /**
     * Blocks an user by adding it to the user blocked list
     *
     * @return true if the user was added successfully to the list
     */
    public boolean blockUser(Email email) {
        return this.blockedUsers.add(email);
    }

    /**
     * @return the list of user's blocked users
     */
    public List<Email> blockedUsers() {
        return this.blockedUsers;
    }

    /**
     * @return the user's username as a string
     */
    public String username() {
        return this.username.toString();
    }
   
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof User)) {
            return false;
        }

        final User that = (User) other;
        if (this == that) {
            return true;
        }

        return (this.username.equals(that.username)
                && this.email.equals(that.email)
                && this.password.equals(that.password));
    }

    @Override
    public boolean is(Long id) {
        return Long.compare(this.id, id) == 0;
    }

    @Override
    public Long id() {
        return this.id;
    }
    
    public static User fromDTO(UserDTO dto){
        return new User(dto.getUsername(), dto.getPassword(), dto.getEmail() ,dto.getRole(), dto.getName(), dto.getPicture());
    }
    
    public UserDTO toDTO(){
        List<WorkbookDescription> workbooks=this.workbooks;
        List<WorkbookDescriptionDTO> workbooksDTO=new ArrayList<>();
        
        for (WorkbookDescription wbd:workbooks) {
            workbooksDTO.add(wbd.toDTO());
        }
        return new UserDTO(this.username.toString(),this.password.toString(),this.email.toString(),this.role, this.name.toString(), this.picURL, workbooksDTO, this.active);    
    }

    public void changeUsername(String username) {
        this.username = new Username(username);
    }

    public void changeName(String name) {
        this.name = new Name(name);
    }

    public void changePhoto(String path) {
        this.picURL = path;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void changeUserStatus(boolean status) {
        active = status;
    }

}
