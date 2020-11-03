package com.ryanslack.assignment3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.ryanslack.assignment3.R;

import com.ryanslack.assignment3.Subject;
import com.ryanslack.assignment3.databasecode.DatabaseHelper;
import com.ryanslack.assignment3.utilities.LetterImageView;

import java.util.ArrayList;

import static android.view.View.GONE;

public class AllSubjectsAdapter extends BaseAdapter
{
    private Context mCont;
    private LayoutInflater mLayoutInflater;
    private LetterImageView mLetterImageView;
    private TextView mSubject;
    private Button mDelete;
    private ArrayList<Subject> mSubjectList;
    private DatabaseHelper db;

    public AllSubjectsAdapter(Context cont, ArrayList<Subject> subjectList)
    {
        mCont = cont;
        mSubjectList = subjectList;
        mLayoutInflater = LayoutInflater.from(cont);

        //Connects to the Database
        db = new DatabaseHelper(cont);
    }

    @Override
    public int getCount()
    {
        return mSubjectList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return mSubjectList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            view = mLayoutInflater.inflate(R.layout.all_subjects_card_item, null);
        }

        //Sets the IDs for the Views
        mSubject = (TextView)view.findViewById(R.id.cardSubjectTv);
        mLetterImageView = (LetterImageView)view.findViewById(R.id.cardSubjectLiv);
        mDelete = (Button)view.findViewById(R.id.btnDeleteSub);

        //Sets the Delete button to Gone when no Subjects are in the list
        if(mSubjectList.get(i).compareName("No Subjects Available"))
        {
            mDelete.setVisibility(GONE);
        }

        //Sets Subject Text
        mSubject.setText(mSubjectList.get(i).getName());

        //Sets the Letter Image View to an Oval Shape and sets the Character
        mLetterImageView.setOval(true);
        mLetterImageView.setLetter(mSubjectList.get(i).getName().charAt(0));

        //Sets up the Delete Button
        mDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Deletes the Subject and refreshes the screen
                db.deleteSubject(mSubjectList.get(i));
                mSubjectList.remove(i);

                //If the list is empty adds the No Subjects Available card
                if(mSubjectList.isEmpty())
                {
                    mSubjectList.add(new Subject("No Subjects Available", "", "Monday"));
                }

                //Resets the view when a Subject is deleted
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
