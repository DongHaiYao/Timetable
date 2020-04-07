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

import java.util.ArrayList;
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
    private ArrayList<TextView> tvList=new ArrayList<TextView>();
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
    private void addToList(){
        tvList.add(tv11);
        tvList.add(tv12);
        tvList.add(tv13);
        tvList.add(tv14);
        tvList.add(tv15);
        tvList.add(tv21);
        tvList.add(tv22);
        tvList.add(tv23);
        tvList.add(tv24);
        tvList.add(tv25);
        tvList.add(tv31);
        tvList.add(tv32);
        tvList.add(tv33);
        tvList.add(tv34);
        tvList.add(tv35);
        tvList.add(tv41);
        tvList.add(tv42);
        tvList.add(tv43);
        tvList.add(tv44);
        tvList.add(tv45);
        tvList.add(tv51);
        tvList.add(tv52);
        tvList.add(tv53);
        tvList.add(tv54);
        tvList.add(tv55);
        tvList.add(tv61);
        tvList.add(tv62);
        tvList.add(tv63);
        tvList.add(tv64);
        tvList.add(tv65);
        tvList.add(tv71);
        tvList.add(tv72);
        tvList.add(tv73);
        tvList.add(tv74);
        tvList.add(tv75);
    }
    private void setTextHeight(){
        for (int i = 0; i < 35; i++) {
          tvList.get(i).getLayoutParams().height=dip2px(this,69)*getDbClassNum(i+1);
        }

    }
    private void setTextContent() {

        for (int i = 0; i < 35; i++) {
          tvList.get(i).setText(getDbContent(i+1));
        }
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
        for (int i = 0; i < 35; i++) {
            tvList.get(i).setOnClickListener(this);
        }
        btnVideo.setOnClickListener(this);
        btnTimeMark.setOnClickListener(this);
        btnRecords.setOnClickListener(this);
    }
    private void setTxBackground(){
        for (TextView e: tvList
             ) {
            if (""!=e.getText()){
                randomBgColor(e);
            }
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