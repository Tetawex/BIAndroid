package net.styleru.biandroid.util;

import android.content.Context;

import net.styleru.biandroid.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;

/**
 * Created by Tetawex on 18.10.2016.
 */

public class DateTimeConverter
{
    public String toHumanLanguage(DateTime dateTime, Context context) {
        DateTime dateTimeNow = new DateTime(DateTimeZone.UTC);
        long interval = dateTimeNow.getMillis() - dateTime.getMillis();

        if (interval < DateTimeConstants.MILLIS_PER_MINUTE)
            return context.getString(R.string.right_now);

        if (interval < DateTimeConstants.MILLIS_PER_HOUR)
            return toHumanCountables((int) (((double) interval) / DateTimeConstants.MILLIS_PER_MINUTE),
                    new String[]{context.getString(R.string.minutes_one),
                            context.getString(R.string.minutes_two_to_four),
                            context.getString(R.string.minutes_default)})+" "+context.getString(R.string.ago);

        if (interval < DateTimeConstants.MILLIS_PER_DAY)
            return toHumanCountables((int) (((double) interval) / DateTimeConstants.MILLIS_PER_HOUR),
                    new String[]{context.getString(R.string.hours_one),
                            context.getString(R.string.hours_two_to_four),
                            context.getString(R.string.hours_default)})+" "+context.getString(R.string.ago);

        if (interval < DateTimeConstants.MILLIS_PER_WEEK)
            return toHumanCountables((int) (((double) interval) / DateTimeConstants.MILLIS_PER_DAY),
                    new String[]{context.getString(R.string.days_one),
                            context.getString(R.string.days_two_to_four),
                            context.getString(R.string.days_default)})+" "+context.getString(R.string.ago);

        if (interval < (long)DateTimeConstants.MILLIS_PER_WEEK * 4)
            return toHumanCountables((int) (((double) interval) / DateTimeConstants.MILLIS_PER_WEEK),
                    new String[]{context.getString(R.string.weeks_one),
                            context.getString(R.string.weeks_two_to_four),
                            context.getString(R.string.weeks_default)})+" "+context.getString(R.string.ago);
        if (interval < (long)DateTimeConstants.MILLIS_PER_WEEK * 4 * 12)
            return toHumanCountables((int) (((double) interval) /( (long)DateTimeConstants.MILLIS_PER_WEEK * 4)),
                    new String[]{context.getString(R.string.months_one),
                            context.getString(R.string.months_two_to_four),
                            context.getString(R.string.months_default)})+" "+context.getString(R.string.ago);
        return dateTime.toString();
    }
    private String toHumanCountables(int count,String[] strings)
    {
        if (count % 100 -getLastDigit(count)!= 10)
        {
            if (getLastDigit(count) == 1) {
                return count + " " + strings[0];
            }
            else if (getLastDigit(count)>1&&getLastDigit(count)<5) {
                return count + " " + strings[1];
            }
        }
        return count + " " + strings[2];
    }
    private int getLastDigit(int number)
    {
        return number % 10;
    }
}
