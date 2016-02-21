package com.sms.golchin9133882797.multipleusersms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsMultiple extends Activity implements OnClickListener {
    BroadcastReceiver smsSentReciver, smsSentDelivery;
    EditText ed1, ed2;
    static int ResultCode = 12;
    static int ResultText = 200;
    ArrayList<String> sendlist = new ArrayList<String>();
    Button b1, b2, btnText, btnGroup;
    String contacts = "";
    String contactsNew ="";
    String delim = ";";
    TextView ed;
    public static final String pref = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smssend);
        b2 = (Button) findViewById(R.id.button2);//choose
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed = (TextView) findViewById(R.id.textView1);
        btnGroup = (Button) findViewById(R.id.btnGroup);
        b1 = (Button) findViewById(R.id.button3);//send
        btnText = (Button) findViewById(R.id.btntext);
        b2.setOnClickListener(this);
        b1.setOnClickListener(this);
        btnText.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        ed1.setText(null);
        ed2.setText(null);

        Bundle b = getIntent().getExtras();
        contactsNew = b.getString("contacts");
        ed1.setText(contactsNew);
        Toast.makeText(SmsMultiple.this, "done", Toast.LENGTH_SHORT).show();

    }


//    void Choosestarter(){
//        final SharedPreferences
//    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregisterReceiver(smsSentReciver);
        unregisterReceiver(smsSentDelivery);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        smsSentReciver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                // TODO Auto-generated method stub
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "sms has been sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Fail",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No Service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio Off",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
            }

        };
        smsSentDelivery = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "Sms Delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "Sms not Delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        };
        registerReceiver(smsSentReciver, new IntentFilter("SMS_SENT"));
        registerReceiver(smsSentDelivery, new IntentFilter("SMS_DELIVERED"));

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.btnGroup:
                Intent group = new Intent(SmsMultiple.this , GroupActivity.class );
                startActivity(group);
                break;

            case R.id.btntext:
                Intent h = new Intent(SmsMultiple.this, TextActivity.class);
                startActivityForResult(h, ResultText);
               // startActivity(h);
                break;

            case R.id.button2:
                Intent a = new Intent(SmsMultiple.this, MainActivity.class);
                startActivityForResult(a, ResultCode);
                break;
            case R.id.button3:

                Log.i("SMS", "Sendlist Size: " + sendlist.size());

                SmsManager smsManager = SmsManager.getDefault();

                String msg = ed2.getText().toString();
                PendingIntent piSend = PendingIntent.getBroadcast(this, 0,
                        new Intent("SMS_SENT"), 0);
                PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0,
                        new Intent("SMS_DELIVERED"), 0);

                Log.i("SMS", "contacts: " + contacts);

                String[] cellArray;
                ed1.append("");
                contacts = ed1.getText().toString();
                cellArray = contacts.toString().split(";");
                ed.setText(contacts);

                for (int a1 = 0; a1 < cellArray.length; a1++) {


                    smsManager.sendTextMessage(cellArray[a1].toString(), null, msg,
                            piSend, piDelivered);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                ed1.setText(null);
                ed2.setText(null);

                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String str= ed1.getText().toString();

        if (requestCode == ResultCode) {

            if (resultCode == RESULT_OK) {
                sendlist = data.getStringArrayListExtra("name");
                if (sendlist != null) {
                    ed1.setText(null);
                    for (int i = 0; i < sendlist.size(); i++) {
                        ed1.append("<" + sendlist.get(i).toString()+ ">");
                        ed1.append(delim);
                    }

                    ed1.append(str);

                }

            }

            if (resultCode == RESULT_CANCELED) {

            }

        }
    }

}