package com.ajsherrell.hyperbaricquiz.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    Context mContext;

    private static final String TAG = JsonUtils.class.getSimpleName();

    //empty constructor
    private JsonUtils() {}

    // json constants
    private final static String TITLE = "title";
    private final static String PHYSICS = "physics";
    private final static String PRESSURE = "pressure";
    private final static String ID = "id";
    private final static String QUESTION = "question";
    private final static String OPTIONS = "options";
    private final static String ANSWER = "answer";


    // extract JSON from Assets folder with help from:
    //https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi/19945484#19945484
    public String loadJSONFromAsset() {

        String json = null;
        try {
            AssetManager assetManager = (AssetManager) mContext.getAssets();
            InputStream is = assetManager.open("QuizData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //parse the JSON
    public static QuizContent parseJson(String quizJson) {

        // if the JSON string is empty or null, then return early
        if (TextUtils.isEmpty(quizJson)) {
            return null;
        }

        // declare local vars for json
        JSONObject jsonObject;
        List<String> title;
        String id = null;
        String question = null;
        List<String> options = null;
        String answer = null;

        try {
            jsonObject = new JSONObject(quizJson);

            //get the base object
            JSONObject main = jsonObject.getJSONObject(TITLE);
            title = jsonArrayList(main.getJSONArray(TITLE));

            //loop through array
            for (int i = 0; i < title.size(); i++) {
                // get optStrings
                JSONObject obj = new JSONObject(title.get(i));
                id = obj.optString(ID);
                question = obj.optString(QUESTION);
                answer = obj.optString(ANSWER);
                options = jsonArrayList(obj.getJSONArray(OPTIONS));
            }


        } catch (JSONException e) {
            Log.d(TAG, "parseJson: not able to parse JSON!!!!!" + quizJson);
            e.printStackTrace();
            return null;
        }
        return new QuizContent(title, id, question, options, answer);
    }

    private static List<String> jsonArrayList(JSONArray jsonArray) throws JSONException {
        // declare arrayList from 0th index
        List<String> arrayList = new ArrayList<>(0);

        // loop through array
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add(jsonArray.optString(i));
        }

        return arrayList;
    }

}
