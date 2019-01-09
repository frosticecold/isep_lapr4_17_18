/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Password;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.shared.services.NoteDTO;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public class NoteTest {
    
    public NoteTest() {
    }

//    /**
//     * Test of defineNoteTitle method, of class Note.
//     */
//    @Test
//    public void testDefineNoteTitle() {
//        System.out.println("defineNoteTitle");
//        String title = "";
//        User user = new User(new Username("test"),new Password("test"), new Email("test@test.com"), "Admin");
//        Note instance = new Note("","", user);
//        instance.defineNoteTitle(title);
//        assertTrue((instance.toDTO()).getTitle().equals(""));
//    }
//
//    /**
//     * Test of defineNoteDescription method, of class Note.
//     */
//    @Test
//    public void testDefineNoteDescription() {
//        System.out.println("defineNoteDescription");
//        String description = "";
//        User user = new User(new Username("test"),new Password("test"), new Email("test@test.com"), "Admin");
//        Note instance = new Note("","", user);
//        instance.defineNoteDescription(description);
//        assertTrue((instance.toDTO()).getDescription().equals(""));
//    }
//
//    /**
//     * Test of fromDTO method, of class Note.
//     */
//    @Test
//    public void testFromDTO() {
//        System.out.println("fromDTO");
//        NoteDTO dto = new NoteDTO("", "",new Date(), "");
//        User user = new User(new Username("test"),new Password("test"), new Email("test@test.com"), "Admin");
//        Note expResult = new Note("","",user);
//        Note result = Note.fromDTO(dto, user);
//        assertEquals((expResult.toDTO()).getTitle(), (result.toDTO()).getTitle());
//    }
//
//    /**
//     * Test of toDTO method, of class Note.
//     */
//    @Test
//    public void testToDTO() {
//        System.out.println("toDTO");
//        
//        User user = new User(new Username("test"),new Password("test"), new Email("test@test.com"), "Admin");
//
//        Note instance = new Note("","",user);
//        String expResult = "";
//        NoteDTO result = instance.toDTO();
//        assertEquals(expResult, (result).getTitle());
//    }
    
}
