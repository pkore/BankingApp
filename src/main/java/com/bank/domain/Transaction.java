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
public class Transaction {
    int id;
    int from_acc;
    int to_acc;
    int value;

    public Transaction(int id, int from_acc, int to_acc, int value) {
        this.id = id;
        this.from_acc = from_acc;
        this.to_acc = to_acc;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getFrom_acc() {
        return from_acc;
    }

    public int getTo_acc() {
        return to_acc;
    }

    public int getValue() {
        return value;
    }    
}
