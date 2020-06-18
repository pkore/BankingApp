/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.initialize;

import com.bank.domain.Customer;
import com.bank.domain.DataConnection;
import com.bank.domain.User;
import com.bank.domain.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Anushka Chincholkar
 */
public class DatabaseInitialize {
    
    private List<Customer> getCustomerList(){
        List<Customer> cstmList = new ArrayList<>();
        cstmList.add(new Customer(123456,"Ram Kapoor", "rkapoor@gmail.com", "9172330811", 25000.00));
        cstmList.add(new Customer(234567,"Pran Gandhi", "pgandhi@gmail.com", "1234567890", 25022.45));
        cstmList.add(new Customer(234531,"Jatin Rao", "jatiar@gmail.com", "3173636442", 453662.77));
        cstmList.add(new Customer(657832,"Radhe Ghosh", "radhsh@gmail.com", "9870675663", 6599403.34));
        cstmList.add(new Customer(977445,"Charlie Bhai", "charlana@gmail.com", "9359159866", 24545.33));
        cstmList.add(new Customer(312134,"Ruchi Ramakrishnan", "ruchnan@gmail.com", "9292923443", 3003.33));
        cstmList.add(new Customer(554739,"Taahid Acharya", "taahrya@gmail.com", "9890901182", 472883.33));
        cstmList.add(new Customer(848492,"Chhavi Zeeshan", "chhaura@gmail.com", "9860576671", 768589.89));
        cstmList.add(new Customer(674832,"Chandni Chokshi", "chandshi@gmail.com", "0505088310", 5000.55));
        cstmList.add(new Customer(986743,"Savita Gill", "savitll@gmail.com", "9323415456", 9000.00));
        cstmList.add(new Customer(564792,"Nitin Krishnan", "nitinan@gmail.com", "8687846321", 766885.86));
        cstmList.add(new Customer(247392,"Faraz Lal", "faratal@gmail.com", "9819293454", 879954.55));
        cstmList.add(new Customer(476329,"Payal Chaudry", "payalcdry@gmail.com", "9836545143", 67748.55));
        cstmList.add(new Customer(137288,"Leelawati Akshay Bumb", "leelawmb@gmail.com", "9115234657", 576849.4));
        cstmList.add(new Customer(172634,"Biren Prasad", "bireogi@gmail.com", "7563728193", 8658473.44));
        cstmList.add(new Customer(665733,"Nakul Prabhu", "nakulphu@gmail.com", "9560302432", 9928379.44));
        cstmList.add(new Customer(474637,"Munni Tandon", "munnon@gmail.com", "9564773382", 5654.54));
        cstmList.add(new Customer(657399,"Ujwal Bhai", "ujwalbnda@gmail.com", "4738182938", 877765.00));
        cstmList.add(new Customer(456000,"Tanay Bhai", "tanaybria@gmail.com", "9890786543", 76758993.44));
        cstmList.add(new Customer(937466,"Urvashi Jagdish", "urvashrin@gmail.com", "9098765432", 7689.44));
        cstmList.add(new Customer(765473,"Sohail Somani", "sohani@gmail.com", "7889977654", 10398478.55));
        cstmList.add(new Customer(765474,"Shah Suman", "ssuman@gmail.com", "7889977635", 775648.33));
        return cstmList;
    }
    
    private List<Customer> getNewUserList(){
        List<Customer> cstmList = new ArrayList<>();
        cstmList.add(new Customer(123456,"Ram Kapoor", "rkapoor@gmail.com", "9172330811", 25000.00));
        cstmList.add(new Customer(986743,"Savita Gill", "savitll@gmail.com", "9323415456", 9000.00));
        cstmList.add(new Customer(247392,"Faraz Lal", "faratal@gmail.com", "9819293454", 879954.55));
        cstmList.add(new Customer(476329,"Payal Chaudry", "payalcdry@gmail.com", "9836545143", 67748.55));
        cstmList.add(new Customer(765474,"Shah Suman", "ssuman@gmail.com", "7889977635", 775648.33));
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
            
            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS admins;")) {
		prepStm.execute();
            }
				
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE customers (account int primary key, name varchar(30), email varchar(150), phone varchar(11), balance float);")) {
		prepStm.execute();
            }
            
            List<Customer> cstmList = getCustomerList();
            for (Customer cstm : cstmList) {
                try (PreparedStatement prepStm = conn.prepareStatement("INSERT INTO customers (account, name, email, phone, balance) values (?,?,?,?,?);");) {
                    prepStm.setInt(1, cstm.getAccount());
                    prepStm.setString(2, cstm.getName());
                    prepStm.setString(3, cstm.getEmail());
                    prepStm.setString(4, cstm.getPhone());
                    prepStm.setDouble(5, cstm.getBalance());
                    prepStm.execute();
                }
            }
            
				
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE users (account int primary key, login varchar(30) unique, password varchar(30), transaction varchar(255), active boolean, cardstat varchar(25));")) {
		prepStm.execute();
            }
            
            List<Customer> userList = getNewUserList();
            Random rand = new Random();
            UserDao userdao = DataConnection.getUserDao();
            for (Customer user : userList) {
                User u = userdao.generateCredentials(user);
                if(rand.nextInt(2) == 1) u.setActive(true);
                userdao.newUser(u);
            }
            
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE transactions (id int auto_increment primary key, source int, dest int, value float);")) {
		prepStm.execute();
            }
            
            try (PreparedStatement prepStm = conn.prepareStatement("ALTER TABLE transactions AUTO_INCREMENT = 1111;")){
                prepStm.execute();
            }
            
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE admins (login varchar(25) primary key, password varchar(30));")) {
		prepStm.execute();
            }
            
            for(int i=0; i<5; i++)
            {
                int user = rand.nextInt(userList.size());
                String login = userdao.getUser(userList.get(user).getAccount()).getUsername();
                int dest = cstmList.get(rand.nextInt(userList.size())).getAccount();
                userdao.sendMoney(login, dest, rand.nextInt(1000));
            }
            int user1 = rand.nextInt(userList.size());
            String login = userdao.getUser(userList.get(user1).getAccount()).getUsername();
            int user2 = rand.nextInt(userList.size());
            int dest = userList.get(user2).getAccount();
            userdao.sendMoney(login, dest, rand.nextInt(1000));
            
            
            
            
	} catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
