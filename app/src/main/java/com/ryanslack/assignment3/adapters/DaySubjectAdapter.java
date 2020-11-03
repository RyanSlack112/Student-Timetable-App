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

public class DaySubjectAdapter extends BaseAdapter
{
    private Context mCont;
    private LayoutInflater mLayoutInflater;
    private LetterImageView mLetterImageView;
    private TextView mSubject, mTime;
    private Button mDelete;
    private ArrayList<Subject> mSubjectList;
    private DatabaseHelper db;

    public DaySubjectAdapter(Context cont, ArrayList<Subject> subjectList)
    {
        mCont = cont;
        mSubjectList = subjectList;
        mLayoutInflater = LayoutInflater.from(cont);
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
            view = mLayoutInflater.inflate(R.layout.day_class_detail_card, null);
        }

        //Sets the IDs for the Views
        mSubject = (TextView)view.findViewById(R.id.cardClassTv);
        mTime = (TextView)view.findViewById(R.id.cardClassTimeTv);
        mLetterImageView = (LetterImageView)view.findViewById(R.id.cardClassLiv);
        mDelete = (Button)view.findViewById(R.id.btnDeleteSub);

        //Sets the Delete button to gone if there are no subjects in the list
        if(mSubjectList.get(i).compareName("No Subjects Available"))
        {
            mDelete.setVisibility(GONE);
        }

        //Sets the Text for Subject and Time
        mSubject.setText(mSubjectList.get(i).getName());
        mTime.setText(mSubjectList.get(i).getTime());
        mDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db.deleteSubjectDay(mSubjectList.get(i));
            }
        });

        //Sets the Letter Image View to an Oval shape and sets the character
        mLetterImageView.setOval(true);
        mLetterImageView.setLetter(mSubjectList.get(i).getName().charAt(0));

        return view;
    }
}
