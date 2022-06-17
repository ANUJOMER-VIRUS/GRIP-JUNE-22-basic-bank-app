package com.example.gripbamnkapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.modalClass.userdatamodal;

import java.util.ArrayList;

public class transferselRvAdapter extends RecyclerView.Adapter<transferselRvAdapter.ViewHolder>  {
   private ArrayList<userdatamodal>userdatamodalArrayList;

    public transferselRvAdapter(ArrayList<userdatamodal> userdatamodalArrayList, OnUserListener onUserListener) {
        this.userdatamodalArrayList = userdatamodalArrayList;
        this.onUserListener = onUserListener;
    }

    private OnUserListener onUserListener;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userrv,parent,false);
        return new ViewHolder(view,onUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
userdatamodal modal=userdatamodalArrayList.get(position);
holder.name.setText(modal.getName());
holder.balance.setText(modal.getBalance());

    }

    @Override
    public int getItemCount() {
        return userdatamodalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      OnUserListener onUserListener;
        private TextView name,balance;
        public ViewHolder(@NonNull View itrmview,OnUserListener onUserListener) {
            super(itrmview);
            name=itrmview.findViewById(R.id.userRvname);
            balance=itrmview.findViewById(R.id.userRvbalance);
            this.onUserListener=onUserListener;
            itrmview.setOnClickListener(this);

        }
        @Override
        public void onClick(View V){
            onUserListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserListener {
        void onUserClick(int position);
    }

}
