package com.example.timetable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MarkTime extends Activity implements View.OnClickListener {
    private TextView txTime,usedTime;
    private EditText eInputTime,eInputName;
    private int i=0,t=0,temp1=0;
    private Timer mTimer=null;
    private SQLiteDatabase dbWriter;
    private recordsDatabase rDB;
    private String courseName;
    float temp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_time);
        initView();
        rDB=new recordsDatabase(this);
        dbWriter=rDB.getWritableDatabase();
    }
    private void initView() {
        txTime=findViewById(R.id.tx_time);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        Button btnBack = findViewById(R.id.btn_back);
        Button btnSave = findViewById(R.id.btn_save);
        eInputTime=findViewById(R.id.et_inputtime);
        eInputName=findViewById(R.id.et_inputNmae);
        usedTime=findViewById(R.id.tx_usedtime);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                i=0;
                t=0;
//若没输入预计时间，则直接开始实际计时
                if (!eInputTime.getText().toString().trim().equals("")){
                    txTime.setText(eInputTime.getText().toString());
                    temp = Float.parseFloat(eInputTime.getText().toString());
                    i=60*(int)(temp /1)+(int)(60*(temp %1));//输入分钟转化为秒
                    temp1=i;
                    startTime();
                }else realTime();
                break;
            case R.id.btn_stop:
                stopTime();
                break;
            case R.id.btn_save:
                if(t!=0){
                    if(!eInputName.getText().toString().trim().equals("")){
                        courseName=eInputName.getText().toString();
                    }else courseName=" ";
                    ContentValues cv=new ContentValues();
                    cv.put(recordsDatabase.NAME,courseName);
                    cv.put(recordsDatabase.EXPECT,(temp1/60)+"分:"+(temp1%60)+"秒");
                    cv.put(recordsDatabase.ACTUL,t/60+"分:"+t%60+"秒");
                    cv.put(recordsDatabase.DATA,getData());
                    dbWriter.insert(recordsDatabase.TABLE_NAME,null,cv);
                    Intent intent =new Intent(MarkTime.this,RecordList.class);
                    startActivity(intent);
                    t=0;
                    ToastUtil.showMsg(this,"已保存");
                }else ToastUtil.showMsg(this,"还没有时间记录！");
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
//接收信息循环更新数据
    @SuppressLint("HandlerLeak")
    private Handler mHandler =new Handler(){
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//到时间了手机震动
            while (i==0){
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(3000);
                break;
            }
//时间超出时文字变红
            if (i <= 0) {
                txTime.setTextColor(getResources().getColor(R.color.red));
            }else txTime.setTextColor(getResources().getColor(R.color.black));
//剩余时间
            txTime.setText(msg.arg1+"分:"+msg.arg2+"秒");//秒转换成分钟加秒显示
//使用时间
            usedTime.setText(t/60+"分:"+t%60+"秒");
            startTime();
        }
    };
    public void startTime(){
        mTimer=new Timer();
        --i;
        t++;
//向主线程发送信息更新ui
        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                Message message = mHandler.obtainMessage();
                message.arg1 = i / 60;
                message.arg2 = i % 60;
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTask,1000);//1s更新一次
    }
    @SuppressLint("HandlerLeak")
    private Handler Handler =new Handler(){
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            usedTime.setText(msg.arg1+"分:"+msg.arg2+"秒");
            realTime();
        }
    };
    public void realTime(){
        mTimer=new Timer();
        t++;
        //向主线程发送信息更新ui
        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                Message message = Handler.obtainMessage();
                message.arg1 = t / 60;
                message.arg2 = t % 60;
                Handler.sendMessage(message);
            }
        };
        mTimer.schedule(mTask,1000);//1s更新一次
    }
    public void stopTime(){
        if(t==0){
            ToastUtil.showMsg(MarkTime.this,"暂未开始");
        }else{
            mTimer.cancel();
        }
    }
    public String getData(){
        String data="";
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                data=month + 1 + "月" + day + "日" + "  " + "周日";
                break;
            case 2:
                data=month + 1 + "月" + day + "日" + "  " + "周一";
                break;
            case 3:
                data=month + 1 + "月" + day + "日" + "  " + "周二";
                break;
            case 4:
                data=month + 1 + "月" + day + "日" + "  " + "周三";
                break;
            case 5:
                data=month + 1 + "月" + day + "日" + "  " + "周四";
                break;
            case 6:
                data=month + 1 + "月" + day + "日" + "  " + "周五";
                break;
            case 7:
                data=month + 1 + "月" + day + "日" + "  " + "周六";
                break;
        }
        return data;
    }
}