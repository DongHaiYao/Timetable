package com.example.timetable;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tvTime;
    protected classDatabase myDb;
    private SQLiteDatabase dbReader,dbWriter;
    private Button btnTimeMark,btnVideo,btnRecords;
    protected boolean flag = false;
    public TextView tv11,tv12,tv13,tv14,tv15,
                    tv21,tv22,tv23,tv24,tv25,
                    tv31,tv32,tv33,tv34,tv35,
                    tv41,tv42,tv43,tv44,tv45,
                    tv51,tv52,tv53,tv54,tv55,
                    tv61,tv62,tv63,tv64,tv65,
                    tv71,tv72,tv73,tv74,tv75;
    public TextView txMon,txTus,txWed,txThurs,txFri,txSatur,txSun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        intiviews();//找到view
//实例化DB
        myDb=new classDatabase(this);
        dbWriter=myDb.getWritableDatabase();
        dbReader=myDb.getReadableDatabase();
//
        tvTime = findViewById(R.id.tv_daytime);
//第一次App运行初始化数据库
        intailDB();
//显示时间
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        monthDayWeek(calendar, month, day);//文本通过calendar获取时间
//
        setTextListenners();//设置点击事件
        setTextContent();//从SQL读取数据
        setTxBackground();//如果tx不为空则设置tx的颜色
        setTextHeight();//根据节数设置高度
    }
    private void setTextHeight(){
        tv11.getLayoutParams().height=dip2px(this,69)*getDbClassNum(1);
        tv12.getLayoutParams().height=dip2px(this,69)*getDbClassNum(2);
        tv13.getLayoutParams().height=dip2px(this,69)*getDbClassNum(3);
        tv14.getLayoutParams().height=dip2px(this,69)*getDbClassNum(4);
        tv15.getLayoutParams().height=dip2px(this,69)*getDbClassNum(5);
        tv21.getLayoutParams().height=dip2px(this,69)*getDbClassNum(6);
        tv22.getLayoutParams().height=dip2px(this,69)*getDbClassNum(7);
        tv23.getLayoutParams().height=dip2px(this,69)*getDbClassNum(8);
        tv24.getLayoutParams().height=dip2px(this,69)*getDbClassNum(9);
        tv25.getLayoutParams().height=dip2px(this,69)*getDbClassNum(10);
        tv31.getLayoutParams().height=dip2px(this,69)*getDbClassNum(11);
        tv32.getLayoutParams().height=dip2px(this,69)*getDbClassNum(12);
        tv33.getLayoutParams().height=dip2px(this,69)*getDbClassNum(13);
        tv34.getLayoutParams().height=dip2px(this,69)*getDbClassNum(14);
        tv35.getLayoutParams().height=dip2px(this,69)*getDbClassNum(15);
        tv41.getLayoutParams().height=dip2px(this,69)*getDbClassNum(16);
        tv42.getLayoutParams().height=dip2px(this,69)*getDbClassNum(17);
        tv43.getLayoutParams().height=dip2px(this,69)*getDbClassNum(18);
        tv44.getLayoutParams().height=dip2px(this,69)*getDbClassNum(19);
        tv45.getLayoutParams().height=dip2px(this,69)*getDbClassNum(20);
        tv51.getLayoutParams().height=dip2px(this,69)*getDbClassNum(21);
        tv52.getLayoutParams().height=dip2px(this,69)*getDbClassNum(22);
        tv53.getLayoutParams().height=dip2px(this,69)*getDbClassNum(23);
        tv54.getLayoutParams().height=dip2px(this,69)*getDbClassNum(24);
        tv55.getLayoutParams().height=dip2px(this,69)*getDbClassNum(25);
        tv61.getLayoutParams().height=dip2px(this,69)*getDbClassNum(26);
        tv62.getLayoutParams().height=dip2px(this,69)*getDbClassNum(27);
        tv63.getLayoutParams().height=dip2px(this,69)*getDbClassNum(28);
        tv64.getLayoutParams().height=dip2px(this,69)*getDbClassNum(29);
        tv65.getLayoutParams().height=dip2px(this,69)*getDbClassNum(30);
        tv71.getLayoutParams().height=dip2px(this,69)*getDbClassNum(31);
        tv72.getLayoutParams().height=dip2px(this,69)*getDbClassNum(32);
        tv73.getLayoutParams().height=dip2px(this,69)*getDbClassNum(33);
        tv74.getLayoutParams().height=dip2px(this,69)*getDbClassNum(34);
        tv75.getLayoutParams().height=dip2px(this,69)*getDbClassNum(35);
    }
    private void setTextContent() {
        tv11.setText(getDbContent(1));
        tv12.setText(getDbContent(2));
        tv13.setText(getDbContent(3));
        tv14.setText(getDbContent(4));
        tv15.setText(getDbContent(5));
        tv21.setText(getDbContent(6));
        tv22.setText(getDbContent(7));
        tv23.setText(getDbContent(8));
        tv24.setText(getDbContent(9));
        tv25.setText(getDbContent(10));
        tv31.setText(getDbContent(11));
        tv32.setText(getDbContent(12));
        tv33.setText(getDbContent(13));
        tv34.setText(getDbContent(14));
        tv35.setText(getDbContent(15));
        tv41.setText(getDbContent(16));
        tv42.setText(getDbContent(17));
        tv43.setText(getDbContent(18));
        tv44.setText(getDbContent(19));
        tv45.setText(getDbContent(20));
        tv51.setText(getDbContent(21));
        tv52.setText(getDbContent(22));
        tv53.setText(getDbContent(23));
        tv54.setText(getDbContent(24));
        tv55.setText(getDbContent(25));
        tv61.setText(getDbContent(26));
        tv62.setText(getDbContent(27));
        tv63.setText(getDbContent(28));
        tv64.setText(getDbContent(29));
        tv65.setText(getDbContent(30));
        tv71.setText(getDbContent(31));
        tv72.setText(getDbContent(32));
        tv73.setText(getDbContent(33));
        tv74.setText(getDbContent(34));
        tv75.setText(getDbContent(35));
    }
    private void intiviews() {
        txMon=findViewById(R.id.tx_monday);
        txTus=findViewById(R.id.tx_tuseday);
        txWed=findViewById(R.id.tx_wednesday);
        txThurs=findViewById(R.id.tx_thursday);
        txFri=findViewById(R.id.tx_friday);
        txSatur=findViewById(R.id.tx_saturday);
        txSun=findViewById(R.id.tx_sunday);

        tv11=findViewById(R.id.tv11);
        tv12=findViewById(R.id.tv12);
        tv13=findViewById(R.id.tv13);
        tv14=findViewById(R.id.tv14);
        tv15=findViewById(R.id.tv15);
        tv21=findViewById(R.id.tv21);
        tv22=findViewById(R.id.tv22);
        tv23=findViewById(R.id.tv23);
        tv24=findViewById(R.id.tv24);
        tv25=findViewById(R.id.tv25);
        tv31=findViewById(R.id.tv31);
        tv32=findViewById(R.id.tv32);
        tv33=findViewById(R.id.tv33);
        tv34=findViewById(R.id.tv34);
        tv35=findViewById(R.id.tv35);
        tv41=findViewById(R.id.tv41);
        tv42=findViewById(R.id.tv42);
        tv43=findViewById(R.id.tv43);
        tv44=findViewById(R.id.tv44);
        tv45=findViewById(R.id.tv45);
        tv51=findViewById(R.id.tv51);
        tv52=findViewById(R.id.tv52);
        tv53=findViewById(R.id.tv53);
        tv54=findViewById(R.id.tv54);
        tv55=findViewById(R.id.tv55);
        tv61=findViewById(R.id.tv61);
        tv62=findViewById(R.id.tv62);
        tv63=findViewById(R.id.tv63);
        tv64=findViewById(R.id.tv64);
        tv65=findViewById(R.id.tv65);
        tv71=findViewById(R.id.tv71);
        tv72=findViewById(R.id.tv72);
        tv73=findViewById(R.id.tv73);
        tv74=findViewById(R.id.tv74);
        tv75=findViewById(R.id.tv75);

        btnRecords=findViewById(R.id.btn_records);
        btnTimeMark=findViewById(R.id.btn_timeMark);
        btnVideo=findViewById(R.id.btn_video);
    }
    private void setTextListenners() {
        tv11.setOnClickListener(this);
        tv12.setOnClickListener(this);
        tv13.setOnClickListener(this);
        tv14.setOnClickListener(this);
        tv15.setOnClickListener(this);
        tv21.setOnClickListener(this);
        tv22.setOnClickListener(this);
        tv23.setOnClickListener(this);
        tv24.setOnClickListener(this);
        tv25.setOnClickListener(this);
        tv31.setOnClickListener(this);
        tv32.setOnClickListener(this);
        tv33.setOnClickListener(this);
        tv34.setOnClickListener(this);
        tv35.setOnClickListener(this);
        tv41.setOnClickListener(this);
        tv42.setOnClickListener(this);
        tv43.setOnClickListener(this);
        tv44.setOnClickListener(this);
        tv45.setOnClickListener(this);
        tv51.setOnClickListener(this);
        tv52.setOnClickListener(this);
        tv53.setOnClickListener(this);
        tv54.setOnClickListener(this);
        tv55.setOnClickListener(this);
        tv61.setOnClickListener(this);
        tv62.setOnClickListener(this);
        tv63.setOnClickListener(this);
        tv64.setOnClickListener(this);
        tv65.setOnClickListener(this);
        tv71.setOnClickListener(this);
        tv72.setOnClickListener(this);
        tv73.setOnClickListener(this);
        tv74.setOnClickListener(this);
        tv75.setOnClickListener(this);

        btnVideo.setOnClickListener(this);
        btnTimeMark.setOnClickListener(this);
        btnRecords.setOnClickListener(this);
    }
    private void setTxBackground(){
        if (tv11.getText()!=""){
            randomBgColor(tv11);
        }
        if (tv12.getText()!=""){
            randomBgColor(tv12);
        }
        if (tv13.getText()!=""){
            randomBgColor(tv13);
        }
        if (tv14.getText()!=""){
            randomBgColor(tv14);
        }
        if (tv15.getText()!=""){
            randomBgColor(tv15);
        }
        if (tv21.getText()!=""){
           randomBgColor(tv21);
        }
        if (tv22.getText()!=""){
            randomBgColor(tv22);
        }
        if (tv23.getText()!=""){
            randomBgColor(tv23);
        }
        if (tv24.getText()!=""){
            randomBgColor(tv24);
        }
        if (tv25.getText()!=""){
            randomBgColor(tv25);
        }
        if (tv31.getText()!=""){
            randomBgColor(tv31);
        }
        if (tv32.getText()!=""){
            randomBgColor(tv32);
        }
        if (tv33.getText()!=""){
            randomBgColor(tv33);
        }
        if (tv34.getText()!=""){
            randomBgColor(tv34);
        }
        if (tv35.getText()!=""){
            randomBgColor(tv35);
        }
        if (tv41.getText()!=""){
            randomBgColor(tv41);
        }
        if (tv42.getText()!=""){
            randomBgColor(tv42);
        }
        if (tv43.getText()!=""){
            randomBgColor(tv43);
        }
        if (tv44.getText()!=""){
            randomBgColor(tv44);
        }
        if (tv45.getText()!=""){
            randomBgColor(tv45);
        }
        if (tv51.getText()!=""){
            randomBgColor(tv51);
        }
        if (tv52.getText()!=""){
            randomBgColor(tv52);
        }
        if (tv53.getText()!=""){
            randomBgColor(tv53);
        }
        if (tv54.getText()!=""){
            randomBgColor(tv54);
        }
        if (tv55.getText()!=""){
            randomBgColor(tv55);
        }
        if (tv61.getText()!=""){
            randomBgColor(tv61);
        }
        if (tv62.getText()!=""){
            randomBgColor(tv62);
        }
        if (tv63.getText()!=""){
            randomBgColor(tv63);
        }
        if (tv64.getText()!=""){
            randomBgColor(tv64);
        }
        if (tv65.getText()!=""){
            randomBgColor(tv65);
        }
        if (tv71.getText()!=""){
           randomBgColor(tv71);
        }
        if (tv72.getText()!=""){
            randomBgColor(tv72);
        }
        if (tv73.getText()!=""){
            randomBgColor(tv73);
        }
        if (tv74.getText()!=""){
            randomBgColor(tv74);
        }
        if (tv75.getText()!=""){
            randomBgColor(tv75);
        }
    }
    public void monthDayWeek(Calendar calendar, int month, int day) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周日");
                txSun.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 2:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周一");
                txMon.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 3:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周二");
                txTus.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 4:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周三");
                txWed.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 5:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周四");
                txThurs.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 6:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周五");
                txFri.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 7:
                tvTime.setText(month + 1 + "月" + day + "日" + "  " + "周六");
                txSatur.setTextColor(getResources().getColor(R.color.orange));
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
//返回时刷新界面
        onCreate(null);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,Import.class);
        switch (v.getId()){
            case R.id.tv11:
                intent.putExtra("flag","0");
                startActivity(intent);
                break;
            case R.id.tv12:
                intent.putExtra("flag","1");
                startActivity(intent);
                break;
            case R.id.tv13:
                intent.putExtra("flag","2");
                startActivity(intent);
                break;
            case R.id.tv14:
                intent.putExtra("flag","3");
                startActivity(intent);
                break;
            case R.id.tv15:
                intent.putExtra("flag","4");
                startActivity(intent);
                break;
            case R.id.tv21:
                intent.putExtra("flag","5");
                startActivity(intent);
                break;
            case R.id.tv22:
                intent.putExtra("flag","6");
                startActivity(intent);
                break;
            case R.id.tv23:
                intent.putExtra("flag","7");
                startActivity(intent);
                break;
            case R.id.tv24:
                intent.putExtra("flag","8");
                startActivity(intent);
                break;
            case R.id.tv25:
                intent.putExtra("flag","9");
                startActivity(intent);
                break;
            case R.id.tv31:
                intent.putExtra("flag","10");
                startActivity(intent);
                break;
            case R.id.tv32:
                intent.putExtra("flag","11");
                startActivity(intent);
                break;
            case R.id.tv33:
                intent.putExtra("flag","12");
                startActivity(intent);
                break;
            case R.id.tv34:
                intent.putExtra("flag","13");
                startActivity(intent);
                break;
            case R.id.tv35:
                intent.putExtra("flag","14");
                startActivity(intent);
                break;
            case R.id.tv41:
                intent.putExtra("flag","15");
                startActivity(intent);
                break;
            case R.id.tv42:
                intent.putExtra("flag","16");
                startActivity(intent);
                break;
            case R.id.tv43:
                intent.putExtra("flag","17");
                startActivity(intent);
                break;
            case R.id.tv44:
                intent.putExtra("flag","18");
                startActivity(intent);
                break;
            case R.id.tv45:
                intent.putExtra("flag","19");
                startActivity(intent);
                break;
            case R.id.tv51:
                intent.putExtra("flag","20");
                startActivity(intent);
                break;
            case R.id.tv52:
                intent.putExtra("flag","21");
                startActivity(intent);
                break;
            case R.id.tv53:
                intent.putExtra("flag","22");
                startActivity(intent);
                break;
            case R.id.tv54:
                intent.putExtra("flag","23");
                startActivity(intent);
                break;
            case R.id.tv55:
                intent.putExtra("flag","24");
                startActivity(intent);
                break;
            case R.id.tv61:
                intent.putExtra("flag","25");
                startActivity(intent);
                break;
            case R.id.tv62:
                intent.putExtra("flag","26");
                startActivity(intent);
                break;
            case R.id.tv63:
                intent.putExtra("flag","27");
                startActivity(intent);
                break;
            case R.id.tv64:
                intent.putExtra("flag","28");
                startActivity(intent);
                break;
            case R.id.tv65:
                intent.putExtra("flag","29");
                startActivity(intent);
                break;
            case R.id.tv71:
                intent.putExtra("flag","30");
                startActivity(intent);
                break;
            case R.id.tv72:
                intent.putExtra("flag","31");
                startActivity(intent);
                break;
            case R.id.tv73:
                intent.putExtra("flag","32");
                startActivity(intent);
                break;
            case R.id.tv74:
                intent.putExtra("flag","33");
                startActivity(intent);
                break;
            case R.id.tv75:
                intent.putExtra("flag","34");
                startActivity(intent);
                break;
            case R.id.btn_timeMark:
                startActivity(new Intent(MainActivity.this,MarkTime.class));
                break;
            case R.id.btn_records:
                startActivity(new Intent(MainActivity.this,RecordList.class));
                break;
            case R.id.btn_video:
                startActivity(new Intent(MainActivity.this,video_activity.class));
                break;
        }
    }
