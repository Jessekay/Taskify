package dao;

import model.Tag;
import model.Tasks; // Needed for addTagToTask, removeTagFromTask
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class TagDao {

    public Tag createTag(Tag tag) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tag); // save will persist the new tag
            transaction.commit();
            return tag;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log error
            return null; // Or throw custom exception
        }
    }

    public Tag getTagByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tag> query = session.createQuery("FROM Tag t WHERE t.name = :name", Tag.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Tag> getAllTags() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tag> query = session.createQuery("FROM Tag", Tag.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to add an existing tag to an existing task
    public String addTagToTask(int taskId, int tagId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tasks task = session.get(Tasks.class, taskId);
            Tag tag = session.get(Tag.class, tagId);
            if (task != null && tag != null) {
                task.addTag(tag); // Use helper method in Tasks entity
                session.update(task); // Task is the owner of the relationship
                transaction.commit();
                return "Tag added to task successfully.";
            } else {
                if (transaction != null) transaction.rollback();
                return "Task or Tag not found.";
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error adding tag to task: " + e.getMessage();
        }
    }

    // Method to remove a tag from a task
    public String removeTagFromTask(int taskId, int tagId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tasks task = session.get(Tasks.class, taskId);
            Tag tag = session.get(Tag.class, tagId);
            if (task != null && tag != null) {
                task.removeTag(tag); // Use helper method in Tasks entity
                session.update(task);
                transaction.commit();
                return "Tag removed from task successfully.";
            } else {
                if (transaction != null) transaction.rollback();
                return "Task or Tag not found.";
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error removing tag from task: " + e.getMessage();
        }
    }
}
