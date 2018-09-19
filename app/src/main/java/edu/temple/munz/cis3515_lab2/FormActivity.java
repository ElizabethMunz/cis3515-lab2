package edu.temple.munz.cis3515_lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;

public class FormActivity extends AppCompatActivity {


    TextView nameInput, emailInput, passwordInput, passwordInput2, passwordInfo;
    Button saveButton;

    private final String ERROR_EMPTY_FIELD = "Please fill out all fields.";
    private final String ERROR_PASSWORD_MISMATCH = "Passwords must match.";
    private final String ERROR_EMAIL_FORMAT = "Must be a valid email address format.";
    private final int REQUEST_CODE = 117;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final int PASSWORD_MIN_LENGTH = 8;

    private final String NAME_KEY = "nmk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //initialize the text fields & button
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput2 = findViewById(R.id.passwordInput2);
        saveButton = findViewById(R.id.saveButton);
        passwordInfo = findViewById(R.id.passwordInfo);

        //define onClick activity for Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user data from the form, strip leading/trailing spaces from name & email
                String name = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim().toLowerCase();
                String password = passwordInput.getText().toString();
                String password2 = passwordInput2.getText().toString();

                //check if any fields are blank
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                    Toast.makeText(FormActivity.this, ERROR_EMPTY_FIELD, Toast.LENGTH_LONG).show();
                }
                //check email format
                else if (!email.matches(EMAIL_PATTERN)) {
                    Toast.makeText(FormActivity.this, ERROR_EMAIL_FORMAT, Toast.LENGTH_LONG).show();
                }
                //check if passwords match
                else if(!password.equals(password2)) {
                    Toast.makeText(FormActivity.this, ERROR_PASSWORD_MISMATCH, Toast.LENGTH_LONG).show();
                }
                //check password length
                else if (password.length() < PASSWORD_MIN_LENGTH) {
                    Toast.makeText(FormActivity.this, R.string.pass_info, Toast.LENGTH_LONG).show();
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

        //give user a popup warning of password length requirements
        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                //make password length popup appear when you click onto password input, disappear when you click off
                passwordInfo.setVisibility((hasFocus) ? View.VISIBLE : View.INVISIBLE);
            }
        });

    }
}
