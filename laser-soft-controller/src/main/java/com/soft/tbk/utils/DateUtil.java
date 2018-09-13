package com.soft.tbk.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

    /**
     * Format:yyyy-MM-dd
     */
    public static final String DATESHOWFORMAT = "yyyy-MM-dd";

    public static final String DATEYMFORMAT = "yyyy-MM";

    /**
     * Format:yyyyMMdd
     */
    public static final String DATESTOREFORMAT = "yyyyMMdd";

    /**
     * Format:MMdd
     */
    public static final String DATEMDSTOREFORMAT = "MMdd";

    /**
     * Format:HH:mm:ss
     */
    public static final String TIMESHOWFORMAT = "HH:mm:ss";

    /**
     * FormatHHmmss
     */
    public static final String TIMESTOREFORMAT = "HHmmss";

    /**
     * Format:yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIMESHOWFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format:yyyyMMddHHmmss
     */
    public static final String DATETIMESTOREFORMAT = "yyyyMMddHHmmss";

    private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final int NUMBER10 = 10;

    /**
     * 计算两个日期的间隔天数
     * 
     * @param startDate
     *            开始时间，如：2008-12-03 11:00:00
     * @param endDate
     *            结束时间，如：2009-12-31 11:00:00
     * @return long 间隔天数(long)
     */
    public static long getBetweenDays(String startDate, String endDate) {

        if (endDate == null || startDate == null) {
            return -1;
        }
        Date dateStart = isDate(startDate);
        if (null == dateStart) {
            return -1;
        }
        Date dateEnd = isDate(endDate);
        if (null == dateEnd) {
            return -1;
        }
        return getBetweenDays(dateStart, dateEnd);
    }

    /**
     * 计算两个日期的间隔天数
     * 
     * @param startDate
     *            开始时间，如：2008-12-03 11:00:00
     * @param endDate
     *            结束时间，如：2009-12-31 11:00:00
     * @return long 间隔天数(long)
     */
    public static long getBetweenDays(Date startDate, Date endDate) {

        if (endDate == null || startDate == null) {
            return -1;
        }
        long days = endDate.getTime() - startDate.getTime();
        days = days / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 获取与指定日期相差指定 天数 的日期
     * 
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param dayCount
     *            向前或向后的天数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterDate(String baseDate, int dayCount, String patternString) {

        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.set(year, month - 1, date);
        calendar.add(Calendar.DATE, dayCount);
        Date dateTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(dateTime);
        return dateString;
    }

    /**
     * 获取与指定日期相差指定 天数 的日期
     * 
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param dayCount
     *            向前或向后的天数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterDate(Date baseDate, int dayCount, String patternString) {

        String baseDateTime = getDateString(baseDate, DATETIMESHOWFORMAT);
        return getAfterDate(baseDateTime, dayCount, patternString);
    }

    /**
     * 获取与指定日期相差指定 月数 的日期
     * 
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterMonth(String baseDate, int monthCount, String patternString) {

        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.add(Calendar.MONTH, monthCount);
        Date dateTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(dateTime);
        return dateString;
    }

    public static String getAfterHour(String baseDate, int hourCount, String patternString) {

        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.add(Calendar.HOUR, hourCount);
        Date dateTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(dateTime);
        return dateString;
    }

    /**
     * 获取与指定日期相差指定 时间 的日期(通用)
     * 
     * @param baseDate
     * @param count 指定时间差
     * @param calendarType 时间差类型
     * @param patternString
     * @return
     */
    public static String getAfterCalendarType(String baseDate, int count, int calendarType, String patternString) {

        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.add(calendarType, count);
        Date dateTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(dateTime);
        return dateString;
    }

    /**
     * 获取与指定日期相差指定 月数 的日期
     * 
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterMonth(Date baseDate, int monthCount, String patternString) {

        String baseDateTime = getDateString(baseDate, DATETIMESHOWFORMAT);
        return getAfterMonth(baseDateTime, monthCount, patternString);
    }

    /**
     * 获取与指定日期相差指定 月数 并减去天数的日期
     * 
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负
     * @param dateCount
     *            加或减去的天数，向后为正数，向前为负
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getEndDate(String baseDate, int monthCount, int dateCount, String patternString) {

        int day = Integer.parseInt(baseDate.substring(8, 10));
        String endDate = getAfterMonth(baseDate, monthCount, patternString);
        int endDay = Integer.parseInt(endDate.substring(8, 10));
        // 说明日期没变
        if (endDay == day || monthCount < 0) {
            endDate = getAfterDate(endDate, dateCount, patternString);
        }
        return endDate;
    }

    /**
     * 获取与指定日期相差指定 月数 并减去天数的日期
     * 
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负
     * @param dateCount
     *            加或减去的天数，向后为正数，向前为负
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getEndDate(Date baseDate, int monthCount, int dateCount, String patternString) {

        String baseDateTime = getDateString(baseDate, DATETIMESHOWFORMAT);
        return getEndDate(baseDateTime, monthCount, dateCount, patternString);
    }

    /**
     * 当前日期转换为指定月数后 的日期
     * 
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 转换后的日期
     */
    public static String getBeforeMonth(int monthCount, String patternString) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount);
        Date dateTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        return formatter.format(dateTime);
    }

    /**
     * 日期格式化(String转换为Date)
     * 
     * @param dateStr
     *            日期字符串
     * @param patten
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static Date getDateToString(String dateStr, String patten) {

        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(patten, Locale.CHINA);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 日期格式化(String转换为String)
     * 
     * @param date
     *            日期字符串
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getDateString(String date, String patternString) {

        if (date == null) {
            return "";
        }
        if (date.length() < NUMBER10) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(patternString, Locale.CHINA);
        int len = patternString.length();
        if (len > date.length()) {
            patternString = patternString.substring(0, date.length());
        }
        return formatter.format(getDateToString(date, patternString));
    }

    /**
     * 日期格式化(Date转换为String)
     * 
     * @param _date
     *            日期
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getDateString(Date dateTime, String patternString) {

        String dateString = "";
        if (dateTime != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(patternString);
            dateString = formatter.format(dateTime);
        }
        return dateString;
    }

    /**
     * 日期格式转换 DATE to DATE
     * 
     * @param _date
     *            日期
     * @param patten
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static Date dateToDate(Date dateTime, String patten) {

        String dateStr = "";
        SimpleDateFormat formatter = new SimpleDateFormat(patten);
        try {
            if (dateTime != null) {
                dateStr = formatter.format(dateTime);
            }
            return formatter.parse(dateStr);
        } catch (ParseException e) {}
        return null;
    }

    /**
     * 获得格式化日期之后的 String数据
     * 
     * @param dateLong
     * @param patten
     * @return
     */
    public static String getDateOfString(Long dateLong, String patten) {

        if (dateLong != null) {
            return (new SimpleDateFormat(patten).format(new Date(dateLong.longValue()))).toString();
        }
        return "";
    }

    /**
     * 文本时间转换为时间对象
     * 
     * @param baseDate
     *            日期字符串
     * @return
     */
    public static java.sql.Date getSqlDate(String baseDate) {

        if (baseDate == null || baseDate.length() == 0) {
            return null;
        }
        Date date = getDateToString(baseDate, DATESHOWFORMAT);
        if (date == null)
            return null;
        return new java.sql.Date(date.getTime());
    }

    /**
     * java.util.Date对象转换为java.sql.Date对象
     * 
     * @param date
     *            java.util.Date对象
     * @return Date java.sql.Date对象
     */
    public static java.sql.Date utilDateToSQLDate(Date date) {

        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取到指定样式的年月日(年月日参数为int型)
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @param patternString
     *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
     * @return 格式化后的字符串
     */
    public static String getDateString(int year, int month, int date, String patternString) {

        String dateString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        Date showDate = calendar.getTime();
        dateString = formatter.format(showDate);
        return dateString;
    }

    /**
     * 获取到指定样式的年月日(年月日参数为String型)
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @param patternString
     *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
     * @return 格式化后的字符串
     */
    public static String getDateString(String year, String month, String date, String patternString) {

        String dateString = "";
        try {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            int d = Integer.parseInt(date);
            dateString = getDateString(y, m, d, patternString);
        } catch (Exception e) {}
        return dateString;
    }

    /**
     * 获取当前日期
     * 
     * @param patten
     *            日期格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateStr(String patternString) {

        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String date = formatter.format(new Date(System.currentTimeMillis()));
        return date;
    }

    /**
     * 验证输入的文本信息日期是否合
     * 
     * @param inputDate
     * @return
     */
    public static Date isDate(String dateStr) {

        String dateFormat1 = "yyyy/MM/dd";
        String dateFormat2 = "yyyy-MM-dd";
        String dateFormat3 = "yyyyMMdd";
        String dateFormat4 = "yyyy.MM.dd";
        String[] dateFormat = { dateFormat1, dateFormat2, dateFormat3, dateFormat4 };
        for (int i = 0; i < dateFormat.length; i++) {
            Date tempDate = isDate(dateStr, dateFormat[i]);
            if (null != tempDate) {
                return tempDate;
            }
        }
        return null;
    }

    /**
     * 验证输入的文本信息日期是否合
     * 
     * @param inputDate
     * @return
     */
    public static Date isDate(String dateStr, String patternString) {

        if (StringUtils.isBlank(patternString)) {
            patternString = DATESHOWFORMAT;
        }
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat(patternString);
            formatDate.setLenient(false);
            ParsePosition pos = new java.text.ParsePosition(0);
            Date tempDate = formatDate.parse(dateStr, pos);
            tempDate.getTime();
            return tempDate;
        } catch (Exception e) {}
        return null;
    }

    /**
     * 把Date转换为Calendar对象
     * 
     * @param d
     *            Date对象
     * @return Calendar对象
     */
    public static Calendar getCalendar(Date d) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }

    /**
     * 将时间对象转换成指定的格式有小时
     * 
     * @param date
     * @return
     */
    public static String parseDateTime(Date date) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATETIMESHOWFORMAT);
        return bartDateFormat.format(date);
    }

    /**
     * 将时间对象转换成指定的格式有
     * 
     * @param date
     * @return
     */
    public static String parsTime(Date date) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(TIMESHOWFORMAT);
        return bartDateFormat.format(date);
    }

    /**
     * 将时间对象转换成指定的格式无小时
     * 
     * @param date
     * @return
     */
    public static String parseDate(Date date) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATESHOWFORMAT);
        return bartDateFormat.format(date);
    }

    /**
     * 获取当前月第一天
     * 
     * @return
     */
    public static String firstDate() {

        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = ca.getTime();
        return getDateString(firstDate, DATESHOWFORMAT);
    }

    /**
     * 获取当前月第一天
     * 
     * @return
     */
    public static String lastDate() {

        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = ca.getTime();
        return getDateString(lastDate, DATESHOWFORMAT);
    }

    /**
     * 获取当前数据里的时间参数
     * 
     * @return
     */
    public static String getDateStr() {

        return "sysdate";
    }

    /**
     * 获取上一个月的日期
     * 
     * @param date
     * @return
     */
    public static Date getUpMouth(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, -1);
        return ca.getTime();
    }

    /**
     * 获取日期的年
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     * 
     * @param date
     * @return
     */
    public static int getDay(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DATE);
    }

    /**
     * 获取日期事第几周
     * 
     * @param date
     * @return
     */
    public static int getWeek(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取上一个月的日期
     * 
     * @param date
     * @return
     */
    public static Date getUpMouth(String date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
        ca.add(Calendar.MONTH, -1);
        return ca.getTime();
    }

    /**
     * 获取日期的年
     * 
     * @param date
     * @return
     */
    public static int getYear(String date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     * 
     * @param date
     * @return
     */
    public static int getMonth(String date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     * 
     * @param date
     * @return
     */
    public static int getDay(String date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.DATE);
    }

    /**
     * 获取日期的第几周
     * 
     * @param date
     * @return
     */
    public static int getWeek(String date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期的第几小时
     * 
     * @param date
     * @return
     */
    public static int getHour(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的第几分钟
     * 
     * @param date
     * @return
     */
    public static int getMin(Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MINUTE);
    }

    /**
     * 检测d1 是否大于等于d2
     * 
     * @param d1
     * @param d2
     * @return true d1 是否大于等于d2
     */
    public static boolean checkMax(Date d1, Date d2) {

        boolean flag = false;
        if (null != d1) {
            if (null != d2) {
                String d1s = getDateString(d1, "yyyyMMdd");
                String d12s = getDateString(d2, "yyyyMMdd");
                if (Double.valueOf(d1s) >= Double.valueOf(d12s)) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isWeekend(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK) || Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
            return true;
        }
        return false;
    }

    /**
     * 给定时间往后延给定分钟
     * 
     * @param date
     * @param minute
     * @return
     * @author doumingjun
     * @create date 2012-06-27
     */
    public static Date addMinutes(Date date, int minute) {

        if (null == date) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return (Date) calendar.getTime();
    }

    /**
     * 获取日期相差天数,精确到秒
     * 
     * @param beginDate 开始时间 格式： yyyyMMdd
     * @param endDate 结束时间 格式： yyyyMMdd
     * @return 天数
     */
    public static Long getDiffDay(String beginDate, String endDate) {

        SimpleDateFormat formatter = new SimpleDateFormat(DATESTOREFORMAT);
        Long checkday = 0L;
        // 开始结束相差天数
        try {
            checkday = (formatter.parse(endDate).getTime() - formatter.parse(beginDate).getTime()) / (1000 * 24 * 60 * 60);
        } catch (ParseException e) {}
        return checkday;
    }

    /**
     * 精度列表,只有在这个列表里指定的字段才会被截取
     */
    private static int[] units = { Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND,
                                   Calendar.MILLISECOND };

    /**
     * 截取到指定精度
     * 
     * @param cal
     * @param field
     *            保留的最小字段
     * @return
     */
    public static Date roundDate(Calendar cal, int field) {

        boolean clearFlag = false;
        for (int i = 0; i < units.length; i++) {
            if (clearFlag) {
                cal.set(units[i], cal.getMinimum(units[i]));
            } else if (units[i] == field) {
                clearFlag = true;
            }
        }
        return cal.getTime();
    }

    public static void main(String[] args) throws Exception {
        // Date pprice =new Date();

        // System.out.println(pprice);
        // System.out.println(pprice.getTime());
        // Long a=pprice.getTime()+480000;
        // System.out.println(a);
        // System.out.println(new Date(a));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        System.out.println(getDateStr(DateUtil.DATESHOWFORMAT));

    }
}
