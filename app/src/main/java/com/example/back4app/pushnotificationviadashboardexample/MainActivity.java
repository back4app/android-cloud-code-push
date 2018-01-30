package com.example.back4app.pushnotificationviadashboardexample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Parse.initialize(this);
        ArrayList<String> channels = new ArrayList<>();
        channels.add("News");
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        // don't forget to change the line below with the sender ID you obtained at Firebase
        installation.put("GCMSenderId", "790565434350");
        installation.put("channels", channels);
        installation.saveInBackground();
        final HashMap<String, String> params = new HashMap<>();


        final Button cloud_code_button = findViewById(R.id.cloud_code_button);
        cloud_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(MainActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Seding push...");
                dlg.show();


                ParseCloud.callFunctionInBackground("pushsample", params, new FunctionCallback<Object>() {
                    @Override
                    public void done(Object response, ParseException exc) {
                        if(exc == null) {
                            // The function executed, but still has to check the response
                            dlg.dismiss();
                            alertDisplayer("Successful Push","Check on your phone the notifications to confirm!");
                        }
                        else {
                            // Something went wrong
                            dlg.dismiss();
                            Toast.makeText(MainActivity.this, exc.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}

//Rest
//curl -X POST -H "X-Parse-Application-Id: mDdW3B96uIS8tsm1Y5TfqUOKPmQ9gGNoXDcPab76" -H "X-Parse-REST-API-Key: nB2zMr3WhFyJIpQje9gW0QNvAVKj73eSDMdPDXkN" -H "Content-Type: application/json" -d '{}' https://parseapi.back4app.com/functions/pushsample