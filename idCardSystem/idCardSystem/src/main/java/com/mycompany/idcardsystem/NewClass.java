/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idcardsystem;

/**
 *
 * @author arunt
 */
public class NewClass {

    public static void main(String[] args) {
        int date = 1;
        int day = 4;
        for (int i = 0; i <= 22; i++) {
            String[] time = {"08:50", "10:45", "14:35"};
            for (int j = 0; j < 3; j++) {
                System.out.println(day);
                AttendanceManagement.addAttendance("CB.EN.U4AIE22004", time[j], day%7, String.format("%02d", date) + "_06_2023_attendance_sem2");
                
            }
            //AttendanceManagement.createAttendanceTable(String.format("%02d", date)+"_06_2023_attendance_sem2", day%7);
            date =date+3;
            day =day+3;
        }
    }

}
