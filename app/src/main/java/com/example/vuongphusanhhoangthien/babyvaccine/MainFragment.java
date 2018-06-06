package com.example.vuongphusanhhoangthien.babyvaccine;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private View rootView;
    private LinearLayout lnPro;
    private onClickButtonListener listener;
    private CircleImageView babyAva;
    private TextView tvbabyName, tvbabyYob, tvAddtask;
    private ListView lvTask;
    private CustomAdapter customAdapter;
    private MyDatabase myDatabase;
    private List<Alarm> list;



    public MainFragment() {
        // Required empty public constructor
    }

    public void setListener(onClickButtonListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        lnPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickButton();
            }
        });
        tvAddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickButton1();
            }
        });
        autoloadinfo();

//        list = myDatabase.getAllAlarm();
  //      customAdapter = new CustomAdapter(this.getContext(), R.layout.one_alrm,list);
    //    lvTask.setAdapter(customAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView() {
        lvTask = rootView.findViewById(R.id.lv_task_list);
        lnPro = rootView.findViewById(R.id.ln_profile);
        babyAva = rootView.findViewById(R.id.profile_cim_baby_ava);
        tvbabyName = rootView.findViewById(R.id.profile_tv_baby_name);
        tvbabyYob = rootView.findViewById(R.id.profile_tv_baby_yob);
        tvAddtask = rootView.findViewById(R.id.profile_tv_add_task_btn);

    }
    public interface onClickButtonListener{
        void clickButton();
        void clickButton1();
    }
    private Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
    public void autoloadinfo(){
        SharedPreferences sharedPreferences;
        sharedPreferences = this.getContext().getSharedPreferences("baby_info", Context.MODE_PRIVATE);
        String baby_name = sharedPreferences.getString("baby_name","");
        String baby_yob = sharedPreferences.getString("baby_yob", "");
        String strbitmap = sharedPreferences.getString("baby_ava","");
        Bitmap bitmap = StringToBitMap(strbitmap);

        tvbabyName.setText(baby_name);
        tvbabyYob.setText(baby_yob);
        babyAva.setImageBitmap(bitmap);

    }
}
