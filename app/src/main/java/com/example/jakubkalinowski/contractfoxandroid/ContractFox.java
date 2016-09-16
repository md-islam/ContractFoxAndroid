package com.example.jakubkalinowski.contractfoxandroid;

//import com.firebase.client.Firebase;

/**
 * Created by jakubkalinowski on 4/20/16.
 */

// Life-cycle of the entire app
public class ContractFox extends android.app.Application {

    // Pass Android context to Firebase
    @Override
    public void onCreate() {
        super.onCreate();
        //Firebase.setAndroidContext(this);
    }
}
