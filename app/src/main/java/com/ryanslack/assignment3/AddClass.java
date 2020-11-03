package com.ryanslack.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ryanslack.assignment3.databasecode.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class AddClass extends AppCompatActivity
{
    private Toolbar toolbarAddClass;
    private EditText editTextClassName;
    private EditText editTextClassRoom;
    private Spinner spinnerClassTimeStart;
    private Spinner spinnerClassTimeMinStart;
    private Spinner spinnerClassTimeEnd;
    private Spinner spinnerClassTimeMinEnd;
    private Spinner spinnerClassTimeAmPmStart;
    private Spinner spinnerClassTimeAmPmEnd;
    private Spinner spinnerClassDay;
    private Button btnSubmit;
    private DatabaseHelper db;
    private String className;
    private String classTimeStartHour;
    private String classTimeStartMin;
    private String classTimeStartAmPm;
    private String classTimeEndHour;
    private String classTimeEndMin;
    private String classTimeEndAmPm;
    private String classDay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        //Connects to the Database
        db = new DatabaseHelper(this);

        setupIDs();
        setupToolbar();
        setupSpinners();

        //Adds the Subject to the Database
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                className = editTextClassName.getText().toString();
                addClass(className, classTimeStartHour + ":" + classTimeStartMin + " " + classTimeStartAmPm + " - " + classTimeEndHour + ":" + classTimeEndMin + " " + classTimeEndAmPm, classDay);
                onBackPressed();
            }
        });
    }

    /**
     * Sets the IDs for the Views
     */
    private void setupIDs()
    {
        toolbarAddClass = findViewById(R.id.toolbarAddClass);
        editTextClassName = findViewById(R.id.classNameEditText);
        spinnerClassTimeStart = findViewById(R.id.classTimeSpinnerStart);
        spinnerClassTimeMinStart = findViewById(R.id.classTimeSpinnerMinStart);
        spinnerClassTimeEnd = findViewById(R.id.classTimeSpinnerEnd);
        spinnerClassTimeMinEnd = findViewById(R.id.classTimeSpinnerMinEnd);
        spinnerClassTimeAmPmStart = findViewById(R.id.classTimeSpinnerAmPmStart);
        spinnerClassTimeAmPmEnd = findViewById(R.id.classTimeSpinnerAmPmEnd);
        spinnerClassDay = findViewById(R.id.classDaySpinner);
        btnSubmit = findViewById(R.id.addClassSubmitButton);
    }

    /**
     * Sets up the Toolbar
     */
    private void setupToolbar()
    {
        setSupportActionBar(toolbarAddClass);
        getSupportActionBar().setTitle("Add Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Sets up and populates the Spinners
     */
    private void setupSpinners()
    {
        ArrayList<String> classTimeMin = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.timetable_times_minutes_array)));
        ArrayList<String> classTimeHour = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.timetable_times_hours_array)));
        ArrayList<String> classTimeAmPm = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.timetable_times_noon_array)));
        ArrayList<String> classDay = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.timetable_days_array)));

        ArrayAdapter<String> classTimeHourAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classTimeHour);
        ArrayAdapter<String> classTimeMinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classTimeMin);
        ArrayAdapter<String> classTimeAmPmAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classTimeAmPm);
        ArrayAdapter<String> classDayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classDay);

        spinnerClassTimeStart.setAdapter(classTimeHourAdapter);
        spinnerClassTimeEnd.setAdapter(classTimeHourAdapter);
        spinnerClassTimeMinStart.setAdapter(classTimeMinAdapter);
        spinnerClassTimeMinEnd.setAdapter(classTimeMinAdapter);
        spinnerClassTimeAmPmStart.setAdapter(classTimeAmPmAdapter);
        spinnerClassTimeAmPmEnd.setAdapter(classTimeAmPmAdapter);
        spinnerClassDay.setAdapter(classDayAdapter);

        setupOnSelectListeners();
    }

    /**
     * Sets the OnSelectListeners for the Spinners
     */
    private void setupOnSelectListeners()
    {
        spinnerClassTimeStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classTimeStartHour = adapterView.getItemAtPosition(i).toString();
                spinnerClassTimeStart.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        spinnerClassTimeMinStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classTimeStartMin = adapterView.getItemAtPosition(i).toString();
                spinnerClassTimeMinStart.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        spinnerClassTimeAmPmStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classTimeStartAmPm = adapterView.getItemAtPosition(i).toString();
                spinnerClassTimeAmPmStart.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        spinnerClassTimeEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classTimeEndHour = adapterView.getItemAtPosition(i).toString();
                spinnerClassTimeEnd.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        spinnerClassTimeMinEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classTimeEndMin = adapterView.getItemAtPosition(i).toString();
                spinnerClassTimeMinEnd.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        spinnerClassTimeAmPmEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classTimeEndAmPm = adapterView.getItemAtPosition(i).toString();
                spinnerClassTimeAmPmEnd.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        spinnerClassDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                classDay = adapterView.getItemAtPosition(i).toString();
                spinnerClassDay.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });
    }

    /**
     * Collects the information and adds the Subject to the Database
     * @param name
     * @param time
     * @param day
     */
    private void addClass(String name, String time, String day)
    {
        db.addSubject(new Subject(name, time, day));
    }

    /**
     * Sets the arrow in the Toolbar to return to the previous screen
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