package com.example.sleep;

public class sleepytime_managing {
    static int hour= 0 , min= 0, am_pm= 0;

    static String s1="",s2="",s3="",s4="";
    public static void set_time(int h, int m, int ap)
    {
        hour = h;
        min = m*5;
        am_pm = ap;
    }
    public static String get_time()
    {
        String result ="";
        if(hour == 0 || min ==0)
        {
            result = "sleepytime을 먼저 설정 해 주세요.";
        }
        else {
            cal();
            result = s4 + " or\n" + s3 + " or\n" + s2 + " or\n" + s1;
        }
        return result;
    }

    public static void cal()
    {

        int temp_hour = hour - 4;
        int temp_min = min - 30;
        if (temp_hour < 0)
        {
            temp_hour = 12 + temp_hour;
            if (am_pm == 0)  am_pm = 1;
            else if (am_pm == 1) am_pm = 0;
        }
        if (temp_min < 0)
        {
            temp_hour = temp_hour - 1;
            temp_min = 60 + (temp_min);
            if (temp_hour < 0)
            {
                if (am_pm == 0) am_pm = 1;
                else if (am_pm == 1) am_pm = 0;
                temp_hour = temp_hour * (-1);
            }
        }
        if(am_pm ==0) {
            if(temp_min ==0)
            {
                s1 = "" + temp_hour + ":00"+"AM";
            }
            else {
                s1 = "" + temp_hour + ":" + "" + temp_min + "AM";
            }
        }
        else
        {
            if(temp_min ==0)
            {
                s1 = "" + temp_hour + ":00"+"PM";
            }
            else {
                s1 = "" + temp_hour + ":" + "" + temp_min + "PM";
            }
        }

        temp_hour = temp_hour - 1;
        temp_min = temp_min - 30;

        if (temp_hour < 0)
        {
            temp_hour = 12 + temp_hour;
            if (am_pm == 0) am_pm = 1;
            else if (am_pm == 1)am_pm = 0;
        }
        if (temp_min < 0)
        {
            temp_hour = temp_hour - 1;
            temp_min = 60 + (temp_min);
            if (temp_hour < 0)
            {
                if (am_pm == 0) am_pm = 1;
                else if (am_pm == 1) am_pm = 0;
                temp_hour = temp_hour * (-1);
            }
        }

        if(am_pm ==0) {
            if(temp_min ==0)
            {
                s2 = "" + temp_hour + ":00"+"AM";
            }
            else {
                s2 = "" + temp_hour + ":" + "" + temp_min + "AM";
            }
        }
        else
        {
            if(temp_min ==0)
            {
                s2 = "" + temp_hour + ":00"+"PM";
            }
            else {
                s2 = "" + temp_hour + ":" + "" + temp_min + "PM";
            }
        }

        int temp_hour2 = temp_hour - 1;
        int temp_min2 = temp_min - 30;

        if (temp_hour2 < 0)
        {
            temp_hour2 = 12 + temp_hour2;
            if (am_pm == 0) am_pm = 1;
            else if (am_pm == 1) am_pm = 0;
        }
        if (temp_min2 < 0)
        {
            temp_hour2 = temp_hour2 - 1;
            temp_min2 = 60 + (temp_min2);
            if (temp_hour2 < 0)
            {
                if (am_pm == 0) am_pm = 1;
                else if (am_pm == 1) am_pm = 0;
                temp_hour2 = temp_hour2 * (-1);
            }
        }

        if(am_pm ==0) {
            if(temp_min2 ==0)
            {
                s3 = "" + temp_hour2 + ":00"+"AM";
            }
            else {
                s3 = "" + temp_hour2 + ":" + "" + temp_min2 + "AM";
            }
        }
        else
        {
            if(temp_min2 ==0)
            {
                s3 = "" + temp_hour2 + ":00"+"PM";
            }
            else {
                s3 = "" + temp_hour2 + ":" + "" + temp_min2 + "PM";
            }
        }

        int temp_hour3 = temp_hour2 - 1;
        int temp_min3 = temp_min2 - 30;

        if (temp_hour3 < 0)
        {
            temp_hour3 = 12 + temp_hour3;
            if (am_pm == 0) am_pm = 1;
            else if (am_pm == 1) am_pm = 0;
        }
        if (temp_min3 < 0)
        {
            temp_hour3 = temp_hour3 - 1;
            temp_min3 = 60 + (temp_min3);
            if (temp_hour3 < 0)
            {
                if (am_pm == 0)am_pm = 1;
                else if (am_pm == 1) am_pm = 0;
                temp_hour3 = temp_hour3 * (-1);
            }
        }

        if(am_pm ==0) {
           // s4 = "" + temp_hour3 + ":" + ""+temp_min3+"AM";
            if(temp_min3 ==0)
            {
                s4 = "" + temp_hour3 + ":00"+"AM";
            }
            else {
                s4 = "" + temp_hour3 + ":" + "" + temp_min3 + "AM";
            }
        }
        else
        {
           // s4 = "" + temp_hour3 + ":" + ""+temp_min3+"PM";
            if(temp_min3 ==0)
            {
                s4 = "" + temp_hour3 + ":00"+"PM";
            }
            else {
                s4 = "" + temp_hour3 + ":" + "" + temp_min3 + "PM";
            }
        }
    }
    public static void init()
    {
        hour = 0;
        min = 0;
        am_pm = 0;
        s1 = "";
        s2 = "";
        s3 = "";
        s4 = "";
    }
}
