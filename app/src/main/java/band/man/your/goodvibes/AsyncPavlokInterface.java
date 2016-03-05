package me.alejandromera.smstest;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alejandro on 3/5/2016.

 Stimulus: vibrate, shock, beep
 Level: 1-255
 new AsyncPavlokInterface(String Stimulus, String Level).execute();

This two lines are required  in the on create method of  the main activity:
    
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
StrictMode.setThreadPolicy(policy);
 
 
 
 */


public class AsyncPavlokInterface  extends AsyncTask <String, Void, Void>{

    private String StrURL;

    //constructor
    public AsyncPavlokInterface(String TypeStimulus, String level ){
        StrURL= "https://pavlok.herokuapp.com/api/B2xzCdZoIO/" + TypeStimulus + "/" + level;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {


            URL url = new URL(StrURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            String urlParameters = "fizz=buzz";
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return null;
    }
}
