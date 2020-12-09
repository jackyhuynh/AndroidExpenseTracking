package androidbootcamp.net.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidbootcamp.net.myapplication.Model.MyListView;


public class MainMenu extends AppCompatActivity {
    ListView list;

    String[] title ={
            "VIEW EXPENSE LIST",
            "SEARCH BY NAME",
            "ADD NEW ITEM",
            "EDIT/DELETE BY LIST",
            "EDIT USER PROFILE"
    };

    //Array of sub-title for the
    String[] sub_title ={
            "View the Total Expenses",
            "Search an Invoice by Name",
            "Add new Item to List",
            "Edit or Delete an Invoice",
            "Edit User Profile"
    };

    //Array of image for the list view
    Integer[] images={
            R.drawable.ic_description_black_24dp,
            R.drawable.ic_search_black_24dp,
            R.drawable.ic_add_black_24dp,
            R.drawable.ic_edit_black_24dp,
            R.drawable.ic_verified_user_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        

        //Declare variable here.
        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        MyListView adapter=new MyListView(this, title, sub_title,images);        // Initialize my list view
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        //Assign task to button help.
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Toast.makeText(getApplicationContext(),
                        "Please go to our website to get more help!!!",
                        Toast.LENGTH_LONG).show();
            }
        });

        //Get the user option here.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //At position 0, call method start Activity to start
                if(position == 0) {
                    // List Data
                    startActivity(new Intent(MainMenu.this, ListDataActivity.class));
                }
                else if(position == 1) {
                    startActivity(new Intent(MainMenu.this, SearchActivity.class));
                }
                else if(position == 2) {
                    //Add new Data
                    startActivity(new Intent(MainMenu.this, AddDataActivity.class));
                }
                else if(position == 3) {
                    startActivity(new Intent(MainMenu.this, EditViewActivity.class));
                }
                else if(position == 4) {
                    Toast.makeText(getApplicationContext(),
                            "App are temporary NOT WORKING due to maintain." +
                                    " Please try again later!!!",Toast.LENGTH_SHORT).show();

                    //startActivity(new Intent(MainMenu.this, ChartView.class));
                }

            }
        });
    }

}
