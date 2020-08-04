package com.example.tasktwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    final String TAG = "LATEST_FRAGMENT";
    int count = 0;
    int countRemoved = 0;
    boolean backClicked = false;
    private TextView tvLifeCycleStat;
    String lifeCycleTemp="";
    final Handler hander=new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tvLifeCycleStat= (TextView) findViewById(R.id.tvLifeCycleStat);
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvLifeCycleStat.setText("onCreate");
            }
        },1500);


        Button btnAddNewFragment = (Button) findViewById(R.id.btnAddNewFragment);

        Button btnRemoveFragment = (Button) findViewById(R.id.btnRemoveFragment);

        Button btnStartSecondActivity = (Button) findViewById(R.id.btnLaunchActivityTwo);

        btnAddNewFragment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (backClicked == true) {
                    count = count - countRemoved;
                    if (count < 0) {
                        count = 0;
                    }
                    backClicked = false;
                    countRemoved = 0;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentComponent componentFragment = FragmentComponent.newInstance(count += 1);
                ft.replace(R.id.dynamicFragmentFrame, componentFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        btnRemoveFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backClicked = true;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                if (manager != null) {
                    manager.popBackStack();
                    countRemoved += 1;
                    trans.commit();
                }
            }
        });

        btnStartSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }

            ;
        });
    }

    private void openNewActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


    //life cycle stat
    protected void onStart() {
        super.onStart();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
              tvLifeCycleStat.setText("onStart");
            }
        },2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvLifeCycleStat.setText("onResume");
            }
        },2500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        tvLifeCycleStat.setText("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvLifeCycleStat.setText("onStop");
            }
        },1000);
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvLifeCycleStat.setText("onRestart");
            }
        },1000);
    }

    @Override
    protected void onDestroy() {
        lifeCycleTemp= (String) tvLifeCycleStat.getText();
        tvLifeCycleStat.setText(lifeCycleTemp);
        super.onDestroy();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvLifeCycleStat.setText("onDestroy");
            }
        },1000);
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }
}