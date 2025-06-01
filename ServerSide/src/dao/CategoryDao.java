/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Category;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kayje
 */
public class CategoryDao {
    
    
    
    public String registerCategory(Category category){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.save(category);
            tr.commit();
            ss.close();
            return "Data saved succesfully";
        }catch(Exception ex){
             ex.printStackTrace();
        }
        return null;
    }
    
    
    
   public String updateCategory(Category category){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.update(category);
            tr.commit();
            ss.close();
            return "Data updated succesfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null; 
    
    
}
   
   public String deleteCategory(Category category){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.delete(category);
            tr.commit();
            ss.close();
            return "Data deleted succesfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
   
   public List<Category> retreiveAll(int userId){
        Session ss= HibernateUtil.getSessionFactory().openSession();
        List<Category> categoryList=ss.createQuery("select cat from Category cat where cat.user.id = :userId")
                                      .setParameter("userId", userId)
                                      .list();
        ss.close();
        return categoryList;
    }
   
   
    public Category retrieveById(Category category){
        Session ss= HibernateUtil.getSessionFactory().openSession();
       Category cas=(Category)ss.get(Category.class, category.getId());
        ss.close();
        return cas;
    }
    
}


