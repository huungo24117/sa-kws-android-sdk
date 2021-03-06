package kws.superawesome.tv.kwssdk.services.kws.notifications;

import android.content.Context;

import org.json.JSONObject;

import kws.superawesome.tv.kwssdk.services.KWSHTTPMethod;
import kws.superawesome.tv.kwssdk.services.KWSService;
import kws.superawesome.tv.kwssdk.services.KWSServiceResponseInterface;
import tv.superawesome.lib.sajsonparser.SAJsonParser;

/**
 * Created by gabriel.coman on 13/06/16.
 */
public class KWSRegisterToken extends KWSService {

    private KWSRegisterTokenInterface listener = null;
    private String token = null;

    @Override
    public String getEndpoint() {
        return "v1/apps/" + super.loggedUser.metadata.appId + "/users/" + super.loggedUser.metadata.userId + "/subscribe-push-notifications";
    }

    @Override
    public KWSHTTPMethod getMethod() {
        return KWSHTTPMethod.POST;
    }

    @Override
    public JSONObject getBody() {
        return SAJsonParser.newObject(new Object[]{
                "token", token,
                "platform", "android"
        });
    }

    @Override
    public void success(int status, String payload, boolean success) {
        listener.registered(success && (status == 200 || status == 204));
    }

    @Override
    public void execute(Context context, Object param, KWSServiceResponseInterface listener) {
        KWSRegisterTokenInterface local = new KWSRegisterTokenInterface() {public void registered(boolean success) {}};
        this.listener = listener != null ? (KWSRegisterTokenInterface) listener : local;

        // check for param
        if (param instanceof  String) {
            token = (String)param;
        } else {
            this.listener.registered(false);
            return;
        }

        // execute
        super.execute(context, param, this.listener);
    }
}
