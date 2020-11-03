package com.ryanslack.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    public static SharedPreferences sharedPreferences;
    public static String daySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_main);

        setupIDs();
        setupToolbar();
        setupListView();
    }

    /**
     * Sets the IDs for the Views
     */
    private void setupIDs()
    {
        toolbarTt = findViewById(R.id.toolbarTtMain);
        listViewTt = findViewById(R.id.listViewTtMain);
        sharedPreferences = getSharedPreferences("daySelected", MODE_PRIVATE);
    }

    /**
     * Sets up the Toolbar
     */
    private void setupToolbar()
    {
        setSupportActionBar(toolbarTt);
        getSupportActionBar().setTitle("View Timetable");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Populates the ListView with the Days of the week and assigns an on click for each day
     */
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
                    case 0: //Monday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Monday").apply();
                        break;
                    }
                    case 1: //Tuesday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Tuesday").apply();
                        break;
                    }
                    case 2: //Wednesday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Wednesday").apply();
                        break;
                    }
                    case 3: //Thursday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Thursday").apply();
                        break;
                    }
                    case 4: //Friday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Friday").apply();
                        break;
                    }
                    case 5: //Saturday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Saturday").apply();
                        break;
                    }
                    case 6: //Sunday
                    {
                        startActivity(new Intent(TimetableMain.this, TimetableDay.class));
                        sharedPreferences.edit().putString(daySelected, "Sunday").apply();
                        break;
                    }
                    default: break;
                }
            }
        });
    }

    /**
     * Sets the arrow in the toolbar to go to previous screen
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