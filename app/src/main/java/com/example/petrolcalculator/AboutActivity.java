package com.example.petrolcalculator;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

        Button buttonBack;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_about);
                buttonBack = findViewById(R.id.buttonBack);
                buttonBack.setOnClickListener(v -> finish());

                TextView github = findViewById(R.id.textGithub);
                github.setMovementMethod(LinkMovementMethod.getInstance());
        }
}