package com.sms.golchin9133882797.multipleusersms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class khanevade extends Activity implements View.OnClickListener {
    Button btnChoose,btnSave,btnDelet,btnSend;
    static int ResultCode = 12;
    String contacts1 = "";
    EditText edChoosen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khanevade);
        btnChoose = (Button)findViewById(R.id.btnChooseContact);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDelet = (Button)findViewById(R.id.btndelet);
        btnSend = (Button)findViewById(R.id.btnforsend);
        btnChoose.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelet.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        edChoosen = (EditText)findViewById(R.id.edChoosen);

      
    }

    @Override
    public void onClick(View v) {
        if (v==btnChoose){
            Intent k = new Intent(khanevade.this, MainKhanevade.class);
            startActivityForResult(k , ResultCode);
        }

    }
}
