package com.myapps.and.flights;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Efrat on 3/3/2017.
 */

public class Flight implements Parcelable{

    String country;
    String city;
    float  price;
    String currency;

    public Flight(String country, String city, float price, String currency) {
        this.country = country;
        this.city = city;
        this.price = price;
        this.currency = currency;
    }

    protected Flight(Parcel in) {
        country = in.readString();
        city = in.readString();
        price = in.readFloat();
        currency = in.readString();
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(city);
        dest.writeFloat(price);
        dest.writeString(currency);
    }
}
