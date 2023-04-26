package com.example.expensetrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    EditText name,date,amount,description;
    TextView category;
    ArrayList<Transaction> arrayList;
    String categoryStr;
    int imageTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.editTextName);
        date = findViewById(R.id.editTextDate);
        amount = findViewById(R.id.editTextAmount);
        category = findViewById(R.id.editTextCategory);
        description = findViewById(R.id.editTextDescription);
        arrayList = PrefConfig.read(this);
        Button save = findViewById(R.id.saveButton);

        if(arrayList == null)
            arrayList = new ArrayList<>();

        // used to reset the arraylist and delete all members
        //arrayList.removeAll(arrayList);


        // Functionality for Save Button
        //-------------------------------------------------------------------------------
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction transaction = new Transaction(imageTemp,"$"+amount.getText().toString(),name.getText().toString(),
                        date.getText().toString(),categoryStr,description.getText().toString());

                arrayList.add(transaction);
                PrefConfig.write(getApplicationContext(),arrayList);
                Toast.makeText(AddActivity.this,"Transaction Saved", Toast.LENGTH_LONG).show();
            }
        });
        //-------------------------------------------------------------------------------


        // This method creates the popup menu for the category section
        //-------------------------------------------------------------------------------
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(AddActivity.this, view);
                popupMenu.setOnMenuItemClickListener(AddActivity.this);
                popupMenu.inflate(R.menu.popup_category_menu);
                popupMenu.show();
            }
        });
        //-------------------------------------------------------------------------------
    }


    // This function adds image and category accordingly to selection by user
    //-------------------------------------------------------------------------------
    public boolean onMenuItemClick(MenuItem item){
        Toast.makeText(this, "Selected Item: " +item.getTitle(),Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.income_item:
                categoryStr = "Income";
                imageTemp = R.drawable.income;
                category.setHint("Income");
                return true;

            case R.id.bills_item:
                categoryStr = "Bills";
                imageTemp = R.drawable.bills;
                category.setHint("Bills");
                return true;

            case R.id.donation_item:
                categoryStr = "Donations";
                imageTemp = R.drawable.donations;
                category.setHint("Donations");
                return true;

            case R.id.entertainment_item:
                categoryStr = "Entertainment";
                imageTemp = R.drawable.entertainment;
                category.setHint("Entertainment");
                return true;

            case R.id.food_item:
                categoryStr = "Food";
                imageTemp = R.drawable.food;
                category.setHint("Food");
                return true;

            case R.id.shopping_item:
                categoryStr = "Shopping";
                imageTemp = R.drawable.shopping;
                category.setHint("Shopping");
                return true;

            case R.id.housing_item:
                categoryStr = "Housing";
                imageTemp = R.drawable.housing;
                category.setHint("Housing");
                return true;

            case R.id.transportation_item:
                categoryStr = "Transportation";
                imageTemp = R.drawable.transportation;
                category.setHint("Transportation");
                return true;

            case R.id.other_item:
                categoryStr = "Other";
                imageTemp = R.drawable.other;
                category.setHint("Other");
                return true;

            default:
                return false;
            }
        }
    //-------------------------------------------------------------------------------


    // functions that launch transactions activity and categories activity
    //-------------------------------------------------------------------------------
    public void launchTransactionsActivity(View view) {
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }
    public void launchCategoriesActivity(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
    //-------------------------------------------------------------------------------
}

