package com.example.expensetrackingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CategoryGridAdapter extends ArrayAdapter<Category> {

    public CategoryGridAdapter(@NonNull Context context, @NonNull ArrayList<Category> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        HolderView holderView;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_categories, parent,false);
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        }
        else{
            holderView = (HolderView) convertView.getTag();
        }

        Category category = getItem(position);
        holderView.icons.setImageResource(category.getImage());
        holderView.text.setText(category.getName());

        return convertView;
    }

    private static class HolderView{
        private final ImageView icons;
        private final TextView text;

        public HolderView(View view){
            icons = view.findViewById(R.id.image);
            text = view.findViewById(R.id.txtName);
        }
    }
}
