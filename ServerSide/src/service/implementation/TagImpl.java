package service.implementation;

import dao.TagDao;
import model.Tag;
import service.TagInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TagImpl extends UnicastRemoteObject implements TagInterface {

    private TagDao dao = new TagDao();

    public TagImpl() throws RemoteException {
        super();
    }

    @Override
    public Tag createTag(Tag tag) throws RemoteException {
        return dao.createTag(tag);
    }

    @Override
    public Tag getTagByName(String name) throws RemoteException {
        return dao.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTags() throws RemoteException {
        return dao.getAllTags();
    }

    @Override
    public String addTagToTask(int taskId, int tagId) throws RemoteException {
        return dao.addTagToTask(taskId, tagId);
    }

    @Override
    public String removeTagFromTask(int taskId, int tagId) throws RemoteException {
        return dao.removeTagFromTask(taskId, tagId);
    }
}
