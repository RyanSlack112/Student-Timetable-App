package com.ryanslack.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.ryanslack.assignment3.adapters.ListAdapter;

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
        getSupportActionBar().setTitle("View Timetable");
    }

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
                    case 0:
                    {
                        Intent intent = new Intent(MainActivity.this, TimetableMain.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: break;
                    case 2: break;
                }
            }
        });
    }
}