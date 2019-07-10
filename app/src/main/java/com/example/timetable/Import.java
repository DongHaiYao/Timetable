package com.example.timetable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Import extends Activity {
    //声明
    private Button cancel,confirm,delete;
    private EditText eclsname,eclsroom,eclsnum;
    public String  clsName="",//课程名
                   roomNum="";//教室号
    private String val;
    private int clsNum;
    protected classDatabase myDb;
    private SQLiteDatabase dbWriter,dbReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
        myDb=new classDatabase(this);
        dbReader=myDb.getReadableDatabase();
        dbWriter=myDb.getWritableDatabase();
        findView();
//val获取intent的传递值
        val=getIntent().getStringExtra("flag");
        setOriginEditText(val);
//设置点击事件
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//获取editext内容
                getEditextContext();
                ToastUtil.showMsg(Import.this,"已设置");
                Handler handler=new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        mDbUpdate(val);//以val判断是哪个text的数据
                        finish();
                    }
                },100);//短暂延迟后关闭导入界面
            }
        });
    }
   // 通过id更新数据库
    public void mDbUpdate(String i){
        ContentValues cv =new ContentValues();
        cv.put(classDatabase.CLASSNAME,clsName);
        cv.put(classDatabase.ROOMNUM,roomNum);
        cv.put(classDatabase.CLASSNUM,clsNum);
        String whereClauseString="";
        switch (i){
            case "0":
                whereClauseString="_id='1'";
                break;
            case "1":
                whereClauseString="_id='2'";
                break;
            case "2":
                whereClauseString="_id='3'";
                break;
            case "3":
                whereClauseString="_id='4'";
                break;
            case "4":
                whereClauseString="_id='5'";
                break;
            case "5":
                whereClauseString="_id='6'";
                break;
            case "6":
                whereClauseString="_id='7'";
                break;
            case "7":
                whereClauseString="_id='8'";
                break;
            case "8":
                whereClauseString="_id='9'";
                break;
            case "9":
                whereClauseString="_id='10'";
                break;
            case "10":
                whereClauseString="_id='11'";
                break;
            case "11":
                whereClauseString="_id='12'";
                break;
            case "12":
                whereClauseString="_id='13'";
                break;
            case "13":
                whereClauseString="_id='14'";
                break;
            case "14":
                whereClauseString="_id='15'";
                break;
            case "15":
                whereClauseString="_id='16'";
                break;
            case "16":
                whereClauseString="_id='17'";
                break;
            case "17":
                whereClauseString="_id='18'";
                break;
            case "18":
                whereClauseString="_id='19'";
                break;
            case "19":
                whereClauseString="_id='20'";
                break;
            case "20":
                whereClauseString="_id='21'";
                break;
            case "21":
                whereClauseString="_id='22'";
                break;
            case "22":
                whereClauseString="_id='23'";
                break;
            case "23":
                whereClauseString="_id='24'";
                break;
            case "24":
                whereClauseString="_id='25'";
                break;
            case "25":
                whereClauseString="_id='26'";
                break;
            case "26":
                whereClauseString="_id='27'";
                break;
            case "27":
                whereClauseString="_id='28'";
                break;
            case "28":
                whereClauseString="_id='29'";
                break;
            case "29":
                whereClauseString="_id='30'";
                break;
            case "30":
                whereClauseString="_id='31'";
                break;
            case "31":
                whereClauseString="_id='32'";
                break;
            case "32":
                whereClauseString="_id='33'";
                break;
            case "33":
                whereClauseString="_id='34'";
                break;
            case "34":
                whereClauseString="_id='35'";
                break;
        }
        String[] whereArgs={};
        dbWriter.update(classDatabase.TABLE_NAME,cv,whereClauseString,whereArgs);
    }
    //获取输入内容
    private void getEditextContext() {
        clsName=eclsname.getText().toString();
        roomNum=eclsroom.getText().toString();
        if (eclsnum.getText().toString().trim().equals("")){
            clsNum=2;
        }else clsNum=Integer.parseInt(eclsnum.getText().toString());;
    }
    //找到控件
    private void findView() {
        cancel=findViewById(R.id.import_cancel);
        confirm=findViewById(R.id.import_confirm);
        eclsname=findViewById(R.id.impot_name);
        eclsroom=findViewById(R.id.import_class);
        delete=findViewById(R.id.import_delete);
        eclsnum=findViewById(R.id.import_clsnum);
    }
    public void setOriginEditText(String s){
        String className="";
        String sql="";
        String classNum="";
        String roomNum="@";
        switch (s){
            case "0":
                sql="select * from classdatas where _id='1'";
                break;
            case "1":
                sql="select * from classdatas where _id='2'";
                break;
            case "2":
                sql="select * from classdatas where _id='3'";
                break;
            case "3":
                sql="select * from classdatas where _id='4'";
                break;
            case "4":
                sql="select * from classdatas where _id='5'";
                break;
            case "5":
                sql="select * from classdatas where _id='6'";
                break;
            case "6":
                sql="select * from classdatas where _id='7'";
                break;
            case "7":
                sql="select * from classdatas where _id='8'";
                break;
            case "8":
                sql="select * from classdatas where _id='9'";
                break;
            case "9":
                sql="select * from classdatas where _id='10'";
                break;
            case "10":
                sql="select * from classdatas where _id='11'";
                break;
            case "11":
                sql="select * from classdatas where _id='12'";
                break;
            case "12":
                sql="select * from classdatas where _id='13'";
                break;
            case "13":
                sql="select * from classdatas where _id='14'";
                break;
            case "14":
                sql="select * from classdatas where _id='15'";
                break;
            case "15":
                sql="select * from classdatas where _id='16'";
                break;
            case "16":
                sql="select * from classdatas where _id='17'";
                break;
            case "17":
                sql="select * from classdatas where _id='18'";
                break;
            case "18":
                sql="select * from classdatas where _id='19'";
                break;
            case "19":
                sql="select * from classdatas where _id='20'";
                break;
            case "20":
                sql="select * from classdatas where _id='21'";
                break;
            case "21":
                sql="select * from classdatas where _id='22'";
                break;
            case "22":
                sql="select * from classdatas where _id='23'";
                break;
            case "23":
                sql="select * from classdatas where _id='24'";
                break;
            case "24":
                sql="select * from classdatas where _id='25'";
                break;
            case "25":
                sql="select * from classdatas where _id='26'";
                break;
            case "26":
                sql="select * from classdatas where _id='27'";
                break;
            case "27":
                sql="select * from classdatas where _id='28'";
                break;
            case "28":
                sql="select * from classdatas where _id='29'";
                break;
            case "29":
                sql="select * from classdatas where _id='30'";
                break;
            case "30":
                sql="select * from classdatas where _id='31'";
                break;
            case "31":
                sql="select * from classdatas where _id='32'";
                break;
            case "32":
                sql="select * from classdatas where _id='33'";
                break;
            case "33":
                sql="select * from classdatas where _id='34'";
                break;
            case "34":
                sql="select * from classdatas where _id='35'";
                break;
        }
        Cursor cursor=dbReader.rawQuery(sql,new String[]{});
        while (cursor.moveToNext()){
            className=cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.CLASSNAME));
            classNum=cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.CLASSNUM));
            if(!cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.ROOMNUM)).trim().equals("")){
                roomNum=cursor.getString(cursor.getColumnIndexOrThrow(classDatabase.ROOMNUM));
            }else  roomNum="@";
        }
        eclsname.setText(className);
        eclsnum.setText(classNum);
        eclsroom.setText(roomNum);
        }
//提示是否删除
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alter);
        builder.setTitle("确定删除吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clsName="";
                roomNum="";
                clsNum=2;
                mDbUpdate(val);
                ToastUtil.showMsg(Import.this,"已删除");
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}