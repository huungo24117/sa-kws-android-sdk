package kws.superawesome.tv;

import kws.superawesome.tv.kws.KWSCheckAllowed;
import kws.superawesome.tv.kws.KWSCheckAllowedInterface;
import kws.superawesome.tv.kws.KWSRequestPermission;
import kws.superawesome.tv.kws.KWSRequestPermissionInterface;

/**
 * Created by gabriel.coman on 24/05/16.
 */
public class KWSManager implements KWSCheckAllowedInterface, KWSRequestPermissionInterface {

    // singleton instance
    public static KWSManager sharedInstance = new KWSManager();

    // listener
    public KWSManagerInterface listener = null;

    // private vars
    private KWSCheckAllowed kwsCheck = null;
    private KWSRequestPermission kwsRequest = null;

    // private constructor
    private KWSManager(){
        kwsCheck = new KWSCheckAllowed();
        kwsRequest = new KWSRequestPermission();
    }

    // main func
    public void checkIfNotficationsAreAllowed () {
        kwsCheck.listener = this;
        kwsCheck.check();
    }

    // <KWSCheckPermissionProtocol>

    @Override
    public void pushAllowedInKWS() {
        kwsRequest.listener = this;
        kwsRequest.request();
    }

    @Override
    public void pushNotAllowedInKWS() {
        lisPushDisabledInKWS();
    }

    @Override
    public void checkError() {
        lisNetworkError();
    }

    // <KWSRequestPermissionProtocol>

    @Override
    public void pushPermissionRequestedInKWS() {
        lisIsAllowedToRegister();
    }

    @Override
    public void parentEmailIsMissingInKWS() {
        lisParentEmailIsMissingInKWS();
    }

    @Override
    public void requestError() {
        lisNetworkError();
    }

    // <Private Del> functions

    void lisPushDisabledInKWS () {
        if (listener != null) {
            listener.pushDisabledInKWS();
        }
    }

    void lisParentEmailIsMissingInKWS () {
        if (listener != null) {
            listener.parentEmailIsMissingInKWS();
        }
    }

    void lisNetworkError () {
        if (listener != null) {
            listener.networkError();
        }
    }

    void lisIsAllowedToRegister () {
        if (listener != null) {
            listener.isAllowedToRegister();
        }
    }

    void lisIsAlreadyRegistered () {
        if (listener != null) {
            listener.isAlreadyRegistered();
        }
    }
}
