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
    private List<Transaction> transaction;
    private boolean active;
    private String cardStat;

    public User(int account_no, String username, String password, List<Transaction> transactions, boolean active, String cardStat) {
        this.account = account_no;
        this.username = username;
        this.password = password;
        this.transaction = transactions;
        this.active = active;
        this.cardStat = cardStat;
    }

    public User() {
        account = 0;
        username = "";
        password = "";
        transaction = new ArrayList<Transaction>();
        active = false;
        cardStat = "none";
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

    public String getCardStat() {
        return cardStat;
    }

    public void setCardStat(String cardStat) {
        this.cardStat = cardStat;
    }

    @Override
    public String toString() {
        String ret = "User{" + "account=" + account + ", username=" + username + ", password=" + password + ", active=" + active + '}';
        for(Transaction t: transaction){
            ret = ret + t.toString();
        }
        return ret;
    }
}