/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
@SuppressWarnings("serial")
public class PublicChatDTO implements Serializable {

    private List<MessageDTO> message;

    public PublicChatDTO() {
    }

    public PublicChatDTO(List<MessageDTO> message) {
        this.message = message;
    }

    public List<MessageDTO> getMessage() {
        return message;
    }

}
