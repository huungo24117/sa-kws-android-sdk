package kws.superawesome.tv.kwssdk.services.kws.user;

import kws.superawesome.tv.kwssdk.services.KWSServiceResponseInterface;

/**
 * Created by gabriel.coman on 24/08/16.
 */
public interface KWSChildrenInviteUserInterface extends KWSServiceResponseInterface {
    void didInviteUser (boolean success);
}
