package kws.superawesome.tv;

/**
 * Created by gabriel.coman on 24/05/16.
 */
public interface KWSManagerInterface {

    void pushDisabledInKWS ();
    void parentEmailIsMissingInKWS ();
    void isAllowedToRegister ();
    void networkErrorCheckingForKWS ();
    void networkErrorRequestingPermissionFromKWS ();

}
