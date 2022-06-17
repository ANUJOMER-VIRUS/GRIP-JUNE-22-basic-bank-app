package com.example.gripbamnkapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gripbamnkapp.R;

public class MainActivity extends AppCompatActivity {
Button btnuser;
Button btnrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnuser=findViewById(R.id.user);

    btnuser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(MainActivity.this,users.class);
            startActivity(i);
        }
    });
    btnrans=findViewById(R.id.trans);
    btnrans.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
Intent i=new Intent(MainActivity.this,Tranactiondetail.class);
startActivity(i);
        }
    });
    }
}