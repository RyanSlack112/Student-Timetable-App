package com.ryanslack.assignment3;

public class Subject
{
    private int mID;
    private String mName;
    private String mTime;
    private String mDay;

    //Default Constructor
    public Subject() {}

    /**
     * Constructor without ID
     * @param name
     * @param time
     * @param day
     */
    public Subject(String name, String time, String day)
    {
        mName = name;
        mTime = time;
        mDay = day;
    }

    /**
     * Constructor with ID
     * @param id
     * @param name
     * @param time
     * @param day
     */
    public Subject(int id, String name, String time, String day)
    {
        mID = id;
        mName = name;
        mTime = time;
        mDay = day;
    }

    /**
     * Returns the ID
     * @return
     */
    public int getID()
    {
        return mID;
    }

    /**
     * Sets the ID
     * @param id
     */
    public void setID(int id)
    {
        mID = id;
    }

    /**
     * Returns the Name
     * @return
     */
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the Name
     * @param name
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * Returns the Time
     * @return
     */
    public String getTime()
    {
        return mTime;
    }

    /**
     * Sets the Time
     * @param time
     */
    public void setTime(String time)
    {
        mTime = time;
    }

    /**
     * Returns the Day
     * @return
     */
    public String getDay()
    {
        return mDay;
    }

    /**
     * Sets the Day
     * @param day
     */
    public void setDay(String day)
    {
        mDay = day;
    }

    public boolean compareName(String name)
    {
        if(mName.equalsIgnoreCase(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
