/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idcardsystem;

import java.sql.*;
import java.text.SimpleDateFormat;


/**
 *
 * @author arunt
 */
public class Students {

    String roll_no;
    //static SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");

    int total_class_MFC2;
    int total_class_DSA1;
    int total_class_JAVA;
    int total_class_EOC2;
    int total_class_EEE;
    int total_class_UID;
    int total_class_GGI;
    int total_class_HARDWARE;
    int attended_class_MFC2;
    int attended_class_DSA1;
    int attended_class_JAVA;
    int attended_class_EOC2;
    int attended_class_EEE;
    int attended_class_UID;
    int attended_class_GGI;
    int attended_class_HARDWARE;
    int percentage_MFC2;
    int percentage_DSA1;
    int percentage_JAVA;
    int percentage_EOC2;
    int percentage_EEE;
    int percentage_UID;
    int percentage_GGI;
    int percentage_HARDWARE;
    
    int total_class_MFC1;
    int total_class_DM;
    int total_class_EOC1;
    int total_class_PHY;
    int total_class_MAOM;
    int total_class_C_PROGRAM;
    int total_class_TECHCOM;
    int total_class_FIH;
    int attended_class_MFC1;
    int attended_class_DM;
    int attended_class_EOC1;
    int attended_class_PHY;
    int attended_class_MAOM;
    int attended_class_C_PROGRAM;
    int attended_class_TECHCOM;
    int attended_class_FIH;
    int percentage_MFC1;
    int percentage_DM;
    int percentage_EOC1;
    int percentage_PHY;
    int percentage_MAOM;
    int percentage_C_PROGRAM;
    int percentage_TECHCOM;
    int percentage_FIH;

