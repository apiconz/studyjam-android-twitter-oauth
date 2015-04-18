package pe.apiconz.mytwitterloginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Indra on 05/04/2015.
 */
public class InitialActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(Constants.TWITTER_KEY,
                        Constants.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        final Session activeSession = SessionRecorder.recordInitialSessionState(Twitter.getSessionManager().getActiveSession());

        if(activeSession != null){
            Log.d("TAG_APICONZ", "There is an active session!!");
            startActivity(new Intent(this, SuccessActivity.class));
        }else{
            Log.d("TAG_APICONZ", "There isn't an active session!!");
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}
