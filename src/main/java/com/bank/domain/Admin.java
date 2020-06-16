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
public class Admin {
    String login;
    String password;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public Admin() {
        this.login = "";
        this.password = "";
    }
    
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + "login=" + login + ", password=" + password + '}';
    }
    
    
}
