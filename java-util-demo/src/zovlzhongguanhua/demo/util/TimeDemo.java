package zovlzhongguanhua.demo.util;

import java.sql.Time;
import java.util.*;

public class TimeDemo {

    public static void main(String[] args) {

        // date();
        gregorian();

        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        calendar = new Calendar.Builder().build();

        Time time = Time.valueOf("");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void gregorian() {
        GregorianCalendar calendar = new GregorianCalendar();

        Date nowDate = new Date();
        nowDate.setTime(System.currentTimeMillis());

        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setGregorianChange(nowDate);
        calendar.setTime(nowDate);
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(2000, 1, 1);
        calendar.set(2000, 1, 1, 1, 1);
        calendar.set(2000, 1, 1, 1, 1, 1);

        calendar.set(GregorianCalendar.YEAR, 2000);
        calendar.set(GregorianCalendar.MONTH, 1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 1);
        calendar.set(GregorianCalendar.MINUTE, 1);
        calendar.set(GregorianCalendar.SECOND, 1);

        String time = calendar.toString();
        Date date = calendar.getTime();
        TimeZone zone = calendar.getTimeZone();
        long msecs = calendar.getTimeInMillis();
        String calendarType = calendar.getCalendarType();
        Date gregorianChange = calendar.getGregorianChange();

        long year = calendar.get(GregorianCalendar.YEAR);
        long month = calendar.get(GregorianCalendar.MONTH);
        long dayOfYear = calendar.get(GregorianCalendar.DAY_OF_YEAR);
        long dayOfMonth = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        long dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        long dayOfWeekInMonth = calendar.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);
        long hourOfDay = calendar.get(GregorianCalendar.HOUR_OF_DAY);
        long hour = calendar.get(GregorianCalendar.HOUR);
        long minute = calendar.get(GregorianCalendar.MINUTE);
        long second = calendar.get(GregorianCalendar.SECOND);
        long millisecond = calendar.get(GregorianCalendar.MILLISECOND);
    }

    private static void date() {
        Date date = new Date();
        long msecs = date.getTime();
        String s = date.toString();
        String gtm = date.toGMTString();
    }
}
