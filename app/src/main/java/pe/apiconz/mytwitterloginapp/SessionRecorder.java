package pe.apiconz.mytwitterloginapp;

import com.twitter.sdk.android.core.Session;

/**
 * Created by Indra on 05/04/2015.
 */
public class SessionRecorder {
    public static Session recordInitialSessionState(Session twitterSession){
        if(twitterSession != null){
            return twitterSession;
        }else{
            return null;
        }
    }
}
