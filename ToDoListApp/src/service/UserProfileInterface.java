package service;

import model.UserProfile; // Ensure this import points to the client's model.UserProfile
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserProfileInterface extends Remote {
    String saveOrUpdateProfile(UserProfile profile) throws RemoteException;
    UserProfile getProfileByUserId(int userId) throws RemoteException;
    UserProfile getProfileById(int profileId) throws RemoteException;
    String deleteProfile(UserProfile profile) throws RemoteException;
}
