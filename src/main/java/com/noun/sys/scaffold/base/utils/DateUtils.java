package com.noun.sys.scaffold.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:gaoxu
 * @create:2020-03-20 14:06
 **/
public class DateUtils {

    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private final static String MAX_PERMANENT_EXPIRE_DATE = "29991231";

    private final static int ZERO_DATE_BETWEEN_DAY = 0;

    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    public static final String YYYY_MM_DATE_FORMAT = "yyyy-MM";

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

    public static final String HHMMSS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDDTHHMMSS_DATE_FORMAT = "yyyy-MM-ddTHH:mm:ss";

    public static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";

    public static final CommonDateFormatter YYYY_MM_FORMATER = new CommonDateFormatter(YYYY_MM_DATE_FORMAT);

    public static final CommonDateFormatter YYYY_MM_DD_FORMATER = new CommonDateFormatter(SIMPLE_DATE_FORMAT);

    public static final CommonDateFormatter HHMMSS_FORMATER = new CommonDateFormatter(HHMMSS_DATE_FORMAT);

    public static final CommonDateFormatter YYYYMMDD_FORMATER = new CommonDateFormatter(YYYYMMDD_DATE_FORMAT);

    public static Date stringToDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        FastDateFormat format = FastDateFormat.getInstance(pattern);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Internal Error", e);
        }
        return null;
    }

    public static String changeFormat(String dateStr, String fromPattern, String toPattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return dateStr;
        }
        try {
            FastDateFormat fromFormat = FastDateFormat.getInstance(fromPattern);
            Date date = fromFormat.parse(dateStr);
            FastDateFormat toFormat = FastDateFormat.getInstance(toPattern);
            return toFormat.format(date);
        } catch (ParseException e) {
            logger.error("Internal Error", e);
        }

        return null;
    }

    public static boolean isMatch(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return StringUtils.isEmpty(pattern);
        }
        FastDateFormat format = FastDateFormat.getInstance(pattern);
        try {
            Date date = format.parse(dateStr);
            return dateStr.equals(format.format(date));
        } catch (ParseException e) {
            logger.info("input date({}) does not match pattern({})", dateStr, pattern);
            return false;
        }
    }

    public static String getFormatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        FastDateFormat format = FastDateFormat.getInstance(pattern);
        return format.format(date);
    }

    public static Date stringToDate(String dateStr, CommonDateFormatter format) {
        return format.parse(dateStr);
    }

    public static String getFormatDate(Date date, CommonDateFormatter format) {
        return format.format(date);
    }

    public static String formatSimpleDate(Date date) {
        return getFormatDate(date, YYYY_MM_DD_FORMATER);
    }

    public static String formatSimpleDate(long timeStamp) {
        return getFormatDate(new Date(timeStamp), YYYY_MM_DD_FORMATER);
    }

    public static Date getAfterDay(String dateStr) {
        Date date = stringToDate(dateStr, YYYY_MM_DD_FORMATER);
        return getAfterDay(date);
    }

    public static Date getDateAfterMonths(Date date, int duration) {
        LocalDateTime dateTime = getLocalDateTime(date).plusMonths(duration);
        return getDate(dateTime);
    }

    public static Date getDateAfterDays(Date date, int duration) {
        LocalDateTime dateTime = getLocalDateTime(date).plusDays(duration);
        return getDate(dateTime);
    }

    public static Date getAfterDay(Date date) {
        LocalDateTime dateTime = getLocalDateTime(date).toLocalDate().plusDays(1).atStartOfDay();
        return getDate(dateTime);
    }

    public static Date getBeforeDay(Date date) {
        LocalDateTime dateTime = getLocalDateTime(date).toLocalDate().minusDays(1).atStartOfDay();
        return getDate(dateTime);
    }

    public static Date getDayStart(Date date) {
        LocalDateTime dateTime = getLocalDateTime(date).toLocalDate().atStartOfDay();
        return getDate(dateTime);
    }

    public static Date getDayEnd(Date date) {
        LocalDateTime dateTime = getLocalDateTime(date).toLocalDate().plusDays(1).atStartOfDay();
        Instant instant = minusMilliseconds(dateTime, 999);
        return Date.from(instant);
    }

    public static Date getMonthStart(Date date) {
        LocalDateTime dateTime = getLocalDateTime(date).toLocalDate().withDayOfMonth(1).atStartOfDay();
        return getDate(dateTime);
    }

    public static Date getMonthEnd(Date date) {
        LocalDateTime dateTime = getLocalDateTime(date).toLocalDate().withDayOfMonth(1).plusMonths(1).atStartOfDay();
        Instant instant = minusMilliseconds(dateTime, 1);
        return Date.from(instant);
    }

    public static Date addMonth(Date date, int months) {
        LocalDateTime dateTime = getLocalDateTime(date).plusMonths(months);
        return getDate(dateTime);
    }

    public static Date addDay(Date date, int days) {
        LocalDateTime dateTime = getLocalDateTime(date).plusDays(days);
        return getDate(dateTime);
    }

    public static Date addHours(Date date, int hours) {
        LocalDateTime dateTime = getLocalDateTime(date).plusHours(hours);
        return getDate(dateTime);
    }

    public static Date addMinutes(Date date, int minutes) {
        LocalDateTime dateTime = getLocalDateTime(date).plusMinutes(minutes);
        return getDate(dateTime);
    }

    public static Date addSeconds(Date date, int seconds) {
        LocalDateTime dateTime = getLocalDateTime(date).plusSeconds(seconds);
        return getDate(dateTime);
    }

    public static int getYear(Date date) {
        return getLocalDateTime(date).getYear();
    }

    public static int getMonth(Date date) {
        return getLocalDateTime(date).getMonthValue();
    }

    public static int getDayOfMonth(Date date) {
        return getLocalDateTime(date).getDayOfMonth();
    }

    public static Long getDateBetweenMonth(Date earlier, Date later) {
        LocalDateTime t1 = getLocalDateTime(earlier);
        LocalDateTime t2 = getLocalDateTime(later);
        Period period = Period.between(t1.toLocalDate(), t2.toLocalDate());
        return period.toTotalMonths();
    }

    /**
     * 获取两日期天数差
     */
    public static int getDateBetweenDay(Date earlier, Date later) {
        LocalDateTime t1 = getLocalDateTime(earlier);
        LocalDateTime t2 = getLocalDateTime(later);
        return (int) (t2.toLocalDate().toEpochDay() - t1.toLocalDate().toEpochDay());
    }

    @Deprecated
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date getDateStart(int year, int month, int day) {
        LocalDateTime dateTime = LocalDate.of(year, month, day).atStartOfDay();
        return getDate(dateTime);
    }

    public static Integer getYearInterval(Date date) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime input = getLocalDateTime(date);
        return Math.max(now.getYear() - input.getYear(), 1);
    }

    public static Date getMaxDateOfCentury(){
        return stringToDate(MAX_PERMANENT_EXPIRE_DATE, DateUtils.YYYYMMDD_FORMATER);
    }

    public static boolean isMaxDateOfCentury(Date date){
        Date maxDateOfCentury = getMaxDateOfCentury();
        int dateBetweenDay = DateUtils.getDateBetweenDay(maxDateOfCentury, date);
        return ZERO_DATE_BETWEEN_DAY == dateBetweenDay;
    }

    public static boolean isBeforeDate(Date date) {
        return date.compareTo(new Date()) < 0;
    }

    private static LocalDateTime getLocalDateTime(Date date) {
        return date == null ? LocalDateTime.now() : LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE);
    }

    private static Date getDate(LocalDateTime localDateTime) {
        return localDateTime == null ? new Date() : Date.from(localDateTime.atZone(DEFAULT_ZONE).toInstant());
    }

    private static Instant minusMilliseconds(LocalDateTime localDateTime, long milliseconds) {
        Instant instant = localDateTime.atZone(DEFAULT_ZONE).toInstant();
        return Instant.ofEpochMilli(instant.toEpochMilli() - milliseconds);
    }

    public static class CommonDateFormatter {

        private final FastDateFormat format;

        private CommonDateFormatter(final String pattern) {
            this.format = FastDateFormat.getInstance(pattern);
        }

        public String format(Date date) {
            if (date == null) {
                return null;
            }
            return format.format(date);
        }

        public Date parse(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            try {
                return format.parse(source);
            } catch (ParseException e) {
                logger.error("Internal Error", e);
            }
            return null;
        }
    }
}
