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
import java.util.Random;

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
                boolean active = results.getBoolean("active");
                User user = new User(account, username, password, tr, active);
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
        if (tr.isEmpty()){
            return new Transaction();
        } else {
            return tr.get(0);
        }
    }
    
    public User getUser(String username){
        List<User> users = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM users WHERE login = ?");
	 ) {	
            stm.setString(1, username);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
		int account = results.getInt("account");
                String password = results.getString("password");
                String tr = results.getString("transaction");
                boolean active = results.getBoolean("active");
                User u = new User(account, username, password, convertToList(tr), active);
                users.add(u);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        if (users.isEmpty()){
            return new User();
        } else {
            return users.get(0);
        }
    }
    
    public User getUser(int account){
        List<User> users = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM users WHERE account = ?");
	 ) {	
            stm.setInt(1, account);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
		String username = results.getString("login");
                String password = results.getString("password");
                String tr = results.getString("transaction");
                boolean active = results.getBoolean("active");
                User u = new User(account, username, password, convertToList(tr), active);
                users.add(u);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        if (users.isEmpty()){
            return new User();
        } else {
            return users.get(0);
        }
    }
    
    public Customer getCustomer(int account){
        List<Customer> cstmList = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM customers WHERE account = ?");
	 ) {	
            stm.setInt(1, account);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
                String name = results.getString("name");
                String phone = results.getString("phone");
                String email = results.getString("email");
                double balance = results.getDouble("balance");
                Customer cstm = new Customer(account, name, phone, email, balance);
                cstmList.add(cstm);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        if(cstmList.isEmpty()){
            return new Customer();
        } else {
            return cstmList.get(0);
        }
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
                PreparedStatement prepStm = conn.prepareStatement("INSERT INTO users (account, login, password, transaction, active) values (?,?,?,?,?);");) {
            prepStm.setInt(1, u.getAccount());
            prepStm.setString(2, u.getUsername());
            prepStm.setString(3, u.getPassword());
            prepStm.setString(4, convertToString(u.getTransaction()));
            prepStm.setBoolean(5, u.isActive());
            prepStm.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public int newTransaction(int source, int dest, double value){
        try (Connection conn = DriverManager.getConnection(dbdriver, dbuser, dbpass);
                PreparedStatement prepStm = conn.prepareStatement("INSERT INTO transactions (source, dest, value) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);) {
            prepStm.setInt(1, source);
            prepStm.setInt(2, dest);
            prepStm.setDouble(3, value);
            prepStm.execute();
            try (ResultSet generatedKeys = prepStm.getGeneratedKeys()) {
                return generatedKeys.getInt(1);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    public String authenticateUser(User userObject){
        String userName = userObject.getUsername(); //Assign user entered values to temporary variables.
        String password = userObject.getPassword();
         
//        try{
//            Connection con = DriverManager.getConnection(this.dbdriver,this.dbuser, this.dbpass); //attempting to connect to MySQL database
//            Statement statement = con.createStatement(); //Statement is used to write queries. Read more about it.
//            ResultSet resultSet = statement.executeQuery("SELECT login,password from users"); //the table name is users and userName,password are columns. Fetching all the records and storing in a resultSet.
// 
//            while(resultSet.next()){
//                String userNameDB = resultSet.getString("login"); //fetch the values present in database
//                String passwordDB = resultSet.getString("password");
// 
//                if(userName.equals(userNameDB) && password.equals(passwordDB)){
//                    return "SUCCESS"; ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
//                }
//            }
//        }catch(SQLException e){
//                e.printStackTrace();
//                // out.print("FAILED");
//            }
        User dbuser = getUser(userName);
        if(dbuser.getAccount() != 0 && dbuser.getPassword().equals(password) &&dbuser.isActive()){
            return "SUCCESS" + dbuser.toString();
        }
        return "Invalid user credentials" + dbuser.toString(); // Return appropriate message in case of failure
    }
    
    public String authenticateCustomer(Customer cstm){
        int account = cstm.getAccount();
        Customer dbcstm = getCustomer(account);
        if(dbcstm.getAccount()!=0 && cstm.getPhone().equals(dbcstm.getPhone()) && cstm.getEmail().equals(dbcstm.getEmail())){
            return "SUCCESS";
        } else {
            return "Please enter registered email and phone for valid account";
        }
    }
    
    public String generateRandomString(){
        int lowerlimit = 97;
        int upperlimit = 122;
        Random rand = new Random();
        StringBuilder r = new StringBuilder(10);
        for(int i=0; i<10; i++){
            int nextRandChar = lowerlimit + (int)(rand.nextFloat()*(upperlimit - lowerlimit +1));
            r.append((char)nextRandChar);
        }
        return r.toString();
    }
    
    public User generateCredentials(Customer cstm){
        Random rand = new Random();
        String name = cstm.getName().toLowerCase().replaceAll("\\s", "");
        if(cstm.getName().length()> 4){
            name = name.substring(0, 4);
        }
        String login = name + Integer.toString(rand.nextInt(2000));
        User dbuser = getUser(login);
        while(dbuser.getAccount() != 0){
            login = name + Integer.toString(rand.nextInt(2000));
            dbuser = getUser(login);
        }
        String password = generateRandomString();
        User u = new User(cstm.getAccount(), login, password, convertToList(""), false);
        return u;
    }
    
    public void sendCredentials(Customer cstm){
        User u = getUser(cstm.getAccount());
    }
    
    private void updateBalance(int account, double value){
        try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("UPDATE customers SET balance=? WHERE account=?");
	 ) {	
            stm.setDouble(1, value);
            stm.setInt(2, account);
            stm.execute();
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
    }
    
    public void updateTransaction(int account, int id){
        User u = getUser(account);
        String tr = convertToString(u.getTransaction());
        String newtr = Integer.toString(id) + tr;
        try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("UPDATE users SET transaction=? WHERE account=?");
	 ) {	
            stm.setString(1, newtr);
            stm.setInt(2, account);
            stm.execute();
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
    }
    
    public String sendMoney(String login, int dest, double value){
        User suser = getUser(login);
        Customer dcstm = getCustomer(dest);
        if(dcstm.getAccount() == 0) return "Invalid Destination account";
        else{
            Customer cstm = getCustomer(suser.getAccount());
            if(cstm.getBalance() <= value) return "Insufficient Balance";
            else{
                double sbal = cstm.getBalance();
                double dbal = dcstm.getBalance();
                updateBalance(dest, dbal + value);
                updateBalance(suser.getAccount(), sbal - value);
                int id = newTransaction(suser.getAccount(), dest, value);
                updateTransaction(suser.getAccount(), id);
                updateTransaction(dest, id);
                return "SUCCESS:" + Integer.toString(id);
            }
        }
    }
}

