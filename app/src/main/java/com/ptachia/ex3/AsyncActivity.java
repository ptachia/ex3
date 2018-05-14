package com.ptachia.ex3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncActivity extends AppCompatActivity {

    private TextView my_text;
    private AsyncTaskThread my_thread;
    private boolean is_run = false;
    private boolean is_created = false;
    private boolean is_cancelled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        Button create1 = findViewById(R.id.create2);
        Button start1 = findViewById(R.id.start2);
        Button cancel1 = findViewById(R.id.cancel2);
        my_text = findViewById(R.id.my_text2);


        create1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAsyncTask();
            }
        });

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
            }
        });

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAsyncTask();
            }
        });
    }

    public void createAsyncTask(){
        if (is_created) {
            Toast.makeText(this, "thread already created",
                    Toast.LENGTH_LONG).show();
            return;}
        my_thread = new AsyncTaskThread();
        is_created = true;
        }


    public void startAsyncTask(){
        if (is_run){
            Toast.makeText(this, "thread is already run",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (!is_created){
            Toast.makeText(this, "no thread created",
                    Toast.LENGTH_LONG).show();
            return;
        }
        is_run = true;
        my_thread.execute();
    }

    public void cancelAsyncTask(){
        if (is_run){
            is_cancelled = true;}
        else {Toast.makeText(this, "no run thread",
                Toast.LENGTH_LONG).show();}
    }

    private class AsyncTaskThread extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... String) {
            if (!is_cancelled) {
                for (int i = 0; i < 10; i++) {
                    if (!is_cancelled) {
                        publishProgress(Integer.toString(i));
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            is_run = false;
            is_created = false;
            if (is_cancelled) {is_cancelled = false; return "";}
            is_cancelled = false;

            return "done!";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            my_text.setText(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            my_text.setText(s);
        }


    }
}
