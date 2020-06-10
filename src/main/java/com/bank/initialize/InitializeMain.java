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
public class InitializeMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DatabaseInitialize db = new DatabaseInitialize();
        db.initializeDatabase();
    }
    
}
