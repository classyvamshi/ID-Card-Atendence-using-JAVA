/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.idcardsystem;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.text.MessageFormat.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author arunt
 */
public class admin_dashboard extends javax.swing.JFrame {
    
    String[][] requests;
    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
    
    public static boolean checkAttendance(Date date, String Roll_no, int sem, int subject) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        String d = formatter.format(date);
        String tablename = d + "_attendance_sem" + sem;
        String sql = "";
        String attendance = "";
        String[] subjects = {"", "MFC", "EOC", "JAVA", "DSA", "EEE", "GGI", "UID", "HARDWARE"};
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            
            Statement stmt = con.createStatement();
            sql = "SELECT ROLL_NUMBER," + subjects[subject] + " FROM " + tablename + " WHERE ROLL_NUMBER='" + Roll_no + "'; ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                attendance = rs.getString(2);
                System.out.println(attendance);
            }
            if (attendance.equals("PRESENT")) {
                return true;
            }
            con.close();
            return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
    
    public static boolean medAttendance(Date From_date, Date To_date, String Roll_no,String sem) {
        boolean success=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();
            Date currDate = From_date;
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
            String strdate;
            String tableName;
            int day = 7;
            String sql="";
            while (To_date.after(currDate)||To_date.equals(currDate)) {
                strdate=formatter.format(currDate);
                tableName=strdate+"_attendance_sem"+sem;
                day=currDate.getDay();
                switch (day) {
                case 0:
                    //sunday                    
                    break;
                case 1:
                    String[] monday_periods = {"UID", "EOC", "JAVA"};
                    sql ="UPDATE " + tableName + " SET "+monday_periods[0]+"='PRESENT',"+monday_periods[1]+"='PRESENT',"+monday_periods[2]+"='PRESENT' WHERE ROLL_NUMBER='"+Roll_no+"';";
                    break;
                case 2:
                    //tuesday
                    String[] tuesday_periods = {"EEE", "DSA", "MFC"};
                    sql ="UPDATE " + tableName + " SET "+tuesday_periods[0]+"='PRESENT',"+tuesday_periods[1]+"='PRESENT',"+tuesday_periods[2]+"='PRESENT' WHERE ROLL_NUMBER='"+Roll_no+"';";
                    break;
                case 3:
                    //wednesday
                    String[] wednesday_periods = {"MFC", "GGI", "EEE"};
                     sql ="UPDATE " + tableName + " SET "+wednesday_periods[0]+"='PRESENT',"+wednesday_periods[1]+"='PRESENT',"+wednesday_periods[2]+"='PRESENT' WHERE ROLL_NUMBER='"+Roll_no+"';";
                    break;
                case 4:
                    //thursday
                    String[] thursday_periods = {"UID", "GGI", "EOC"};
                     sql ="UPDATE " + tableName + " SET "+thursday_periods[0]+"='PRESENT',"+thursday_periods[1]+"='PRESENT',"+thursday_periods[2]+"='PRESENT' WHERE ROLL_NUMBER='"+Roll_no+"';";
                    break;
                case 5:
                    //friday
                    String[] friday_periods = {"JAVA", "DSA", "HARDWARE"};
                     sql ="UPDATE " + tableName + " SET "+friday_periods[0]+"='PRESENT',"+friday_periods[1]+"='PRESENT',"+friday_periods[2]+"='PRESENT' WHERE ROLL_NUMBER='"+Roll_no+"';";
                    break;
                case 6:
                    //saturday
                    String[] saturday_periods = {"MFC", "GGI", "EEE"};
                     sql ="UPDATE " + tableName + " SET "+saturday_periods[0]+"='PRESENT',"+saturday_periods[1]+"='PRESENT',"+saturday_periods[2]+"='PRESENT' WHERE ROLL_NUMBER='"+Roll_no+"';";
                    break;
            }
                 System.out.println(sql);
                stmt.executeUpdate(sql);
                currDate = findNextDay(currDate);
                 System.out.println(currDate);
                  System.out.println(To_date);
            }
            success=true;
           
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    public void removeRequest(int req_index) {
        String req_no = requests[req_index][0];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");
            Statement stmt = con.createStatement();
            String sql="DELETE FROM requests WHERE Request_no ="+req_no+" ;";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static Date findNextDay(Date date) {
        return new Date(date.getTime() + MILLIS_IN_A_DAY);
    }
    
    public admin_dashboard() {
        initComponents();     // ui components
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        student2 = new javax.swing.JButton();
        student4 = new javax.swing.JButton();
        student3 = new javax.swing.JButton();
        student25 = new javax.swing.JButton();
        student26 = new javax.swing.JButton();
        student27 = new javax.swing.JButton();
        student31 = new javax.swing.JButton();
        student28 = new javax.swing.JButton();
        student32 = new javax.swing.JButton();
        student65 = new javax.swing.JButton();
        student29 = new javax.swing.JButton();
        student33 = new javax.swing.JButton();
        student66 = new javax.swing.JButton();
        student30 = new javax.swing.JButton();
        student34 = new javax.swing.JButton();
        student67 = new javax.swing.JButton();
        student68 = new javax.swing.JButton();
        student69 = new javax.swing.JButton();
        student70 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        student5 = new javax.swing.JButton();
        student6 = new javax.swing.JButton();
        student7 = new javax.swing.JButton();
        student8 = new javax.swing.JButton();
        student9 = new javax.swing.JButton();
        student10 = new javax.swing.JButton();
        student35 = new javax.swing.JButton();
        student36 = new javax.swing.JButton();
        student37 = new javax.swing.JButton();
        student38 = new javax.swing.JButton();
        student39 = new javax.swing.JButton();
        student40 = new javax.swing.JButton();
        student41 = new javax.swing.JButton();
        student42 = new javax.swing.JButton();
        student43 = new javax.swing.JButton();
        student44 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        student45 = new javax.swing.JButton();
        student46 = new javax.swing.JButton();
        student47 = new javax.swing.JButton();
        student11 = new javax.swing.JButton();
        student48 = new javax.swing.JButton();
        student12 = new javax.swing.JButton();
        student49 = new javax.swing.JButton();
        student13 = new javax.swing.JButton();
        student50 = new javax.swing.JButton();
        student14 = new javax.swing.JButton();
        student51 = new javax.swing.JButton();
        student52 = new javax.swing.JButton();
        student53 = new javax.swing.JButton();
        student54 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        student1 = new javax.swing.JButton();
        student15 = new javax.swing.JButton();
        student16 = new javax.swing.JButton();
        student17 = new javax.swing.JButton();
        student18 = new javax.swing.JButton();
        student55 = new javax.swing.JButton();
        student19 = new javax.swing.JButton();
        student56 = new javax.swing.JButton();
        student20 = new javax.swing.JButton();
        student57 = new javax.swing.JButton();
        student21 = new javax.swing.JButton();
        student58 = new javax.swing.JButton();
        student22 = new javax.swing.JButton();
        student59 = new javax.swing.JButton();
        student23 = new javax.swing.JButton();
        student60 = new javax.swing.JButton();
        student24 = new javax.swing.JButton();
        student61 = new javax.swing.JButton();
        student62 = new javax.swing.JButton();
        student63 = new javax.swing.JButton();
        student64 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Requests_ComboBox = new javax.swing.JComboBox<>();
        Request_Label = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Name1_Label = new javax.swing.JLabel();
        Branch1_Label = new javax.swing.JLabel();
        Roll_Number_Label = new javax.swing.JLabel();
        leave_applied_for_Panel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Certificate_Label = new javax.swing.JLabel();
        Approve_button = new javax.swing.JButton();
        Reject_Button = new javax.swing.JButton();
        refresh_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(226, 55, 98));

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMIN DASHBOARD");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        student2.setBackground(new java.awt.Color(255, 102, 102));
        student2.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student2.setText("2");

        student4.setBackground(new java.awt.Color(255, 102, 102));
        student4.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student4.setText("4");

        student3.setBackground(new java.awt.Color(255, 102, 102));
        student3.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student3.setText("3");

        student25.setBackground(new java.awt.Color(255, 102, 102));
        student25.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student25.setText("25");

        student26.setBackground(new java.awt.Color(255, 102, 102));
        student26.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student26.setText("26");

        student27.setBackground(new java.awt.Color(255, 102, 102));
        student27.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student27.setText("27");

        student31.setBackground(new java.awt.Color(255, 102, 102));
        student31.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student31.setText("31");

        student28.setBackground(new java.awt.Color(255, 102, 102));
        student28.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student28.setText("28");

        student32.setBackground(new java.awt.Color(255, 102, 102));
        student32.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student32.setText("32");
        student32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student32ActionPerformed(evt);
            }
        });

        student65.setBackground(new java.awt.Color(255, 102, 102));
        student65.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student65.setText("65");

        student29.setBackground(new java.awt.Color(255, 102, 102));
        student29.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student29.setText("29");

        student33.setBackground(new java.awt.Color(255, 102, 102));
        student33.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student33.setText("33");

        student66.setBackground(new java.awt.Color(255, 102, 102));
        student66.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student66.setText("68");

        student30.setBackground(new java.awt.Color(255, 102, 102));
        student30.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student30.setText("30");

        student34.setBackground(new java.awt.Color(255, 102, 102));
        student34.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student34.setText("34");

        student67.setBackground(new java.awt.Color(255, 102, 102));
        student67.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student67.setText("67");

        student68.setBackground(new java.awt.Color(255, 102, 102));
        student68.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student68.setText("62");

        student69.setBackground(new java.awt.Color(255, 102, 102));
        student69.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student69.setText("70");

        student70.setBackground(new java.awt.Color(255, 102, 102));
        student70.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student70.setText("61");

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel4.setText("Semester");

        jComboBox2.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        student5.setBackground(new java.awt.Color(255, 102, 102));
        student5.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student5.setText("5");

        student6.setBackground(new java.awt.Color(255, 102, 102));
        student6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student6.setText("6");

        student7.setBackground(new java.awt.Color(255, 102, 102));
        student7.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student7.setText("7");

        student8.setBackground(new java.awt.Color(255, 102, 102));
        student8.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student8.setText("8");

        student9.setBackground(new java.awt.Color(255, 102, 102));
        student9.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student9.setText("9");

        student10.setBackground(new java.awt.Color(255, 102, 102));
        student10.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student10.setText("10");

        student35.setBackground(new java.awt.Color(255, 102, 102));
        student35.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student35.setText("35");

        student36.setBackground(new java.awt.Color(255, 102, 102));
        student36.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student36.setText("36");

        student37.setBackground(new java.awt.Color(255, 102, 102));
        student37.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student37.setText("37");

        student38.setBackground(new java.awt.Color(255, 102, 102));
        student38.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student38.setText("38");

        student39.setBackground(new java.awt.Color(255, 102, 102));
        student39.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student39.setText("39");

        student40.setBackground(new java.awt.Color(255, 102, 102));
        student40.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student40.setText("40");

        student41.setBackground(new java.awt.Color(255, 102, 102));
        student41.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student41.setText("41");

        student42.setBackground(new java.awt.Color(255, 102, 102));
        student42.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student42.setText("42");

        student43.setBackground(new java.awt.Color(255, 102, 102));
        student43.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student43.setText("43");

        student44.setBackground(new java.awt.Color(255, 102, 102));
        student44.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student44.setText("44");

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        jLabel2.setText("View Attendance");

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel3.setText("Subject");

        jComboBox1.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "MFC2", "EOC2", "JAVA", "DAS1", "EEE", "GGI", "UID", "HARDWARE", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        jLabel5.setText("Select Date");

        jButton1.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jButton1.setText("Fetch Attendance Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        student45.setBackground(new java.awt.Color(255, 102, 102));
        student45.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student45.setText("45");

        student46.setBackground(new java.awt.Color(255, 102, 102));
        student46.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student46.setText("46");

        student47.setBackground(new java.awt.Color(255, 102, 102));
        student47.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student47.setText("47");

        student11.setBackground(new java.awt.Color(255, 102, 102));
        student11.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student11.setText("11");

        student48.setBackground(new java.awt.Color(255, 102, 102));
        student48.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student48.setText("48");

        student12.setBackground(new java.awt.Color(255, 102, 102));
        student12.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student12.setText("12");

        student49.setBackground(new java.awt.Color(255, 102, 102));
        student49.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student49.setText("49");

        student13.setBackground(new java.awt.Color(255, 102, 102));
        student13.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student13.setText("13");

        student50.setBackground(new java.awt.Color(255, 102, 102));
        student50.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student50.setText("50");

        student14.setBackground(new java.awt.Color(255, 102, 102));
        student14.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student14.setText("14");

        student51.setBackground(new java.awt.Color(255, 102, 102));
        student51.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student51.setText("53");

        student52.setBackground(new java.awt.Color(255, 102, 102));
        student52.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student52.setText("56");

        student53.setBackground(new java.awt.Color(255, 102, 102));
        student53.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student53.setText("59");

        student54.setBackground(new java.awt.Color(255, 102, 102));
        student54.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student54.setText("54");

        student1.setBackground(new java.awt.Color(255, 102, 102));
        student1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student1.setText("1");
        student1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student1ActionPerformed(evt);
            }
        });

        student15.setBackground(new java.awt.Color(255, 102, 102));
        student15.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student15.setText("15");

        student16.setBackground(new java.awt.Color(255, 102, 102));
        student16.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student16.setText("16");

        student17.setBackground(new java.awt.Color(255, 102, 102));
        student17.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student17.setText("17");

        student18.setBackground(new java.awt.Color(255, 102, 102));
        student18.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student18.setText("18");

        student55.setBackground(new java.awt.Color(255, 102, 102));
        student55.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student55.setText("55");

        student19.setBackground(new java.awt.Color(255, 102, 102));
        student19.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student19.setText("19");

        student56.setBackground(new java.awt.Color(255, 102, 102));
        student56.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student56.setText("58");

        student20.setBackground(new java.awt.Color(255, 102, 102));
        student20.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student20.setText("20");

        student57.setBackground(new java.awt.Color(255, 102, 102));
        student57.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student57.setText("57");

        student21.setBackground(new java.awt.Color(255, 102, 102));
        student21.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student21.setText("21");

        student58.setBackground(new java.awt.Color(255, 102, 102));
        student58.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student58.setText("52");

        student22.setBackground(new java.awt.Color(255, 102, 102));
        student22.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student22.setText("22");

        student59.setBackground(new java.awt.Color(255, 102, 102));
        student59.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student59.setText("60");

        student23.setBackground(new java.awt.Color(255, 102, 102));
        student23.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student23.setText("23");

        student60.setBackground(new java.awt.Color(255, 102, 102));
        student60.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student60.setText("51");

        student24.setBackground(new java.awt.Color(255, 102, 102));
        student24.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student24.setText("24");

        student61.setBackground(new java.awt.Color(255, 102, 102));
        student61.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student61.setText("63");

        student62.setBackground(new java.awt.Color(255, 102, 102));
        student62.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student62.setText("66");

        student63.setBackground(new java.awt.Color(255, 102, 102));
        student63.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student63.setText("69");

        student64.setBackground(new java.awt.Color(255, 102, 102));
        student64.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        student64.setText("64");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton1))
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(63, 63, 63))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(49, 49, 49)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(190, 190, 190))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student70, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student60, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student41, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student31, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student68, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student58, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student42, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student32, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student61, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student51, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student43, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student33, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(student64, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(student65, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(student62, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student54, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student44, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student34, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student55, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student45, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student35, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student25, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student52, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student46, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student36, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student26, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student67, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student57, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student47, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student27, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student37, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student66, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student56, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student48, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student38, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student28, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student63, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student53, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student49, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student39, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student29, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student69, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student59, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student50, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student40, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student30, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(52, 52, 52)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jButton1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(student4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(student14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(student24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(student23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(student25, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student26, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(student34, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student35, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student36, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(student37, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student44, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student45, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(student46, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student47, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(student54, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student55, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student52, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(student57, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student64, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student65, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(student62, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student67, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student31, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student32, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student33, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student41, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student42, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student43, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student60, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student58, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student51, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student70, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student68, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student61, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(student7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(student17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(student27, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(student10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(student20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(student30, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(student40, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(student50, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(student59, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(student69, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student28, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student29, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student38, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student39, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student48, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student49, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student56, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student53, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(student66, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student63, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Attenadance", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Requests_ComboBox.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        Requests_ComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Requests_ComboBoxMouseClicked(evt);
            }
        });
        Requests_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Requests_ComboBoxActionPerformed(evt);
            }
        });

        Request_Label.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        Request_Label.setText("Requests");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel7.setText("Name");

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel8.setText("Branch");

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel9.setText("Roll Number");

        Name1_Label.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        Name1_Label.setText("jLabel10");

        Branch1_Label.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        Branch1_Label.setText("jLabel11");

        Roll_Number_Label.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        Roll_Number_Label.setText("jLabel12");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(Roll_Number_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(80, 80, 80)
                        .addComponent(Name1_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Branch1_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Name1_Label))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Branch1_Label))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Roll_Number_Label))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        leave_applied_for_Panel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leave Applied For", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N
        leave_applied_for_Panel6.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel13.setText("From Date");

        jLabel14.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel14.setText("jLabel14");

        jLabel15.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel15.setText("To Date");

        jLabel16.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel16.setText("jLabel16");

        jLabel17.setText("Sem");

        jLabel18.setText("Sem");

        javax.swing.GroupLayout leave_applied_for_Panel6Layout = new javax.swing.GroupLayout(leave_applied_for_Panel6);
        leave_applied_for_Panel6.setLayout(leave_applied_for_Panel6Layout);
        leave_applied_for_Panel6Layout.setHorizontalGroup(
            leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leave_applied_for_Panel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(leave_applied_for_Panel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leave_applied_for_Panel6Layout.createSequentialGroup()
                        .addGroup(leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leave_applied_for_Panel6Layout.setVerticalGroup(
            leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leave_applied_for_Panel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(30, 30, 30)
                .addGroup(leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(27, 27, 27)
                .addGroup(leave_applied_for_Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reason", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N
        jPanel7.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        Certificate_Label.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        Certificate_Label.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Certificate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N

        Approve_button.setText("Approve");
        Approve_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Approve_buttonActionPerformed(evt);
            }
        });

        Reject_Button.setText("Reject");
        Reject_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reject_ButtonActionPerformed(evt);
            }
        });

        refresh_button.setIcon(new javax.swing.ImageIcon("C:\\Users\\arunt\\Downloads\\icons8-sync-28.png")); // NOI18N
        refresh_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(leave_applied_for_Panel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Approve_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Reject_Button)
                        .addGap(18, 18, 18)
                        .addComponent(Request_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Requests_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Certificate_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Reject_Button)
                                    .addComponent(Approve_button)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Requests_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Request_Label)))
                                    .addComponent(refresh_button, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(Certificate_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(leave_applied_for_Panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Leave Requests", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date date = jCalendar1.getDate();
        int sem = jComboBox2.getSelectedIndex();
        int subject = jComboBox1.getSelectedIndex();
        for (int i = 1; i <= 70; i++) {
            String Roll_no = "CB.EN.U4AIE220" + String.format("%02d", i);
            boolean a = checkAttendance(date, Roll_no, sem, subject);
            System.out.println(a);
            if (a) {
                if (i == 1) {
                    student1.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 2) {
                    student2.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 3) {
                    student3.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 4) {
                    student4.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 5) {
                    student5.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 6) {
                    student6.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 7) {
                    student7.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 8) {
                    student8.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 9) {
                    student9.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 10) {
                    student10.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 11) {
                    student11.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 12) {
                    student12.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 13) {
                    student13.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 14) {
                    student14.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 15) {
                    student15.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 16) {
                    student16.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 17) {
                    student17.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 18) {
                    student18.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 19) {
                    student19.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 20) {
                    student20.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 21) {
                    student21.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 22) {
                    student22.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 23) {
                    student23.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 24) {
                    student24.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 25) {
                    student25.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 26) {
                    student26.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 27) {
                    student27.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 28) {
                    student28.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 29) {
                    student29.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 30) {
                    student30.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 31) {
                    student31.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 32) {
                    student32.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 33) {
                    student33.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 34) {
                    student34.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 35) {
                    student35.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 36) {
                    student36.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 37) {
                    student37.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 38) {
                    student38.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 39) {
                    student39.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 40) {
                    student40.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 41) {
                    student41.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 42) {
                    student42.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 43) {
                    student43.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 44) {
                    student44.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 45) {
                    student45.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 46) {
                    student46.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 47) {
                    student47.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 48) {
                    student48.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 49) {
                    student49.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 50) {
                    student50.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 51) {
                    student51.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 52) {
                    student52.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 53) {
                    student53.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 54) {
                    student54.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 55) {
                    student55.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 56) {
                    student56.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 57) {
                    student57.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 58) {
                    student58.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 59) {
                    student59.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 60) {
                    student60.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 61) {
                    student61.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 62) {
                    student62.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 63) {
                    student63.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 64) {
                    student64.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 65) {
                    student65.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 66) {
                    student66.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 67) {
                    student67.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 68) {
                    student68.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 69) {
                    student69.setBackground(new java.awt.Color(102, 255, 102));
                }
                if (i == 70) {
                    student70.setBackground(new java.awt.Color(102, 255, 102));
                }
            }
            continue;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void student32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student32ActionPerformed

    private void student1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void refresh_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_buttonActionPerformed
        Request req = new Request();
        requests = Request.getRequests();
        
        for (int i = 0; i < requests.length; i++) {
            
            if(!requests[i][1].equals(null)){
                Requests_ComboBox.addItem(requests[i][1]);
            }
            
        }
    }//GEN-LAST:event_refresh_buttonActionPerformed

    private void Approve_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Approve_buttonActionPerformed
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");
            Date From_date = format.parse(jLabel14.getText());
            Date To_date = format.parse(jLabel16.getText());
            String Roll_no = Roll_Number_Label.getText();
            String sem = jLabel18.getText();
            boolean a =medAttendance(From_date, To_date, Roll_no, sem);
            if(a){
                
            }
        } catch (ParseException ex) {
            Logger.getLogger(admin_dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Approve_buttonActionPerformed

    private void Requests_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Requests_ComboBoxActionPerformed
        int request_no = Requests_ComboBox.getSelectedIndex();
        request_no = Integer.parseInt(requests[request_no][0]);
        Request request = new Request(request_no);
        
        Name1_Label.setText(request.Name);
        Branch1_Label.setText("B.Tech2022 AIE");
        Roll_Number_Label.setText(request.Roll_no);
        
        jLabel14.setText(request.From_date);
        jLabel16.setText(request.To_date);
        jLabel18.setText(Integer.toString(request.sem));
        jTextArea1.setText(request.Reason);
        byte[] imagedata = request.Certificate;
        
        ImageIcon format = new ImageIcon(imagedata);
        Image mm = format.getImage();
        Image img2 = mm.getScaledInstance(Certificate_Label.getWidth(), Certificate_Label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        
        Certificate_Label.setIcon(image);
    }//GEN-LAST:event_Requests_ComboBoxActionPerformed

    private void Requests_ComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Requests_ComboBoxMouseClicked

    }//GEN-LAST:event_Requests_ComboBoxMouseClicked

    private void Reject_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reject_ButtonActionPerformed
        int index =Requests_ComboBox.getSelectedIndex();
        removeRequest(index);
    }//GEN-LAST:event_Reject_ButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            
            UIManager.put("ScrollBar.trackArc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
            UIManager.put("ScrollBar.track", new Color(0xe0e0e0));
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.arc", 999);
            UIManager.put("ProgressBar.arc", 999);
            UIManager.put("TextComponent.arc", 999);
            UIManager.put("Component.arrowType", "triangle");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Approve_button;
    private javax.swing.JLabel Branch1_Label;
    private javax.swing.JLabel Certificate_Label;
    private javax.swing.JLabel Name1_Label;
    private javax.swing.JButton Reject_Button;
    private javax.swing.JLabel Request_Label;
    private javax.swing.JComboBox<String> Requests_ComboBox;
    private javax.swing.JLabel Roll_Number_Label;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel leave_applied_for_Panel6;
    private javax.swing.JButton refresh_button;
    private javax.swing.JButton student1;
    private javax.swing.JButton student10;
    private javax.swing.JButton student11;
    private javax.swing.JButton student12;
    private javax.swing.JButton student13;
    private javax.swing.JButton student14;
    private javax.swing.JButton student15;
    private javax.swing.JButton student16;
    private javax.swing.JButton student17;
    private javax.swing.JButton student18;
    private javax.swing.JButton student19;
    private javax.swing.JButton student2;
    private javax.swing.JButton student20;
    private javax.swing.JButton student21;
    private javax.swing.JButton student22;
    private javax.swing.JButton student23;
    private javax.swing.JButton student24;
    private javax.swing.JButton student25;
    private javax.swing.JButton student26;
    private javax.swing.JButton student27;
    private javax.swing.JButton student28;
    private javax.swing.JButton student29;
    private javax.swing.JButton student3;
    private javax.swing.JButton student30;
    private javax.swing.JButton student31;
    private javax.swing.JButton student32;
    private javax.swing.JButton student33;
    private javax.swing.JButton student34;
    private javax.swing.JButton student35;
    private javax.swing.JButton student36;
    private javax.swing.JButton student37;
    private javax.swing.JButton student38;
    private javax.swing.JButton student39;
    private javax.swing.JButton student4;
    private javax.swing.JButton student40;
    private javax.swing.JButton student41;
    private javax.swing.JButton student42;
    private javax.swing.JButton student43;
    private javax.swing.JButton student44;
    private javax.swing.JButton student45;
    private javax.swing.JButton student46;
    private javax.swing.JButton student47;
    private javax.swing.JButton student48;
    private javax.swing.JButton student49;
    private javax.swing.JButton student5;
    private javax.swing.JButton student50;
    private javax.swing.JButton student51;
    private javax.swing.JButton student52;
    private javax.swing.JButton student53;
    private javax.swing.JButton student54;
    private javax.swing.JButton student55;
    private javax.swing.JButton student56;
    private javax.swing.JButton student57;
    private javax.swing.JButton student58;
    private javax.swing.JButton student59;
    private javax.swing.JButton student6;
    private javax.swing.JButton student60;
    private javax.swing.JButton student61;
    private javax.swing.JButton student62;
    private javax.swing.JButton student63;
    private javax.swing.JButton student64;
    private javax.swing.JButton student65;
    private javax.swing.JButton student66;
    private javax.swing.JButton student67;
    private javax.swing.JButton student68;
    private javax.swing.JButton student69;
    private javax.swing.JButton student7;
    private javax.swing.JButton student70;
    private javax.swing.JButton student8;
    private javax.swing.JButton student9;
    // End of variables declaration//GEN-END:variables
}
