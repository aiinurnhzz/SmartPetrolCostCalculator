package com.example.petrolcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerPetrol;
    EditText editPrice, editUsage;
    RadioButton radioYes;
    Button buttonCalculate, buttonAbout;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerPetrol = findViewById(R.id.spinnerPetrol);
        editPrice = findViewById(R.id.editPrice);
        editUsage = findViewById(R.id.editUsage);
        radioYes = findViewById(R.id.radioYes);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonAbout = findViewById(R.id.buttonAbout);
        textResult = findViewById(R.id.textResult);

        String[] petrolTypes = {"Select Petrol Type ▼", "RON95", "RON97", "Diesel"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                petrolTypes
        ) {
            @Override
            public View getView(int position, View convertView,
                                android.view.ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView) view;

                text.setTextColor(android.graphics.Color.parseColor("#666666"));

                return view;
            }
        };

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinnerPetrol.setAdapter(adapter);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editPrice.getText().toString().isEmpty() ||
                        editUsage.getText().toString().isEmpty()) {

                    textResult.setText("Please enter petrol price and fuel usage.");
                    return;
                }

                String petrolType = spinnerPetrol.getSelectedItem().toString();

                double price = Double.parseDouble(editPrice.getText().toString());
                double usage = Double.parseDouble(editUsage.getText().toString());

                double totalCost = price * usage;

                double rebate = 0;

                if (petrolType.equals("RON95") && radioYes.isChecked()) {
                    rebate = usage * 1.99;
                }

                double finalAmount = totalCost - rebate;

                textResult.setText(
                        "Total Petrol Cost: RM " + String.format("%.2f", totalCost)
                                + "\nBUDI Rebate: RM " + String.format("%.2f", rebate)
                                + "\nFinal Payable Amount: RM " + String.format("%.2f", finalAmount)
                );
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });
    }
}