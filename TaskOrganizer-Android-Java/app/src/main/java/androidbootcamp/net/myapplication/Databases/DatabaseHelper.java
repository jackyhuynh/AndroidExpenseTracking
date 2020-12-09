package androidbootcamp.net.myapplication.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidbootcamp.net.myapplication.Model.Expense;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "Expenses";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "amount";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, "+
                COL3 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, amount);

        Log.d(TAG, "addData: Adding " + name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param ID
     * @return
     */
    public Cursor getName(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + ID + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateDatabase(String newName, int id, String oldName, String newAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName +"' ,"+COL3+" = '" + newAmount  + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);

    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }


    //Function Get All Item information
    public List<Expense> getExpenses()
    {
        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Column table name
        String[] sqSelect={COL1,COL2,COL3};

        qb.setTables(TABLE_NAME);
        Cursor cursor=qb.query(db, sqSelect, null, null, null, null,null);
        List<Expense> result= new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Expense expense= new Expense();
                expense.setId(cursor.getInt(cursor.getColumnIndex(COL1)));
                expense.setItem(cursor.getString(cursor.getColumnIndex(COL2)));
                expense.setAmount(cursor.getString(cursor.getColumnIndex(COL3)));
                result.add(expense);
            }while(cursor.moveToNext());
        }
        return result;
    }

    //Function Get All Item (Item Only)
    public List<String> getItem()
    {
        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Column table name
        String[] sqSelect={COL2};
        qb.setTables(TABLE_NAME);
        Cursor cursor=qb.query(db, sqSelect, null, null, null, null,null);
        List<String> result= new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{
                result.add(cursor.getString(cursor.getColumnIndex(COL2)));
            }while(cursor.moveToNext());
        }
        return result;
    }

    //Get Expense by Name
    public List<Expense> getExpenseByName(String name)
    {
        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Column table name
        String[] sqSelect={COL1,COL2,COL3};

        qb.setTables(TABLE_NAME);
        Cursor cursor=qb.query(db, sqSelect, COL2+" LIKE ?", new String[]{"%"+name+"%"}, null, null,null);
        List<Expense> result= new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Expense expense= new Expense();
                expense.setId(cursor.getInt(cursor.getColumnIndex(COL1)));
                expense.setItem(cursor.getString(cursor.getColumnIndex(COL2)));
                expense.setAmount(cursor.getString(cursor.getColumnIndex(COL3)));

                result.add(expense);

            }while(cursor.moveToNext());
        }
        return result;
    }

    public List<Expense> getExpenseById(int ID)
    {
        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Column table name
        String[] sqSelect={COL1,COL2,COL3};

        qb.setTables(TABLE_NAME);
        Cursor cursor=qb.query(db, sqSelect, COL1+" LIKE ?", new String[]{"%"+ID+"%"}, null, null,null);
        List<Expense> result= new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Expense expense= new Expense();
                expense.setId(cursor.getInt(cursor.getColumnIndex(COL1)));
                expense.setItem(cursor.getString(cursor.getColumnIndex(COL2)));
                expense.setAmount(cursor.getString(cursor.getColumnIndex(COL3)));

                result.add(expense);

            }while(cursor.moveToNext());
        }
        return result;
    }

}










