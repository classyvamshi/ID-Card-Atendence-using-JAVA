    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idcardsystem;


import java.awt.Color;
import java.sql.*;
import javax.swing.JFrame;



/**
 *
 * @author arunt
 */
public class Authentication extends JFrame {
    public static void userlogin(String Username , String Password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            
            String ext_password=" ";
            boolean accesslevel=true;
            boolean usernotfounderr= false;
            
            String QUERY = "SELECT password, admin FROM users WHERE username='"+Username+"'";
            System.out.println(QUERY);
            Statement stmt = con.createStatement();
           
            ResultSet rs = stmt.executeQuery(QUERY);
            login frame =new login();
            
            if(rs.next()){
                ext_password=rs.getString("password");
                accesslevel=rs.getBoolean("admin");
                System.out.println(accesslevel);
            }
            else{
                //System.out.println("not found");
                frame.jLabel6.setText("Username not found");
                frame.jLabel6.setForeground(Color.red);
                frame.setVisible(true);
                usernotfounderr=true;             
            }
                        
            if(Password.equals(ext_password)){
                if(accesslevel){
                    admin_dashboard d=new admin_dashboard();
                    d.setTitle("DashBoard");
                    d.setVisible(true);
                }
                else if(!accesslevel){
                    //System.out.println("ACNSJKKKJKSCNKJNCKJN");
                    students_dashboard d=new students_dashboard(Username);
                    d.setTitle("DashBoard");
                    d.setVisible(true);
                }   
            }
            else if (!usernotfounderr) {
                frame.jLabel7.setText("Incorrect password");
                frame.jLabel7.setForeground(Color.red);
                frame.setVisible(true);   
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }  
    }
    
    public static boolean register(String Id,String Name,String Username , String Password, String adminkey){
        boolean regsuccess=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();
            String sql = "";
            
            if(!adminkey.equals("NOT_ADMIN")&& adminkey.equals("1234")){
                sql = "INSERT INTO users VALUES ('"+Id+"','"+Name+"', '"+Username+"','"+Password+"','"+1+"')"; 
            }
            else{
                sql = "INSERT INTO users VALUES ('"+Id+"','"+Name+"', '"+Username+"','"+Password+"','"+0+"')";
            }
           
            stmt.executeUpdate(sql);
            regsuccess=true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
     return regsuccess;   
    }
    
}
