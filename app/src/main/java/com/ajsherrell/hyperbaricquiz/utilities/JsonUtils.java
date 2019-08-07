package com.ajsherrell.hyperbaricquiz.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class JsonUtils {

    Context mContext;

    //json vars
    public String title;
    public String

    private static final String TAG = JsonUtils.class.getSimpleName();

    //empty constructor
    private JsonUtils() {}

    // extract JSON from Assets folder with help from:
    //https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi/19945484#19945484
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            AssetManager assetManager = (AssetManager) context.getAssets();
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
    public ArrayList<QuizContent> parseJson(Context context, String quizJson) {

        // if the JSON string is empty or null, then return early
        if (TextUtils.isEmpty(quizJson)) {
            return null;
        }
        //create an empty array list to add content to
        ArrayList<QuizContent> data = new ArrayList<>();
        String[] jsonResponse = null;
        try {
            JSONObject baseObject = new JSONObject(loadJSONFromAsset(context));
            //extract object "title" key from base object
            JSONObject titleObject = baseObject.getJSONObject("title");
            //extract array "physics" key from titleObject
            JSONArray physicsArray = titleObject.getJSONArray("physics");
            // add array to string[]
            jsonResponse = new String[physicsArray.length()];

            // iterate through physicsArray
            for (int i = 0; i < physicsArray.length(); i++) {

                // get single content in array index 0 in the list
                JSONObject physicsId = physicsArray.getJSONObject(i);

                //TODO: finish parsing json
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
