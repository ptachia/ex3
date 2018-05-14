    package com.ptachia.ex3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

    public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button handler_thread_button = findViewById(R.id.threadButton);
        Button async_thread_button = findViewById(R.id.asyncButton);

        handler_thread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent handler_intent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(handler_intent);
            }
        });

        async_thread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent async_intent = new Intent(MainActivity.this, AsyncActivity.class);
                startActivity(async_intent);
            }
        });



    }
}
