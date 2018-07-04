package es.shwebill.component.android;

import es.shwebill.component.util.Logger;

public class NoInternetConnectionException
        extends Exception {

    public NoInternetConnectionException() {
        Logger.log("No Internet Connection...");
    }
}
