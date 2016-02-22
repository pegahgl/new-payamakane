package com.sms.golchin9133882797.multipleusersms;

import java.io.IOException;
import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainKhanevade extends Activity implements OnClickListener {
    Button btnChoose, btnSave, btnDelete, btnSendAct;
    static int ResultCode = 12;
    EditText editChoosen;
    String delim = ";";
    String contacts = "";
    ArrayList<String> sendlist = new ArrayList<String>();
    private DBHelper DbHelper;
    public SQLiteDatabase newDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khanevade);
        btnChoose = (Button) findViewById(R.id.btnChooseContact);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSendAct = (Button) findViewById(R.id.btnForSend);
        editChoosen = (EditText) findViewById(R.id.edChoosen);
        btnChoose.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSendAct.setOnClickListener(this);

        DbHelper = new DBHelper(this);
        try {
            DbHelper.createDataBase();
//            DbHelper.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String myPath = DBHelper.DB_PATH  + DBHelper.DB_NAME ;
        newDb = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);


    }

    @Override
    public void onClick(View v) {
        if (v == btnChoose) {
            Intent g = new Intent(MainKhanevade.this, MainActivity.class);
            startActivityForResult(g, ResultCode);
        }
        if (v== btnSendAct){
            Intent sendnames = new Intent(MainKhanevade.this , SmsMultiple.class);
            Bundle b = new Bundle();
            b.putString("contacts",contacts);
            sendnames.putExtras(b);
            startActivity(sendnames);

        }



        String[] cellArray;
        editChoosen.append("");
        contacts = editChoosen.getText().toString();
        cellArray = contacts.toString().split(";");
        editChoosen.setText(contacts);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String str = editChoosen.getText().toString();

        if (requestCode == ResultCode) {

            if (resultCode == RESULT_OK) {
                sendlist = data.getStringArrayListExtra("name");
                if (sendlist != null) {
                    editChoosen.setText(null);
                    for (int i = 0; i < sendlist.size(); i++) {
                        editChoosen.append("<" + sendlist.get(i).toString() + ">");
                        editChoosen.append(delim);
                    }

                    editChoosen.append(str);

                }

            }

            if (resultCode == RESULT_CANCELED) {

            }
        }
    }
}