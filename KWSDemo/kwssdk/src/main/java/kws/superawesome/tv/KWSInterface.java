package kws.superawesome.tv;

/**
 * Created by gabriel.coman on 23/05/16.
 */
public interface KWSInterface {
    void kwsSDKDidRegisterUserForRemoteNotifications ();
    void kwsSDKDidUnregisterUserForRemoteNotifications ();
    void kwsSDKDidFailToRegisterUserForRemoteNotificationsWithError (KWSErrorType type);
}
