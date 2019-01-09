package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain;

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
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Chat;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PrivateChatDTO;

/**
 *
 * @author MFerreira
 */
@Entity
public class PrivateChat implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 41006373465343297L;

    @GeneratedValue
    @Id
    private Long id;
    
    private String nome;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn()
    private final List<MessagePrivate> messages;
    
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn()
    private final List<User> users;

    private static final String TYPE_PRIVATE = "private";

    protected PrivateChat() {
        this.messages = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public PrivateChat(String nome, List<MessagePrivate> messages, List<User> users) {
        this.nome = nome;
        this.messages = messages;
        this.users = users;
    }

    public boolean addChatMessage(MessagePrivate message) {
        return messages.add(message);
    }

    public List<User> usersInConversation() {
        return users;
    }
    
    public String nome(){
        return this.nome;
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
        final PrivateChat obj = (PrivateChat) other;
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
        return this.id;
    }

    public PrivateChatDTO toDTO() {
        List<MessageDTO> listMsg = new ArrayList<>();
        for (MessagePrivate msg : this.messages) {
            listMsg.add(msg.toDTO(msg));
        }
        Collections.reverse(listMsg);
        List<String> listUsers = new ArrayList<>();
        for(User user : this.users){
            listUsers.add(user.username());
        }
        return new PrivateChatDTO(listMsg, this.nome, listUsers);
    }
    
    
    public static PrivateChat fromDTO(PrivateChatDTO dto, ArrayList<User> users) {

        List<MessagePrivate> messages= new ArrayList<>();

        for(MessageDTO message : dto.getMessage()){
            messages.add(new MessagePrivate(message.getMessage()));
        }
 
        return new PrivateChat(dto.getNome(), messages, users);
    }

}
