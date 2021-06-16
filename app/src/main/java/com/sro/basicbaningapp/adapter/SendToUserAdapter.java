package com.sro.basicbaningapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sro.basicbaningapp.R;
import com.sro.basicbaningapp.model.user;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SendToUserAdapter extends RecyclerView.Adapter<SendToUserAdapter.ViewHolder> {
    ViewHolder.OnUserListener onUserListener;
    ArrayList<user> arrayList;

    public SendToUserAdapter(ViewHolder.OnUserListener onUserListener, ArrayList<user> arrayList) {
        this.onUserListener = onUserListener;
        this.arrayList = arrayList;
    }

    @NonNull
    @NotNull
    @Override
    public SendToUserAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem, parent, false);
        return new SendToUserAdapter.ViewHolder(view, onUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SendToUserAdapter.ViewHolder holder, int position) {
       holder.userName.setText(arrayList.get(position).getName());
       holder.userAccountBalance.setText(String.format("%d",arrayList.get(position).getBal()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userName, userAccountBalance;
        OnUserListener onUserListener;

        public ViewHolder(@NonNull View itemView, OnUserListener onUserListener) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userAccountBalance = itemView.findViewById(R.id.userBalance);
            this.onUserListener = onUserListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onUserListener.onUserClick(getAdapterPosition());
        }

        public interface OnUserListener {
            void onUserClick(int position);
        }
    }
}