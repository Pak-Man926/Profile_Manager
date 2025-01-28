package app.profile.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.homepage);

        TextView profileUsername = findViewById(R.id.profileUsername); // TextView to display username
        DatabaseHandler dbHandler = new DatabaseHandler(this);

        // Simulate logged-in username (this would come from your login logic)
        String loggedInUsername = getIntent().getStringExtra("loggedInUsername");;

        // Fetch username from database
        String username = dbHandler.getUsername(loggedInUsername);

        // Display username in TextView
        if (username != null) {
            profileUsername.setText("Welcome, " + username);
        } else {
            profileUsername.setText("Error: Username not found.");
        }

    }

    public void showPopup(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();
    }

    public void OnMenuItemClick(MenuItem item)
    {
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
