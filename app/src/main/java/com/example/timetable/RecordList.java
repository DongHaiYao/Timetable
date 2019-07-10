package com.example.timetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class RecordList extends Activity {
    private ListView myLv;
    private myListAdapter adapter;
    private Button btnBack;
    private recordsDatabase rDB;
    private SQLiteDatabase dbReader,dbWriter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        myLv=findViewById(R.id.record_list);
        rDB=new recordsDatabase(this);
        dbReader=rDB.getReadableDatabase();
        dbWriter=rDB.getWritableDatabase();
        Cursor cursor=dbReader.query(recordsDatabase.TABLE_NAME,null,null,null,
                null,null,null);
        final myListAdapter myListAdapter =new myListAdapter(this,cursor);
        btnBack=findViewById(R.id.btn_records_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmDel(position);
            }
        });
    }
    //获取DB数据
    private void selecDB() {
        Cursor cursor=dbReader.query(recordsDatabase.TABLE_NAME,null,null,null,
                null,null,null);
        adapter=new myListAdapter(this,cursor);
        myLv.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        selecDB();
    }
    public void deleteDatas(int id) {
        dbWriter.delete(recordsDatabase.TABLE_NAME,"_id="+id,null);
    }
 //提示是否删除
    private void confirmDel(final int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alter);
        builder.setTitle("确定删除此条记录吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDatas(myListAdapter.getId(position));
                onResume();
                ToastUtil.showMsg(RecordList.this,"已删除");
                dialog.dismiss();
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