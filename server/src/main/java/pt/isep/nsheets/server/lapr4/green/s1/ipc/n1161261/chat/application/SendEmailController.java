/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.SendEmail;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161027.ipc06.application.ipc06Controller;
import pt.isep.nsheets.shared.services.UserDTO;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class SendEmailController implements Controller {
    
    public boolean sendEmail(String username){
        ipc06Controller controller = new ipc06Controller();
        UserDTO user = controller.findUser(username);
        if(user.isActive()){
        SendEmail se = new SendEmail(user.getEmail(), user.getName());
        boolean validation = se.send();
        return validation;
        }
        
        return false;
    }
    
}
