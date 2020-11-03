/**
 * Author: Ryan Slack
 * ID: 18037676
 * Subject: Mobile Application Development
 * Assignment: Assignment 3
 * Description: A simple timetable app for keeping track of classes and times
 */

package com.ryanslack.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.ryanslack.assignment3.adapters.ListAdapter;

public class MainActivity extends AppCompatActivity
{

    private Toolbar toolbarMain;
    private ListView listviewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupIDs();
        setupToolbar();
        setupListView();
    }

    /**
     * Sets the ID of the View elements
     */
    private void setupIDs()
    {
        toolbarMain = findViewById(R.id.toolbarMainMenu);
        listviewMain = findViewById(R.id.listViewMainMenu);
    }

    /**
     * Sets up the Toolbar
     */
    private void setupToolbar()
    {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Student Timetable");
    }

    /**
     * Populates the ListView with the options
     */
    private void setupListView()
    {
        String[] titles = getResources().getStringArray(R.array.main_menu_title_array);
        String[] descriptions = getResources().getStringArray(R.array.main_menu_desc_array);

        ListAdapter listAdapter = new ListAdapter(this, titles, descriptions);
        listviewMain.setAdapter(listAdapter);

        listviewMain.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch(i)
                {
                    case 0: //View Timetable
                    {
                        Intent intent = new Intent(MainActivity.this, TimetableMain.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: //View Subjects
                    {
                        Intent intent = new Intent(MainActivity.this, ShowSubjects.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: //Add Subject
                    {
                        Intent intent = new Intent(MainActivity.this, AddClass.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }
}