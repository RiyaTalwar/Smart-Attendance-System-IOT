package com.example.skeny.smartattendanceapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class DataFAdapter extends ArrayAdapter{

    List list=new ArrayList();
    public DataFAdapter(Context context, int resource){
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();

    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        DataFAdapter.DataFHolder dataHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            dataHolder=new DataFAdapter.DataFHolder();
            dataHolder.tx_courseID=row.findViewById(R.id.CourseID);
            dataHolder.tx_attendance=row.findViewById(R.id.Attendance);
            row.setTag(dataHolder);

        }
        else{
            dataHolder=(DataFAdapter.DataFHolder)row.getTag();
        }
        DataF data=(DataF)this.getItem(position);
        dataHolder.tx_courseID.setText(data.getCourseID());
        dataHolder.tx_attendance.setText(data.getAttendance());
        dataHolder.tx_date.setText(data.getDate());
        return row;
    }

    static class DataFHolder{
        TextView tx_courseID, tx_attendance,tx_date;

    }
}
