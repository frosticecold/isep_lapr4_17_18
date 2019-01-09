package pt.isep.nsheets.shared.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MFerreira
 */
@SuppressWarnings("serial")
public class PrivateChatDTO implements Serializable {

    private String nome;

    private List<MessageDTO> message;

    private List<String> usernames;

    public PrivateChatDTO() {
    }

    public PrivateChatDTO(List<MessageDTO> message, String nome, List<String> usernames) {
        this.message = message;
        this.usernames = usernames;
        this.nome = nome;
    }
    
    public PrivateChatDTO(String nome, List<String> usernames) {
        this.message = new ArrayList<>();
        this.usernames = usernames;
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public List<MessageDTO> getMessage() {
        return message;
    }

    public List<String> getUsernames() {
        return usernames;
    }

}
