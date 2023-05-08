/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Entities.Article;
import blog.Services.PostService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aloui
 */
public class AjoutPostController implements Initializable {

    @FXML
    private Button exit;

    @FXML
    private TextField titleAjoutP;

    @FXML
    private TextField descAjoutP;

    @FXML
    private Button ajouterP;

    @FXML
    private Button annulerP;

    @FXML
    private Button imageButton;

    @FXML
    private TextField fileP;
    FileChooser filechooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        filechooser.setInitialDirectory(new File("C:\\Users\\aloui\\Desktop\\WeGym\\public\\uploads\\images"));
    }

    @FXML
    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

    @FXML
    private void getImage() {
        File file = filechooser.showOpenDialog(new Stage());
        if (file != null) {
            String filePath = file.toURI().toString();

            fileP.setText(file.getName());
        }
        
    }

    @FXML
    private void retourBlog(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ShowPosts.fxml"));
        Parent root = loader.load();
        annulerP.getScene().setRoot(root);

    }
    @FXML
    private TextField tfPrix;

    @FXML
    private TextField nbrPlace;

    @FXML

    private boolean verifs() {
        if (titleAjoutP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champ titre est obligatoire !");
            return false;
        }
        if (descAjoutP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champ description est obligatoire !");
            return false;
        }
        if (tfPrix.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champ prix est obligatoire et doit contenir que des chiffres!");
            return false;
        }
        if (nbrPlace.getText().isEmpty() || !nbrPlace.getText().matches("[0-9.]+")) {
            JOptionPane.showMessageDialog(null, "Le champ nbr de place est obligatoire et doit contenir que des chiffres!");
            return false;
        }
        if (fileP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "tu dois choisir une image!");
            return false;
        }

        return true;
    }

    @FXML
    private void ajouterF(ActionEvent event) throws SQLException, IOException {
        PostService ps = new PostService();

        if (verifs()) {

            Article p = new Article(filterWords(titleAjoutP.getText()), filterWords(descAjoutP.getText()), fileP.getText(), Integer.parseInt(nbrPlace.getText()), Integer.parseInt(tfPrix.getText()));

            ps.create(p);
            JOptionPane.showMessageDialog(null, "Post ajouté avec succe");
            ShowPostsController.i = 0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ShowPosts.fxml"));
            Parent root = loader.load();
            ajouterP.getScene().setRoot(root);
        }
    }

     @FXML 
    private Button retour;
    
     @FXML
        private void GoToF(javafx.event.ActionEvent event) throws IOException 
       {
            
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ShowPosts.fxml"));
                   Parent root= loader.load();
                 retour.getScene().setRoot(root);
                   
       }
    
    @FXML
    
    // MAILING 
    
    private static void sendMail() {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "arco.sc0156@gmail.com";
        String password = "hghseksuroiqviag";

        // Set the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a new email session using the specified properties
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {

            Message msg = new MimeMessage(session);

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alouimed08@gmail.com"));

            msg.setSubject("Avertissement!");
            msg.setText("Votre commentaire contient des mots innapropriés, la deuxième avertissement votre compte sera bloqué!");
            Transport.send(msg);

            // Print a message to the console to indicate that the email was sent successfully
        } catch (AddressException e) {
// Create an alert to notify the user that there was an error with the email address
                        System.out.println("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
   
    public static String filterWords(String text) {
        String[] filterWords = {"bhim", "ahla", "dh3if"};
        String[] data = text.split("\\s+");
        String str = "";
        for (String s : data) {
            boolean g = false;
            for (String lib : filterWords) {
                if (s.equals(lib)) {
                    String t = "";
                    sendMail();
                    for (int i = 0; i < s.length(); i++) {
                        t += "*";
                    }
                    str += t + " ";
                    g = true;
                    break;
                }
                
            }
            if (!g) {
                str += s + " ";
            }
            
        }
        
        return str.trim();
    }
    



}

/*
private String filterComment(String comment, String username) {
    String[] words = comment.split("\\s+");
    int badCount = 0;
    for (String word : words) {
        if (isBadWord(word)) {
            badCount++;
        }
    }
    int userBadCommentCount = userBadCommentCounts.getOrDefault(username, 0);
    if (badCount > 0) {
        if (userBadCommentCount >= MAX_BAD_COMMENTS) {
            return "You have reached the maximum number of bad comments.";
        } else {
            userBadCommentCounts.put(username, userBadCommentCount + 1);
        }
    }
    String filteredComment = comment;
    for (String badWord : BAD_WORDS) {
        filteredComment = filteredComment.replaceAll("(?i)" + badWord, "*");
    }
    return filteredComment;
}

private boolean isBadWord(String word) {
    for (String badWord : BAD_WORDS) {
        if (word.equalsIgnoreCase(badWord)) {
            return true;
        }
    }
    return false;
}
*/

