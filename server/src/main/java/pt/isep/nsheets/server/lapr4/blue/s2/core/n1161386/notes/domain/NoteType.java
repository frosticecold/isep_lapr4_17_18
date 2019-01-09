/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s2.core.n1161386.notes.domain;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public enum NoteType {
    TEXT("TEXT"), LIST("LIST");

    String key;

    NoteType(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
    
    

}
