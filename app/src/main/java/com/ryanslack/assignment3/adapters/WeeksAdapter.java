package com.ryanslack.assignment3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ryanslack.assignment3.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ryanslack.assignment3.utilities.LetterImageView;

public class WeeksAdapter extends ArrayAdapter
{
    private int mResource;
    private LayoutInflater mLayoutInflater;
    private String[] mDaysArray;

    public WeeksAdapter(Context context, int resource, String[] daysArray)
    {
        super(context, resource, daysArray);
        mResource = resource;
        mDaysArray = daysArray;
        mLayoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(mResource, null);
            viewHolder.livDay = (LetterImageView)convertView.findViewById(R.id.cardLetterImageView);
            viewHolder.tvDay = (TextView)convertView.findViewById(R.id.cardDayTextView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.livDay.setOval(true);
        viewHolder.livDay.setLetter(mDaysArray[position].charAt(0));
        viewHolder.tvDay.setText(mDaysArray[position]);

        return convertView;
    }

    class ViewHolder
    {
        private LetterImageView livDay;
        private TextView tvDay;
    }
}
