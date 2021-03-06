package pe.apiconz.mytwitterloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by armando on 4/3/15.
 */
public class TwitterFragment extends Fragment {

    public static final String LOG_TAG = "TAG_APICONZ";
    private TwitterLoginButton loginButton;

    public TwitterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        loginButton = (TwitterLoginButton) rootView.findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a
                // TwitterSession for making API calls
                Log.d(LOG_TAG, "Success");

                TwitterSession session =
                        Twitter.getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;

                startSecondActivity();

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Log.d(LOG_TAG,"Failure");
            }
        });

        return rootView;
    }


    private void startSecondActivity(){
        final Intent intent = new Intent(getActivity(), SuccessActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG_APICONZ","Entro aqui");
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}