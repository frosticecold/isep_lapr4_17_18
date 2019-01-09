/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.ibm.icu.util.Calendar;
import java.io.Serializable;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
@SuppressWarnings("serial")
public class MessageDTO implements Serializable {

    private String message;
    private String date;
    private String user;

    public MessageDTO() {
    }

    public MessageDTO(String message, String date, String user) {
        this.message = message;
        this.date = date;
        this.user = user;
    }

    public MessageDTO(String message, String user) {
        this.message = message;
        this.user = user;
        this.date = "2018-12-15 00:00:00";
    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

}
