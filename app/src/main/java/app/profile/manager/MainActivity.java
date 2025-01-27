package app.profile.manager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view)
    {
        Intent intent = new Intent(MainActivity.this, Login.class);

        startActivity(intent);
    }

    public void Register(View view)
    {
        Intent intent2 = new Intent(MainActivity.this, Register.class);

        startActivity(intent2);
    }
}