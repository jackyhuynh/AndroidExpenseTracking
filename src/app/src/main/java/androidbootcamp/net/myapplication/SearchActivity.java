package androidbootcamp.net.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidbootcamp.net.myapplication.Databases.DatabaseHelper;
import androidbootcamp.net.myapplication.Model.Expense;

public class SearchActivity extends AppCompatActivity {
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Button btnSearch=(Button)findViewById(R.id.btnSearch);
        final EditText SearchText= (EditText)findViewById(R.id.Search_item);
        final TextView toastMessage= (TextView)findViewById(R.id.SearchReturn) ;

        // Create new Database
        database= new DatabaseHelper(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = SearchText.getText().toString();
                if (SearchText.length() != 0 ) {
                    //Set Text

                    List<Expense> NameList = database.getExpenseByName(newEntry);
                    String Display="Invoice is not exist in our system!!!";
                    int i=0;
                    if(NameList.size()>0)
                    {
                        Display="";
                        while(!NameList.isEmpty()&& i<NameList.size()) {

                            Display+=NameList.get(i).getItem()+
                                    ("\r\n");
                            i++;
                        }
                        SearchText.setText("");
                    }
                    Toast.makeText(getApplicationContext(),
                            "Click on the result to edit\r\n" +
                                    "Can Only Process One Result at a time!!!",Toast.LENGTH_SHORT).show();
                    toastMessage.setText(Display);

                } else {
                    toastMessage.setText("You must put something in the text field!");
                }

            }
        });


        toastMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start Edit Activity
                String S= (String) toastMessage.getText();
                if ( S.length()>=15)
                {
                    Toast.makeText(getApplicationContext(),
                            "Can Only Process One Result at a time!!!\r\n" +
                                    "Please make another search with correct keywords",Toast.LENGTH_SHORT).show();
                }else
                {
                    String name =S.replace("\r\n","");
                    Cursor data = database.getItemID(name); //get the id associated with that name
                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);
                    }
                    if(itemID > -1){
                        //Start Edit Activity
                        Intent editScreenIntent = new Intent(SearchActivity.this, EditDataActivity.class);
                        editScreenIntent.putExtra("id",itemID);
                        editScreenIntent.putExtra("name",name);
                        startActivity(editScreenIntent);
                    }
                    else{
                        toastMessage.setText("No ID associated with that name");
                    }
                }

            }
        });
    }



}
