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
import javax.mail.MessagingException;

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
    
    public List<Transaction> getAllTransactions() {
	List<Transaction> trList = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 Statement stm = conn.createStatement();
	 ) {	
			
            ResultSet results = stm.executeQuery("SELECT * FROM transactions");
            while (results.next()) {
		int id = results.getInt("id");
                int source = results.getInt("source");
                int dest = results.getInt("dest");
                double value = results.getDouble("value");
                Transaction tr = new Transaction(id, source, dest, value);
                trList.add(tr);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return trList;
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
    
    public String convertToString(List<Transaction> tr){
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
    
    public Transaction getTransaction(int id){
        List<Transaction> tr = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM transactions WHERE id = ?");
	 ) {	
            stm.setInt(1, id);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
		int from = results.getInt("source");
                int to = results.getInt("dest");
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
                Customer cstm = new Customer(account, name, email, phone, balance);
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
    
    public Admin getAdmin(String login){
        List<Admin> adminList = new ArrayList<>();
	try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("SELECT * FROM admins WHERE login = ?");
	 ) {	
            stm.setString(1, login);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
                String password = results.getString("password");
                Admin a = new Admin(login, password);
                adminList.add(a);
            }
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        if(adminList.isEmpty()){
            return new Admin();
        } else {
            return adminList.get(0);
        }
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
            try (Statement stm = conn.createStatement()) {
                ResultSet results = stm.executeQuery("SELECT MAX(id) FROM transactions;");
                if(results.next()) return results.getInt(1);
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
        User duser = getUser(userName);
        if(duser.getAccount() != 0 && duser.getPassword().equals(password) &&duser.isActive()){
            return "SUCCESS";
        }
        return "Invalid user credentials."; // Return appropriate message in case of failure
    }
    
    public String authenticateAdmin(Admin admin){
        String userName = admin.getLogin(); //Assign user entered values to temporary variables.
        String password = admin.getPassword();
        Admin dbadmin = getAdmin(userName);
        if(dbadmin.getLogin() != "" && dbadmin.getPassword().equals(password)){
            return "SUCCESS";
        }
        return "Invalid admin credentials."; // Return appropriate message in case of failure
    }
    
    public String authenticateCustomer(Customer cstm){
        int account = cstm.getAccount();
        Customer dbcstm = getCustomer(account);
        if (dbcstm.getAccount()==0){
            return "Please enter valid bank account number.";
        }
        if(cstm.getPhone().equals(dbcstm.getPhone()) && cstm.getEmail().equals(dbcstm.getEmail())){
            return "SUCCESS";
        } else {
            return "Please enter registered email and phone for your account." + cstm.getPhone() + dbcstm.getPhone() + cstm.getEmail() + dbcstm.getEmail();
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
        User duser = getUser(login);
        while(duser.getAccount() != 0){
            login = name + Integer.toString(rand.nextInt(2000));
            duser = getUser(login);
        }
        String password = generateRandomString();
        User u = new User(cstm.getAccount(), login, password, convertToList(""), false);
        return u;
    }
    
    public void sendCredentials(Customer cstm){
        User u = getUser(cstm.getAccount());
        String email = cstm.getEmail();
        String subject = "IMPORTANT - Netbanking Details";
        String content = "<h2>Your netbanking details are: </h2><p>Username: " + u.getUsername() + "<br>Password: " + u.getPassword() + "</p>";
        try{
            Email.sendAsHtml(email, subject, content);
        } catch(MessagingException e){
            e.printStackTrace();
        }
    }
    
    public void approveUser(Customer cstm){
        int account = cstm.getAccount();
        try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("UPDATE users SET active=true WHERE account=?");
	 ) {	
            stm.setInt(1, account);
            stm.execute();
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        sendCredentials(cstm);
    }
    
    public String blockUser(int account){
        try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("UPDATE users SET active=false WHERE account=?");
	 ) {	
            stm.setInt(1, account);
            stm.execute();
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return "SUCCESS";
    }
    
    public String deleteUser(int account){
        try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
	 PreparedStatement stm = conn.prepareStatement("DELETE FROM users WHERE account=?");
	 ) {	
            stm.setInt(1, account);
            stm.execute();
	} catch (SQLException e) {
            throw new RuntimeException(e); 
	}
        return "SUCCESS";
    }
    
    public String updatePassword(String login, String oldpassword, String newpassword){
        User u = getUser(login);
        if(u.getAccount() != 0 && u.isActive()){
            if(u.getPassword().equals(oldpassword)){
                try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
                    PreparedStatement stm = conn.prepareStatement("UPDATE users SET password=? WHERE account=?");
                ) {	
                    stm.setString(1, newpassword);
                    stm.setInt(2, u.getAccount());
                    stm.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e); 
                }
                return "SUCCESS";
            }
            else{
                return "Please enter correct current password";
            }
        }
        else{
            return "User Not Found";
        }
    }
    
    public String updateLogin(String login, String newlogin){
        User u = getUser(login);
        if(u.getAccount() != 0 && u.isActive()){
            User duser = getUser(newlogin);
            if(duser.getAccount() == 0){
                try (Connection conn = DriverManager.getConnection(dbdriver,dbuser,dbpass);
                    PreparedStatement stm = conn.prepareStatement("UPDATE users SET login=? WHERE account=?");
                ) {	
                    stm.setString(1, newlogin);
                    stm.setInt(2, u.getAccount());
                    stm.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e); 
                }
                return "SUCCESS";
            }
            else{
                return "This username is already taken.";
            }
        }
        else{
            return "User Not Found";
        }
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
        String newtr = Integer.toString(id) + ":" + tr;
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
    
    public String sendMoney(String login,int dest, double value){
        User suser = getUser(login);
        Customer dcstm = getCustomer(dest);
        if(dcstm.getAccount() == 0){
            return "Invalid Destination account";
        }
        else{
            Customer cstm = getCustomer(suser.getAccount());
            if(cstm.getBalance() <= value){
                return "Insufficient Balance";
            }
            else{
                double sbal = cstm.getBalance();
                double dbal = dcstm.getBalance();
                updateBalance(dest, dbal + value);
                updateBalance(suser.getAccount(), sbal - value);
                int id = newTransaction(suser.getAccount(), dest, value);
                updateTransaction(suser.getAccount(), id);
                updateTransaction(dest, id);
                return "SUCCESS";
            }
        }
    }
}

