package edu.fpdual.proyecto.mangashelf.email;

import lombok.Getter;
import lombok.Setter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


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


    public boolean send(String from, String to, String subject, String content){
        Session session = createSession();

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html");

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully...");
            return true;
        } catch (MessagingException mex){
            mex.printStackTrace();
            return false;
        }
    }
    private Session createSession() {
        Session session = Session.getInstance(mailProp, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(credentialProp.getProperty(CredentialsConstants.USER),
                                credentialProp.getProperty(CredentialsConstants.PASSWD));
                    }
                }
        );

        session.setDebug(true);
        return session;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Sender().send("info.mangashelf@gmail.com", "albalima.gar@gmail.com", "Hola =D",
                "<b>¡Bienvenido a MANGASHELF!<b> Enhorabuena, tu registro está completo. ¡Ahora puedes inciar sesión en tu aplicación con tu correo electrónico y adentrarte en el universo Manga!");
    }

}