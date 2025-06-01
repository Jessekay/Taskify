package service.implementation;

import dao.UserProfileDao;
import model.UserProfile;
import service.UserProfileInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserProfileImpl extends UnicastRemoteObject implements UserProfileInterface {

    private UserProfileDao dao = new UserProfileDao();

    public UserProfileImpl() throws RemoteException {
        super();
    }

    @Override
    public String saveOrUpdateProfile(UserProfile profile) throws RemoteException {
        return dao.saveOrUpdateProfile(profile);
    }

    @Override
    public UserProfile getProfileByUserId(int userId) throws RemoteException {
        return dao.getProfileByUserId(userId);
    }

    @Override
    public UserProfile getProfileById(int profileId) throws RemoteException {
        return dao.getProfileById(profileId);
    }

    @Override
    public String deleteProfile(UserProfile profile) throws RemoteException {
        return dao.deleteProfile(profile);
    }
}
