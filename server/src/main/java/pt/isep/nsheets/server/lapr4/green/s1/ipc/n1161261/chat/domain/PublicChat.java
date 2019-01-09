/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain;

import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PublicChatDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
@Entity
public class PublicChat implements AggregateRoot<Long>, Serializable, Chat {

    private static final long serialVersionUID = 41006373465343229L;

    @GeneratedValue
    @Id
    private Long id;

    /**
     * List of the messages
     */
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn()
    private final List<Message> messages;

    /**
     * Chat room type
     */
    private static final String TYPE_PUBLIC = "public";

    /**
     * Create a new instance of chat
     *
     */
    public PublicChat() {
        this.messages = new ArrayList<>();
    }

    public PublicChat(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Adds a new message to the chat
     *
     * @param message the message to adds
     * @return true if addded
     */
    @Override
    public boolean addChatMessage(Message message) {
        return messages.add(message);

    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final PublicChat obj = (PublicChat) other;
        if (this.id != obj.id) {
            return false;
        }
        return true;
    }

    @Override
    public boolean is(Long id) {
        return (this.id == id);
    }

    @Override
    public Long id() {
        return id;
    }

    public PublicChatDTO toDTO() {
        List<MessageDTO> listMsg = new ArrayList<>();
        for (Message msg : this.messages) {
            listMsg.add(msg.toDTO(msg));
        }
        Collections.reverse(listMsg);
        return new PublicChatDTO(listMsg);
    }

}
