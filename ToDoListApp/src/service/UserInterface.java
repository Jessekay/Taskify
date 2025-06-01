/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.User;

/**
 *
 * @author kayje
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.User;
import java.util.List;


public interface UserInterface extends Remote{
    public String registerUser(User user) throws RemoteException;
    public String updateUser(User user) throws RemoteException;
    public String deleteUser(User user) throws RemoteException;
    public List<User> retrieveAll(User user) throws RemoteException;
    public User retrieveById(User user) throws RemoteException;
    User loginUser(String email, String password) throws RemoteException;
    
}