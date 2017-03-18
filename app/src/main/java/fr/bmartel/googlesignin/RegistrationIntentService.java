package fr.bmartel.googlesignin;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.v(TAG, "onHandleIntent");

        String token = "";

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            // [END register_for_gcm]
        } catch (Exception e) {
            //setError(ClientError.NOTIFICATION_REGISTRATION, e.getMessage());
            Log.e(TAG, "Failed to complete token refresh", e);
        }
        Log.v(TAG, "retrieve token : " + token);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        /*
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("registrationId", token);
        sendBroadcast(registrationComplete);
        */
    }
}
