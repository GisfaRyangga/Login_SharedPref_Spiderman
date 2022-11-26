package com.example.loginsharedpreference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn_submit;
    EditText user_name, pass;

    private boolean menglogin= false;

    static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.loginsharedpreference";

    static String COUNT_KEY = "count-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submit = findViewById(R.id.buttonSubmit);
        user_name = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        menglogin = mSharedPref.getBoolean(COUNT_KEY, false);
        if (menglogin){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menglogin = true;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                savePage();
                startActivity(intent);
            }
        });
    }

    private void savePage() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(COUNT_KEY, menglogin);
        editor.apply();
    }

    static void logoutPage() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(COUNT_KEY, false);
        editor.apply();
    }
}