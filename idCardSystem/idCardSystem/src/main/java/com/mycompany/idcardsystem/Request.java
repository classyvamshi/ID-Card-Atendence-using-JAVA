
package com.mycompany.idcardsystem;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author arunt
 */
public class Request {
    String Certificate_path;
    String Roll_no;
    String Name;
    String Reason;
    String From_date;
    String To_date;
    int sem;
    boolean success;
    byte[] Certificate;

    public Request(){
        
    }
    public Request(String Certificate_path, String Roll_no, String Name, String Reason, String From_date, String To_date,int sem) {
        this.Certificate_path = Certificate_path;
        this.Roll_no = Roll_no;
        this.Name = Name;
        this.Reason = Reason;
        this.From_date = From_date;
        this.To_date = To_date;
        this.sem = sem;
        
         try {
            File f =new File(Certificate_path);
            InputStream Certificate=new FileInputStream(f);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            
             System.out.println(Roll_no);
             System.out.println(Name);
             System.out.println(From_date);
             System.out.println(To_date);
             System.out.println(Reason);
            PreparedStatement pst = con.prepareStatement("INSERT INTO requests (Roll_no,Name,From_date,To_date,sem,Reason,Certificate) VALUES (?,?,?,?,?,?,?)");
            pst.setString(1, Roll_no);
            pst.setString(2, Name);
            pst.setString(3, From_date);
            pst.setString(4, To_date);
            pst.setInt(5, sem);
            pst.setString(6, Reason);
            pst.setBlob(7, Certificate);
            
            
            
            int Inserted =pst.executeUpdate();
            if(Inserted>0){
                success=true;
            }else{
                success=false;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static String[][] getRequests(){
        String[][] requests = new String[100][2];
        requests[0][0]="";
        
        try {
            int i =0,j=0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();
            
            String sql = "SELECT Request_no, Roll_no FROM requests;";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               
                requests[j][0]=rs.getString("Request_no");
                requests[i][1]=rs.getString("Roll_no");
                System.out.println(requests[j][0]);
                j++;
                i++;
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return requests;
    }
    public Request(int index){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM requests WHERE Request_no ="+index+";";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                requests[i]=rs.getString("Roll_no");
//                i++;
                Roll_no=rs.getString("Roll_no");
                Name=rs.getString("Name");
                From_date=rs.getString("From_date");
                To_date=rs.getString("To_date");
                sem=rs.getInt("sem");
                Reason=rs.getString("Reason");
                Certificate=rs.getBytes("Certificate");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    
    
    
}
