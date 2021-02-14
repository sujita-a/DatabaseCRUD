package edu.tu.databaseinsertupdatedel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "IDU.db";
    public static final String TABLE_NAME = "IDU";
    public static final String TAG = DatabaseHelper.class.getName();

    //COLUMNS
    public static final String COLS_ID = "ID";
    public static final String COLS_NAME = "name";
    public static final String COLS_ADDRESS = "address";
    public static final String COLS_PHONE = "phone";
    public static final String COLS_EMAIL = "email";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "  + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, phone TEXT, email TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
    public void insertData (String name, String address, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLS_NAME, name);
        cv.put(DatabaseHelper.COLS_ADDRESS, address);
        cv.put(DatabaseHelper.COLS_PHONE, phone);
        cv.put(DatabaseHelper.COLS_EMAIL, email);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null,cv);
        Log.d(TAG, "insertData: index " +id);
    }

    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT ID, name, address, phone, email FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLS_ID)));
            Log.d(TAG, "GetUsers: ID " + cursor.getColumnIndex(COLS_ID));
            user.put("name",cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLS_NAME)));
            user.put("address",cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLS_ADDRESS)));
            user.put("phone",cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLS_PHONE)));
            user.put("email",cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLS_EMAIL)));
            userList.add(user);
        }
        return  userList;
    }

    public boolean deleteData (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLS_NAME + "=?", new String[]{name}) > 0;
    }

    public boolean updateData(String name, String address, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLS_NAME, name);
        cv.put(DatabaseHelper.COLS_ADDRESS, address);
        cv.put(DatabaseHelper.COLS_PHONE, phone);
        cv.put(DatabaseHelper.COLS_EMAIL, email);
        //String id = _txtID.getText().toString();
        return db.update(DatabaseHelper.TABLE_NAME, cv, DatabaseHelper.COLS_NAME + "=?", new String[]{name})>0;
    }
}
