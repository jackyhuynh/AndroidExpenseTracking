package androidbootcamp.net.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidbootcamp.net.myapplication.Databases.DatabaseHelper;
import androidbootcamp.net.myapplication.R;

public class AddDataActivity extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData, btnSearch, btnSearchName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        //Declare variable
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2=(EditText) findViewById(R.id.editText2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        btnSearchName= (Button) findViewById(R.id.btnSearchName);

        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String amount = editText2.getText().toString();
                if (editText.length() != 0 || editText2.length() != 0) {
                    AddData(newEntry, amount);
                    editText.setText("");
                    editText2.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDataActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });


        btnSearchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDataActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry, String amount) {

        boolean insertData = mDatabaseHelper.addData(newEntry,amount);
        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
