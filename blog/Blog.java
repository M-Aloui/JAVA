/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import blog.Services.soundService;
import blog.Entities.Article;
import blog.Services.PostService;
import java.io.File;
import javafx.application.Application;
import java.io.IOException;
import java.sql.*;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author aloui
 */
public class Blog extends Application {

    /*
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        //
        /*String clipPath;
        clipPath = "C:\\Users\\aloui\\Desktop\\Hama\\src\\blog\\Sound\\start.mp3";
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(clipPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
             clip.start();*/
        
        
        // SOUND 
        
      String path = "C:\\Users\\aloui\\Desktop\\Hama\\src\\blog\\Sound\\start.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
    


        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        
        
        
      launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
       



        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/ShowPosts.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("GUI/navsidebar.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setHeight(960);
      primaryStage.setWidth(1210);
       
         
        primaryStage.show();
//       String fileNamee = "../Sound/hmf.mp3";
// BackOfficeHomeController boc = new BackOfficeHomeController();
//boc.playSound(fileNamee);
    }

}
