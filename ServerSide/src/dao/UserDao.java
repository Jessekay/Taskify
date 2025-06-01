/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author kayje
 */

public class UserDao {
public String registerUser(User user){
    try {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.save(user);
        tr.commit();
        ss.close();
        return "Success"; // Changed
    } catch (Exception ex) {
        ex.printStackTrace();
        return "Failed"; // Optional: Return a specific failure message
    }
}
    
    
    
   public String updateUser(User user){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.update(user);
            tr.commit();
            ss.close();
            return "Data updated succesfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null; 
    
    
}
   
   public String deleteUser(User user){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.delete(user);
            tr.commit();
            ss.close();
            return "Data deleted succesfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
   
   public List<User> retreiveAll(){
        Session ss= HibernateUtil.getSessionFactory().openSession();
        List<User> userList=ss.createQuery("select us from"
                + "User us").list();
        ss.close();
        return userList;
    }
   
   
    public User retrieveById(User user){
        Session ss= HibernateUtil.getSessionFactory().openSession();
       User us=(User)ss.get(User.class, user.getId());
        ss.close();
        return us;
    }
    
  public User loginUser(String email, String password) {
        Session ss = null;
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Attempting login for email: " + email);
            Query query = ss.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            User user = (User) query.uniqueResult();
            if (user != null) {
                System.out.println("User found: " + user.getEmail() + ", stored password hash: " + user.getPassword());
                if (BCrypt.checkpw(password, user.getPassword())) {
                    System.out.println("Password verification successful");
                    return user;
                } else {
                    System.out.println("Password verification failed");
                }
            } else {
                System.out.println("No user found with email: " + email);
            }
            return null;
        } catch (Exception ex) {
            System.out.println("Login error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        } finally {
            if (ss != null) ss.close();
        }
    }
}
       
