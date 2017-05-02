package com.example.codebind.sqliteapp;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,  editText, editText2, editText3, editText4, editText5, editText6 ;
    Button btnAddData;
    Button buttonViewAll;
    Button btnDelete, btnSort;

    Button buttonSearchByName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_Marks);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.total_fee);
        btnAddData = (Button)findViewById(R.id.button_add);
        buttonViewAll = (Button)findViewById(R.id.button_viewAll);
        buttonSearchByName = (Button) findViewById(R.id.button_search);
        btnSort =(Button) findViewById(R.id.button_sort_by_name);
        btnDelete= (Button)findViewById(R.id.button_delete);
        buttonViewAll.setOnClickListener(this);
        buttonSearchByName.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        AddData();
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows0 = myDb.deleteData();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString(),
                                editText.getText().toString(),
                                Integer.parseInt(editText2.getText().toString()),
                                editText3.getText().toString(),
                                Integer.parseInt(editText4.getText().toString()),
                                Integer.parseInt(editText5.getText().toString()),
                                Integer.parseInt(editText6.getText().toString()));
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    @Override
    public void onClick(View v) {
        Cursor res = null;
        switch(v.getId()){
            case R.id.button_search:
                res = myDb.getSearchedData(((EditText)findViewById(R.id.editText_search_name)).getText().toString());
                break;
            case R.id.button_viewAll:
                res = myDb.getAllData();
                break;
            case R.id.button_sort_by_name:
                res = myDb.getSortedData();
                break;
        }
        if(res.getCount() == 0) {
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        Log.d("myLog","Norm");
        while (res.moveToNext()) {
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Surname :"+ res.getString(2)+"\n");
            buffer.append("Location :"+ res.getString(3)+"\n");
            buffer.append("Start Date:"+ res.getString(4)+"\n");
            buffer.append("Phone Number:"+ res.getInt(5)+"\n");
            buffer.append("End Date :"+ res.getString(6)+"\n");
            buffer.append("Staffing Req :"+ res.getInt(7)+"\n");
            buffer.append("Number People:"+ res.getInt(8)+"\n");
            buffer.append("Total fee :"+ res.getInt(9)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }



    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }





}