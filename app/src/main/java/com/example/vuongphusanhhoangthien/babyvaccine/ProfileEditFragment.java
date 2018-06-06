package com.example.vuongphusanhhoangthien.babyvaccine;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileEditFragment extends Fragment {
    private Date date;
    private View rootView;
    private CircleImageView circleImageViewAva;
    private EditText edtName;
    private TextView tvYob;
    private Button btnOk, btnCancel;
    private Calendar cal;
    private Bitmap bitmap;
    private final int PICK_IMAGE_GALLERY = 1;
    private SharedPreferences sharedPreferences;
    private onClick listener;

    public ProfileEditFragment() {
        // Required empty public constructor
    }

    public void setListener(onClick listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        initView();
        setDateNow();

        return rootView;
    }

    private void setDateNow() {
        cal = Calendar.getInstance();
        SimpleDateFormat dft;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        tvYob.setText(strDate);
    }

    private void initView() {
        circleImageViewAva = rootView.findViewById(R.id.cim_baby_ava);
        edtName = rootView.findViewById(R.id.et_baby_name);
        tvYob = rootView.findViewById(R.id.tv_baby_yob);
        btnOk = rootView.findViewById(R.id.btn_ok);
        btnCancel = rootView.findViewById(R.id.btn_cancel);

        tvYob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        circleImageViewAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAvaImg();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBabyInfomation();
            }
        });
    }

    private void saveBabyInfomation() {
        String babyName = edtName.getText().toString();
        String date = tvYob.getText().toString();
        String img = bitmapToString(bitmap);
        sharedPreferences = this.getContext().getSharedPreferences("baby_info",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("baby_name",babyName);
        editor.putString("baby_yob",date);
        editor.putString("baby_ava",img);
        editor.commit();
        backToMainFragment();
    }

    private void backToMainFragment() {
        listener.clickOk();
    }

    private void setAvaImg() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "select picture"),PICK_IMAGE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE_GALLERY && data != null && data.getData() != null){
            Uri uri = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), uri);
                circleImageViewAva.setImageBitmap(bitmap);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void setDate() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvYob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                cal.set(year,month,dayOfMonth);
                date = cal.getTime();

            }
        };
        String s = tvYob.getText()+"";
        //Lấy ra chuỗi của textView Date
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1]) - 1;
        int nam=Integer.parseInt(strArrtmp[2]);
        //Hiển thị ra Dialog
        DatePickerDialog pic=new DatePickerDialog(
                this.getContext(),
                callBack, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }
    public interface onClick{
        void clickOk();
    }
    private String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;

    }
}
