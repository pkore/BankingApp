/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.domain;

import static java.lang.System.out;
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
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
	List<User> users = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 Statement stm = conn.createStatement();
	 ) {	
			
            ResultSet results = stm.executeQuery("SELECT * FROM users");
            while (results.next()) {
		int account = results.getInt("account");
                String username = results.getString("login");
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
    
    public List<Customer> getAllCustomers() {
	List<Customer> cstmList = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 Statement stm = conn.createStatement();
	 ) {	
			
            ResultSet results = stm.executeQuery("SELECT * FROM customers");
            while (results.next()) {
		int account = results.getInt("account");
                String name = results.getString("name");
                String email = results.getString("email");
                String phone = results.getString("phone");
                double bal = results.getDouble("balance");
                Customer cstm = new Customer(account, name, email, phone, bal);
                cstmList.add(cstm);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return cstmList;
    }
    
    public List<Transaction> convertToList(String transaction){
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
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM transactions WHERE id = ?");
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
    
    public User getUser(String username){
        List<User> users = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
	 ) {	
            stm.setString(1, username);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
		int account = results.getInt("account");
                String password = results.getString("password");
                String tr = results.getString("transaction");
                User u = new User(account, username, password, convertToList(tr));
                users.add(u);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return users.get(0);
    }
    
    private String convertToString(List<Transaction> tr){
        String contents = "";
	if (tr.isEmpty()) {
            return contents;
	}
	for (Transaction t : tr ) {
            contents = contents + t.getId() + ":";
	}
	contents = contents.substring(0, contents.length()-1);
	return contents;
    }
    
    public void newUser(User u){
        List<User> users = getAllUsers();
        
        for(User temp_u: users){
            if(temp_u.getAccount() == u.getAccount()){
                return;
            }
        }
        
        try (Connection conn = DriverManager.getConnection(dbdriver, dbuser, dbpass);
                PreparedStatement prepStm = conn.prepareStatement("INSERT INTO users (account, login, password, transaction) values (?,?,?,?);");) {
            prepStm.setInt(1, u.getAccount());
            prepStm.setString(2, u.getUsername());
            prepStm.setString(3, u.getPassword());
            prepStm.setString(4, convertToString(u.getTransaction()));
            prepStm.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public String authenticateUser(User userObject){
        String userName = userObject.getUsername(); //Assign user entered values to temporary variables.
        String password = userObject.getPassword();
         
         try
         {
             Connection con = DriverManager.getConnection(this.dbdriver,this.dbuser, this.dbpass); //attempting to connect to MySQL database
             Statement statement = con.createStatement(); //Statement is used to write queries. Read more about it.
             ResultSet resultSet = statement.executeQuery("SELECT login,password from users"); //the table name is users and userName,password are columns. Fetching all the records and storing in a resultSet.
 
             while(resultSet.next()) // Until next row is present otherwise it return false
             {
              String userNameDB = resultSet.getString("login"); //fetch the values present in database
              String passwordDB = resultSet.getString("password");
 
               if(userName.equals(userNameDB) && password.equals(passwordDB))
               {
                  return "SUCCESS"; ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
               }
             }
         }catch(SQLException e)
             {
                e.printStackTrace();
                // out.print("FAILED");
             }
             return "Invalid user credentials"; // Return appropriate message in case of failure
         }
    }

