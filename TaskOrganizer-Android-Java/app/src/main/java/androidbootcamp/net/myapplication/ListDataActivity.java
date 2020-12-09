package androidbootcamp.net.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import androidbootcamp.net.myapplication.Databases.DatabaseHelper;

public class ListDataActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        //Declare value
        final Button btnAdd= (Button) findViewById(R.id.btnAdd);
        mListView = (ListView) findViewById(R.id.listView);     //Create List View
        mDatabaseHelper = new DatabaseHelper(this);     //Create Database Helper Object

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };

        //Btn Add to add new data to the database
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDataActivity.this, AddDataActivity.class);
                startActivity(intent);
            }
        });

        //Show the list of item
        populateListView();
    }


    private void populateListView() {

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add("Item:"+fixedLengthString(data.getString(1), 20)+
                     ", Expense: $"+ fixedLengthString(data.getString(2), 15) );
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toastMessage("Go to Edit Tab to edit details");
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }


}

