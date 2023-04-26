package com.example.expensetrackingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        ListView listView = findViewById(R.id.listView);

        // retrieves transaction values within shared-preferences
        // through add activity and puts them into an arrayList
        ArrayList<Transaction> arrayList = PrefConfig.read(this);

        // puts values from arraylist into listview to be displayed
        TransactionListAdapter transactionListAdapter = new TransactionListAdapter(this,R.layout.list_transactions,arrayList);
        listView.setAdapter(transactionListAdapter);

        TextView positiveCountView = findViewById(R.id.positiveCount);
        TextView negativeCountView = findViewById(R.id.negativeCount);


        // this block of code calculates the totals for positive
        // and negative transactions and displays it on UI
        //-------------------------------------------------------------------------------
        String temp;
        double positiveCount = 0.00;
        double negativeCount = 0.00;
        DecimalFormat df = new DecimalFormat("0.00");

        for (Transaction transaction : arrayList) {
            if (transaction.getCategory().equals("Income")) {
                temp = transaction.getAmount();
                temp = temp.substring(1);
                double a = Double.parseDouble(temp);
                positiveCount += a;
                df.format(positiveCount);
                String b = "$" + Double.toString(positiveCount);
                positiveCountView.setText(b);
            } else if (transaction.getCategory().equals("Entertainment") ||
                        transaction.getCategory().equals("Shopping") ||
                        transaction.getCategory().equals("Bills") ||
                        transaction.getCategory().equals("Donations") ||
                        transaction.getCategory().equals("Food") ||
                        transaction.getCategory().equals("Housing") ||
                        transaction.getCategory().equals("Transportation") ||
                        transaction.getCategory().equals("Other")){
                temp = transaction.getAmount();
                temp = temp.substring(1);
                double c = Double.parseDouble(temp);
                negativeCount += c;
                df.format(negativeCount);
                String d = "-$" + Double.toString(negativeCount);
                negativeCountView.setText(d);
            }
        //-------------------------------------------------------------------------------
        }

        // this allows for each transaction to be clickable
        // and send its individual data onto Details Activity
        //-------------------------------------------------------------------------------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Transaction transaction = arrayList.get(position);
                Intent intent = new Intent(TransactionActivity.this,DetailsActivity.class);
                intent.putExtra("image",transaction.getImage());
                intent.putExtra("name",transaction.getName());
                intent.putExtra("date",transaction.getDate());
                intent.putExtra("amount",transaction.getAmount());
                intent.putExtra("category",transaction.getCategory());
                intent.putExtra("description",transaction.getDescription());
                startActivity(intent);
            }
        });
        //-------------------------------------------------------------------------------
    }


    // functions that launch add activity and categories activity
    //-------------------------------------------------------------------------------
    public void launchCategoriesActivity(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
    public void launchAddActivity(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
    //-------------------------------------------------------------------------------
}