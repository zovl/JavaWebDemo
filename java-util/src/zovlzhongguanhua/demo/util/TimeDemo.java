package zovlzhongguanhua.demo.util;

import java.sql.Time;
import java.util.*;

import static java.util.Calendar.getAvailableCalendarTypes;

public class TimeDemo {

    public static void main(String[] args) {

        // time();
        // date();
        // gregorian();
        calendar();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void system() {

        System.currentTimeMillis();
        System.nanoTime();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void time() {
        Time t = new Time(System.currentTimeMillis());

        new Time(System.currentTimeMillis());
        Time.valueOf("10:10:10");


        int year = t.getYear();
        int month = t.getMonth();
        int monthDay = t.getDay();
        int hour = t.getHours();
        int minute = t.getMinutes();
        int second = t.getSeconds();

        System.out.println("Time: year=" + year);
        System.out.println("Time: month=" + month);
        System.out.println("Time: monthDay=" + monthDay);
        System.out.println("Time: hour=" + hour);
        System.out.println("Time: minute=" + minute);
        System.out.println("Time: second=" + second);
    }

    private static void date() {
        Date d = new Date();
        new Date(System.currentTimeMillis());
        new Date(2016, 0, 1, 10, 10, 10);
        new Date("");

        d.setTime(System.currentTimeMillis());
        d.setYear(2016);
        d.setMonth(0);
        d.setDate(1);
        d.setHours(10);
        d.setMinutes(10);
        d.setSeconds(10);

        long milliseconds = d.getTime();
        // "EEE MMM dd HH:mm:ss zzz yyyy";
        String toString = d.toString();
        String toGMTString = d.toGMTString();
        int timezoneOffset = d.getTimezoneOffset();
        int date = d.getDate();

        System.out.println("Date: milliseconds=" + milliseconds);
        System.out.println("Date: toString=" + toString);
        System.out.println("Date: toGMTString=" + toGMTString);
        System.out.println("Date: timezoneOffset=" + timezoneOffset);
        System.out.println("Date: date=" + date);

        int year = d.getYear();
        int month = d.getMonth();
        int day = d.getDay();
        int hour = d.getHours();
        int minute = d.getMinutes();
        int second = d.getSeconds();

        System.out.println("Date: year=" + year);
        System.out.println("Date: month=" + month);
        System.out.println("Date: day=" + day);
        System.out.println("Date: hour=" + hour);
        System.out.println("Date: minute=" + minute);
        System.out.println("Date: second=" + second);
    }

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

    private static void calendar() {
        Calendar calendar = Calendar.getInstance();

        Calendar.getInstance();
        Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        new Calendar.Builder().build();

        // 2016/01/01-10:10:10
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);

        calendar.set(2016, 0, 1);
        calendar.set(2016, 0, 0, 10, 10, 10);

        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.setTimeInMillis(System.currentTimeMillis());

        // calendar.setLenient(true);
        // calendar.setFirstDayOfWeek(1);
        // calendar.setMinimalDaysInFirstWeek(1);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println("Calendar: year=" + year);
        System.out.println("Calendar: month=" + month);
        System.out.println("Calendar: dayOfMonth=" + dayOfMonth);
        System.out.println("Calendar: hourOfDay=" + hourOfDay);
        System.out.println("Calendar: minute=" + minute);
        System.out.println("Calendar: second=" + second);

        Date date = calendar.getTime();
        TimeZone timeZone = calendar.getTimeZone();
        long timeInMillis = calendar.getTimeInMillis();

        System.out.println("Calendar: date=" + date);
        System.out.println("Calendar: timeZone=" + timeZone);
        System.out.println("Calendar: timeInMillis=" + timeInMillis);

        Set<String> calendarTypes = Calendar.getAvailableCalendarTypes();
        for (String calendarType : calendarTypes) {
            System.out.println("Calendar: calendarType=" + calendarType);
        }

        int weekYear = calendar.getWeekYear();
        int weeksInWeekYear = calendar.getWeeksInWeekYear();
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        int minimalDaysInFirstWeek = calendar.getMinimalDaysInFirstWeek();

        System.out.println("Calendar: weekYear=" + weekYear);
        System.out.println("Calendar: weeksInWeekYear=" + weeksInWeekYear);
        System.out.println("Calendar: firstDayOfWeek=" + firstDayOfWeek);
        System.out.println("Calendar: minimalDaysInFirstWeek=" + minimalDaysInFirstWeek);
/*
        String displayName = calendar.getDisplayName(Calendar.DATE, Calendar.ALL_STYLES, Locale.ENGLISH);
        Map<String, Integer> displayNames = calendar.getDisplayNames(Calendar.DATE, Calendar.ALL_STYLES, Locale.ENGLISH);

        System.out.println("Calendar: displayName=" + displayName);
        for (Map.Entry<String, Integer> entry : displayNames.entrySet()) {
            System.out.println("Calendar: displayNames/" + entry.getKey() + "=" + entry.getValue());
        }*/

        int minimum = calendar.getMinimum(Calendar.DAY_OF_MONTH);
        int maximum = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        int actualMinimum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int leastMaximum = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
        int greatestMinimum = calendar.getGreatestMinimum(Calendar.DAY_OF_MONTH);

        System.out.println("Calendar: minimum=" + minimum);
        System.out.println("Calendar: maximum=" + maximum);
        System.out.println("Calendar: actualMinimum=" + actualMinimum);
        System.out.println("Calendar: actualMaximum=" + actualMaximum);
        System.out.println("Calendar: leastMaximum=" + leastMaximum);
        System.out.println("Calendar: greatestMinimum=" + greatestMinimum);
    }
}
