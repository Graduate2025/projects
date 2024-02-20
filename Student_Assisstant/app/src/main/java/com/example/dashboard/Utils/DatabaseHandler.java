package com.example.dashboard.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dashboard.Model.toDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String USER = "user";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT, "
            + STATUS + " INTEGER, " + USER + " TEXT)" ;

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(toDoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        cv.put(USER, task.getUser());
        db.insert(TODO_TABLE, null, cv);
    }

    public List<toDoModel> getAllTasks(String user){
        List<toDoModel> taskList = new ArrayList<>();
        db.beginTransaction();
        Cursor cur = db.rawQuery("select * from todo where user = '" + user + "'",null);
        while (cur.moveToNext()){
            toDoModel to = new toDoModel();
            to.setId(cur.getInt(cur.getColumnIndex(ID)));
            to.setTask(cur.getString(cur.getColumnIndex(TASK)));
            to.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
            to.setUser(cur.getString(cur.getColumnIndex(USER)));
            taskList.add(to);
        }
        db.endTransaction();
        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}