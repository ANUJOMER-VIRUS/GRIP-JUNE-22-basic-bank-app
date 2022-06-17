package com.example.gripbamnkapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.Adapter.TransactionAdapter;
import com.example.gripbamnkapp.databasehandler.TransactionContractdb;
import com.example.gripbamnkapp.modalClass.Transactiondatamodel;
import com.example.gripbamnkapp.databasehandler.transactiondbhandler;

import java.util.ArrayList;

public class Tranactiondetail extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
RecyclerView.LayoutManager layoutManager;
ArrayList<Transactiondatamodel> transactiondatamodelArrayList;
private transactiondbhandler transactiondbhandler;
TextView emptylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranactiondetail);

        emptylist=findViewById(R.id.empty_text);

        transactiondatamodelArrayList=new ArrayList<>();

        transactiondbhandler=new transactiondbhandler(this);


        displaydbInfo();

        recyclerView=findViewById(R.id.transactions_list);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);

       adapter=new TransactionAdapter(this,transactiondatamodelArrayList);
    recyclerView.setAdapter(adapter);
    }

    private void displaydbInfo() {

        Log.d("TAG", "displayDataBaseInfo()");

        // Create and/or open a database to read from it
        SQLiteDatabase db = transactiondbhandler.getReadableDatabase();

        Log.d("TAG", "displayDataBaseInfo()1");

        String[] projection = {
                TransactionContractdb.TransactionEntry.COLUMN_FROM_NAME,
                TransactionContractdb.TransactionEntry.COLUMN_TO_NAME,
                TransactionContractdb.TransactionEntry.COLUMN_AMOUNT,
                TransactionContractdb.TransactionEntry.COLUMN_STATUS
        };

        Log.d("TAG", "displayDataBaseInfo()2");

        Cursor cursor = db.query(
                TransactionContractdb.TransactionEntry.TABLE_NAME,   // The table to query
                projection,                          // The columns to return
                null,                        // The columns for the WHERE clause
                null,                     // The values for the WHERE clause
                null,                        // Don't group the rows
                null,                         // Don't filter by row groups
                null);                       // The sort order

        try {
            // Figure out the index of each column
            int fromNameColumnIndex = cursor.getColumnIndex(TransactionContractdb.TransactionEntry.COLUMN_FROM_NAME);
            int ToNameColumnIndex = cursor.getColumnIndex(TransactionContractdb.TransactionEntry.COLUMN_TO_NAME);
            int amountColumnIndex = cursor.getColumnIndex(TransactionContractdb.TransactionEntry.COLUMN_AMOUNT);
            int statusColumnIndex = cursor.getColumnIndex(TransactionContractdb.TransactionEntry.COLUMN_STATUS);

            Log.d("TAG", "displayDataBaseInfo()3");

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String fromName = cursor.getString(fromNameColumnIndex);
                String ToName = cursor.getString(ToNameColumnIndex);
                int accountBalance = cursor.getInt(amountColumnIndex);
                int status = cursor.getInt(statusColumnIndex);


                //Log.d("TAG", "displayDataBaseInfo()4");

                // Display the values from each column of the current row in the cursor in the TextView
                transactiondatamodelArrayList.add(new Transactiondatamodel(fromName, ToName, accountBalance, status));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        if (transactiondatamodelArrayList.isEmpty()) {
            emptylist.setVisibility(View.VISIBLE);
        } else {
            emptylist.setVisibility(View.GONE);
        }
    }
    @Override
    public void onBackPressed() {
startActivity(new Intent(Tranactiondetail.this,MainActivity.class));
    }
}