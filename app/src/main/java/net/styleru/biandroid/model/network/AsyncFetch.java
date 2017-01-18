package net.styleru.biandroid.model.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncFetch extends AsyncTask<String, String, String>
{
    private ProgressDialog pdLoading;
    private HttpURLConnection conn;
    private URL url = null;
    private String loadingMsg;
    private String failMsg;
    private String urlPath;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    public AsyncFetch(Context context, String loadingMsg, String failMsg,String urlPath)
    {
        pdLoading=new ProgressDialog(context);
        this.loadingMsg=loadingMsg;
        this.failMsg=failMsg;
        this.urlPath=urlPath;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread
        //pdLoading.setMessage("\t"+loadingMsg+"...");
        //pdLoading.setCancelable(false);
        //pdLoading.show();

    }

    @Override
    protected String doInBackground(String... params) {
        try
        {
            url = new URL(urlPath);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.toString();
        }
        try {

            // Setup HttpURLConnection class to send and receive data from php and mysql
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("GET");

            // setDoOutput to true as we recieve data from json file
            conn.setDoOutput(true);

        } catch (IOException ioex) {
            // TODO Auto-generated catch block
            ioex.printStackTrace();
            return ioex.toString();
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                return (result.toString());

            } else {

                return ("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            conn.disconnect();
        }


    }
    @Override
    protected void onPostExecute(String result)
    {
        pdLoading.dismiss();
    }

}
