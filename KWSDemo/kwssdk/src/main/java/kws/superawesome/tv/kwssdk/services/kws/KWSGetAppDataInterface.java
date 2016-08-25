package kws.superawesome.tv.kwssdk.services.kws;

import java.util.List;

import kws.superawesome.tv.kwssdk.models.appdata.KWSAppData;
import kws.superawesome.tv.kwssdk.services.KWSServiceResponseInterface;

/**
 * Created by gabriel.coman on 25/08/16.
 */
public interface KWSGetAppDataInterface extends KWSServiceResponseInterface {
    void gotAppData(List<KWSAppData> appData);
}
