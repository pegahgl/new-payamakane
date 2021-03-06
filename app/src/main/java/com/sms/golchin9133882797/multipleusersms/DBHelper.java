package com.sms.golchin9133882797.multipleusersms;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class DBHelper extends SQLiteOpenHelper {
    //--------------------- variable define -----------------------
    public static String DB_NAME = "payamak.db";
    private final Context context;
    public static String DB_PATH;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/" + "databases/";
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        if (dbExist) {

        } else {
            this.getWritableDatabase();
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        File  dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //---------------------------Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }


    @Override
    public void onCreate(SQLiteDatabase arg0) {
        //Ç?äÇ åã?äØæÑ?å ã?ÎÇÓÊ?ã ?å ˜ÇÑÇ? Ï?å ÇäÌÇã ÔæÏ ˜å ÇÓÊÝÇÏå Ô.Ï.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OV, int newVersion) {

    }
//    db=myoutput;
//    onUpgrade(db,1,1.1);


}