    Students(String roll_no) {
        this.roll_no = roll_no;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("Show tables");
            System.out.println("Tables in the current database: ");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.println();
                String tablename=rs.getString(1);
                
                String[] split = tablename.split("_");
                if(split.length<5){
                    System.out.println("less tha5");
                    continue;
                    
                }
                if (split[4].equals("sem1")) {
                    
                    Statement stmt2 = con.createStatement();
                    String sql = "SELECT * FROM "+tablename+" WHERE ROLL_NUMBER='"+roll_no+"';";
                    System.out.println(sql);
                    ResultSet rs2 = stmt2.executeQuery(sql);
                    while (rs2.next()) {
                        System.out.println(rs2.getString("MFC"));
                        if(rs2.getString("MFC1").equals("PRESENT")){
                            System.out.println("less");
                            total_class_MFC1++;attended_class_MFC1++;
                        }else if(rs2.getString("MFC1").equals("ABSENT")){
                            total_class_MFC1++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("DM").equals("PRESENT")){
                            total_class_DM++;attended_class_DM++;
                            System.out.println("udcbuya");
                        }else if(rs2.getString("DM").equals("ABSENT")){
                            total_class_DM++;
                            System.out.println("asnbcb manscb");
                        }
                        
                        if(rs2.getString("PHY").equals("PRESENT")){
                            total_class_PHY++;attended_class_PHY++;
                            System.out.println("less");
                        }else if(rs2.getString("PHY").equals("ABSENT")){
                            total_class_PHY++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("EOC1").equals("PRESENT")){
                            total_class_EOC1++;attended_class_EOC1++;
                            System.out.println("less");
                        }else if(rs2.getString("EOC1").equals("ABSENT")){
                            total_class_EOC1++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("MAOM").equals("PRESENT")){
                            total_class_MAOM++;attended_class_MAOM++;
                            System.out.println("less");
                        }else if(rs2.getString("MAOM").equals("ABSENT")){
                            total_class_MAOM++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("C_PROGRAM").equals("PRESENT")){
                            total_class_C_PROGRAM++;attended_class_C_PROGRAM++;
                            System.out.println("less");
                        }else if(rs2.getString("C_PROGRAM").equals("ABSENT")){
                            total_class_C_PROGRAM++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("TECHCOM").equals("PRESENT")){
                            total_class_TECHCOM++;attended_class_TECHCOM++;
                            System.out.println("less");
                        }else if(rs2.getString("TECHCOM").equals("ABSENT")){
                            total_class_TECHCOM++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("FIH").equals("PRESENT")){
                            total_class_FIH++;attended_class_FIH++;
                            System.out.println("less");
                        }else if(rs2.getString("FIH").equals("ABSENT")){
                            total_class_HARDWARE++;
                            System.out.println("less");
                        }
                        
                    }
                    System.out.println("lessggg");
                    
                }
                if (split[4].equals("sem2")) {
                    
                    Statement stmt2 = con.createStatement();
                    String sql = "SELECT * FROM "+tablename+" WHERE ROLL_NUMBER='"+roll_no+"';";
                    System.out.println(sql);
                    ResultSet rs2 = stmt2.executeQuery(sql);
                    while (rs2.next()) {
                        System.out.println(rs2.getString("MFC"));
                        if(rs2.getString("MFC").equals("PRESENT")){
                            System.out.println("less");
                            total_class_MFC2++;attended_class_MFC2++;
                        }else if(rs2.getString("MFC").equals("ABSENT")){
                            total_class_MFC2++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("DSA").equals("PRESENT")){
                            total_class_DSA1++;attended_class_DSA1++;
                            System.out.println("udcbuya");
                        }else if(rs2.getString("DSA").equals("ABSENT")){
                            total_class_DSA1++;
                            System.out.println("asnbcb manscb");
                        }
                        
                        if(rs2.getString("JAVA").equals("PRESENT")){
                            total_class_JAVA++;attended_class_JAVA++;
                            System.out.println("less");
                        }else if(rs2.getString("JAVA").equals("ABSENT")){
                            total_class_JAVA++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("EOC").equals("PRESENT")){
                            total_class_EOC2++;attended_class_EOC2++;
                            System.out.println("less");
                        }else if(rs2.getString("EOC").equals("ABSENT")){
                            total_class_EOC2++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("EEE").equals("PRESENT")){
                            total_class_EEE++;attended_class_EEE++;
                            System.out.println("less");
                        }else if(rs2.getString("EEE").equals("ABSENT")){
                            total_class_EEE++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("UID").equals("PRESENT")){
                            total_class_UID++;attended_class_UID++;
                            System.out.println("less");
                        }else if(rs2.getString("UID").equals("ABSENT")){
                            total_class_UID++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("GGI").equals("PRESENT")){
                            total_class_GGI++;attended_class_GGI++;
                            System.out.println("less");
                        }else if(rs2.getString("GGI").equals("ABSENT")){
                            total_class_GGI++;
                            System.out.println("less");
                        }
                        
                        if(rs2.getString("HARDWARE").equals("PRESENT")){
                            total_class_HARDWARE++;attended_class_HARDWARE++;
                            System.out.println("less");
                        }else if(rs2.getString("HARDWARE").equals("ABSENT")){
                            total_class_HARDWARE++;
                            System.out.println("less");
                        }
                        
                    }
                    System.out.println("lessggg");
                    
                }
                
            }
            //(Obtained score x 100) / Total Score;
                percentage_C_PROGRAM=(attended_class_C_PROGRAM*100)/total_class_C_PROGRAM;
                percentage_MFC1=(attended_class_MFC1*100)/total_class_MFC1;
                percentage_PHY=(attended_class_PHY*100)/total_class_PHY;
                percentage_EOC1=(attended_class_EOC1*100)/total_class_EOC1;
                percentage_MAOM=(attended_class_MAOM*100)/total_class_MAOM;
                percentage_TECHCOM=(attended_class_TECHCOM*100)/total_class_TECHCOM;
                percentage_DM=(attended_class_DM*100)/total_class_DM;
                percentage_FIH=(attended_class_FIH*100)/total_class_FIH;
                
                
                percentage_MFC2=(attended_class_MFC2*100)/total_class_MFC2;
                percentage_DSA1=(attended_class_DSA1*100)/total_class_DSA1;
                percentage_EOC2=(attended_class_EOC2*100)/total_class_EOC2;
                percentage_EEE=(attended_class_EEE*100)/total_class_EEE;
                percentage_UID=(attended_class_UID*100)/total_class_UID;
                percentage_GGI=(attended_class_GGI*100)/total_class_GGI;
                percentage_HARDWARE=(attended_class_HARDWARE*100)/total_class_HARDWARE;

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
