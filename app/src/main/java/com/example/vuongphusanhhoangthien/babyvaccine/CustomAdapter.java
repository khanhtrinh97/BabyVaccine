package com.example.vuongphusanhhoangthien.babyvaccine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Alarm> arrAlarm;


    public CustomAdapter(@NonNull Context context, int resource, List arrAlarm) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrAlarm = arrAlarm;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.one_alrm, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvAlarmName = convertView.findViewById(R.id.tv_alarm_name);
            viewHolder.tvAlarmTime = convertView.findViewById(R.id.tv_alarm_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return super.getView(position, convertView, parent);

    }

    public class ViewHolder{
        TextView tvAlarmName, tvAlarmTime;
    }
}
