package com.example.winter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    static final String INTENT_USER_NAME = "username"; // intent 中的数据标记
    private static final String INTENT_PASSWORD = "password"; // intent 中的数据标记


    public static void startActivity(Context context, EditText username, EditText password) {
        Intent intent = new Intent(context, MainActivity2.class);
        intent.putExtra(INTENT_USER_NAME, username);
        intent.putExtra(INTENT_PASSWORD, password);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String mUserName = intent.getStringExtra(INTENT_USER_NAME);
        String mPassword = intent.getStringExtra(INTENT_PASSWORD);
    }
}