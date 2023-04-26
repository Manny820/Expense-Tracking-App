package com.example.expensetrackingapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Transaction implements Parcelable {
    int Image;
    String Amount;
    String Name;
    String Date;
    String Category;
    String Description;

    public Transaction(int image, String amount, String name, String date ,String category, String description) {
        Image = image;
        Amount = amount;
        Name = name;
        Date = date;
        Category = category;
        Description = description;
    }

    protected Transaction(Parcel in) {
        Image = in.readInt();
        Amount = in.readString();
        Name = in.readString();
        Date = in.readString();
        Category = in.readString();
        Description = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(Image);
        parcel.writeString(Amount);
        parcel.writeString(Name);
        parcel.writeString(Date);
        parcel.writeString(Category);
        parcel.writeString(Description);
    }
}


