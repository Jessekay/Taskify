/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;
// import org.hibernate.Query; // Will be replaced by org.hibernate.query.Query
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import java.util.HashMap; // Added for OTP storage
import java.util.Map; // Added for OTP storage
import org.hibernate.query.Query; // For typed queries

/**
 *
 * @author kayje
 */

public class UserDao {

    // OTP Storage
    private static class OtpData {
        String otp;
        long expiryTime;
        OtpData(String otp, long expiryTime) { this.otp = otp; this.expiryTime = expiryTime; }
    }
    private static final Map<String, OtpData> otpStorage = new HashMap<>();
    private static final long OTP_VALIDITY_DURATION_MS = 5 * 60 * 1000; // 5 minutes

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

    public User getUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateAndStoreOtp(String email) {
        // Check if user exists
        User user = getUserByEmail(email);
        if (user == null) {
            System.out.println("OTP generation failed: User not found for email " + email);
            return null;
        }

        String otp = String.format("%06d", new java.util.Random().nextInt(999999));
        long expiryTime = System.currentTimeMillis() + OTP_VALIDITY_DURATION_MS;
        otpStorage.put(email, new OtpData(otp, expiryTime));
        System.out.println("Generated OTP for " + email + ": " + otp); // For testing
        // In a real app, you would email the OTP to the user here, not return it directly.
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        OtpData storedOtpData = otpStorage.get(email);
        if (storedOtpData == null) {
            System.out.println("OTP verification failed: No OTP found for email " + email);
            return false; // No OTP requested or already used/expired
        }
        if (System.currentTimeMillis() > storedOtpData.expiryTime) {
            otpStorage.remove(email); // OTP expired
            System.out.println("OTP verification failed: OTP expired for email " + email);
            return false;
        }
        if (storedOtpData.otp.equals(otp)) {
            otpStorage.remove(email); // OTP successfully verified, remove it
            System.out.println("OTP verification successful for email " + email);
            return true;
        }
        System.out.println("OTP verification failed: Invalid OTP for email " + email);
        return false; // Invalid OTP
    }
}
       
