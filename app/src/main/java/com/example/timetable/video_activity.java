package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class video_activity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_activity);
        intiviews();
    }
    private void intiviews() {
        Button btnBack = findViewById(R.id.btn_video_back);
        btnBack.setOnClickListener(this);
        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(this);
        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(this);
        Button btn6 = findViewById(R.id.button6);
        btn6.setOnClickListener(this);
        Button btn7 = findViewById(R.id.button7);
        btn7.setOnClickListener(this);
        Button btn8 = findViewById(R.id.button8);
        btn8.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av50267369?from=search&seid=10032786548327533084")));
                break;
            case R.id.button2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av26628873?from=search&seid=14076187365034239125")));
                break;
            case R.id.button3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av29971113?from=search&seid=4225407064392890576")));
                break;
            case R.id.button4:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av31398168?from=search&seid=15210698359342426067")));
                break;
            case R.id.button5:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av19087994?from=search&seid=1524395780073933822")));
                break;
            case R.id.button6:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av28030909?from=search&seid=9581506050534035701")));
                break;
            case R.id.button7:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av35689815?from=search&seid=12083727553561546394")));
                break;
            case R.id.button8:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/video/av23632686?from=search&seid=917643867371364942")));
                break;
            case R.id.btn_video_back:
                finish();
                break;
        }
    }
}