package com.boes.guideproject.app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class GuideApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(
                this,
                "yA9L6CVkkMw3XVBhEA304TJDSpp3OZj2284sKJGI",
                "xVEtykWkg0hQy0gyp4Q5neaA16GKsEnRI6IdlibH"
        );

        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
    }

}
