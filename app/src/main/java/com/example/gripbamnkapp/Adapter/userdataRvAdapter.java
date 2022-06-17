package com.example.gripbamnkapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.ui.Userdetails;
import com.example.gripbamnkapp.modalClass.userdatamodal;

import java.util.ArrayList;

public class userdataRvAdapter extends RecyclerView.Adapter<userdataRvAdapter.ViewHolder> {
    public userdataRvAdapter(ArrayList<userdatamodal> userdatamodalArrayList, Context context) {
        this.userdatamodalArrayList = userdatamodalArrayList;
        this.context = context;
    }

    private ArrayList<userdatamodal> userdatamodalArrayList;
   private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.userrv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
userdatamodal modal=userdatamodalArrayList.get(position);
holder.name.setText(modal.getName());
holder.balance.setText(modal.getBalance());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(context, Userdetails.class);

        i.putExtra("name",modal.getName());
        i.putExtra("balance",modal.getBalance());
        i.putExtra("email",modal.getEmail());
        i.putExtra("phone",modal.getPhone());
        i.putExtra("bankname",modal.getBankname());
        i.putExtra("accnum",modal.getAccountnum());
        i.putExtra("ifsc",modal.getIfsc());
        context.startActivity(i);

    }
});
    }

    @Override
    public int getItemCount() {
     return  userdatamodalArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,balance;
    public ViewHolder(@NonNull View itemView){
        super(itemView);
        name=itemView.findViewById(R.id.userRvname);
        balance=itemView.findViewById(R.id.userRvbalance);

    }
    }
}
