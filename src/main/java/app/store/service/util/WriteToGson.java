package app.store.service.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WriteToGson {

    private static Gson gson = new Gson();

    public static String getJson(Object obj) {
        return new GsonBuilder().setLenient().create().toJson(obj);
    }

    public static JsonObject jsonFromStringSecound(Object jsonObjectStr) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(jsonObjectStr.toString()).getAsJsonObject();
    }

}
