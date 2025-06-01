/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.UserDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.User;
import service.UserInterface;

/**
 *
 * @author kayje
 */


public class UserImpl extends UnicastRemoteObject implements UserInterface{
    // public UserDao dao = new UserDao(); // Ensure dao is initialized, if not already
    public UserImpl() throws RemoteException{
        super();
        
    }
    
     public UserDao dao = new UserDao();

    @Override
    public String registerUser(User user) throws RemoteException {
        return dao.registerUser(user);
    }

    @Override
    public String updateUser(User user) throws RemoteException {
      return dao.updateUser(user);
    }

    @Override
    public String deleteUser(User user) throws RemoteException {
        return dao.deleteUser(user);
    }

    @Override
    public List<User> retrieveAll(User user) throws RemoteException {
    return dao.retreiveAll();
    }

    @Override
    public User retrieveById(User user) throws RemoteException {
       return dao.retrieveById(user);
    }

     @Override
    public User loginUser(String email, String password) throws RemoteException {
        return dao.loginUser(email, password);
    }

    @Override
    public String requestOtp(String email) throws RemoteException {
        // In a real scenario, this would trigger an email.
        // For now, it calls the DAO method which logs and returns the OTP.
        return dao.generateAndStoreOtp(email);
    }

    @Override
    public User loginUserWithOtp(String email, String otp) throws RemoteException {
        if (dao.verifyOtp(email, otp)) {
            // OTP is valid, now retrieve the user (without password check)
            return dao.getUserByEmail(email);
        }
        return null; // OTP verification failed
    }
   
   }