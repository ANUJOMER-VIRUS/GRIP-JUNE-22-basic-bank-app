package com.example.gripbamnkapp.databasehandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class transactiondbhandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaction.db";

    private static final int DATABASE_VERSION = 1;

    public transactiondbhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TRANSACTION_TABLE =  "CREATE TABLE " + TransactionContractdb.TransactionEntry.TABLE_NAME + " ("
                + TransactionContractdb.TransactionEntry.COLUMN_FROM_NAME + " VARCHAR, "
                + TransactionContractdb.TransactionEntry.COLUMN_TO_NAME + " VARCHAR, "
                + TransactionContractdb.TransactionEntry.COLUMN_AMOUNT + " INTEGER, "
                + TransactionContractdb.TransactionEntry.COLUMN_STATUS + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);

    }
    public boolean insertTransferData (String fromName, String toName, String amount, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TransactionContractdb.TransactionEntry.COLUMN_FROM_NAME, fromName);
        contentValues.put(TransactionContractdb.TransactionEntry.COLUMN_TO_NAME, toName);
        contentValues.put(TransactionContractdb.TransactionEntry.COLUMN_AMOUNT, amount);
        contentValues.put(TransactionContractdb.TransactionEntry.COLUMN_STATUS, status);
        Long result = db.insert(TransactionContractdb.TransactionEntry.TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TransactionContractdb.TransactionEntry.TABLE_NAME);
            onCreate(db);
        }
    }

}
