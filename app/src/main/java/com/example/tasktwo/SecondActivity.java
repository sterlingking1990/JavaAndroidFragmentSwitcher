package com.example.tasktwo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    int countStatus=0;
    private Button btnIncrementNum;
    private  Button btnDecrementNum;
    private TextView tvNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnIncrementNum =(Button) findViewById(R.id.btnIncrementNum);
        btnDecrementNum=(Button) findViewById(R.id.btnDecrementNum);
        tvNum = (TextView) findViewById(R.id.tvNumberStatus);

        btnIncrementNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countStatus+=1;
                tvNum.setText(Integer.toString(countStatus));
            }
        });

        btnDecrementNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countStatus-=1;
                tvNum.setText(Integer.toString(countStatus));
            }
        });

        //check if saved instance is true, then retrieve the value and reflect update on tvNum
        if(savedInstanceState!=null) {
            countStatus=savedInstanceState.getInt("countStats");
            tvNum.setText(String.valueOf(countStatus));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("countStats", Integer.parseInt(tvNum.getText().toString()));
        super.onSaveInstanceState(outState);
    }


}