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
public class KWSUnregisterToken extends KWSService {

    private KWSUnregisterTokenInterface listener = null;
    private String token;

    @Override
    public String getEndpoint() {
        return "v1/apps/" + super.loggedUser.metadata.appId + "/users/" + super.loggedUser.metadata.userId + "/unsubscribe-push-notifications";
    }

    @Override
    public KWSHTTPMethod getMethod() {
        return KWSHTTPMethod.POST;
    }

    @Override
    public JSONObject getBody() {
        return SAJsonParser.newObject(new Object[]{
                "token", token
        });
    }

    @Override
    public void success(int status, String payload, boolean success) {
        listener.unregistered(success && (status == 200 || status == 204));
    }

    @Override
    public void execute(Context context, Object param, KWSServiceResponseInterface listener) {
        KWSUnregisterTokenInterface local = new KWSUnregisterTokenInterface() {public void unregistered(boolean success) {}};
        this.listener = listener != null ? (KWSUnregisterTokenInterface) listener : local;

        // check param
        if (param instanceof String) {
            token = (String)param;
        } else {
            this.listener.unregistered(false);
            return;
        }

        // execute
        super.execute(context, param, this.listener);
    }
}
