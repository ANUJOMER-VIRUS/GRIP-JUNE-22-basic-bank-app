package com.example.gripbamnkapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gripbamnkapp.R;
import com.example.gripbamnkapp.modalClass.Transactiondatamodel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolderl> {
    private ArrayList<Transactiondatamodel> transactiondatamodelArrayList;
    public TransactionAdapter(Context context,ArrayList<Transactiondatamodel> list){
        transactiondatamodelArrayList=list;
    }

    @NonNull
    @Override
    public ViewHolderl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transactionrv,parent,false);
   return new ViewHolderl(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderl holder, int position) {
        holder.itemView.setTag(transactiondatamodelArrayList.get(position));
        holder.fromName.setText(transactiondatamodelArrayList.get(position).getFromuser());
        holder.toName.setText(transactiondatamodelArrayList.get(position).getTouser());
        holder.amountTransferred.setText(String.format("%d",transactiondatamodelArrayList.get(position).getAmounttransfered()));

        if (transactiondatamodelArrayList.get(position).getStatus() == 1) {
            holder.cardView.setCardBackgroundColor(Color.argb(100, 105, 187, 105));
        } else {
            holder.cardView.setCardBackgroundColor(Color.argb(100, 239, 100, 100));
        }
    }

    @Override
    public int getItemCount() {
        return transactiondatamodelArrayList.size();
    }

    public class ViewHolderl extends RecyclerView.ViewHolder {
        TextView fromName, toName, amountTransferred, date, time;
        CardView cardView;
        LinearLayout toUserInfo;

        public ViewHolderl(@NonNull View itemView) {
            super(itemView);
            fromName=itemView.findViewById(R.id.tv_from_name);
            toName=itemView.findViewById(R.id.tv_to_name);
            amountTransferred=itemView.findViewById(R.id.tv_amount);
            cardView=itemView.findViewById(R.id.transaction_card_view);
            toUserInfo=itemView.findViewById(R.id.to_user_info);

        }
    }

}
