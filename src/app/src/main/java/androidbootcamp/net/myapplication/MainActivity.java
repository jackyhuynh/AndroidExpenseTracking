package androidbootcamp.net.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidbootcamp.net.myapplication.Databases.DatabaseUser;


public class MainActivity extends AppCompatActivity{
    DatabaseUser databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseUser= new DatabaseUser(this);

        final EditText etUsername= (EditText) findViewById(R.id.etUsername);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink=(TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin= (Button) findViewById(R.id.bLogin);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get User Input
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                if(databaseUser.VerifyLogin(username,password))
                {
                    Toast.makeText(MainActivity.this,"Username and Password is correct",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainMenu.class));
                }else
                {
                    Toast.makeText(getApplicationContext(),
                            "Wrong Password! " +
                                    "User Name = 'abc' & Password = 'Khong@biet@1987'", Toast.LENGTH_SHORT).show();
                }


            }
        });

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, Register.class));

            }
        });
    }
}
