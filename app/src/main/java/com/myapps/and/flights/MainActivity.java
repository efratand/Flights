package com.myapps.and.flights;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText countryET;
    EditText cityET;
    EditText priceET;
    TextView currencyTV;

    String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalizeFields();


        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When clicked initiate call to ConfirmationActivity with intent;

                if ( validValues() ) {
                    Flight flight = new Flight(countryET.getText().toString(),
                            cityET.getText().toString(),
                            Float.parseFloat(priceET.getText().toString()),
                            currencyTV.getText().toString());
                    Intent confirmIntent = new Intent(MainActivity.this,ConfirmationActivity.class);
                    confirmIntent.putExtra("flight",flight);
                    startActivity(confirmIntent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settingsOpt:
                Intent setIntent = new Intent(MainActivity.this,SetPrefSettings.class);
                startActivity(setIntent);
                break;
            case R.id.exitOpt:
                finish();
                break;
        }
        return true;
    }

    private void initalizeFields() {
        countryET = (EditText) findViewById(R.id.countryET);
        cityET = (EditText) findViewById(R.id.cityET);
        priceET = (EditText) findViewById(R.id.priceET);
        currencyTV = (TextView) findViewById(R.id.currencyTV);
    }

    private boolean validValues(){
        // Make sure all fields have valid values;
        if ( countryET.getText().toString().trim().equals("") ) {
            Toast.makeText(this,"Country should not be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if ( cityET.getText().toString().trim().equals("") ) {
            Toast.makeText(this,"City should not be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            Float.parseFloat(priceET.getText().toString());
        } catch (Exception NumberFormatException) {
            Toast.makeText(this,"Enter a valid price",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        currency = pref.getString("currency","USD");
        String bgColor = pref.getString("r_bg_color","white");
        LinearLayout main_lo = (LinearLayout) findViewById(R.id.activity_main);
        main_lo.setBackgroundColor(Color.parseColor(bgColor));

        currencyTV.setText(currency);
    }
}
