package kws.superawesome.tv.kwssdk.kws;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tv.superawesome.lib.sautils.SAApplication;
import tv.superawesome.lib.sautils.SAUtils;
import tv.superawesome.lib.sanetwork.request.*;
import kws.superawesome.tv.kwssdk.KWS;
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
        String version = KWS.sdk.getVersion();

        if (email == null || email.length() == 0 || SAUtils.isValidEmail(email) == false) {
            lisInvalidError();
            return;
        }

        if (kwsApiUrl != null && oauthToken != null && metadata != null){

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

            JSONObject header = new JSONObject();
            try {
                header.put("Authorization", "Bearer " + oauthToken);
                header.put("Content-Type", "application/json");
                header.put("kws-sdk-version", version);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            SANetwork network = new SANetwork();
            network.sendPOST(SAApplication.getSAApplicationContext(), endpoint, new JSONObject(), header, body, new SANetworkInterface() {
                @Override
                public void success(int status, String payload) {
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
            lisEmailError();
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

    void lisInvalidError () {
        if (listener != null) {
            listener.invalidEmail();
        }
    }
}
