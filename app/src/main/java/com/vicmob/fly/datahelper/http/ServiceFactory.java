package com.vicmob.fly.datahelper.http;


import com.vicmob.fly.datahelper.BuildConfig;

public class ServiceFactory {

    private static MainService mService;

    public static MainService getMainIns() {
        if (mService == null) {
            mService = HttpClient.getIns(BuildConfig.BASE_URL).createService(MainService.class);
        }
        return mService;
    }

}