/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Entities.Comment;
import blog.Entities.Article;
import static blog.GUI.AjoutPostController.filterWords;
import blog.Services.CommentService;
import blog.Services.PostService;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.waiting;

/**
 * FXML Controller class
 *
 * @author aloui
 */
public class BlogTestController implements Initializable {
private static int i=0 ;
private static Comment commentaire=new Comment();
    @FXML
    private Label rateP;

    @FXML
    private Button returnP;
    @FXML
    private Button exit;

    @FXML
    private Label descP;

    @FXML
    private Label dateP;
    
    @FXML
    private ImageView PicP;

    @FXML
    private Button titleeP;
     @FXML
    private Button modiff;
    
    @FXML 
    private VBox VV;
    
    @FXML
    private Label nbrp;
     
    @FXML
    private Label datef;

    @FXML
    private Label prixxx;
     @FXML
    private Label titre;



    public static Article getPostt() {
        return postt;
    }

    public static void setPostt(Article postt) {
        BlogTestController.postt = postt;
    }
    
      private static Article postt;
    private static int idP;
    @FXML
    private AnchorPane AA;
    @FXML
    private Button clear;
    @FXML
    private Button val1;
    @FXML
    private Button val2;
    @FXML
    private Button val3;
    @FXML
    private Button val4;
    @FXML
    private Button val5;
    @FXML
    private VBox ContainerVBox;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
   
      
            
        //titleeP.setDisable(true);
      //suppPost.setVisible(false);
//        File file = new File(path);
//        System.out.println(file.toURI().toString());

//        PicP.setPreserveRatio(true);
//PicP.fitWidthProperty().bind(PP.widthProperty());
//PicP.fitHeightProperty().bind(PP.heightProperty());

    
//System.out.println(path);

         tfCom.setPromptText("Add your comment..");
       BlogTestController.i=0;
        try {
            ShowComments();
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
      System.out.println(i);
       
    }

