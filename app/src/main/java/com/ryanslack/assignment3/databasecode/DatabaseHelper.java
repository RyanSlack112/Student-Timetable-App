package com.ryanslack.assignment3.databasecode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ryanslack.assignment3.Subject;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "subjectsManager.db";
    private static final String TABLE_SUBJECTS = "subjects";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_DAY = "day";

    /**
     * Constructor
     * @param context
     */
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_SUBJECTS_TABLE = "CREATE TABLE " + TABLE_SUBJECTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TIME + " TEXT," + KEY_DAY + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_SUBJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
    }

    /**
     * Adds a Subject
     * @param subject
     */
    public void addSubject(Subject subject)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subject.getName()); // Subject Name
        values.put(KEY_TIME, subject.getTime()); // Subject Time
        values.put(KEY_DAY, subject.getDay());   // Subject Day

        // Inserting Row
        db.insert(TABLE_SUBJECTS, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Returns a Subject
     * @param id
     * @return
     */
    public Subject getSubject(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECTS, new String[]
                        {
                                KEY_ID, KEY_NAME, KEY_TIME, KEY_DAY
                        },
                KEY_ID + "=?",
                new String[]
                        {
                                String.valueOf(id)
                        },
                null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        Subject subject = new Subject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return subject;
    }

    /**
     * Returns all the subjects to a list
     * @return
     */
    public List<Subject> getAllSubjects()
    {
        List<Subject> subjectList = new ArrayList<Subject>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECTS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Subject subject = new Subject();
                subject.setID(Integer.parseInt(cursor.getString(0)));
                subject.setName(cursor.getString(1));
                subject.setTime(cursor.getString(3));
                subject.setDay(cursor.getString(4));

                // Adding contact to list
                subjectList.add(subject);
            }
            while (cursor.moveToNext());
        }

        return subjectList;
    }

    /**
     * Updates the values within the subject
     * @param subject
     * @return
     */
    public int updateSubject(Subject subject)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subject.getName());
        values.put(KEY_TIME, subject.getTime());
        values.put(KEY_DAY, subject.getDay());

        // updating row
        return db.update(TABLE_SUBJECTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(subject.getID()) });
    }

    /**
     * Deletes a subject by it's Name
     * @param subject
     */
    public void deleteSubject(Subject subject)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECTS, KEY_NAME + " = ?",
                new String[] { String.valueOf(subject.getName()) });
        db.close();
    }

    /**
     * Deletes a subject by it's ID
     * @param subject
     */
    public void deleteSubjectDay(Subject subject)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECTS, KEY_ID + " = ?",
                new String[] { String.valueOf(subject.getID()) });
        db.close();
    }

    /**
     * Gets the amount of Subjects in the database
     * @return
     */
    public int getSubjectsCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_SUBJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}
