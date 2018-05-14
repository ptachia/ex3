package com.ptachia.ex3;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerActivity extends AppCompatActivity {

    private Button create1;
    private Button start1;
    private Button cancel1;
    private TextView my_text;
    private boolean my_flag = false;
    private boolean is_run = false;

    private Handler my_handler = new Handler();
    private HandlerThread my_thread;


    private void createThread(View v){
        if (my_flag){
            Toast.makeText(this, "thread already created",
                Toast.LENGTH_LONG).show();
            return;}
        my_thread = new HandlerThread();
        my_flag = true;
    }


    private void startThread(View v){
        if (is_run){
            Toast.makeText(this, "thread was already started",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (!my_flag){
            Toast.makeText(this, "no thread created",
                    Toast.LENGTH_LONG).show();
            return;
        }
        my_thread.start();
    }

    private void cancelThread(View v){
        my_flag = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        create1 = findViewById(R.id.create1);
        start1 = findViewById(R.id.start1);
        cancel1 = findViewById(R.id.cancel1);
        my_text = findViewById(R.id.my_text1);

        create1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createThread(v);
            }
        });
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread(v);
            }
        });
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelThread(v);
            }
        });

    }

    class HandlerThread extends Thread{
        @Override
        public void run() {
            is_run = true;
            for (int i = 1; i <= 11; i++){
                my_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!my_flag){
                            my_text.setText("");
                            is_run = false;
                            return;}
                        String content = my_text.getText().toString();
                        if (content == "" || content == "done!"){my_text.setText("1");}
                        else{
                        int num = Integer.parseInt(content);
                        if (num == 10)
                        {
                            my_text.setText("done!");
                            my_flag = false;
                        }
                        else
                        {
                            num = num + 1;
                            my_text.setText(String.valueOf(num));
                        }}
                    }
                });
                if (!my_flag){
                    is_run = false;
                    return;}
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            is_run = false;
        }
    }
}
