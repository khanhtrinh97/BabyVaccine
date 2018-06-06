package com.example.vuongphusanhhoangthien.babyvaccine;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {
    private View rootView;
    private EditText etNameTask, etNumCount;
    private RadioButton rdoSeconds, rdoMinutes, rdoHours, rdoDays, rdoMonths, rdoYears;
    private Button btnOk, btnCancel;
    private MyDatabase myDatabase;
    public AddTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_task, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        etNameTask = rootView.findViewById(R.id.et_task_name);
        etNumCount = rootView.findViewById(R.id.addtask_et_time_adding);

        rdoSeconds = rootView.findViewById(R.id.rdo_seconds);
        rdoMinutes = rootView.findViewById(R.id.rdo_minutes);
        rdoHours = rootView.findViewById(R.id.rdo_hours);
        rdoDays = rootView.findViewById(R.id.rdo_days);
        rdoMonths = rootView.findViewById(R.id.rdo_months);
        rdoYears = rootView.findViewById(R.id.rdo_years);


        btnOk = rootView.findViewById(R.id.addtask_btn_ok);
        btnCancel = rootView.findViewById(R.id.addtask_btn_cancel);


    }

    private void startAlrm(){
        int numCount;
        int timeType = 1000;
        String alarTime ="Giay";

        numCount = Integer.parseInt(etNumCount.getText().toString());
        if (rdoSeconds.isChecked() == true){
            rdoHours.setVisibility(View.VISIBLE);
            rdoMinutes.setVisibility(View.VISIBLE);
            rdoDays.setVisibility(View.VISIBLE);
            rdoMonths.setVisibility(View.VISIBLE);
            rdoYears.setVisibility(View.VISIBLE);
            timeType = (1000 * 1);
            alarTime = "Giây";
        }else if(rdoMinutes.isChecked() == true){
            rdoSeconds.setVisibility(View.VISIBLE);
            rdoHours.setVisibility(View.VISIBLE);
            rdoDays.setVisibility(View.VISIBLE);
            rdoMonths.setVisibility(View.VISIBLE);
            rdoYears.setVisibility(View.VISIBLE);
            timeType = (1000 * 60);
            alarTime = "Phút";
        }else if(rdoHours.isChecked() == true){
            rdoSeconds.setVisibility(View.VISIBLE);
            rdoMinutes.setVisibility(View.VISIBLE);
            rdoMonths.setVisibility(View.VISIBLE);
            rdoYears.setVisibility(View.VISIBLE);
            rdoDays.setVisibility(View.VISIBLE);
            timeType = (1000 * 60 * 60);
            alarTime = "Giờ";
        }else if(rdoDays.isChecked() == true){
            rdoMinutes.setVisibility(View.VISIBLE);
            rdoSeconds.setVisibility(View.VISIBLE);
            rdoHours.setVisibility(View.VISIBLE);
            rdoMonths.setVisibility(View.VISIBLE);
            rdoYears.setVisibility(View.VISIBLE);
            timeType = (1000 * 60 * 60 * 24);
            alarTime = "Ngày";
        }else if(rdoMonths.isChecked() == true){
            rdoSeconds.setVisibility(View.VISIBLE);
            rdoMinutes.setVisibility(View.VISIBLE);
            rdoHours.setVisibility(View.VISIBLE);
            rdoDays.setVisibility(View.VISIBLE);
            rdoYears.setVisibility(View.VISIBLE);
            timeType = (1000 * 60 * 60 * 24 * 30);
            alarTime = "Tháng";
        }else if(rdoYears.isChecked() == true){
            rdoSeconds.setVisibility(View.VISIBLE);
            rdoMinutes.setVisibility(View.VISIBLE);
            rdoHours.setVisibility(View.VISIBLE);
            rdoDays.setVisibility(View.VISIBLE);
            rdoMonths.setVisibility(View.VISIBLE);
            timeType = (1000* 60 * 60 * 24 *30 *12);
            alarTime = "năm";
        }

        Intent i = new Intent(this.getContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getContext(),6969, i, 0);
        AlarmManager alarmManager = (AlarmManager) this.getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (numCount * timeType),pendingIntent);
        Alarm alarm = new Alarm();
        alarm.setAlarm_name(etNameTask.getText().toString());
        alarm.setAlarm_time(alarTime);
        myDatabase.addAlarm(alarm);

    }



}
