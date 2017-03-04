package com.myapps.and.flights;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmationActivity extends AppCompatActivity {


    TextView countryTV;
    TextView cityTV;
    TextView priceTV;
    TextView currencyTV;
    String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Button confirmBtn = (Button) findViewById(R.id.saveBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        countryTV = (TextView) findViewById(R.id.countryTV);
        cityTV = (TextView) findViewById(R.id.cityTV);
        priceTV = (TextView) findViewById(R.id.cpriceTV);
        currencyTV = (TextView) findViewById(R.id.ccurrencyTV);


        Intent resIntent=getIntent();
        Flight flight = resIntent.getParcelableExtra("flight");

        countryTV.setText(flight.country);
        cityTV.setText(flight.city);
        priceTV.setText(""+flight.price);
        currencyTV.setText(flight.currency);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ConfirmationActivity.this);

        currency = pref.getString("currency","USD");
        String bgColor = pref.getString("c_bg_color","white");
        LinearLayout confirm_lo = (LinearLayout) findViewById(R.id.activity_confirmation);
        confirm_lo.setBackgroundColor(Color.parseColor(bgColor));
    }
}
