package com.example.savingactivitystate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNTER = "KEY_COUNTER";
    private int mCounter = 0;
    private TextView textView;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText =findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView =findViewById(R.id.tv_counter);

        SharedPreferences sharedPrefs = getPreferences(MODE_PRIVATE);
        int defaultCounter = 0;
        mCounter= sharedPrefs.getInt(KEY_COUNTER, defaultCounter);
        textView.setText("Counter:" +mCounter);
    }

    public void onCounterClick(View view) {
        mCounter++;
        button.setText("Counter :" + mCounter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNTER, mCounter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCounter = savedInstanceState.getInt(KEY_COUNTER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(KEY_COUNTER,mCounter);
        editor.apply();
    }
}
