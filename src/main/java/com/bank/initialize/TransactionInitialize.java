/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.initialize;

/**
 *
 * @author Anushka Chincholkar
 */
public class TransactionInitialize {
    private String login;
    private int dest;
    private double value;

    public TransactionInitialize(String login, int dest, double value) {
        this.login = login;
        this.dest = dest;
        this.value = value;
    }

    public String getLogin() {
        return login;
    }

    public int getDest() {
        return dest;
    }

    public double getValue() {
        return value;
    }
    
}
