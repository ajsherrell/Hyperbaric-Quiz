package com.ajsherrell.hyperbaricquiz.utilities;

import android.content.Context;
import android.text.TextUtils;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    //empty constructor
    private JsonUtils() {}

    // extract JSON from Assets folder
    public static ArrayList<QuizContent> extractContent(Context context, String jsonResponse) {
        // if the JSON string is empty or null, return early
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        // create an empty array list to add content to
        ArrayList<QuizContent> data = new ArrayList<>();
        String[] content = null;

        try {
            //create a JsonObject from the Json response string
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            //TODO: finsih parsing JSON.
        }
    }

}
