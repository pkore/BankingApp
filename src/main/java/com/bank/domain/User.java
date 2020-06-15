/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.domain;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Anushka Chincholkar
 */
public class User {
    private int account;
    private String username;
    private String password;
    private List transaction;
    private boolean active;

    public User(int account_no, String username, String password, List transactions, boolean active) {
        this.account = account_no;
        this.username = username;
        this.password = password;
        this.transaction = transactions;
        this.active = active;
    }

    public User() {
        account = 0;
        username = "";
        password = "";
        transaction = new ArrayList<String>();
        active = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccount() {
        return account;
    }

    public List getTransaction() {
        return transaction;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" + "account=" + account + ", username=" + username + ", password=" + password + ", active=" + active + '}';
    }
}