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
import org.hibernate.query.Query; // Added for Query type
import java.util.Date; // Added for java.util.Date
import java.util.Map; // Added for Map
import java.util.HashMap; // Added for HashMap

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
   
   public List<Tasks> retreiveAll(int userId){
        Session ss= HibernateUtil.getSessionFactory().openSession();
        List<Tasks> tasksList=ss.createQuery("select tas from Tasks tas where tas.user.id = :userId")
                                .setParameter("userId", userId)
                                .list();
        ss.close();
        return tasksList;
    }
   
   
    public Tasks retrieveById(Tasks tasks){
        Session ss= HibernateUtil.getSessionFactory().openSession();
       Tasks tas=(Tasks)ss.get(Tasks.class, tasks.getId());
        ss.close();
        return tas;
    }

    public List<Tasks> searchTasks(int userId, String searchTerm, String priority, Date dueDate, String tagName) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           StringBuilder hql = new StringBuilder("SELECT DISTINCT t FROM Tasks t LEFT JOIN FETCH t.tags tag WHERE t.user.id = :userId");
           Map<String, Object> parameters = new HashMap<>();
           parameters.put("userId", userId);

           if (searchTerm != null && !searchTerm.trim().isEmpty()) {
               hql.append(" AND (LOWER(t.title) LIKE LOWER(:searchTerm) OR LOWER(t.description) LIKE LOWER(:searchTerm))");
               parameters.put("searchTerm", "%" + searchTerm + "%");
           }
           if (priority != null && !priority.trim().isEmpty()) {
               hql.append(" AND t.priority = :priority");
               parameters.put("priority", priority);
           }
           if (dueDate != null) {
               // Assuming t.dueDate is java.sql.Date in entity, convert if needed or ensure direct compatibility
               // For simplicity here, direct comparison. Real-world might need date range or careful time part handling.
               hql.append(" AND t.dueDate = :dueDate");
               parameters.put("dueDate", new java.sql.Date(dueDate.getTime())); // Convert to sql.Date if entity uses it
           }
           if (tagName != null && !tagName.trim().isEmpty()) {
               // No need for explicit JOIN with tags if already joined for fetching,
               // but ensure the alias 'tag' is correct or adjust.
               // If not LEFT JOIN FETCH t.tags initially, then:
               // hql.append(" JOIN t.tags tag WHERE tag.name = :tagName");
               hql.append(" AND tag.name = :tagName"); // Assumes 'tag' is the alias for elements in t.tags
               parameters.put("tagName", tagName);
           }

           Query<Tasks> query = session.createQuery(hql.toString(), Tasks.class);
           for (Map.Entry<String, Object> entry : parameters.entrySet()) {
               query.setParameter(entry.getKey(), entry.getValue());
           }
           return query.list();
       } catch (Exception e) {
           e.printStackTrace();
           return java.util.Collections.emptyList(); // Return empty list on error
       }
   }
}
       


