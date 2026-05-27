package com.gitstats.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonExporter {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object data) {
        return gson.toJson(data);
    }
}
