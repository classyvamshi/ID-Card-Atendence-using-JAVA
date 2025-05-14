package com.mycompany.idcardsystem;

import java.sql.*;



public class AttendanceManagement {

    public static boolean addAttendance(String rollno, String time, int day, String tableName) {
        boolean success = false;
        String sql = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");

            Statement stmt = con.createStatement();

            String[] timeSegs = time.split(":");
            int hour = Integer.parseInt(timeSegs[0]);
            int minutes = Integer.parseInt(timeSegs[1]);

            switch (day) {
                case 0:
                    //sunday
                    System.out.println("eufif");
                    String[] sunday_periods = {"UID", "EOC", "JAVA"};
                    sql = findperiod(rollno, sunday_periods, "18:30:00", tableName);
                    break;
                case 1:
                    String[] monday_periods = {"UID", "EOC", "JAVA"};
                    sql = findperiod(rollno, monday_periods, time, tableName);
                    break;
                case 2:
                    //tuesday
                    String[] tuesday_periods = {"EEE", "DSA", "MFC"};
                    sql = findperiod(rollno, tuesday_periods, time, tableName);
                    break;
                case 3:
                    //wednesday
                    String[] wednesday_periods = {"MFC", "GGI", "EEE"};
                    sql = findperiod(rollno, wednesday_periods, time, tableName);
                    break;
                case 4:
                    //thursday
                    String[] thursday_periods = {"UID", "GGI", "EOC"};
                    sql = findperiod(rollno, thursday_periods, time, tableName);
                    break;
                case 5:
                    //friday
                    String[] friday_periods = {"JAVA", "DSA", "HARDWARE"};
                    sql = findperiod(rollno, friday_periods, time, tableName);
                    break;
                case 6:
                    //saturday
                    String[] saturday_periods = {"MFC", "GGI", "EEE"};
                    sql = findperiod(rollno, saturday_periods, time, tableName);
                    break;
            }
              
            //String sql = "UPDATE " + tableName + " SET ATTENDANCE='PRESENT',ENTRY_TIME='" + time + "' WHERE ROLL_NUMBER='" + rollno + "'";
            System.out.println(sql);

            if (sql.equals("")) {
                success = false;
            }
            stmt.executeUpdate(sql);
            success = true;
            System.out.println("Attendance added");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static String findperiod(String rollno, String[] periods, String time, String tableName) {
        String[] timeSegs = time.split(":");
        int hour = Integer.parseInt(timeSegs[0]);
        int minutes = Integer.parseInt(timeSegs[1]);
        String sql = "";
        if (hour == 8 && minutes <= 59) {
            sql = "UPDATE " + tableName + " SET " + periods[0] + "='PRESENT' WHERE ROLL_NUMBER='" + rollno + "'";
        } else if (hour == 10 && minutes >= 40 && minutes <= 50) {
            sql = "UPDATE " + tableName + " SET " + periods[1] + "='PRESENT' WHERE ROLL_NUMBER='" + rollno + "'";
        } else if (hour == 15 && minutes >= 40 && minutes <= 50) {
            sql = "UPDATE " + tableName + " SET " + periods[2] + "='PRESENT' WHERE ROLL_NUMBER='" + rollno + "'";
        } 
        return sql;
    }

    public static void createAttendanceTable(String tableName, int day) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/idmanagement", "root", "arun@T555");

            Statement stmt = con.createStatement();
            String[] periods = new String[3];
            String[] nonperiods = new String[5];
            switch (day) {
                case 0:
                    periods[0] = "UID";
                    periods[1] = "EOC";
                    periods[2] = "JAVA";
                    nonperiods[0] = "MFC";
                    nonperiods[1] = "DSA";
                    nonperiods[2] = "EEE";
                    nonperiods[3] = "GGI";
                    nonperiods[4] = "HARDWARE";
                    break;
                case 1:
                    //MONDAY
                    periods[0] = "UID";
                    periods[1] = "EOC";
                    periods[2] = "JAVA";
                    nonperiods[0] = "MFC";
                    nonperiods[1] = "DSA";
                    nonperiods[2] = "EEE";
                    nonperiods[3] = "GGI";
                    nonperiods[4] = "HARDWARE";
                    break;
                case 2:
                    //tuesday
                    periods[0] = "EEE";
                    periods[1] = "DSA";
                    periods[2] = "MFC";
                    nonperiods[0] = "JAVA";
                    nonperiods[1] = "EOC";
                    nonperiods[2] = "UID";
                    nonperiods[3] = "GGI";
                    nonperiods[4] = "HARDWARE";
                    break;
                case 3:
                    //wednesday
                    periods[0] = "MFC";
                    periods[1] = "GGI";
                    periods[2] = "EEE";
                    nonperiods[0] = "JAVA";
                    nonperiods[1] = "DSA";
                    nonperiods[2] = "EOC";
                    nonperiods[3] = "UID";
                    nonperiods[4] = "HARDWARE";
                    break;
                case 4:
                    //thursday
                    periods[0] = "UID";
                    periods[1] = "GGI";
                    periods[2] = "EOC";
                    nonperiods[0] = "JAVA";
                    nonperiods[1] = "DSA";
                    nonperiods[2] = "EEE";
                    nonperiods[3] = "MFC";
                    nonperiods[4] = "HARDWARE";
                    break;
                case 5:
                    //friday
                    periods[0] = "JAVA";
                    periods[1] = "DSA";
                    periods[2] = "HARDWARE";
                    nonperiods[0] = "MFC";
                    nonperiods[1] = "EOC";
                    nonperiods[2] = "EEE";
                    nonperiods[3] = "UID";
                    nonperiods[4] = "GGI";
                    break;
                case 6:
                    //saturday
                    periods[0] = "MFC";
                    periods[1] = "GGI";
                    periods[2] = "EEE";
                    nonperiods[0] = "JAVA";
                    nonperiods[1] = "DSA";
                    nonperiods[2] = "EOC";
                    nonperiods[3] = "UID";
                    nonperiods[4] = "HARDWARE";
                    break;
            }
            if (day == 0) {
                String sql = "CREATE TABLE " + tableName + " (ROLL_NUMBER varchar(45), " + periods[0] + " varchar(45), " + periods[1] + " varchar(45), " + periods[2] + " varchar(45)," + nonperiods[0] + " varchar(45)," + nonperiods[1] + " varchar(45)," + nonperiods[2] + " varchar(45)," + nonperiods[3] + " varchar(45)," + nonperiods[4] + " varchar(45),PRIMARY KEY (`ROLL_NUMBER`) ) ;";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                System.out.println("Database created successfully...");
                String sql1 = "INSERT INTO " + tableName + " (ROLL_NUMBER," + periods[0] + "," + periods[1] + "," + periods[2] + "," + nonperiods[0] + "," + nonperiods[1] + "," + nonperiods[2] + "," + nonperiods[3] + "," + nonperiods[4] + ") VALUES ('CB.EN.U4AIE22001','NULL','NULL','NULL','NULL','NULL','NULL','NULL','NULL')";
                for (int i = 2; i <= 70; i++) {
                    sql1 = sql1 + ",('CB.EN.U4AIE220" + String.format("%02d", i) + "','NULL','NULL','NULL','NULL','NULL','NULL','NULL','NULL')";
                }
                sql1 = sql1 + ";";
                System.out.println(sql1);
                stmt.executeUpdate(sql1);
                con.close();
            } else {
                String sql = "CREATE TABLE " + tableName + " (ROLL_NUMBER varchar(45), " + periods[0] + " varchar(45), " + periods[1] + " varchar(45), " + periods[2] + " varchar(45)," + nonperiods[0] + " varchar(45)," + nonperiods[1] + " varchar(45)," + nonperiods[2] + " varchar(45)," + nonperiods[3] + " varchar(45)," + nonperiods[4] + " varchar(45),PRIMARY KEY (`ROLL_NUMBER`) ) ;";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                System.out.println("Database created successfully...");
                String sql1 = "INSERT INTO " + tableName + " (ROLL_NUMBER," + periods[0] + "," + periods[1] + "," + periods[2] + "," + nonperiods[0] + "," + nonperiods[1] + "," + nonperiods[2] + "," + nonperiods[3] + "," + nonperiods[4] + ") VALUES ('CB.EN.U4AIE22001','ABSENT','ABSENT','ABSENT','NULL','NULL','NULL','NULL','NULL')";
                for (int i = 2; i <= 70; i++) {
                    sql1 = sql1 + ",('CB.EN.U4AIE220" + String.format("%02d", i) + "','ABSENT','ABSENT','ABSENT','NULL','NULL','NULL','NULL','NULL')";
                }
                sql1 = sql1 + ";";
                System.out.println(sql1);
                stmt.executeUpdate(sql1);
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
}

