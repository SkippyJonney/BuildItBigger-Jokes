package com.example.jokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class jokeLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_library);

        // Get joke from passing intent
        String joke = getIntent().getStringExtra("joke");
        if(joke == null) { joke = "joke not found"; }

        // Set joke text
        TextView textView = findViewById(R.id.joke_tv);
        textView.setText(joke);
    }
}
