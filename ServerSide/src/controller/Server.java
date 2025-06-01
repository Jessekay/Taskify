package controller;

import dao.HibernateUtil;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.implementation.UserImpl;
import service.implementation.TasksImpl;
import service.implementation.CategoryImpl;
import service.implementation.UserProfileImpl; // Added import
import service.implementation.TagImpl; // Added import

public class Server {
    public static void main(String[] args) {
        try {
            
            System.out.println("Setting RMI hostname...");
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Initializing SessionFactory...");
            HibernateUtil.getSessionFactory();
            System.out.println("SessionFactory initialized, creating RMI registry...");
            Registry registry = LocateRegistry.createRegistry(6000);
            System.out.println("Binding services...");
            registry.rebind("user", new UserImpl());
            registry.rebind("tasks", new TasksImpl());
            registry.rebind("category", new CategoryImpl());
            registry.rebind("userprofile", new UserProfileImpl()); // Added binding
            registry.rebind("tagservice", new TagImpl()); // Added binding
            System.out.println("Server is running on port 6000 and is ready for clients!");
        } catch (ExceptionInInitializerError ex) {
            System.err.println("Critical: Database initialization failed, server cannot start: " + ex.getCause());
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception ex) {
            System.err.println("Server startup failed: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
}