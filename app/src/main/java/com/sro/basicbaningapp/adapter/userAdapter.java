package com.sro.basicbaningapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sro.basicbaningapp.R;
import com.sro.basicbaningapp.activity.userDetail;
import com.sro.basicbaningapp.model.user;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.myViewHolder> {
    ArrayList<user> userList;
    Context context;

    public userAdapter(ArrayList<user> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public userAdapter.myViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
       myViewHolder viewHolder;
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem,parent,false);
        viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull userAdapter.myViewHolder holder, int position) {

        holder.userName.setText(userList.get(position).getName());
        holder.userBalance.setText(String.format("%d", userList.get(position).getBal()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), userDetail.class);
                intent.putExtra("username", userList.get(position).getName());
                intent.putExtra("userbalance", userList.get(position).getBal());
                intent.putExtra("useremail", userList.get(position).getEmail());
                intent.putExtra("userifsc", userList.get(position).getIfsc());
                intent.putExtra("userphno", userList.get(position).getPhno());
                intent.putExtra("useraccno", userList.get(position).getAcno());
                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView userName,userBalance;
        public myViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
             userName = itemView.findViewById(R.id.userName);
             userBalance = itemView.findViewById(R.id.userBalance);
        }
    }
}
