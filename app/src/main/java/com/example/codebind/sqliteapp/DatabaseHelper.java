package com.example.codebind.sqliteapp;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";
    public static final String COL_5 = "STARTDATE";
    public static final String COL_6 = "PHONENUMBER";
    public static final String COL_7 = "ENDDATE";
    public static final String COL_8 = "STAFFING";
    public static final String COL_9 = "NUMBERPEOPLE";
    public static final String COL_10 = "TOTALFEE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, "+
                "SURNAME TEXT," +
                "MARKS INTEGER," +
                "STARTDATE TEXT," +
                "PHONENUMBER INT, " +
                "ENDDATE TEXT, " +
                "STAFFING INT, " +
                "NUMBERPEOPLE INT, " +
                "TOTALFEE INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String surname, String marks, String startDate,
                              int phoneNumber, String endDate, int staffing , int numberpeople, int totalFee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        contentValues.put(COL_5,startDate);
        contentValues.put(COL_6,phoneNumber);
        contentValues.put(COL_7,endDate);
        contentValues.put(COL_8,staffing);
        contentValues.put(COL_9,numberpeople);
        contentValues.put(COL_10,totalFee);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getSearchedData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where name like ?",new String[]{"%"+name+"%"});
        return res;
    }

    public Cursor getSortedData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" order by name",null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Integer deleteData () {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "",new String[] {});
    }
}