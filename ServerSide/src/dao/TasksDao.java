/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Tasks;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kayje
 */
public class TasksDao {
    
    
   
    public String registerTasks(Tasks tasks){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.save(tasks);
            tr.commit();
            ss.close();
            return "Data saved succesfully";
        }catch(Exception ex){
             ex.printStackTrace();
        }
        return null;
    }
    
    
    
   public String updateTasks(Tasks tasks){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.update(tasks);
            tr.commit();
            ss.close();
            return "Data updated succesfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null; 
    
    
}
   
   public String deleteTasks(Tasks tasks){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.delete(tasks);
            tr.commit();
            ss.close();
            return "Data deleted succesfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
   
   public List<Tasks> retreiveAll(){
        Session ss= HibernateUtil.getSessionFactory().openSession();
        List<Tasks> tasksList=ss.createQuery("select tas from"
                + "Tasks tas").list();
        ss.close();
        return tasksList;
    }
   
   
    public Tasks retrieveById(Tasks tasks){
        Session ss= HibernateUtil.getSessionFactory().openSession();
       Tasks tas=(Tasks)ss.get(Tasks.class, tasks.getId());
        ss.close();
        return tas;
    }
}
       


