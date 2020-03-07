package com.topjal.loginregisterui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private  Context context;
    private ArrayList<TimeModel> arrayList;

    public UserAdapter(Context context, ArrayList<TimeModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.item_date.setText(arrayList.get(position).getDate());
        holder.item_time.setText(arrayList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView item_date, item_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_date = itemView.findViewById(R.id.item_date);
            item_time = itemView.findViewById(R.id.item_time);

        }
    }

}
