package edu.temple.munz.cis3515_lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {


    TextView nameInput, emailInput, passwordInput, passwordInput2;
    Button saveButton;

    public final String ERROR_EMPTY_FIELD = "Please fill out all fields.";
    public final String ERROR_PASSWORD_MISMATCH = "Passwords must match.";
    public final int REQUEST_CODE = 117;

    public final String NAME_KEY = "nmk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //initialize the 4 text fields & button
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput2 = findViewById(R.id.passwordInput2);
        saveButton = findViewById(R.id.saveButton);


        //define onClick activity for Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user data from the form
                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String password2 = passwordInput2.getText().toString();

                //check if any fields are blank
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                    Toast.makeText(FormActivity.this, ERROR_EMPTY_FIELD, Toast.LENGTH_LONG).show();
                }
                //check if passwords match
                else if(!password.equals(password2)) {
                    Toast.makeText(FormActivity.this, ERROR_PASSWORD_MISMATCH, Toast.LENGTH_LONG).show();
                }
                //user input is good, send it to the welcome page
                else {
                    //create Intent
                    Intent startActivityIntent = new Intent(FormActivity.this, welcomeActivity.class);
                    //pass in the user's name to the intent
                    startActivityIntent.putExtra(NAME_KEY, name);
                    //start welcomeActivity
                    startActivityForResult(startActivityIntent, REQUEST_CODE);
                }

            }
        });



    }
}
