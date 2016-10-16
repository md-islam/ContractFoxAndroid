package com.example.jakubkalinowski.contractfoxandroid.interfaces;

import android.os.Bundle;

/**
 * Created by MD on 10/15/2016.
 * An Interface to help communicate from fragment to fragment
 */



public interface Communicator {
    public void respond(Bundle recievedBundle, String fragmentTag);
}
