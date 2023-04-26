package com.example.expensetrackingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TransactionListAdapter extends ArrayAdapter<Transaction> {
    private Context mContext;
    private  int mResource;

    public TransactionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Transaction> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);


        ImageView imageView = convertView.findViewById(R.id.image);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtAmount = convertView.findViewById(R.id.txtAmount);

        if (getItem(position).getCategory().equals("Income")) {
            txtAmount.setTextColor(0xFF73E60F);
        }

        imageView.setImageResource(getItem(position).getImage());
        txtName.setText(getItem(position).getName());
        txtDate.setText(getItem(position).getDate());
        txtAmount.setText(getItem(position).getAmount());

        return convertView;
    }
}