    @FXML
    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale-
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
@FXML 
private BorderPane PP;
    public void setFournisseur(Article fournisseur) {
titre.setText(fournisseur.getTitle()+" détail:");
        prixxx.setText(String.valueOf(fournisseur.getPrix()+" DT"));
      
           if(fournisseur.getNbr_place()==0){
               part.setDisable(true);
               nbrp.setText("Complet!");
           }
        nbrp.setText(String.valueOf("Places diponibles: "+fournisseur.getNbr_place()));
        dateP.setText(fournisseur.getDate_fin());
        titleeP.setText(fournisseur.getTitle());
        descP.setText(fournisseur.getDetails());
        datef.setText("Date fin de participation:\n"+fournisseur.getDate_post());
         if(fournisseur.getNbrRate()==0){
        rateP.setText(String.valueOf((int)fournisseur.getNbrRate())+"/5");
        }else{
            DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(fournisseur.getNbrRate())) + "/5");
        }
        
        System.out.println(fournisseur.getImage());
        Image image = new Image("file:/C:\\Users\\aloui\\Desktop\\WeGym\\public\\uploads\\images\\"+fournisseur.getImage());
      
       PicP.setImage(image);
    PicP.setPreserveRatio(true);
    }

    public static int getIdP() {
        return idP;
    }

    public static void setIdP(int idP) {
        BlogTestController.idP = idP;
    }

    @FXML
    private void retourBlog(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/blog/GUI/ShowPosts.fxml"));
        Parent root = loader.load();
        returnP.getScene().setRoot(root);

    }

    @FXML
    private void val1() throws SQLException {
        
       PostService ps = new PostService();
        Article post = ps.getOneById(idP);
          int nbr = post.getRate();
        double moy= post.getNbrRate();
        double summ = nbr * moy;
        
            post.setRate(nbr+1);
      
        post.setNbrRate((summ+1)/post.getRate());
        ps.update(post, idP);
         DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");

    }

    @FXML
    private void val2() throws SQLException {

         PostService ps = new PostService();
        Article post = ps.getOneById(idP);
          int nbr = post.getRate();
        double moy= post.getNbrRate();
        double summ = nbr * moy;
            post.setRate(nbr+1);
      
        post.setNbrRate((summ+2)/post.getRate());
        ps.update(post, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");

    }

    @FXML
    private void val3() throws SQLException {

      PostService ps = new PostService();
        Article post = ps.getOneById(idP);
          int nbr = post.getRate();
        double moy= post.getNbrRate();
        double summ = nbr * moy;
            post.setRate(nbr+1);
      
        post.setNbrRate((summ+3)/post.getRate());
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");


    }

    @FXML
    private void val4() throws SQLException {

       PostService ps = new PostService();
        Article post = ps.getOneById(idP);
          int nbr = post.getRate();
        double moy= post.getNbrRate();
        double summ = nbr * moy;
            post.setRate(nbr+1);
      
        post.setNbrRate((summ+4)/post.getRate());
        ps.update(post, idP);
         DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");

    }

    @FXML
    private void val5() throws SQLException {

        PostService ps = new PostService();
        Article post = ps.getOneById(idP);
          int nbr = post.getRate();
        double moy= post.getNbrRate();
        double summ = nbr * moy;
            post.setRate(nbr+1);
      
        post.setNbrRate((summ+5)/post.getRate());
        ps.update(post, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");


    }
     @FXML
     
     //PARTICIPATION 
     
    private Button part;
@FXML 
private void participate() throws SQLException{
      PostService ps = new PostService();

   Article p = ps.getOneById(idP);
  
int a =p.getNbr_place()-1;
   p.setNbr_place(a);
   ps.update(p, idP);
    if(p.getNbr_place()==0){part.setDisable(true);
    nbrp.setText("Complet!");
    }else{
   nbrp.setText(null);
    nbrp.setText("Places diponibles: "+String.valueOf(p.getNbr_place()));
    }
}
    @FXML
    private void clearValue() throws SQLException {

        PostService ps = new PostService();

        Article post = ps.getOneById(idP);
        post.setRate(0);
        post.setNbrRate(0);
        ps.update(post, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf((int)post.getNbrRate()) + "/5");

    }

    @FXML
    private void modifyP() throws SQLException {
ModifPostController ms = new ModifPostController();
ms.setIdP(idP);
        // Load the "ModifFournisseur.fxml" file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifPost.fxml"));
        try {
            PostService ps = new PostService();

            Article post = ps.getOneById(idP);
          
            Parent root = loader.load();
            ModifPostController controller = loader.getController();

            // Set the text fields in the "ModifFournisseur.fxml" file with the values from the labels
            controller.tfPrix.setText(String.valueOf(post.getPrix()));
            controller.nbrPlace.setText(String.valueOf(post.getNbr_place()));
           controller.titlemodP.setText(post.getTitle());
            controller.descmodP.setText(post.getDetails());
controller.setPostt(post);
System.out.println(controller.getPostt().toString());
            // Show the "ModifFournisseur.fxml" file in a new stage
            Scene scene = modiff.getScene();
            scene.setRoot(root);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  @FXML
    private Button suppPost;
  
  
  
  
      @FXML
    private void DeleteP(javafx.event.ActionEvent event) throws IOException, SQLException {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Post");
        alert.setHeaderText("Are you sure you want to delete this post?");
        alert.setContentText("This action is required!");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // If the user clicks "OK", delete the fournisseur
        if (result.get() == ButtonType.OK) {
           
            PostService fs = new PostService();
            fs.delete(postt.getId());
             ShowPostsController.i=0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ShowPosts.fxml"));
            Parent root = loader.load();
            suppPost.getScene().setRoot(root);
        } else {
            // Close the dialog and do nothing
            alert.close();
        }

    }
    @FXML
    private TextArea tfCom;

    @FXML
    private Button ajoutCom;
     @FXML
    private Button AnnulerCom;
     @FXML
     private void annulerC(){
         BlogTestController.i=0;
         tfCom.setPromptText("Add your comment..");
           tfCom.clear();
           System.out.println(BlogTestController.i);
     }
    
 private boolean verifs() {
if (tfCom.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Le champ commentaire est obligatoire !");
        return false;
    }
     return true;
}
  @FXML
    private ScrollPane scrollCom;
    
  @FXML
    private VBox VBoxCom;
  
  @FXML
    private void newComment(ActionEvent event) throws SQLException, IOException {
        PostService utilisateurService = new PostService();
         CommentService cs = new CommentService();
    
        if(i==0){
               if (verifs()) {
        Comment p = new Comment(filterWords(tfCom.getText()),postt.getId());
         cs.create(p);
               
           tfCom.setPromptText("Add your comment..");
           tfCom.clear();
            System.out.println(BlogTestController.i); 
          try {
            VBoxCom.getChildren().clear();
            ShowComments();
          
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
               }
              
        }else {
        if (verifs()) {
        BlogTestController.commentaire.setDecription(filterWords(tfCom.getText()));
         cs.update(BlogTestController.commentaire,BlogTestController.commentaire.getId());
           tfCom.setPromptText("Add your comment..");
           tfCom.clear();
         
           BlogTestController.i=0;
           System.out.println(BlogTestController.i);
          try {
            VBoxCom.getChildren().clear();
            ShowComments();
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        }
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
    
    
    private void ShowComments() throws SQLException{
        PostService ps = new PostService();
   List<Comment> comments = ps.showComments(idP);
    Collections.reverse(comments);
    
if(comments.size()==0){
 Pane pane = new Pane();
pane.setLayoutX(10.0);
pane.setLayoutY(30.0);
pane.setPrefHeight(208.0);
pane.setPrefWidth(458.0);
pane.setStyle("-fx-border-color: #ffffff;");

Label label2 = new Label("No comments found for this post");
label2.setAlignment(Pos.CENTER);
label2.setLayoutY(10.0);
label2.setPrefHeight(93.0);
label2.setPrefWidth(458.0);
label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0 0 0 0;");
label2.setFont(Font.font("Calibri Italic", 30));
label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));

pane.getChildren().addAll(label2);
VBoxCom.getChildren().add(pane);
scrollCom.setContent(VBoxCom);
}else{
    for(int i=0;i<comments.size();i++){
        Comment com = comments.get(i);
        System.out.println(com.toString());
      Pane pane = new Pane();
pane.setLayoutX(10.0);
pane.setLayoutY(30.0);
pane.setPrefHeight(208.0);
pane.setPrefWidth(458.0);
pane.setStyle("-fx-border-color: #DCDCDC;");

Label label1 = new Label("User");
label1.setLayoutX(76.0);
label1.setLayoutY(12.0);
label1.setPrefHeight(44.0);
label1.setPrefWidth(163.0);
label1.setFont(Font.font("Calibri Bold Italic", 20));

ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../Image/comment_2.png")));
imageView.setFitHeight(53.0);
imageView.setFitWidth(78.0);
imageView.setLayoutX(14.0);
imageView.setLayoutY(9.0);
imageView.setPreserveRatio(true);


Label label2 = new Label(com.getDecription());
label2.setAlignment(Pos.TOP_LEFT);
label2.setLayoutY(69.0);
label2.setPrefHeight(93.0);
label2.setPrefWidth(458.0);
label2.setStyle("-fx-border-color: #DCDCDC; -fx-border-width: 1 0 0 0;");
label2.setFont(Font.font("Calibri Italic", 20));
label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));

Label label3 = new Label(com.getDateCom());
label3.setLayoutX(14.0);
label3.setLayoutY(163.0);
label3.setPrefHeight(23.0);
label3.setPrefWidth(186.0);
label3.setFont(Font.font("Calibri Bold Italic", 18));
label3.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));
Button SuppC = new Button();
SuppC.setLayoutX(408.0);
SuppC.setLayoutY(147.0);
SuppC.setMnemonicParsing(false);
SuppC.getStyleClass().add("buttonNext");
ImageView imageSupp = new ImageView(new Image(getClass().getResourceAsStream("../Image/icons8-delete-trash-50.png")));
imageSupp.setFitHeight(24.0);
imageSupp.setFitWidth(26.0);
imageSupp.setPickOnBounds(true);
imageSupp.setPreserveRatio(true);
SuppC.setGraphic(imageSupp);
SuppC.setOnAction(event -> {
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Delete Comment");
        alert.setHeaderText("Are you sure you want to delete this Comment?");
        alert.setContentText("This action is required!");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // If the user clicks "OK", delete the fournisseur
        if (result.get() == ButtonType.OK) {
            CommentService fs = new CommentService();
       try {
           fs.delete(com.getId());
            BlogTestController.i=0;
         tfCom.setPromptText("Add your comment..");
           tfCom.setText(null);
           System.out.println(BlogTestController.i);
           VBoxCom.getChildren().clear();
            ShowComments(); 
       } catch (SQLException ex) {
           Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
       }
           
        } else {
            // Close the dialog and do nothing
            alert.close();
        }
});

Button ModC = new Button();
ModC.setLayoutX(380.0);
ModC.setLayoutY(147.0);
ModC.setMnemonicParsing(false);
ModC.getStyleClass().add("buttonNext");
ImageView imageMod = new ImageView(new Image(getClass().getResourceAsStream("../Image/a.gif")));
imageMod.setFitHeight(24.0);
imageMod.setFitWidth(26.0);
imageMod.setPickOnBounds(true);
imageMod.setPreserveRatio(true);
ModC.setGraphic(imageMod);
ModC.setOnAction(event -> {
tfCom.setText(com.getDecription());
tfCom.setPromptText("Modify "+com.getDecription()+".." );
BlogTestController.i=1;
int a =BlogTestController.i;
System.out.println(a);
BlogTestController.commentaire=com;
System.out.println(BlogTestController.commentaire);
});
pane.getChildren().addAll(label1, imageView, label2, label3,SuppC,ModC);

VBoxCom.getChildren().add(pane);
//vBox.setPadding(new Insets(20.0));
     

    }
    scrollCom.setContent(VBoxCom);
    }}
    
    @FXML 
    public  void PDF(){
    
    try {
        ShowPostsController.generatePDF(postt);
    } catch (Exception ex) {
        Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
}
