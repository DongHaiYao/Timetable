package com.example.timetable;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myListAdapter extends BaseAdapter {
    private Context context;
    private static Cursor cursor ;
    TextView txday;
    myListAdapter(Context context, Cursor cursor){
        this.context=context;
        this.cursor=cursor;
    }
    @Override
    public int getCount() {

        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
//获得item的id值
    public static int getId(int position){
            cursor.moveToPosition(position);
            return Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(recordsDatabase.ID)));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.list_item, null);
        TextView tvTitle= linearLayout.findViewById(R.id.list_tx_title);
        TextView expectTime= linearLayout.findViewById(R.id.list_expect);
        TextView actulTime= linearLayout.findViewById(R.id.list_actul);
        txday= linearLayout.findViewById(R.id.list_tx_day);
        cursor.moveToPosition(position);
        String title=cursor.getString(cursor.getColumnIndex("courseName"));
        String eTime=cursor.getString(cursor.getColumnIndex("expctTime"));
        String aTime=cursor.getString(cursor.getColumnIndex("actulTime"));
        tvTitle.setText(title);
        expectTime.setText(eTime);
        actulTime.setText(aTime);
        txday.setText(cursor.getString(cursor.getColumnIndex(recordsDatabase.DATA)));
        return linearLayout;
    }
}