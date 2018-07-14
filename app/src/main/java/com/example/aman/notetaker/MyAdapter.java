package com.example.aman.notetaker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myholder> {
    ArrayList<Data> arrayList;

    public MyAdapter(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        Data currentData =arrayList.get(position);
        holder.head.setText(currentData.getHead());
        holder.data.setText(currentData.getData());
        holder.numb.setText(currentData.getNumb());

    }

    @Override
    public int getItemCount() {
return arrayList.size();

    }

    public class Myholder extends RecyclerView.ViewHolder
    { TextView data,numb,head;
     public Myholder(View itemView) {
            super(itemView);
            data=itemView.findViewById(R.id.tvContent);
            numb=itemView.findViewById(R.id.tvNumb);
            head=itemView.findViewById(R.id.tvHead);

        }
    }


}
