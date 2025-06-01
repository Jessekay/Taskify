/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author kayje
 */

    
import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Category;
import java.util.List;


public interface CategoryInterface extends Remote{
    public String registerCategory(Category category) throws RemoteException;
    public String updateCategory(Category category) throws RemoteException;
    public String deleteCategory(Category category) throws RemoteException;
    public List<Category> retrieveAll(Category category) throws RemoteException;
    public Category retrieveById(Category category) throws RemoteException;
    
}
    

