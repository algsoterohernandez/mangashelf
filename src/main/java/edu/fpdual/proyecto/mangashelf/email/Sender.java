package edu.fpdual.proyecto.mangashelf.email;

import lombok.Getter;
import lombok.Setter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public boolean send(String from, String to, String subject, String text, String content) throws FileNotFoundException, IOException {
        Session session = createSession();
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);

            BodyPart texto = new MimeBodyPart();
            texto.setContent(text,"text/html");

            File file = new File(content);

            InputStream fileData = getClass().getClassLoader().getResourceAsStream("mail.properties");

            try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
                int read;
                byte[] bytes = new byte[8192];
                while ((read = fileData.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }

            BodyPart fichero = new MimeBodyPart();
            fichero.setDataHandler(new DataHandler(new FileDataSource(file)));
            fichero.setFileName(file.getName());

            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(texto);
            multiPart.addBodyPart(fichero);

            message.setContent(multiPart);

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