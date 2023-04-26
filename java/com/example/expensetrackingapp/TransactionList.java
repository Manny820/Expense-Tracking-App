package com.example.expensetrackingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TransactionList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        ListView listView = findViewById(R.id.listView);
        ArrayList<Transaction> arrayList = (ArrayList<Transaction>) getIntent().getSerializableExtra("arrayList");

        TransactionListAdapter transactionListAdapter = new TransactionListAdapter(this,R.layout.list_transactions,arrayList);
        listView.setAdapter(transactionListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Transaction transaction = arrayList.get(position);
                Intent intent = new Intent(TransactionList.this, DetailsActivity.class);
                intent.putExtra("image", transaction.getImage());
                intent.putExtra("name", transaction.getName());
                intent.putExtra("date", transaction.getDate());
                intent.putExtra("amount", transaction.getAmount());
                intent.putExtra("category", transaction.getCategory());
                intent.putExtra("description", transaction.getDescription());
                startActivity(intent);
            }
        });
    }
}
