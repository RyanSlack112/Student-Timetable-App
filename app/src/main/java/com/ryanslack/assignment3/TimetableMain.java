package com.ryanslack.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.ryanslack.assignment3.adapters.WeeksAdapter;

public class TimetableMain extends AppCompatActivity
{
    private Toolbar toolbarTt;
    private ListView listViewTt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_main);

        setupIDs();
        setupToolbar();
        setupListView();
    }

    private void setupIDs()
    {
        toolbarTt = findViewById(R.id.toolbarTtMain);
        listViewTt = findViewById(R.id.listViewTtMain);
    }

    private void setupToolbar()
    {
        setSupportActionBar(toolbarTt);
        getSupportActionBar().setTitle("View Timetable");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView()
    {
        String[] days = getResources().getStringArray(R.array.timetable_days_array);
        WeeksAdapter weeksAdapter = new WeeksAdapter(this, R.layout.activity_timetable_main_card_item, days);
        listViewTt.setAdapter(weeksAdapter);
        listViewTt.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch(i)
                {
                    case 0: break;
                    case 1: break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                    case 6: break;
                    default: break;
                }
            }
        });
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