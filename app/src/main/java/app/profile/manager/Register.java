package app.profile.manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity
{
private EditText txtFname, txtLname, txtUsername, txtPassword;
private Button buttonRegister;
private DatabaseHandler dbHandler;

@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);

    // Initialize DatabaseHelper
    dbHandler = new DatabaseHandler(this);

    // Connect Views
    txtFname = findViewById(R.id.txtFname);
    txtLname = findViewById(R.id.txtLname);
    txtUsername = findViewById(R.id.txtUsername);
    txtPassword = findViewById(R.id.txtPassword);
    buttonRegister = findViewById(R.id.buttonRegister);

    // Set OnClickListener for Register Button
    buttonRegister.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            registerUser();
        }
    });
}

private void registerUser() {
    // Get input values
    String firstName = txtFname.getText().toString().trim();
    String lastName = txtLname.getText().toString().trim();
    String username = txtUsername.getText().toString().trim();
    String password = txtPassword.getText().toString().trim();

    // Validate inputs
    if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
            TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        return;
    }

    // Insert user into database
    boolean isInserted = dbHandler.registerUsers(firstName, lastName, username, password);

    if (isInserted) {
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        // Redirect to LoginActivity
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish(); // Close the current activity
    } else {
        Toast.makeText(this, "Registration Failed. Username might already exist.", Toast.LENGTH_SHORT).show();
    }
}
}