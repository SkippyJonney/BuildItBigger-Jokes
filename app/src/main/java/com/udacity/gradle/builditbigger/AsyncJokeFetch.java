package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.jokelibrary.jokeLibrary;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class AsyncJokeFetch extends AsyncTask<Pair<Context,String>,Void,String> {

    private static MyApi myApiService = null;
    private Context context;
    //private static final String LOG_TAG = AsyncJokeFetch.class.getSimpleName();

    @Override
    protected String doInBackground(Pair<Context,String>... params) {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setApplicationName("backend")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        MyApi myApiService = builder.build();

        String joke = null;
        context = params[0].first;
        String name = params[0].second;

        try {
            joke = myApiService.queryJoke().execute().getData();
            //return myApiService.sayHi("Jon").execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        // Fire Intent
        Intent jokeIntent = new Intent(context, jokeLibrary.class);
        jokeIntent.putExtra("joke",result);
        context.startActivity(jokeIntent);
    }



}