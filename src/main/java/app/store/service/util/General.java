package app.store.service.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bson.types.ObjectId;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


public class General {

    private static String date;
    private String time;

    public static String parseString(Object s) {
        try {
            return TextHelper.toStandardPersian(s.toString());
        } catch (Exception var2) {
            return "";
        }
    }

    public static String addSeparator(String path) {
        path = parseString(path);
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }

        return path;
    }

    public static long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isEmpty(ObjectId s) {
        return s == null || s.toString().trim().equals("");
    }

    public static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception var2) {
            return 0;
        }
    }

    public static Date convertPersianDateString2MiladiDate(String pdate) {
        CalendarTool ca = new CalendarTool();
        int year = parseInt(pdate.split("-")[0]);
        int mont = parseInt(pdate.split("-")[1]);
        int day = parseInt(pdate.split("-")[2]);
        ca.setIranianDate(year, mont, day);
        return parseDate(ca.getGregorianDate2());
    }

    public static Date parseDate(String str) {
        SimpleDateFormat pd = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return pd.parse(str);
        } catch (Exception var3) {
            return new Date();
        }
    }

    public static Date parseTime(String str) {
        SimpleDateFormat pd = new SimpleDateFormat("HH:mm");

        try {
            return pd.parse(str);
        } catch (Exception var3) {
            return new Date();
        }
    }

    public static Date parseAnyDateToMiladiWithTime(Object str) {
        return parseAnyDateToMiladiWithTime(parseString(str));
    }

    public static Date parseAnyDateToMiladiWithTime(String datestr) {
        if (isEmpty(datestr)) {
            return new Date();
        } else {
            String[] datArr = datestr.split("-");
            int year = 0;
            if (datArr.length > 0) {
                year = parseInt(datArr[0]);
            }

            if (year == 0) {
                return new Date();
            } else {
                return year < 1500 ? convertPersianDateString2MiladiDateTime(datestr) : parseDateWithTime(datestr);
            }
        }
    }

    public static Date convertPersianDateString2MiladiDateTime(String pdate) {
        CalendarTool ca = new CalendarTool();
        String[] pdateArr = pdate.split(" ");
        String date = pdateArr.length > 0 ? pdateArr[0] : "";
        String time = pdateArr.length > 1 ? pdateArr[1] : "";
        String[] dateArr = date.split("-");
        int year = dateArr.length > 0 ? parseInt(dateArr[0]) : 0;
        int mont = dateArr.length > 1 ? parseInt(dateArr[1]) : 0;
        int day = dateArr.length > 2 ? parseInt(dateArr[2]) : 0;
        String[] timeArr = time.split(":");
        int hour = timeArr.length > 0 ? parseInt(timeArr[0]) : 0;
        int min = timeArr.length > 1 ? parseInt(timeArr[1]) : 0;
        int sec = timeArr.length > 2 ? parseInt(timeArr[2]) : 0;
        ca.setIranianDate(year, mont, day, hour, min, sec);
        return parseDateWithTime(ca.getGregorianDate2WithTime());
    }

    public static Date parseDateWithTime(String str) {
        SimpleDateFormat pd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return pd.parse(str);
        } catch (Exception var3) {
            return new Date();
        }
    }

    public static Date parseDateWithTimeFromTS(Object obj) {
        return parseDateWithTimeFromTS(parseString(obj));
    }

    public static Date parseDateWithTimeFromTS(String str) {
        SimpleDateFormat pd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = new Date(parseLong(str));
            return parseDateWithTime(pd.format(date));
        } catch (Exception var3) {
            return new Date();
        }
    }

    public static void addJsonParseValue(JsonObject jsonObject, String key, String value) {
        Object result = value;
        String s = value.trim();
        if (s.startsWith("["))
            jsonObject.add(key, jsonArrayFromString(s));
        else if (s.startsWith("{"))
            jsonObject.add(key, jsonFromString(s));
        else
            jsonObject.addProperty(key, value);

    }

    public static JsonArray jsonArrayFromString(String jsonArrayStr) {
        JsonParser parser = new JsonParser();
        return parser.parse(jsonArrayStr).getAsJsonArray();
    }


    public static JsonObject jsonFromString(String jsonObjectStr) {
        JsonParser parser = new JsonParser();
        return parser.parse(jsonObjectStr).getAsJsonObject();
    }

    public static String urlencode(String url) {
        try {
            return URLEncoder.encode(parseString(url), "UTF-8");
        } catch (Exception e) {
            return parseString(url);
        }
    }
}
