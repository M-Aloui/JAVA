/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Entities.Article;
import blog.Services.PostService;
import com.itextpdf.text.Document;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * FXML Controller class
 *
 * @author aloui
 */
public class ShowPostsController implements Initializable {
public static int i=0 ;

  
    /**
     * Initializes the controller class.
     */
@FXML
    private AnchorPane Empty;
@FXML 
private BorderPane borderPost;
 @FXML
    private Label rateP;
 @FXML
    private Label postNbr;
 
   @FXML
    private Label commentP;
   
 @FXML 
 private Button ajoutPP;
@FXML
    private Button exit;

    @FXML
    private Label descP;

    @FXML
    private Label dateP;

    @FXML
    private Button titleP;

    @FXML
    private ImageView imageP;

    @FXML
    private Button newtP;

    @FXML
    private Button PreviousP;
      private static   List<Article>   posts; 
      
      /* public void playSound(String fileName) {

        String path = getClass().getResource(fileName).getPath().replace("%20", " ");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
    


        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    }*/


    public void initialize(URL url, ResourceBundle rb) {
       //  ajoutPP.setVisible(false);
         
    try {
        ShowPostsController.posts=new PostService().getAll();
        Collections.reverse(ShowPostsController.posts);
    } catch (SQLException ex) {
        Logger.getLogger(ShowPostsController.class.getName()).log(Level.SEVERE, null, ex);
    } 

 //showPost();
 
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();
  
    }   
    
