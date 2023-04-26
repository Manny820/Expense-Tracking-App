package com.example.expensetrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ArrayList<Transaction> transactionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        GridView gridView = findViewById(R.id.gridView);
        transactionsList = PrefConfig.read(this);

        // ArrayList for Categories
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(R.drawable.entertainment,"Entertainment"));
        categoryList.add(new Category(R.drawable.shopping,"Shopping"));
        categoryList.add(new Category(R.drawable.food,"Food"));
        categoryList.add(new Category(R.drawable.bills,"Bills"));
        categoryList.add(new Category(R.drawable.income,"Income"));
        categoryList.add(new Category(R.drawable.housing,"Housing"));
        categoryList.add(new Category(R.drawable.transportation,"Transportation"));
        categoryList.add(new Category(R.drawable.donations,"Donations"));
        categoryList.add(new Category(R.drawable.other,"Other"));

        // ArrayLists for each type of transaction
        ArrayList<Transaction> entertainmentList = filterList("Entertainment");
        ArrayList<Transaction> shoppingList = filterList("Shopping");
        ArrayList<Transaction> foodList = filterList("Food");
        ArrayList<Transaction> billsList = filterList("Bills");
        ArrayList<Transaction> incomeList = filterList("Income");
        ArrayList<Transaction> housingList = filterList("Housing");
        ArrayList<Transaction> transportationList = filterList("Transportation");
        ArrayList<Transaction> donationsList = filterList("Donations");
        ArrayList<Transaction> otherList = filterList("Other");

        // puts values from categoryList into gridview to be displayed
        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(this,categoryList);
        gridView.setAdapter(categoryGridAdapter);


        // this allows each category to be clickable and
        // upon click, the desired list appears
        //-------------------------------------------------------------------------------
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Category category = categoryList.get(position);
                Intent intent = new Intent(CategoryActivity.this,TransactionList.class);

                if (category.getName().equals("Entertainment")) {
                    intent.putExtra("arrayList", entertainmentList);

                } else if (category.getName().equals("Shopping")) {
                    intent.putExtra("arrayList", shoppingList);

                } else if (category.getName().equals("Food")) {
                    intent.putExtra("arrayList", foodList);

                } else if (category.getName().equals("Bills")) {
                    intent.putExtra("arrayList", billsList);

                } else if (category.getName().equals("Income")) {
                    intent.putExtra("arrayList", incomeList);

                } else if (category.getName().equals("Housing")) {
                    intent.putExtra("arrayList", housingList);

                } else if (category.getName().equals("Transportation")) {
                    intent.putExtra("arrayList", transportationList);

                } else if (category.getName().equals("Donations")) {
                    intent.putExtra("arrayList", donationsList);

                } else if (category.getName().equals("Other")) {
                    intent.putExtra("arrayList", otherList);

                } else {
                    finish();
                }
                startActivity(intent);
            }
        });
        //-------------------------------------------------------------------------------
    }


    // this function filters an arrayList into an
    // arrayList with only one type of transaction
    //-------------------------------------------------------------------------------
    public  ArrayList<Transaction> filterList(String n) {
        ArrayList<Transaction> testList = new ArrayList<>();
        for (Transaction transaction : transactionsList) {
            if (transaction.getCategory().equals(n)) {
                testList.add(transaction);
            }
        }
        return testList;
    }
    //-------------------------------------------------------------------------------


    // functions that launch transactions activity and add activity
    //-------------------------------------------------------------------------------
    public void launchTransactionsActivity(View view) {
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }
    public void launchAddActivity(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
    //-------------------------------------------------------------------------------
}
