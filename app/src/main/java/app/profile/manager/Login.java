package app.profile.manager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity
{
    private EditText txtUsername, txtPassword2;
    private Button buttonLogin;
    private DatabaseHandler  dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.login);

        dbHandler =new DatabaseHandler(this);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword2 = findViewById(R.id.txtPassword2);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginUser();
            }
        });
    }
    private void loginUser() {
        // Get input values
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword2.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check credentials in the database
        boolean isAuthenticated = dbHandler.checkUsers(username, password);

        if (isAuthenticated) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            // Navigate to the next activity (e.g., HomeActivity)
            Intent intent = new Intent(Login.this, HomePage.class);
            intent.putExtra("loggedInUsername", username);
            startActivity(intent);
            finish(); // Close the current activity
        } else {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}


