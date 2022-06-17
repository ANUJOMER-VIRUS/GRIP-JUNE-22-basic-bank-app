package com.example.gripbamnkapp.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.databasehandler.TransactionContractdb;
import com.example.gripbamnkapp.databasehandler.transactiondbhandler;
import com.example.gripbamnkapp.Adapter.transferselRvAdapter;
import com.example.gripbamnkapp.modalClass.userdatamodal;
import com.example.gripbamnkapp.databasehandler.userdbhandler;

import java.util.ArrayList;

public class TransferSelector extends AppCompatActivity implements com.example.gripbamnkapp.Adapter.transferselRvAdapter.OnUserListener {
private ArrayList<userdatamodal> userdatamodalArrayList;
private com.example.gripbamnkapp.databasehandler.userdbhandler userdbhandler;
   private  RecyclerView recyclerView;
private transferselRvAdapter transferselRvAdapter;
RecyclerView.LayoutManager layoutManager;

    String  toUserAccountBalance;
    String fromUserAccountName, fromUserAccountBalance, transferAmount, toUserAccountName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_selector);



        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            fromUserAccountName=bundle.getString("from_user_name");
            fromUserAccountBalance=bundle.getString("from_user_amt");
            transferAmount=bundle.getString("amount");
        }
        userdatamodalArrayList=new ArrayList<>();

        userdbhandler=new userdbhandler(TransferSelector.this);

        userdatamodalArrayList=userdbhandler.readuserdata();

        transferselRvAdapter=new transferselRvAdapter(userdatamodalArrayList,TransferSelector.this);

        recyclerView=findViewById(R.id.transfer_RV);



        layoutManager=new LinearLayoutManager(TransferSelector.this);
        recyclerView.setLayoutManager(layoutManager);
        // transferselRvAdapter=new transferselRvAdapter(userdatamodalArrayList,TransferSelector.this);
        recyclerView.setAdapter(transferselRvAdapter);








    }


    @Override
    public void onUserClick(int position) {

        toUserAccountName=userdatamodalArrayList.get(position).getName();
        toUserAccountBalance=userdatamodalArrayList.get(position).getBalance();
        if(fromUserAccountName.equals(toUserAccountName)){
            Toast.makeText(this,"Transaction not be done on same account",Toast.LENGTH_SHORT).show();
        }
        else {
            calculateAmount();

            // Transaction history work;

            new transactiondbhandler(this).insertTransferData(fromUserAccountName, toUserAccountName, transferAmount, 1);
            Toast.makeText(this, "Transaction Successful!! " , Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(TransferSelector.this,Tranactiondetail.class));
       finish();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_exitButton = new AlertDialog.Builder(TransferSelector.this);
        builder_exitButton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton ("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i) {
                        // Transactions Cancelled
                        transactiondbhandler transactiondbhandler=new transactiondbhandler(getApplicationContext());
                        SQLiteDatabase db = transactiondbhandler.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_FROM_NAME, fromUserAccountName);
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_TO_NAME, toUserAccountName);
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_STATUS, 0);
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_AMOUNT, transferAmount);

                        db.insert(TransactionContractdb.TransactionEntry.TABLE_NAME, null, values);

                        Toast.makeText(TransferSelector.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(TransferSelector.this, MainActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alertExit = builder_exitButton.create();
        alertExit.show();
                    }

    private void calculateAmount() {
        Integer currentamt=Integer.parseInt(fromUserAccountBalance);
        Integer transferAmt=Integer.parseInt(transferAmount);
        Integer touserac=Integer.parseInt(toUserAccountBalance);
        Integer fromrem=currentamt-transferAmt;
        Integer torem=transferAmt+touserac;

        new userdbhandler(this).updatedata(toUserAccountName,String.valueOf(torem));
        new userdbhandler(this).updatedata(fromUserAccountName,String.valueOf(fromrem));

    }
}

