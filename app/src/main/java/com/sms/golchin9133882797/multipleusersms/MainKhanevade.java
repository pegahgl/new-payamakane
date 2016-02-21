package com.sms.golchin9133882797.multipleusersms;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainKhanevade extends Activity implements OnClickListener {
    Button btnChoose;
    static int ResultCode = 12;
    EditText editChoosen;
    String delim = ";";
    String contacts = "";
    ArrayList<String> sendlist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khanevade);
        btnChoose = (Button) findViewById(R.id.btnChooseContact);
        editChoosen = (EditText) findViewById(R.id.edChoosen);
        btnChoose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnChoose) {
            Intent g = new Intent(MainKhanevade.this, MainActivity.class);
            startActivityForResult(g, ResultCode);
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