package com.example.gripbamnkapp.databasehandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gripbamnkapp.modalClass.userdatamodal;

import java.util.ArrayList;

public class userdbhandler extends SQLiteOpenHelper {
    private static final String DB_NAME="userdata";
    private static final int DB_VER=1;
    private static final String TABLE_NAME="usertable";
    private static final String ID_COL="id";
    private static final String NAME_COL="name";
    private static  final String EMAIL_COL="email";
    private static final String PHONE_COL="phone";
    private static final String BANK_COL="bank";
    private static final String ACC_COL="acnum";
    private static final String IFSC_COL="ifsc";
    private static final String BAl_COL="balance";
public userdbhandler(Context context){
    super(context,DB_NAME,null,DB_VER);
}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_NAME+" ("
                +ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME_COL+" TEXT,"
                +EMAIL_COL+" TEXT,"
                +PHONE_COL+" TEXT,"
                +BANK_COL+" TEXT,"
                +ACC_COL+" TEXT,"
                +IFSC_COL+" TEXT,"
                +BAl_COL+" TEXT)";
        sqLiteDatabase.execSQL(query);
    }
    public void adduserdata(String name,String email,String phone,String bank,String acc,String ifsc,String bal){
    SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME_COL,name);
        values.put(EMAIL_COL,email);
        values.put(PHONE_COL,phone);
        values.put(BANK_COL,bank);
        values.put(ACC_COL,acc);
        values.put(IFSC_COL,ifsc);
        values.put(BAl_COL,bal);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
onCreate(sqLiteDatabase);
    }
    public ArrayList<userdatamodal> readuserdata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursordata = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<userdatamodal> userdatamodalsArraylist = new ArrayList<>();
        if (cursordata.moveToFirst()) {
    do{
            userdatamodalsArraylist.add(new userdatamodal(
                    cursordata.getString(1),
                    cursordata.getString(7),
                    cursordata.getString(2),
                    cursordata.getString(3),
                    cursordata.getString(4),
                    cursordata.getString(5),
                    cursordata.getString(6)));

        }
        while (cursordata.moveToNext()) ;
    }
    cursordata.close();
        return userdatamodalsArraylist;
    }
    public void updatedata(String originalname,String bal){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues values=new ContentValues();
    values.put(BAl_COL,bal);
    db.update(TABLE_NAME,values,"name=?",new String[]{originalname});
    db.close();
    }
    public void deleteCourse(String courseName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }
}
