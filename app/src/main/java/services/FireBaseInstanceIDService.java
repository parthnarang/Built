package services;


import android.util.Log;

import com.google.firebase.iid.*;

public class FireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG ="FireBaseInstanceIDS";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
