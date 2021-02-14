package edu.tu.databaseinsertupdatedel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button _btnInsert, _btnDelete, _btnUpdate;
    EditText _txtID, _txtName, _txtAdd, _txtPhone, _txtEmail;
    SQLiteOpenHelper openHelper;
    Intent intent;
    DatabaseHelper dh = new DatabaseHelper(MainActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnInsert = (Button) findViewById(R.id.btnInsert);
        _btnDelete = (Button) findViewById(R.id.btnDlt);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _txtID = (EditText) findViewById(R.id.txtId);
        _txtName = (EditText) findViewById(R.id.txtName);
        _txtAdd = (EditText) findViewById(R.id.txtAddress);
        _txtPhone = (EditText) findViewById(R.id.txtPhone);
        _txtEmail = (EditText) findViewById(R.id.txtEmail);
        final DatabaseHelper dh = new DatabaseHelper(MainActivity.this);
        openHelper = new DatabaseHelper(this);
        _btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = _txtName.getText().toString();
                String address = _txtAdd.getText().toString();
                String phone = _txtPhone.getText().toString();
                String email = _txtEmail.getText().toString();
                //db = openHelper.getWritableDatabase();

                dh.insertData(name, address, phone, email);
                callNextPage();
                Toast.makeText(getApplicationContext(), "INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            }
        });

        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db = openHelper.getWritableDatabase();
                String name = _txtName.getText().toString();

                dh.deleteData(name);
                callNextPage();
                Toast.makeText(getApplicationContext(), "DELETED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });

        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = _txtName.getText().toString();
                String address = _txtAdd.getText().toString();
                String phone = _txtPhone.getText().toString();
                String email = _txtEmail.getText().toString();
                //db = openHelper.getWritableDatabase();
                dh.updateData(name, address, phone, email);
                callNextPage();
                Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void callNextPage(){
        intent = new Intent(MainActivity.this,DetailsActivity.class);
        startActivity(intent);
    }


}