package kws.superawesome.tv.kwssdk.services.kws;

import kws.superawesome.tv.kwssdk.services.KWSServiceResponseInterface;

/**
 * Created by gabriel.coman on 13/06/16.
 */
public interface KWSUnregisterTokenInterface extends KWSServiceResponseInterface {
    void unregistered(boolean success);
}
