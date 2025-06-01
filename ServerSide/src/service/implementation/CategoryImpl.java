/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.CategoryDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Category;
import service.CategoryInterface;

/**
 *
 * @author kayje
 */

public class CategoryImpl extends UnicastRemoteObject implements CategoryInterface{
    public CategoryImpl() throws RemoteException{
        super();
        
    }
    
     public CategoryDao dao = new CategoryDao();

    @Override
    public String registerCategory(Category category) throws RemoteException {
        return dao.registerCategory(category);
    }

    @Override
    public String updateCategory(Category category) throws RemoteException {
      return dao.updateCategory(category);
    }

    @Override
    public String deleteCategory(Category category) throws RemoteException {
        return dao.deleteCategory(category);
    }

    @Override
    public List<Category> retrieveAll(int userId) throws RemoteException {
    return dao.retreiveAll(userId);
    }

    @Override
    public Category retrieveById(Category category) throws RemoteException {
       return dao.retrieveById(category);
    }
}