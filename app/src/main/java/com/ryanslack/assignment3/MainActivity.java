package com.ryanslack.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private ListView listviewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupIDs();
        setupToolbar();
        setupListView();
    }

    private void setupIDs()
    {
        toolbarMain = findViewById(R.id.toolbarMainMenu);
        listviewMain = findViewById(R.id.listViewMainMenu);
    }

    private void setupToolbar()
    {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Student Timetable");
    }

    private void setupListView()
    {
        String[] titles = getResources().getStringArray(R.array.main_menu_title_array);
        String[] descriptions = getResources().getStringArray(R.array.main_menu_desc_array);

        ListAdapter listAdapter = new ListAdapter(this, titles, descriptions);
        listviewMain.setAdapter(listAdapter);
    }
}