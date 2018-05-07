package com.miketa.locationtracker;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RouteFragment.OnListFragmentInteractionListener {


    private UserTask mUserTask = null;
    private String lastQueryResult = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_add_device) {
            // Handle the camera action
        } else if (id == R.id.nav_del_device) {

        } else if (id == R.id.nav_manage_route) {
            mUserTask = new UserTask(1, 0);
            mUserTask.execute((Void) null);

        } else if (id == R.id.nav_add_route) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onListFragmentInteraction(Routes item)
    {

    }

    public class UserTask extends AsyncTask<Void, Void, String> {
        private final int mQueryID;
        private final int mTrackID;


        UserTask(int queryID, int trackID) {
            mQueryID = queryID;
            mTrackID = trackID;
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            switch (mQueryID) {
                case 1:
                    return GetTrackList();
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
            lastQueryResult = response;
            mUserTask = null;
            switch(mQueryID)
            {
                case 1:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    RouteFragment fragment = RouteFragment.newInstance(1, lastQueryResult);
                    fragmentTransaction.replace(R.id.routesHolder, fragment);
                    fragmentTransaction.commit();
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
    }

}
