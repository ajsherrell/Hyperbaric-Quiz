package com.ajsherrell.hyperbaricquiz;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_LIST_ID = "list_id";
    /**
     * The content this fragment is presenting.
     */
    private QuizContent title;

    // vars
    TextView titleTextView;
    ImageView imageView;

    AssetManager am; //TODO

    public CategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_LIST_ID)) {
            // load content specified by the fragment arguments.
            //TODO: load content?
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);
        //show content in text view and image view
        //TODO: find the views and set the content.

        return rootView;
    }


    //TODO: switch statement for images with titles
}