    PostService ps = new PostService();
           Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
   
 
    i++;
    if( ShowPostsController.posts.size()==i){
        i=0;
        
        showPost();
        
    }else{
        showPost();
        
        
    }
   
}));
 public void showPost(){   
      
       
        
        try {
          
           
            if( ShowPostsController.posts.isEmpty()){
            Empty.getChildren().clear();
            Pane pane = new Pane();
            Label label2 = new Label("Sorry, no Post for today.");
            label2.setAlignment(Pos.CENTER);
label2.setLayoutY(69.0);
label2.setPrefHeight(93.0);
label2.setPrefWidth(458.0);
label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1 0 0 0;");

label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
pane.getChildren().addAll(label2,ajoutPP);
Empty.getChildren().add(pane);
            }else{
            
            Article post = ShowPostsController.posts.get(i);
            int NbrComments =  ps.showComments(ShowPostsController.posts.get(i).getId()).size();
            if(post.getNbr_place()==0){commentP.setText("Complet!");}else{
            commentP.setText(String.valueOf("Places disponibles: "+ShowPostsController.posts.get(i).getNbr_place()));}
            postNbr.setText(i+1 + "#");
            titleP.setText(post.getTitle());
           dateP.setText(post.getDate_post());
           descP.setText(post.getDetails()); 
           Image image = new Image("file:/C:\\Users\\aloui\\Desktop\\WeGym\\public\\uploads\\images\\"+post.getImage());
      
       imageP.setImage(image);
        if(post.getNbrRate()==0){
        rateP.setText("Rate: "+String.valueOf((int)post.getNbrRate())+"/5");
        }else{
             DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText("Rate: "+String.valueOf(df.format(post.getNbrRate())) + "/5");
        }
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
 
  @FXML 
    private Button retour;
    
     @FXML
        private void GoToF(javafx.event.ActionEvent event) throws IOException 
       {
           /* String fileNamee = "../Sound/start.mp3";
            playSound(fileNamee);*/
          
                   
       }
 
@FXML
 public void nextPost(){   
      i++;
       
      
        try {
            
            if(ShowPostsController.posts.size()==i){
                i=0;
                
            showPost();
            }else{
            Article post = ShowPostsController.posts.get(i);
            int NbrComments =  ps.showComments(ShowPostsController.posts.get(i).getId()).size();
            commentP.setText(String.valueOf("Places disponibles: "+ShowPostsController.posts.get(i).getNbr_place()));
             postNbr.setText(i+1 + "#");
            titleP.setText(post.getTitle());
           dateP.setText(post.getDate_post());
            Image image = new Image(post.getImage());
      
       imageP.setImage(image);
           descP.setText(post.getDetails());
           
          
         //RATING 
         
         
         if(post.getNbrRate()==0){
        rateP.setText("Rate: "+String.valueOf((int)post.getNbrRate())+"/5");
        }else{
             DecimalFormat df = new DecimalFormat("#.0");              
        rateP.setText("Rate: "+String.valueOf(df.format(post.getNbrRate())) + "/5");
        }
            System.out.println(post.getDate_post());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
 @FXML
 public void PreviousPost(){   
      i--;
       
        try {
           
            if(i<0){
               
                 i=ShowPostsController.posts.size()-1;
                
            showPost();
            
            }else{
            Article post = ShowPostsController.posts.get(i);
            int NbrComments =  ps.showComments(ShowPostsController.posts.get(i).getId()).size();
            commentP.setText(String.valueOf("Places disponibles: "+ShowPostsController.posts.get(i).getNbr_place()));
             postNbr.setText(i+1 + "#");
            titleP.setText(post.getTitle());
           dateP.setText(post.getDate_post());
            Image image = new Image(post.getImage());
      
       imageP.setImage(image);
           descP.setText(post.getDetails());
            if(post.getNbrRate()==0){
        rateP.setText("Rate: "+String.valueOf((int)post.getNbrRate())+"/5");
        }else{
             DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText("Rate: "+String.valueOf(df.format(post.getNbrRate())) + "/5");
        }
            System.out.println(post.getDate_post());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

 
 
 
 
 @FXML
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
 
 @FXML
    private void AjoutPost(javafx.event.ActionEvent event) throws IOException {
          timeline.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/blog/GUI/AjoutPost.fxml"));
        Parent root = loader.load();
        ajoutPP.getScene().setRoot(root);
      

    }
    
   @FXML
   
     private void detailP() throws SQLException {
        try {
              timeline.stop();
              List<Article> posts;
            posts = new PostService().getAll();
             Collections.reverse(posts);
             Article fournisseur = posts.get(i);
             BlogTestController b =new BlogTestController();
              b.setIdP(fournisseur.getId());
            URL url = getClass().getResource("/blog/GUI/BlogTest.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            BlogTestController controller = loader.getController();
            controller.setFournisseur(fournisseur);
            controller.setPostt(fournisseur);
           

          
            Scene scene = new Scene(root);
            Stage stage = (Stage) titleP.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 


    
    
    public static void generatePDF( Article c) throws Exception {
    Document document = new Document();
    String fileName = "Evenement" + c.getId() + ".pdf";

    // Ouvrir une fenêtre de choix de fichier pour sélectionner l'emplacement où enregistrer le fichier PDF
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Pdf pour l'evenement "+ c.getId());
    fileChooser.setInitialFileName(fileName);
    File selectedFile = fileChooser.showSaveDialog(null);

    if (selectedFile != null) {
        // Enregistrer le fichier PDF à l'emplacement sélectionné
        PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
        document.open();

        // Ajouter les informations du ticket
        Font fontTitre = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
        Paragraph titre = new Paragraph("Nom d'evenement: " + c.getTitle(), fontTitre);
        titre.setAlignment(Element.ALIGN_CENTER);
        titre.setSpacingAfter(20f);
        document.add(titre);

//        Image image = Image.getInstance(evenement.getPhoto());
//        image.setAlignment(Element.ALIGN_CENTER);
//        image.scaleAbsolute(400, 200); // ajuster la taille de l'image en points
//        document.add(image);

        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14); // créer une police avec une taille de 12 points

        Paragraph info = new Paragraph("Description: "+c.getDetails(), font);
        info.setAlignment(Element.ALIGN_CENTER);
        info.setSpacingBefore(20f);
        info.setSpacingAfter(10f);
        document.add(info);

        Paragraph ref = new Paragraph("Date: "+ c.getDate_post(), font);
        ref.setSpacingAfter(5f);
        document.add(ref);

        Paragraph date = new Paragraph("Date fin de participation: " + c.getDate_fin(), font);
        date.setSpacingAfter(5f);
        document.add(date);

        

       
      

        document.close();

        // Ouvrir le fichier PDF une fois qu'il est enregistré
        Desktop.getDesktop().open(selectedFile);
    }
}
    
}
