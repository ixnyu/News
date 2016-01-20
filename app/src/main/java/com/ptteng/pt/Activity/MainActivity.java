package com.ptteng.pt.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.ptteng.pt.Fragment.BossFragment;
import com.ptteng.pt.Fragment.CarerFragment;
import com.ptteng.pt.R;

public class MainActivity extends AppCompatActivity {

    private RadioButton mFindCarer;
    private RadioButton mFindBoss;
    private ImageButton mHomeMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FragmentManager fm = getFragmentManager();
        mFindCarer = (RadioButton) findViewById(R.id.home_findcarer_rb);
        mFindBoss = (RadioButton) findViewById(R.id.home_findboss_rb);
        mHomeMy = (ImageButton) findViewById(R.id.home_my_btn);
        mHomeMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        mFindCarer.setChecked(true);
        mFindCarer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarerFragment carerFragment = new CarerFragment();
                fm.beginTransaction().replace(R.id.home_fl,carerFragment).commit();
            }
        });
        mFindBoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BossFragment bossFragment = new BossFragment();
                fm.beginTransaction().replace(R.id.home_fl,bossFragment).commit();
            }
        });
        CarerFragment carerFragment = new CarerFragment();
        fm.beginTransaction().replace(R.id.home_fl,carerFragment).commit();
    }
}
