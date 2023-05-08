/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.DbConfig;

import java.sql.*;


/**
 *
 * @author aloui
 */
public class MyDB {
      final String url="jdbc:mysql://localhost:3306/wegym";
    final String username="root";
    final String pwd="";
    private Connection conx;
    
    public static MyDB instance;
    
    
    public static MyDB getInstance(){
        if (instance == null)
           instance = new MyDB();
        return instance;
        
    }
    public MyDB(){
        
        try {
            conx = DriverManager.getConnection(url, username, pwd);
            System.out.println("Blog is ready");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

    public Connection getConx() {
        return conx;
    }
    
}