//通过id获取数据
    public String getDbContent(int i){
        String className="";
        String classRoom="";
        String sql="select * from classdatas where _id='"+i+"'";
        Cursor cursor=dbReader.rawQuery(sql,new String[]{});
        while (cursor.moveToNext()){
            classRoom=cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.ROOMNUM));
            className=cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.CLASSNAME));
        }
        return className+classRoom;
    }
//返回课程节数
    public int getDbClassNum(int i){
        int classNum=3;
        String sql="select classNum from classdatas where _id='"+i+"'";
        Cursor cursor=dbReader.rawQuery(sql,new String[]{});
        while (cursor.moveToNext()){
            classNum=Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.CLASSNUM)));
        }
        return classNum;
    }
    private void intailDB() {
        SharedPreferences sharedPreferences=this.getSharedPreferences("share",MODE_PRIVATE);
        boolean isFirstRun=sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(isFirstRun) {
            for (int i = 0; i <35 ; i++) {
                addDb();
            }
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }
    }
    public void addDb(){
        ContentValues cv=new ContentValues();
        cv.put(classDatabase.CLASSNAME,"");
        cv.put(classDatabase.ROOMNUM,"");
        cv.put(classDatabase.CLASSNUM,2);
        dbWriter.insert(classDatabase.TABLE_NAME,null,cv);
    }
