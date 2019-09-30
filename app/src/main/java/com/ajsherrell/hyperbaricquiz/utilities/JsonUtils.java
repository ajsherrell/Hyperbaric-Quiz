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
    private static Context context;

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
    public static String loadJSONFromAsset(Context context) {

        String json = null;
        try {
           // AssetManager assetManager = (AssetManager) context.getAssets();
           // InputStream is = assetManager.open(fileName);
            InputStream is = context.getAssets().open("QuizData.json");
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
    public static ArrayList<QuizContent> parseQuizJson(String quizJson) throws JSONException {
        ArrayList<QuizContent> list = new ArrayList<>();
        // if the JSON string is empty or null, then return early
        if (TextUtils.isEmpty(quizJson)) {
            return null;
        }
        // TODO: change the json again!!
        // declare local vars for json
        JSONArray baseJsonArray;
        String title = null;
        String id = null;
        String question = null;
        List<String> options = null;
        String answer = null;
        String physics = null;
        String pressure = null;
        Log.d(TAG, "parseJson: !!! this is quizJson " + quizJson);

        try {
            baseJsonArray = new JSONArray(loadJSONFromAsset(context));
            //loop through array
            for (int i = 0; i < baseJsonArray.length(); i++) {
                JSONObject obj = new JSONObject(String.valueOf(baseJsonArray.getJSONObject(i)));
                title = obj.optString(TITLE);
                id = obj.optString(ID);
                question = obj.optString(QUESTION);
                answer = obj.optString(ANSWER);
                options = jsonArrayList(obj.getJSONArray(OPTIONS));
                Log.d(TAG, "parseJson: !!! This is QUESTION " + QUESTION);

                //store the json items in variables
                QuizContent content = new QuizContent();
                content.setTitle(title);
                content.setId(id);
                content.setQuestion(question);
                content.setAnswer(answer);
                content.setOptions(options);

                list.add(content);
            }

        } catch (JSONException e) {
            Log.d(TAG, "parseJson: not able to parse JSON!!!!!" + quizJson);
            e.printStackTrace();
            return null;
        }
        return list;
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
