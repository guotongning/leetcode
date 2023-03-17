package com.ning.table_sql;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class SqlCreator {

    private static final String DATE_FORMATTER = "yyyyMMdd";
    public static final String LOCAL_TIME_ZONE = "Asia/Shanghai";

    protected abstract String createSql();

    protected void printCreateSqlTemplate() {
        System.out.println(createSql());
    }

    protected void printSqlByLength(int length) {
        createSqlByDtRange(today(), addDay(today(), length));
    }

    protected void createSqlByDtRange(String start, String end) {
        StringBuilder sb = new StringBuilder();
        List<String> dtRange = getDtListByDtRange(start, end);
        dtRange.stream().map(dt -> createSql().replaceAll("@values", dt)).forEach(sb::append);
        System.out.println(sb.toString());
    }

    protected static String today() {
        return formatDay(new Date());
    }

    protected static String addDay(String date, int n) {
        return formatDay(subOrAddDay(parseDay(date), n));
    }

    private static List<String> getDtListByDtRange(String start, String end) {
        List<String> dts = new ArrayList<>();
        Date startDate = parseDay(start);
        Date endDate = parseDay(end);
        assert startDate != null;
        do {
            dts.add(formatDay(startDate));
            startDate = subOrAddDay(startDate, 1);
        } while (startDate.before(subOrAddDay(endDate, 1)));
        return dts;
    }

    private static Date subOrAddDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(LOCAL_TIME_ZONE));
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    private static String formatDay(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMATTER);
        return df.format(date);
    }

    private static Date parseDay(String date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMATTER);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
