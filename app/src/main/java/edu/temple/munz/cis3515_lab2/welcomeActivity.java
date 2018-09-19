package edu.temple.munz.cis3515_lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class welcomeActivity extends AppCompatActivity {

    TextView welcomeText;
    Button backButton;

    public final String NAME_KEY = "nmk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //get user's name from the intent in FormActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME_KEY);

        //initialize welcome textView
        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome " + name + "! Thank you for signing up via this form!");

        //back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBackIntent = new Intent(welcomeActivity.this, FormActivity.class);
                startActivity(goBackIntent);

            }
        });

    }
}
