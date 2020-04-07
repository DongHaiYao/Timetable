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
        String whereClauseString="_id='"+(i-1)"+'";
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
        int num= Integer.parseInt(s);
        sql="select * from classdatas where _id='"+num+1+"'";
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