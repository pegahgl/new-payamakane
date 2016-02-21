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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainKhanevade extends Activity implements OnItemClickListener {

    ArrayList<String> name2 = new ArrayList<String>();
    ArrayList<String> phno2 = new ArrayList<String>();
    ArrayList<String> phno3 = new ArrayList<String>();
    MyAdapter ma1;
    Button send1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.get2);
        getAllCallLogs(this.getContentResolver());
        ListView lv = (ListView) findViewById(R.id.lv1);
        ma1 = new MyAdapter();
        lv.setAdapter(ma1);
        lv.setOnItemClickListener(this);
        lv.setItemsCanFocus(false);
        lv.setTextFilterEnabled(true);
        send1 = (Button) findViewById(R.id.send1);
        send1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                StringBuilder checkedcontacts = new StringBuilder();
                System.out.println(".............." + ma1.mCheckStates.size());
                for (int i = 0; i < name2.size(); i++)

                {
                    if (ma1.mCheckStates.get(i) == true) {
                        phno3.add(phno2.get(i).toString());
                        checkedcontacts.append(name2.get(i).toString());
                        checkedcontacts.append("\n");

                    } else {
                        System.out.println("..Not Checked......"
                                + name2.get(i).toString());
                    }

                }
                Toast.makeText(MainKhanevade.this, checkedcontacts, Toast.LENGTH_LONG ).show();
                Intent returnIntent = new Intent();
                returnIntent.putStringArrayListExtra("name", phno3);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        ma1.toggle(arg2);
    }

    public void getAllCallLogs(ContentResolver cr) {

        Cursor phones = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null,
                null, null);
        while (phones.moveToNext()) {
            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            System.out.println(".................." + phoneNumber);
            name2.add(name);
            phno2.add(phoneNumber);
        }

        phones.close();
    }

    class MyAdapter extends BaseAdapter implements
            CompoundButton.OnCheckedChangeListener {
        private SparseBooleanArray mCheckStates;
        LayoutInflater mInflater;
        TextView tv4, tv5;
        CheckBox cb2;

        MyAdapter() {
            mCheckStates = new SparseBooleanArray(name2.size());
            mInflater = (LayoutInflater) MainKhanevade.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return name2.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub

            return 0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi = convertView;
            if (convertView == null)
                vi = mInflater.inflate(R.layout.row2, null);
            tv4 = (TextView) vi.findViewById(R.id.textView4);
            tv5 = (TextView) vi.findViewById(R.id.textView5);
            cb2 = (CheckBox) vi.findViewById(R.id.checkBox2);
            tv4.setText("Name :" + name2.get(position));
            tv5.setText("Phone No :" + phno2.get(position));
            cb2.setTag(position);
            cb2.setChecked(mCheckStates.get(position, false));
            cb2.setOnCheckedChangeListener(this);

            return vi;
        }

        public boolean isChecked(int position) {
            return mCheckStates.get(position, false);
        }

        public void setChecked(int position, boolean isChecked) {
            mCheckStates.put(position, isChecked);
        }

        public void toggle(int position) {
            setChecked(position, !isChecked(position));
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub
            mCheckStates.put((Integer) buttonView.getTag(), isChecked);
        }

    }
}