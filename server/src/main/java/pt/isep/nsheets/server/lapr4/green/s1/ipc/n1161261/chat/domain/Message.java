/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.MessageDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
@Entity
public class Message implements ValueObject, Serializable {

    private static final long serialVersionUID = 41006373465343228L;

    @GeneratedValue
    @Id
    private Long id;

    /**
     * Message of the user
     */
    @Column(unique = false)
    private String message;
    /**
     * User
     */
    @ManyToOne
    private User user;
    /**
     * Date of the message
     */
    @Column(unique = false)
    private Calendar date;

    protected Message() {
    }

    /**
     * Constuctor
     *
     * @param message
     */
    public Message(String message, User user) {
        this.message = message;
        this.date = Calendar.getInstance();
        this.user = user;
    }

    /**
     * Information about messages
     *
     * @return
     */
    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", user=" + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    public static Message fromDTO(MessageDTO msgDTO) {
        final UserRepository repoUsers = PersistenceContext.repositories().users();
        User userLog = repoUsers.findUserByUsername(new Username(msgDTO.getUser()));
        String msg = msgDTO.getMessage();
        return new Message(msg, userLog);

    }

    public Message(String message) {
        date = Calendar.getInstance();
        this.message = message;
    }

    public MessageDTO toDTO(Message messages) {

        return new MessageDTO(this.message, DateTime.format(date, "YYYY-MM-dd HH:mm:ss"), this.user.username());

    }

}
