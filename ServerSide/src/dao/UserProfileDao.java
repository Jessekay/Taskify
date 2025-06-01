package dao;

import model.UserProfile;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query; // Ensure this import is org.hibernate.query.Query

public class UserProfileDao {

    public String saveOrUpdateProfile(UserProfile profile) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(profile);
            transaction.commit();
            return "UserProfile saved/updated successfully";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error saving/updating UserProfile: " + e.getMessage();
        }
    }

    public UserProfile getProfileByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Assuming UserProfile has a 'user' field which has an 'id'
            Query<UserProfile> query = session.createQuery("FROM UserProfile up WHERE up.user.id = :userId", UserProfile.class);
            query.setParameter("userId", userId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteProfile(UserProfile profile) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(profile);
            transaction.commit();
            return "UserProfile deleted successfully";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error deleting UserProfile: " + e.getMessage();
        }
    }

    public UserProfile getProfileById(int profileId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(UserProfile.class, profileId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
