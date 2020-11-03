package com.ryanslack.assignment3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.ryanslack.assignment3.adapters.AllSubjectsAdapter;
import com.ryanslack.assignment3.databasecode.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ShowSubjects extends AppCompatActivity
{
    private Toolbar toolbarAllSubjects;
    private ListView listViewAllSubjects;
    private List<Subject> subjectList;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subjects);

        //Connects to the Database
        db = new DatabaseHelper(this);

        setupIDS();
        setupToolbar();
        setupListView();
    }

    /**
     * Sets the IDs for the View Elements
     */
    private void setupIDS()
    {
        toolbarAllSubjects = findViewById(R.id.toolbarAllSubjects);
        listViewAllSubjects = findViewById(R.id.listViewAllSubjects);
    }

    /**
     * Sets up the Toolbar
     */
    private void setupToolbar()
    {
        setSupportActionBar(toolbarAllSubjects);
        getSupportActionBar().setTitle("View Subjects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Populates the ListView with all the Subjects
     */
    private void setupListView()
    {
        subjectList = db.getAllSubjects();
        ArrayList<Subject> newSubjectList = new ArrayList<Subject>();
        for(int i = 0; i < subjectList.size(); i++)
        {
            if(newSubjectList.isEmpty())
            {
                newSubjectList.add(subjectList.get(i));
            }
            else
            {
                for(int j = 0; j < newSubjectList.size(); j++)
                {
                    if(!newSubjectList.get(j).compareName(subjectList.get(i).getName()))
                    {
                        newSubjectList.add(subjectList.get(i));
                    }
                }
            }
        }
        if(newSubjectList.isEmpty())
        {
            newSubjectList.add(new Subject("No Subjects Available", "", "Monday"));
        }

        AllSubjectsAdapter adapter = new AllSubjectsAdapter(this, newSubjectList);
        listViewAllSubjects.setAdapter(adapter);
    }

    /**
     * Sets the Arrow in the Toolbar to go to the previous screen
     * @param item
     * @return
     */
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