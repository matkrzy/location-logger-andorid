package com.miketa.locationtracker;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mietek on 2018-05-21.
 */

public class UserTask extends AsyncTask<Void, Void, String> {
    private final int mQueryID;
    private final int mTrackID;
    private final Context mContext;
    private final int mID;


    UserTask(int queryID, int trackID, Context context, int id) {
        mQueryID = queryID;
        mTrackID = trackID;
        mContext = context;
        mID = id;
    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        switch (mQueryID) {
            case 1:
                return GetTrackList();
            case 2:
                return GetDeviceList();
            case 3:
                return GetDeleteDeviceInfo(0);
        }
        //Making JSON
            /*
            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("username", mEmail);
                jsonObj.put("password", mPassword);
            } catch (JSONException e) {
                return false;
            }
            */
        return null;
    }

    @Override
    protected void onPostExecute(final String response) {
        switch(mQueryID)
        {
            case 1:
                AppCompatActivity activity = (AppCompatActivity) mContext;
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RouteFragment fragment = RouteFragment.newInstance(1, response);
                fragmentTransaction.replace(R.id.routesHolder, fragment);
                fragmentTransaction.commit();
                break;
            case 2:
                AppCompatActivity activity_2 = (AppCompatActivity) mContext;
                FragmentManager fragmentManager_2 = activity_2.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_2 = fragmentManager_2.beginTransaction();
                DeviceFragment fragment_2 = DeviceFragment.newInstance(1, response);
                fragmentTransaction_2.replace(R.id.routesHolder, fragment_2);
                fragmentTransaction_2.commit();
                break;
            case 3:
                String text = response;
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(mContext, text, duration).show();
        }
    }


    protected String GetDeleteDeviceInfo(int ID)
    {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request;
            request = new Request.Builder()
                    .url("http://private-7bed3-locationlogger.apiary-mock.com/device/device_id")
                    .delete()
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (java.io.IOException e) {
            return e.getMessage();
        }
    }

    protected String GetTrackList() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request;
            request = new Request.Builder()
                    .url("http://private-7bed3-locationlogger.apiary-mock.com/tracks")
                    .get()
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (java.io.IOException e) {
            return null;
        }

    }

    protected String GetDeviceList() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request;
            request = new Request.Builder()
                    .url("http://private-7bed3-locationlogger.apiary-mock.com/device/device_id")
                    .get()
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (java.io.IOException e) {
            return null;
        }
    }
}