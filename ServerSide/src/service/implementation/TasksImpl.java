package service.implementation;

import dao.TasksDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Tasks;
import service.TasksInterface;
import java.util.Date; // Added import

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kayje
 */

    public class TasksImpl extends UnicastRemoteObject implements TasksInterface{
    public TasksImpl() throws RemoteException{
        super();
        
    }
    
     public TasksDao dao = new TasksDao();

    @Override
    public String registerTasks(Tasks tasks) throws RemoteException {
        return dao.registerTasks(tasks);
    }

    @Override
    public String updateTasks(Tasks tasks) throws RemoteException {
      return dao.updateTasks(tasks);
    }

    @Override
    public String deleteTasks(Tasks tasks) throws RemoteException {
        return dao.deleteTasks(tasks);
    }

    @Override
    public List<Tasks> retrieveAll(int userId) throws RemoteException {
    return dao.retreiveAll(userId);
    }

    @Override
    public Tasks retrieveById(Tasks tasks) throws RemoteException {
       return dao.retrieveById(tasks);
    }

    @Override
    public List<Tasks> searchTasks(int userId, String searchTerm, String priority, Date dueDate, String tagName) throws RemoteException {
        return dao.searchTasks(userId, searchTerm, priority, dueDate, tagName);
    }
    
}
