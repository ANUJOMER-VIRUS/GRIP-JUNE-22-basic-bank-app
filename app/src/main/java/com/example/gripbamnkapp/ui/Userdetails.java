package com.example.gripbamnkapp.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.databasehandler.TransactionContractdb;
import com.example.gripbamnkapp.databasehandler.transactiondbhandler;
import com.example.gripbamnkapp.modalClass.userdatamodal;

public class Userdetails extends AppCompatActivity {
private TextView nametv,emailtv,phonetv,banknametv,ifsctv,balancetv,accnotv;
String nameud,emailud,phoneud,banknameud,accnoud,ifscud,balud;
userdatamodal userdetailmodel;
com.example.gripbamnkapp.databasehandler.userdbhandler userdbhandler;

    int balance;
Button Transfermoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
    nametv=findViewById(R.id.userdataname);
    emailtv=findViewById(R.id.userdataemail);
    phonetv=findViewById(R.id.userdataphone);
    banknametv=findViewById(R.id.userdatabankname);
    ifsctv=findViewById(R.id.userdataifsc);
    accnotv=findViewById(R.id.userdataaccno);
    balancetv=findViewById(R.id.userdatabalance);
    Transfermoney=findViewById(R.id.transfermoney);

   nameud=getIntent().getStringExtra("name");
    emailud=getIntent().getStringExtra("email");
    phoneud=getIntent().getStringExtra("phone");
    banknameud=getIntent().getStringExtra("bankname");
    accnoud=getIntent().getStringExtra("accnum");
    ifscud=getIntent().getStringExtra("ifsc");
    balud=getIntent().getStringExtra("balance");

userdetailmodel=new userdatamodal(nameud,balud,emailud,phoneud,banknameud,accnoud,ifscud);


    nametv.setText(nameud);
    emailtv.setText(emailud);
    phonetv.setText(phoneud);
    balancetv.setText(balud);
    banknametv.setText(banknameud);
    ifsctv.setText(ifscud);
    accnotv.setText(accnoud);
  balance=Integer.parseInt(balud);

    }
    public void showAlertDialogButtonClicked(View view) {
        enteramount();

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("enter amount");
        final View customLayout=getLayoutInflater().inflate(R.layout.alertdialog,null);
        builder.setView(customLayout);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText=customLayout.findViewById(R.id.editText);
                sendDialogtoactivity(editText.getText().toString());
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void enteramount() {
    }

    private void cancel() {
        AlertDialog.Builder builder_exitbutton=new AlertDialog.Builder(Userdetails.this);
        builder_exitbutton.setTitle("Do you want to cancel transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        transactiondbhandler transactiondbhandler=new transactiondbhandler(getApplicationContext());
                        SQLiteDatabase db = transactiondbhandler.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_FROM_NAME, nameud);
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_TO_NAME, "");
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_STATUS, 0);
                        values.put(TransactionContractdb.TransactionEntry.COLUMN_AMOUNT, 0);

                        db.insert(TransactionContractdb.TransactionEntry.TABLE_NAME, null, values);

                        Toast.makeText(Userdetails.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
startActivity(new Intent(Userdetails.this,MainActivity.class));
finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                enteramount();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();


    }

    private void sendDialogtoactivity(String toString) {
        int amount=Integer.parseInt(toString);
        int bal=Integer.parseInt(userdetailmodel.getBalance());
        if(amount>bal){
            Toast.makeText(Userdetails.this,"insufficent balance to transfer",Toast.LENGTH_SHORT).show();
        }
        else if(toString.isEmpty()){
            Toast.makeText(Userdetails.this,"please enter some value",Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            Intent i=new Intent(Userdetails.this,TransferSelector.class);
            i.putExtra("amount",toString);
            i.putExtra("from_user_name",userdetailmodel.getName());
            i.putExtra("from_user_amt",userdetailmodel.getBalance());
            Toast.makeText(Userdetails.this," "+toString,Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();
        }
    }


}