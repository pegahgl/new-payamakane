package com.sms.golchin9133882797.multipleusersms;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TextActivity extends Activity {
    Button btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        btn1= (Button)findViewById(R.id.btn1);
        btn2= (Button)findViewById(R.id.btn2);
        btn3= (Button)findViewById(R.id.btn3);
        btn4= (Button)findViewById(R.id.btn4);
        btn5= (Button)findViewById(R.id.btn5);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
//        btn3.setOnClickListener(this);
//        btn4.setOnClickListener(this);
//        btn5.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View v) {
//
//      /*  if (v==btn1){
//
//        }
//        if (v==btn2){
//
//        }
//        if (v==btn3){
//
//        }
//        if (v==btn4){
//
//        }
//        if (v==btn5){
//
//        }*/
//    }
}
