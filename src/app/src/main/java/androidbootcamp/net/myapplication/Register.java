package androidbootcamp.net.myapplication;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidbootcamp.net.myapplication.Databases.DatabaseUser;
import androidbootcamp.net.myapplication.Validation.StringValidation;


public class Register extends AppCompatActivity{
    DatabaseUser databaseUser;
    StringValidation stringValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseUser= new DatabaseUser(this);
        stringValidation=new StringValidation();

        final EditText etName=(EditText) findViewById(R.id.etName);
        final EditText etPassword=(EditText) findViewById(R.id.etPassword);
        final EditText etUsername=(EditText) findViewById(R.id.etUsername);
        final Button bRegister=(Button) findViewById(R.id.bRegister);

        Toast.makeText(getApplicationContext(),
                stringValidation.inValidUsernameMessage(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),
                stringValidation.inValidPasswordMessage(), Toast.LENGTH_LONG).show();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name= etName.getText().toString();
                final String username= etUsername.getText().toString();
                final String password= etPassword.getText().toString();
                boolean Validation=true;

                if(!stringValidation.isValidUsername(username))
                {
                    Toast.makeText(getApplicationContext(),
                            stringValidation.inValidUsernameMessage(), Toast.LENGTH_LONG).show();
                }
                else if(!stringValidation.isValidPassword(password))
                {
                    Toast.makeText(getApplicationContext(),
                            stringValidation.inValidPasswordMessage(), Toast.LENGTH_LONG).show();
                }
                else if(name.length()>30||name.length()<2)
                {
                    Toast.makeText(getApplicationContext(),
                            "Name must contain more than 2 and less than 30 characters!!!", Toast.LENGTH_SHORT).show();
                }else{
                    if(databaseUser.insertData(username,password,name))
                    {
                        Toast.makeText(getApplicationContext(),
                                "Successfully creating new user's account ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, MainMenu.class));
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Fail to create user account!!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

}
