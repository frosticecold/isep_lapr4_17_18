/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1161386.notes.domain.NoteType;

import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.shared.services.NoteDTO;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
@Entity
public class Note implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private NoteType type;

    @Column(name="title", unique=true)
    private String title;

    private String description;
    
    @ElementCollection
    @OneToMany
    private LinkedList<NoteDTO> versions = new LinkedList<>();;
    
    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @OneToOne
    @JoinColumn(name="user")
    private User user;

    public Note() {
        //For ORM only AND for fromDTO usage
    }
    
    /**
     * Construtor
     * @param title
     * @param description
     * @param user 
     */
    public Note(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
        timestamp = new Date();
        
    }
    
    /**
     * 
     * @param title
     * @param description
     * @param timestamp
     * @param user
     * @param type 
     */
    public Note(String title, String description, Date timestamp, User user, String type, LinkedList<NoteDTO> versions){
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
        this.user = user;
        this.timestamp = new Date();
        for (NoteDTO version : versions) {
            this.versions.add(version);
        }
        convertStringToNoteType(type);
    }

    private void convertStringToNoteType(String noteType){
        this.type = NoteType.valueOf(noteType);
    }
    
    /**
     * define the title of a note
     * @param title 
     */
    public void defineNoteTitle(String title) {
        try {
            if (title == null) {
                throw new NullPointerException("The title you defined is null.");
            }
            if (title.isEmpty()) {
                throw new NumberFormatException("The title must not be empty.");
            }
            this.title = title;
        } catch (NumberFormatException | NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

    }
    /**
     * define the description of a note
     * @param description 
     */
    public void defineNoteDescription(String description) {
        try {
            if (description == null) {
                throw new NullPointerException("The description you defined is null.");
            }
            if (description.isEmpty()) {
                throw new NumberFormatException("The description must not be empty.");
            }
            this.description = description;
        } catch (NumberFormatException | NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addVersion(NoteDTO note){
        versions.add(note);
    }
    
    /**
     * Get the information from a Data Transfer Object
     * @param dto
     * @param user
     * @return
     * @throws IllegalArgumentException 
     */
    public static Note fromDTO(NoteDTO dto, User user) throws IllegalArgumentException {
        return new Note(dto.getTitle(), dto.getDescription(), dto.getTimestamp(),user, dto.getNoteType(), dto.getVersions());
    }
    /**
     * Exports the note to a Data Transfer Object
     * @return 
     */
    public NoteDTO toDTO(){
        String noteTitle = this.title;
        String noteDescription = this.description;
        Date noteTimeStamp = this.timestamp;
        String username = this.user.username();
        String noteType = this.type.toString();
        LinkedList<NoteDTO> noteVersions = this.versions;
        return new NoteDTO(noteTitle, noteDescription, noteTimeStamp, username, noteType, noteVersions);
    }
}
