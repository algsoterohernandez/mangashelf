package edu.fpdual.proyecto.mangashelf.email;

import lombok.Getter;
import lombok.Setter;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * Sender.
 *
 * Realiza el envío de emails.
 *
 * @author ikisaki
 *
 */
public class Sender {

    @Setter
    @Getter
    Properties mailProp = new Properties();

    @Setter
    @Getter
    Properties credentialProp = new Properties();

    public Sender() {

        try {

            mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * send.
     *
     * Envía un correo desde, hasta, con asunto y mensaje pasados por parámetro.
     *
     * @author ikisaki
     *
     */
    public boolean send(String from, String to, String subject, String content) {

        Session session = createSession();

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content,"text/html" );

            System.out.println("sending...");

            Transport.send(message);

            System.out.println("Sent message successfully....");

            return true;

        } catch (MessagingException mex) {

            mex.printStackTrace();

            return false;

        }

    }

    private Session createSession() {

        Session session = Session.getInstance(mailProp, new jakarta.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(credentialProp.getProperty(CredentialsConstants.USER),
                        credentialProp.getProperty(CredentialsConstants.PASSWD));

            }

        });

        session.setDebug(true);

        return session;

    }

}
