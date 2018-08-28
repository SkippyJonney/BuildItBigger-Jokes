package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jokeCoffee.jokeCoffee;
import com.example.jokelibrary.jokeLibrary;

public class MainActivity extends AppCompatActivity {


    jokeCoffee mJokeCoffee = new jokeCoffee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        Toast.makeText(this, mJokeCoffee.getJoke(), Toast.LENGTH_LONG).show();
    }

    public void intentJoke(View view) {

        new AsyncJokeFetch().execute(new Pair<Context,String>(this, "Jon"));

        //Intent jokeIntent = new Intent(this, jokeLibrary.class);
        //jokeIntent.putExtra("joke",mJokeCoffee.getJoke());
        //startActivity(jokeIntent);
    }


}
