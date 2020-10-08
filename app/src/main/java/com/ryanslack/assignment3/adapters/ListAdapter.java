package com.ryanslack.assignment3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryanslack.assignment3.R;

public class ListAdapter extends BaseAdapter
{
    private Context mCont;
    private LayoutInflater mLayoutInflater;
    private ImageView mImageView;
    private TextView mTitle, mDesc;
    private String[] mTitleArray, mDescArray;

    public ListAdapter(Context cont, String[] titleArray, String[] descArray)
    {
        mCont = cont;
        mTitleArray = titleArray;
        mDescArray = descArray;
        mLayoutInflater = LayoutInflater.from(cont);
    }

    @Override
    public int getCount()
    {
        return mTitleArray.length;
    }

    @Override
    public Object getItem(int i)
    {
        return mTitleArray[i];
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
            view = mLayoutInflater.inflate(R.layout.activity_main_card_item, null);
        }
        mTitle = (TextView)view.findViewById(R.id.cardTitleTextView);
        mDesc = (TextView)view.findViewById(R.id.cardDescTextView);
        mImageView = (ImageView)view.findViewById(R.id.cardImageView);

        mTitle.setText(mTitleArray[i]);
        mDesc.setText(mDescArray[i]);

        if(mTitleArray[i].equalsIgnoreCase("View Timetable"))
        {
            mImageView.setImageResource(R.drawable.img_timetable);
        }
        else if(mTitleArray[i].equalsIgnoreCase("View Subjects"))
        {
            mImageView.setImageResource(R.drawable.img_subjects);
        }
        else if(mTitleArray[i].equalsIgnoreCase("Options"))
        {
            mImageView.setImageResource(R.drawable.img_options);
        }

        return view;
    }
}
