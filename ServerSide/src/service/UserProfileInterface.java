package service;

import model.UserProfile;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserProfileInterface extends Remote {
    String saveOrUpdateProfile(UserProfile profile) throws RemoteException;
    UserProfile getProfileByUserId(int userId) throws RemoteException;
    UserProfile getProfileById(int profileId) throws RemoteException; // Added for completeness
    String deleteProfile(UserProfile profile) throws RemoteException;
}
