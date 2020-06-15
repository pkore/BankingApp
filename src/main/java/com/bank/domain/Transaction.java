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
    int source;
    int dest;
    double value;

    public Transaction(int id, int from_acc, int to_acc, double value) {
        this.id = id;
        this.source = from_acc;
        this.dest = to_acc;
        this.value = value;
    }

    public Transaction() {
        this.id = 0;
        this.source = 0;
        this.dest = 0;
        this.value = 0;
    }

    public int getId() {
        return id;
    }

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", source=" + source + ", dest=" + dest + ", value=" + value + '}';
    }
}
