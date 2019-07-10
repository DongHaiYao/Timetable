package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;

import android.os.Bundle;
import android.widget.TextView;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler mHandler=new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView textView = findViewById(R.id.welcomText);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "ziti1.ttf");
        textView.setTypeface(typeface);
        mHandler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1300);
    }
}