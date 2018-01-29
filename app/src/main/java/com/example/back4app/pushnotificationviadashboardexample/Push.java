package com.example.back4app.pushnotificationviadashboardexample;

import android.app.Application;

import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseInstallation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Push extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);
        ArrayList<String> channels = new ArrayList<>();
        channels.add("News");
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        // don't forget to change the line below with the sender ID you obtained at Firebase
        installation.put("GCMSenderId", "790565434350");
        installation.put("channels", channels);

        installation.saveInBackground();

        HashMap<String, String> params = new HashMap<>();

        ParseCloud.callFunctionInBackground("pushsample", params, new FunctionCallback<Object>() {
            @Override
            public void done(Object response, ParseException exc) {
                if(exc == null) {
                    // The function executed, but still has to check the response
                }
                else {
                    // Something went wrong
                }
            }
        });
    }
}

Rest
curl -X POST -H "X-Parse-Application-Id: mDdW3B96uIS8tsm1Y5TfqUOKPmQ9gGNoXDcPab76" -H "X-Parse-REST-API-Key: nB2zMr3WhFyJIpQje9gW0QNvAVKj73eSDMdPDXkN" -H "Content-Type: application/json" -d '{}' https://parseapi.back4app.com/functions/pushsample