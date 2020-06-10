/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.initialize;

import com.bank.domain.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anushka Chincholkar
 */
public class DatabaseInitialize {
    
    private List<Customer> getCustomerList(){
        List<Customer> cstmList = new ArrayList<>();
        cstmList.add(new Customer(234567,"Pran Gandhi", "pgandhi@gmail.com", 1234567890, 25022.45));
        return cstmList;
    }
    
    public void initializeDatabase() {
	try {
                    
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");

            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS customers;")) {
		prepStm.execute();
            }
				
            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS users;")) {
		prepStm.execute();
            }
            
            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS transactions;")) {
		prepStm.execute();
            }
				
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE customers (account int primary key, name varchar(30), email varchar(150), phone int, balance float);")) {
		prepStm.execute();
            }
            
            List<Customer> cstmList = getCustomerList();
            for (Customer cstm : cstmList) {
                try (PreparedStatement prepStm = conn.prepareStatement("INSERT INTO customers (account, name, email, phone, balance) values (?,?,?,?,?);");) {
                    prepStm.setInt(1, cstm.getAccount());
                    prepStm.setString(2, cstm.getName());
                    prepStm.setString(3, cstm.getEmail());
                    prepStm.setInt(4, cstm.getPhone());
                    prepStm.setDouble(5, cstm.getBalance());
                    prepStm.execute();
                }
            }
            
				
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE users (account int primary key, login varchar(30), password varchar(30), transaction varchar(255));")) {
		prepStm.execute();
            }
            
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE transaction (id int auto_increment primary key, source int, dest int, value float);")) {
		prepStm.execute();
            }
            
	} catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
