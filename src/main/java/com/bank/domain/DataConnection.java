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
public class DataConnection {
    
    private static UserDao userDao;
	
	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}
    
}
