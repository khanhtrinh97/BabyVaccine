package com.example.vuongphusanhhoangthien.babyvaccine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ProfileEditFragment mProFragment;
    private MainFragment mMainFragment;
    private AddTaskFragment mAddTaskFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        showMain();

    }
    private void showMain() {
        getSupportFragmentManager().beginTransaction().show(mMainFragment).show(mProFragment).commit();
    }
    private void showProfileEdit(){
        getSupportFragmentManager().beginTransaction().show(mProFragment).hide(mMainFragment).hide(mAddTaskFragment).addToBackStack(null).commit();
    }
    private void addTaskOnClickButton(){
        getSupportFragmentManager().beginTransaction().show(mAddTaskFragment).hide(mMainFragment).hide(mProFragment).addToBackStack(null).commit();
    }
    private void initFragment() {
        mAddTaskFragment = new AddTaskFragment();
        mProFragment = new ProfileEditFragment();
        mMainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.ln_main, mMainFragment, MainFragment.class.getName()).
                add(R.id.ln_main, mProFragment, ProfileEditFragment.class.getName()).
                add(R.id.ln_main, mAddTaskFragment, AddTaskFragment.class.getName()).
                commit();

        mMainFragment.setListener(new MainFragment.onClickButtonListener() {
            @Override
            public void clickButton() {
                showProfileEdit();
            }

            @Override
            public void clickButton1() {
                addTaskOnClickButton();
            }
        });

        mProFragment.setListener(new ProfileEditFragment.onClick() {
            @Override
            public void clickOk() {
                showMain();
                mMainFragment.autoloadinfo();
            }
        });
    }
}
