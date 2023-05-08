/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Entities.Article;
import static blog.GUI.AjoutPostController.filterWords;
import blog.Services.PostService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aloui
 */
public class ModifPostController implements Initializable {
  @FXML
    public TextField tfPrix;

    @FXML
    public TextField nbrPlace;

    @FXML
    private Button exit;

    @FXML
    public TextField titlemodP;

    @FXML
    public TextField descmodP;

    @FXML
    private Button modiferP;

    @FXML
    private Button annulerP;
    private static Article postt;

    public static Article getPostt() {
        return postt;
    }

    public static void setPostt(Article postt) {
        ModifPostController.postt = postt;
    }
    
    private static int idP;

    public static int getIdP() {
        return idP;
    }

    public static void setIdP(int idP) {
        ModifPostController.idP = idP;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    
    private boolean verifs() {
if (titlemodP.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Le champ titre est obligatoire !");
        return false;
    }
    if (descmodP.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Le champ description est obligatoire !");
        return false;
    }
 
      
  
     return true;
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
    private void modP(ActionEvent event) throws SQLException, IOException {
        PostService ps = new PostService();
        
       if (verifs()) {
       
        Article p = new Article(filterWords(titlemodP.getText()),filterWords(descmodP.getText()),postt.getImage(),postt.getDate_post(),postt.getRate(),postt.getNbrRate(),Integer.parseInt(nbrPlace.getText()),Integer.parseInt(tfPrix.getText()),postt.getDate_fin());
         ps.update(p, postt.getId());
       }
      
       postt = ps.getOneById(postt.getId());
    
     try {
              
             Article fournisseur = postt;
             BlogTestController b =new BlogTestController();
              b.setIdP(fournisseur.getId());
              System.out.println(b.getIdP());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BlogTest.fxml"));
            Parent root = loader.load();
            BlogTestController controller = loader.getController();
            controller.setFournisseur(fournisseur);
           controller.setPostt(fournisseur);
           System.out.println(controller.getPostt().toString());
            Scene scene = new Scene(root);
            Stage stage = (Stage) modiferP.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
       }
    
        @FXML
    private void returnDetailP(ActionEvent event) throws SQLException, IOException {
        
        PostService ps = new PostService();
     try {
               postt = ps.getOneById(postt.getId());
             Article fournisseur = postt;
             BlogTestController b =new BlogTestController();
              b.setIdP(fournisseur.getId());
              System.out.println(b.getIdP());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BlogTest.fxml"));
            Parent root = loader.load();
            BlogTestController controller = loader.getController();
            controller.setFournisseur(fournisseur);
           controller.setPostt(fournisseur);
           System.out.println(controller.getPostt().toString());
            Scene scene = new Scene(root);
            Stage stage = (Stage) annulerP.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       }
    
    
    
}
