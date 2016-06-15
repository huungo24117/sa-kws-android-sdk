package kws.superawesome.tv.kwssdk.kws;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kws.superawesome.tv.kwslib.SANetwork;
import kws.superawesome.tv.kwslib.SANetworkInterface;
import kws.superawesome.tv.kwslib.SANetworkResponse;
import kws.superawesome.tv.kwslib.SAUtils;
import kws.superawesome.tv.kwssdk.KWS;
import kws.superawesome.tv.kwssdk.models.KWSError;
import kws.superawesome.tv.kwssdk.models.KWSMetadata;

/**
 * Created by gabriel.coman on 23/05/16.
 */
public class KWSParentEmail {

    // public listener
    public KWSParentEmailInterface listener = null;

    /**
     * Main class function
     * @param email email to send
     */
    public void submitEmail(String email) {

        String kwsApiUrl = KWS.sdk.getKwsApiUrl();
        String oauthToken = KWS.sdk.getOauthToken();
        KWSMetadata metadata = KWS.sdk.getMetadata();

        if (kwsApiUrl != null && oauthToken != null && metadata != null){

            if (SAUtils.isValidEmail(email)) {
                int userId= metadata.userId;
                String endpoint = kwsApiUrl + "users/" + userId + "/request-permissions";
                JSONObject body = new JSONObject();
                JSONArray array = new JSONArray();
                array.put("sendPushNotification");
                try {
                    body.put("permissions", array);
                    body.put("parentEmail", email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SANetwork network = new SANetwork();
                network.sendPOST(endpoint, oauthToken, body, new SANetworkInterface() {
                    @Override
                    public void success(Object data) {

                        SANetworkResponse response = (SANetworkResponse)data;
                        int status = response.statusCode;

                        if (status == 200 || status == 204) {
                            lisEmailSubmittedInKWS();
                        }
                        else {
                            lisEmailError();
                        }
                    }

                    @Override
                    public void failure() {
                        lisEmailError();
                    }
                });
            } else {

            }   lisEmailError();
        }
    }

    // <Private> functions

    void lisEmailSubmittedInKWS () {
        if (listener != null) {
            listener.emailSubmittedInKWS();
        }
    }

    void lisEmailError () {
        if (listener != null) {
            listener.emailError();
        }
    }
}
