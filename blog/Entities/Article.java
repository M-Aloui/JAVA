/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.Entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author aloui
 */
public class Article {
    private int id ;
    private String title;
    private String details;
    private String image;
    private String date_post;
    private int rate;
    private double nbrRate;
    private String date_fin;
    private int nbr_place;
    private int prix;

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    

    public Article() {
    }

    public Article(int id, String title, String details, String image, String date_post, int rate,double nbrRate,int nbr_place,int prix,String date_fin) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = date_post;
        this.rate = rate;
        this.nbrRate=nbrRate;
        this.date_fin=date_fin;
        this.nbr_place=nbr_place;
        this.prix=prix;
    }
 

    public Article(String title, String details, String image, String date_post, int rate, double nbrRate,int nbr_place,int prix,String date_fin) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = date_post;
        this.rate = rate;
        this.nbrRate = nbrRate;
         this.date_fin=date_fin;
        this.nbr_place=nbr_place;
        this.prix=prix;
    }
    
    public void setNbrRate(double a){
    this.nbrRate=a;
    }
 public double getNbrRate() {
        return nbrRate;
    }

    public void setNbrRate(int nbrRate) {
        this.nbrRate = nbrRate;
    }
    public Article(String title, String details,String image,int nbr_place,int prix) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = LocalDate.now().toString();
        this.rate = 0;
        this.nbrRate=0;
        this.date_fin = LocalDate.now().plusDays(10).toString();
        this.nbr_place=nbr_place;
        this.prix=prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", details=" + details + ", image=" + image + ", date_post=" + date_post + ", rate=" + rate + ", nbrRate=" + nbrRate + ", date_fin=" + date_fin + ", nbr_place=" + nbr_place + ", prix=" + prix + '}';
    }

   

    
    
    
    
    
    
    
}
