/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.domain;

/**
 *
 * @author Anushka Chincholkar
 */
public class Customer {
    int account;
    String name;
    String email;
    int phone;
    double balance;

    public Customer(int account, String name, String email, int phone, double value) {
        this.account = account;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double value) {
        this.balance = value;
    }

    public int getAccount() {
        return account;
    }
}
