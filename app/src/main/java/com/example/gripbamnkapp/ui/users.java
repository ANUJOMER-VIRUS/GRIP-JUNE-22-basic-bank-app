package com.example.gripbamnkapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.Adapter.userdataRvAdapter;
import com.example.gripbamnkapp.modalClass.userdatamodal;
import com.example.gripbamnkapp.databasehandler.userdbhandler;

import java.util.ArrayList;

public class users extends AppCompatActivity {
    private ArrayList<userdatamodal> userdatamodalArrayList;
    private userdbhandler userDbhandler;
    private com.example.gripbamnkapp.Adapter.userdataRvAdapter userdataRvAdapter;
    private RecyclerView userRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
userdatamodalArrayList=new ArrayList<>();
        userDbhandler=new userdbhandler(users.this);
        userdatamodalArrayList=userDbhandler.readuserdata();
        userdataRvAdapter=new userdataRvAdapter(userdatamodalArrayList,users.this);
        userRv=findViewById(R.id.user_RV);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(users.this,RecyclerView.VERTICAL,false);
        userRv.setLayoutManager(linearLayoutManager);
        userRv.setAdapter(userdataRvAdapter);
    }


}