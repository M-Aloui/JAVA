/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.Services;

import blog.DbConfig.MyDB;
import blog.Entities.Comment;
import blog.Entities.Article;
import blog.Interfaces.BlogCRUD;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aloui
 */
public class PostService implements BlogCRUD<Article>{
    
      public Connection conx= MyDB.getInstance().getConx();
    public Statement stm;

 public PostService() {
    conx = MyDB.getInstance().getConx();

   }

    @Override
    public void create(Article post) throws SQLException {
String sql = "INSERT INTO evenements (titre, descritption,image,date_deb,rate,moy_rate,date_fin,nbr_place,prix) VALUES (?,?,?,?,?,?,?,?,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDetails());
            pstmt.setString(3, post.getImage());
             pstmt.setString(4, post.getDate_post());
             pstmt.setInt(5, post.getRate());
             pstmt.setDouble(6, post.getNbrRate());
             pstmt.setString(7,post.getDate_fin());
             pstmt.setInt(8, post.getNbr_place());
             pstmt.setDouble(9, post.getPrix());
            pstmt.executeUpdate();
            
                    System.out.println("Post ajouté");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }
     public List<Comment> showComments(int id) throws SQLException {
String sql = "Select * From comment Where post_id_id = ?"; 
try (
             PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


        List<Comment> personnes = new ArrayList<Comment>();
        while (rs.next()) {
            Comment p = new Comment(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("description"),
                    rs.getString("date_com"),
            rs.getInt("post_id_id"));
            personnes.add(p);
        }

        return personnes;
    } catch (SQLException e) {
System.out.println(e.getMessage());
        }
return null;
    }

    @Override
    public Article getOneById(int id) throws SQLException {
String sql = "SELECT id, titre, descritption,image,date_deb,rate,moy_rate,prix , nbr_place,date_fin FROM evenements WHERE id = ?";
        try (
             PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Article person = new Article();
                person.setId(rs.getInt("id"));
                person.setTitle(rs.getString("titre"));
                person.setDetails(rs.getString("descritption"));
                person.setImage(rs.getString("image"));
                person.setDate_post(rs.getString("date_deb"));
                person.setRate(rs.getInt("rate"));
                 person.setNbrRate(rs.getDouble("moy_rate"));
                 person.setDate_fin(rs.getString("date_fin"));
                         person.setNbr_place(rs.getInt("nbr_place"));
                         person.setPrix(rs.getInt("prix"));
                return person;
            }
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Article post, int id) throws SQLException {

        String sql = "UPDATE evenements SET titre = ?, descritption = ?,image = ?,rate = ?, date_deb = ?, moy_rate = ?, nbr_place = ?, prix = ? , date_fin = ? WHERE id = ?";
        try ( 
               
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
             
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDetails());
             pstmt.setString(3, post.getImage());
              pstmt.setInt(4, post.getRate());
               pstmt.setString(5, post.getDate_post());
               pstmt.setDouble(6,post.getNbrRate());
            pstmt.setInt(7, post.getNbr_place());
            pstmt.setInt(8, post.getPrix());
            pstmt.setString(9, post.getDate_fin());
             pstmt.setInt(10, id);
            
            pstmt.executeUpdate();
            System.out.println("Post updated");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {
 String sql = "DELETE FROM evenements WHERE id = ?";
 //String sql = "DELETE FROM post WHERE id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
  String sql = "DELETE FROM evenements";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }
    
    public void deleteAllComments(int id) throws SQLException {
  String sql = "DELETE FROM comment WHERE post_id_id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
             pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Article> getAll() throws SQLException {
String sql = "Select * From evenements";
PreparedStatement pstmt = conx.prepareStatement(sql);

ResultSet rs = pstmt.executeQuery("Select * From evenements");
        List<Article> personnes = new ArrayList<Article>();
        while (rs.next()) {
            Article p = new Article(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("titre"),
                    rs.getString("descritption"),
                    rs.getString("image"),
                    rs.getString("date_deb"),
                    rs.getInt("rate"),
            rs.getDouble("moy_rate"),
            rs.getInt("nbr_place"),
            rs.getInt("prix"),
            rs.getString("date_fin"));
            personnes.add(p);
        }

        return personnes;
    }

    
}
