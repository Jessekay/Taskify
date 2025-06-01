package service;

import model.Tag; // Ensure this import points to the client's model.Tag
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TagInterface extends Remote {
    Tag createTag(Tag tag) throws RemoteException;
    Tag getTagByName(String name) throws RemoteException;
    List<Tag> getAllTags() throws RemoteException;
    String addTagToTask(int taskId, int tagId) throws RemoteException;
    String removeTagFromTask(int taskId, int tagId) throws RemoteException;
}
