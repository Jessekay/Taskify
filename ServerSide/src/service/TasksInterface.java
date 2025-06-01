/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Tasks;

/**
 *
 * @author kayje
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Tasks;
import java.util.List;
import java.util.Date; // Added import


public interface TasksInterface extends Remote{
    public String registerTasks(Tasks tasks) throws RemoteException;
    public String updateTasks(Tasks tasks) throws RemoteException;
    public String deleteTasks(Tasks tasks) throws RemoteException;
    public List<Tasks> retrieveAll(int userId) throws RemoteException;
    public Tasks retrieveById(Tasks tasks) throws RemoteException;
    List<Tasks> searchTasks(int userId, String searchTerm, String priority, Date dueDate, String tagName) throws RemoteException;
    
}