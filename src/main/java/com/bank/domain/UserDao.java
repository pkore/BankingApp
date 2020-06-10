/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anushka Chincholkar
 */
public class UserDao {
    
    String dbdriver;
    String dbuser;
    String dbpass;

    public UserDao() {
        this.dbdriver = "jdbc:mysql://localhost:3306/bank";
        this.dbuser = "root";
        this.dbpass = "root";
    }
    
    
    
    public List<User> getAllUsers() {
	List<User> users = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 Statement stm = conn.createStatement();
	 ) {	
			
            ResultSet results = stm.executeQuery("SELECT * FROM user");
            while (results.next()) {
		int account = results.getInt("account");
                String username = results.getString("username");
                String password = results.getString("password");
                String transaction = results.getString("transaction");
                List<Transaction> tr = convertToList(transaction);
                User user = new User(account, username, password, tr);
                users.add(user);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return users;
    }
    
    private List<Transaction> convertToList(String transaction){
        List<Transaction> tr = new ArrayList<>();
        if(transaction == null || transaction.equals("")){
            return tr;
        }
        String[] ids = transaction.split(":");
        for(int i=0; i<ids.length; i++){
            Transaction t = getTransaction(Integer.valueOf(ids[i]));
            tr.add(t);
        }
        return tr;
    }
    
    public Transaction getTransaction(int id){
        List<Transaction> tr = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM transaction WHERE id = ?");
	 ) {	
            stm.setInt(1, id);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
		int from = results.getInt("from");
                int to = results.getInt("to");
                int value = results.getInt("value");
                Transaction t = new Transaction(id, from, to, value);
                tr.add(t);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return tr.get(0);
    }
}
