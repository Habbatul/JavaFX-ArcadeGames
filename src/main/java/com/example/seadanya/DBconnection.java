package com.example.seadanya;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static java.lang.String.format;

public class DBconnection {

    public static Connection con;
    public static Statement stm;
    public Connection koneksi(){
        try {
            String url = "jdbc:mysql://localhost/gaming";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" + e.getMessage());
        }
        return null;
    }


    public void insert_score(int x, String nama) {
        // query simpan
        try{
        String sql = "UPDATE skor set score='%d' WHERE nama = '"+nama+"'";
        sql = String.format(sql, x);
        // simpan buku
        stm.execute(sql);}catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert_score2(int x, String nama) {
        // query simpan
        try{
            String sql = "UPDATE hard set score='%d' WHERE nama = '"+nama+"'";
            sql = String.format(sql, x);
            // simpan buku
            stm.execute(sql);}catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert_score3(int x, String nama) {
        // query simpan
        try{
            String sql = "UPDATE easy set score='%d' WHERE nama = '"+nama+"'";
            sql = String.format(sql, x);
            // simpan buku
            stm.execute(sql);}catch (Exception e) {
            e.printStackTrace();
        }
    }

}

