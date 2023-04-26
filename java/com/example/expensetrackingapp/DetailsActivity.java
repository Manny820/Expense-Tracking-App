package com.example.expensetrackingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Button editButton = findViewById(R.id.editButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        ArrayList<Transaction> arrayList = PrefConfig.read(this);

        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);
        TextView date = findViewById(R.id.date);
        TextView amount = findViewById(R.id.amount);
        TextView category = findViewById(R.id.category);
        TextView description = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();

        image.setImageResource(bundle.getInt("image"));
        name.setText(bundle.getString("name"));
        date.setText(bundle.getString("date"));
        amount.setText(bundle.getString("amount"));
        category.setText(bundle.getString("category"));
        description.setText(bundle.getString("description"));

        String testName = bundle.getString("name");

        if (category.getText().equals("Income")) {
            amount.setTextColor(0xFF73E60F);
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // to be added
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(DetailsActivity.this);
                altdial.setTitle("Are you sure?");
                altdial.setMessage("All information will be DELETED.").setCancelable(false)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        for (Transaction transaction : arrayList) {
                            if (transaction.getName().equals(testName)) {
                                arrayList.remove(transaction);
                            }
                        }
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

                altdial.show();
            }
        });

    }
}