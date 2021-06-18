package com.sro.basicbaningapp.adapter;
// Code written by SWASTI RANJAN OJHA
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sro.basicbaningapp.R;
import com.sro.basicbaningapp.model.Transaction;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.myViewHolder> {
    private ArrayList<Transaction> transactionArrayList;

    public TransactionAdapter(ArrayList<Transaction> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext()).inflate(R.layout.transaction_history_item, parent, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TransactionAdapter.myViewHolder holder, int position) {
        holder.itemView.setTag(transactionArrayList.get(position));
        holder.fromName.setText(transactionArrayList.get(position).getFromName());
        holder.toName.setText(transactionArrayList.get(position).getToName());
        holder.amountTransferred.setText(String.format("%d", transactionArrayList.get(position).getAmount()));


        if (transactionArrayList.get(position).getStatus().equals("1")) {
            holder.cardView.setCardBackgroundColor(Color.argb(100, 105, 187, 105));
        }
        else {
            holder.cardView.setCardBackgroundColor(Color.argb(100, 239, 100, 100));
        }
    }

    @Override
    public int getItemCount() {
        return transactionArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView fromName, toName, amountTransferred, date, time;
        CardView cardView;
        LinearLayout toUserInfo;
        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            fromName = itemView.findViewById(R.id.t_from_name);
            toName = itemView.findViewById(R.id.t_to_name);
            amountTransferred = itemView.findViewById(R.id.t_amount);
            cardView = itemView.findViewById(R.id.transaction_card_view);
            toUserInfo = itemView.findViewById(R.id.to_user_info);
            date = itemView.findViewById(R.id.t_date);
            time = itemView.findViewById(R.id.t_time);

        }
    }
}
