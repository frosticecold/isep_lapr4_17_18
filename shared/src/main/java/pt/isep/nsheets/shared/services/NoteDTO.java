/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
@SuppressWarnings("serial")
public class NoteDTO implements Serializable  {
    
    private String title;
    private String description;
    private Date timestamp;
    private String username;
    private String type;
    private LinkedList<NoteDTO> versions;
    
    public NoteDTO(){
    }
    
    public NoteDTO(String title, String description, Date timestamp, String username, String type, LinkedList<NoteDTO> versions){
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
        this.username = username;
        this.type = type;
        this.versions = versions;
    }
    
    public String getUser(){
        return this.username;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    public String getNoteType(){
        return type;
    }
    
    public LinkedList<NoteDTO> getVersions(){
        return versions;
    } 
}
