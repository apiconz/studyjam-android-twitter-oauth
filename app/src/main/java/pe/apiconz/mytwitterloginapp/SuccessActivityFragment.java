package pe.apiconz.mytwitterloginapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;


/**
 * A placeholder fragment containing a simple view.
 */
public class SuccessActivityFragment extends Fragment {

    public static final String LOG_TAG = "TAG_APICONZ";
    TwitterAuthClient authClient;

    public SuccessActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView()");
        TwitterSession activeSession = Twitter.getSessionManager().getActiveSession();
        authClient = new TwitterAuthClient();
        authClient.requestEmail(activeSession, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                Log.d(LOG_TAG, result.data);

            }

            @Override
            public void failure(TwitterException e) {
                Log.e(LOG_TAG,e.getMessage());
            }
        });
        return inflater.inflate(R.layout.fragment_success, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "Entró a onActivityResult del Fragment");


        authClient.onActivityResult(requestCode, resultCode, data);

    }
}
