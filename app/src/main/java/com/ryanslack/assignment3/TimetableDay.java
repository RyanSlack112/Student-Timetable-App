package com.ryanslack.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.ryanslack.assignment3.adapters.DaySubjectAdapter;
import com.ryanslack.assignment3.databasecode.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TimetableDay extends AppCompatActivity
{
    DatabaseHelper db;
    private ListView listViewDay;
    private Toolbar toolbarDay;
    public static ArrayList<Subject> listMonday;
    public static ArrayList<Subject> listTuesday;
    public static ArrayList<Subject> listWednesday;
    public static ArrayList<Subject> listThursday;
    public static ArrayList<Subject> listFriday;
    public static ArrayList<Subject> listSaturday;
    public static ArrayList<Subject> listSunday;

    private ArrayList<Subject> preferredDay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_day);

        db = new DatabaseHelper(this);

        setupIDs();
        setupToolbar();
        setupArrays();
        setupListView();
    }

    /**
     * Sets the IDs for all the view items
     */
    private void setupIDs()
    {
        listViewDay = findViewById(R.id.listViewTtDay);
        toolbarDay = findViewById(R.id.toolbarTtDay);
    }

    /**
     * Sets up the Toolbar
     */
    private void setupToolbar()
    {
        setSupportActionBar(toolbarDay);
        getSupportActionBar().setTitle(TimetableMain.sharedPreferences.getString(TimetableMain.daySelected, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Sets up and populates the ArrayLists used for populating the Timetable
     */
    private void setupArrays()
    {
        //Array Lists
        listMonday = new ArrayList<Subject>();
        listTuesday = new ArrayList<Subject>();
        listWednesday = new ArrayList<Subject>();
        listThursday = new ArrayList<Subject>();
        listFriday = new ArrayList<Subject>();
        listSaturday = new ArrayList<Subject>();
        listSunday = new ArrayList<Subject>();
        preferredDay = new ArrayList<Subject>();

        //Gets all the Subjects from Database
        List<Subject> allSubjects = db.getAllSubjects();

        for(int i = 0; i < allSubjects.size(); i++)
        {
            Subject subject = allSubjects.get(i);
            String subjectDay = subject.getDay();
            switch(subjectDay.toLowerCase())
            {
                case "monday": //Monday Array
                    listMonday.add(subject);
                    break;
                case "tuesday": //Tuesday Array
                    listTuesday.add(subject);
                    break;
                case "wednesday": //Wednesday Array
                    listWednesday.add(subject);
                    break;
                case "thursday": //Thursday Array
                    listThursday.add(subject);
                    break;
                case "friday": //Friday Array
                    listFriday.add(subject);
                    break;
                case "saturday": //Saturday Array
                    listSaturday.add(subject);
                    break;
                case "sunday": //Sunday Array
                    listSunday.add(subject);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Populates the ListView with the contents of the selected days subjects
     */
    private void setupListView()
    {
        String daySelected = TimetableMain.sharedPreferences.getString(TimetableMain.daySelected, null);

        switch(daySelected.toLowerCase())
        {
            case "monday": //Monday Results
                if(listMonday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Monday"));
                }
                else {
                    preferredDay = listMonday;
                }
                break;
            case "tuesday": //Tuesday Results
                if(listTuesday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Tuesday"));
                }
                else {
                    preferredDay = listTuesday;
                }
                break;
            case "wednesday": //Wednesday Results
                if(listWednesday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Wednesday"));
                }
                else {
                    preferredDay = listWednesday;
                }
                break;
            case "thursday": //Thursday Results
                if(listThursday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Thursday"));
                }
                else {
                    preferredDay = listThursday;
                }
                break;
            case "friday": //Friday Results
                if(listFriday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Friday"));
                }
                else {
                    preferredDay = listFriday;
                }
                break;
            case "saturday": //Saturday Results
                if(listSaturday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Saturday"));
                }
                else {
                    preferredDay = listSaturday;
                }
                break;
            case "sunday": //Sunday Results
                if(listSunday.isEmpty())
                {
                    //If it's empty says no subjects are available
                    preferredDay.add(new Subject("No Subjects Available", "", "Sunday"));
                }
                else {
                    preferredDay = listSunday;
                }
                break;
            default:
                break;
        }
        DaySubjectAdapter dsAdapter = new DaySubjectAdapter(this, preferredDay);
        listViewDay.setAdapter(dsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
            {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}