//db转化px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
//随机设置背景颜色
    private void randomBgColor(TextView tv){
        int i= (int) (Math.random() * 7);
        switch (i){
            case 0:tv.setBackgroundColor(getResources().getColor(R.color.txcolor0));
                break;
            case 1:tv.setBackgroundColor(getResources().getColor(R.color.txcolor1));
                break;
            case 2:tv.setBackgroundColor(getResources().getColor(R.color.txcolor2));
                break;
            case 3:tv.setBackgroundColor(getResources().getColor(R.color.txcolor3));
                break;
            case 4:tv.setBackgroundColor(getResources().getColor(R.color.txcolor4));
                break;
            case 5:tv.setBackgroundColor(getResources().getColor(R.color.txcolor5));
                break;
            case 6:tv.setBackgroundColor(getResources().getColor(R.color.txcolor6));
                break;
            case 7:tv.setBackgroundColor(getResources().getColor(R.color.txcolor7));
                break;
        }
    }
//重写返回方法实现点两次退出应用
    @Override
    public void onBackPressed() {
        if (flag) {
            super.onBackPressed();
            System.exit(0);
        } else {
            Toast toast =Toast.makeText(MainActivity.this,"再次点击退出",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            flag = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag = false;
                }
            }, 2000);
        }
    }
}