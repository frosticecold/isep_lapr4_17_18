/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class SendEmail {

    private Email email;
    private Mailer mailer;
    

    public SendEmail(String userEmail, String username) {
        email = EmailBuilder.startingBlank()
                .from("nsheets", "nsheets@mail.com")
                .to(username, userEmail)
                .withSubject("Test")
                .withPlainText("Hello! You have pending messages in nsheet's chat!")
                .buildEmail();

        mailer = MailerBuilder
                .withSMTPServer("smtp.mail.com", 587, "nsheets@mail.com", "qwerty1234")
                .withTransportStrategy(TransportStrategy.SMTP)
                .buildMailer();
    }

    public boolean send() {
        try{
        mailer.sendMail(email);
        } catch(Exception e){
            return false;
        }
        return true;
    }

}
