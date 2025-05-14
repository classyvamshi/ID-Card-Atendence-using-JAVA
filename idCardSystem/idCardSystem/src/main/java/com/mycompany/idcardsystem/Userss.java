/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idcardsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author arunt
 */
public class Userss {
    String ID;
    String Name;
    String Username;
    boolean Admin;
    
    Userss(String username) {
    this.Username = username;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();

            System.out.println(ID);
            String sql = "SELECT Username, ID, Name, Admin  FROM users WHERE Username='"+Username+"';";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Name=rs.getString("name");
                ID=rs.getString("username");
                Admin=rs.getBoolean("admin"); 
            }
            System.out.println("Name: "+Name);
            System.out.println("ID"+ ID);
            System.out.println("Admin:"+Admin);
            
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
