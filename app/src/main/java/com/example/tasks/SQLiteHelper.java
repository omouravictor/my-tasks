package com.example.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tb_tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SLA_DATE = "sla_date";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_SLA_DATE + " TEXT" + ")");
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long createTask(TaskModel task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, task.getName());
        cv.put(COLUMN_SLA_DATE, task.getSlaDate());

        long result = db.insert(TABLE_NAME, null, cv);
        db.close();

        return result;
    }

    public long updateTask(TaskModel updatedTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, updatedTask.getName());
        cv.put(COLUMN_SLA_DATE, updatedTask.getSlaDate());

        long result = db.update(TABLE_NAME, cv, "id=" + updatedTask.getId(), null);
        db.close();

        return result;
    }

    public long deleteTask(TaskModel taskToBeDeleted) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "id=" + taskToBeDeleted.getId(), null);
        db.close();

        return result;
    }

    public void deleteAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public ArrayList<TaskModel> getAllTasks() {

        ArrayList<TaskModel> allTasks = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        if (db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                TaskModel task = new TaskModel(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                allTasks.add(task);
            }
            cursor.close();
        }

        return allTasks;
    }
}
