package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ajsherrell.hyperbaricquiz.Constants;
import com.ajsherrell.hyperbaricquiz.R;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private static final String TAG = QuizAdapter.class.getSimpleName();

    private Context mContext;
    private Constants.ClickListener.OnItemClickListener onItemClickListener;
    private List<QuizContent> quizList;

    public QuizAdapter(Context context, List<QuizContent> quizList, Constants.
            ClickListener.OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.quizList = quizList;
        this.onItemClickListener = onItemClickListener;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public QuizViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.category_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onItemClickListener.onItemClick(quizList.get(position));
            Log.d(TAG, "onClick: this is onClick in Adapter at position: " + position);
        }
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        QuizContent quizContent = quizList.get(position);
        String CATEGORY_IMAGE = "assets?";
        //TODO: get image array above, or add them to JSON
        if (!TextUtils.isEmpty(CATEGORY_IMAGE)) {
            Picasso.with(mContext)
                    .load(CATEGORY_IMAGE.trim())
                    .placeholder(R.drawable.ic_stat_name)
                    .error(R.drawable.ic_stat_name)
                    .into(holder.imageView);
        }
        Log.d(TAG, "onBindViewHolder: this is in the adapter: " + CATEGORY_IMAGE);
    }

    @Override
    public int getItemCount() {
        return quizList == null ? 0 : quizList.size();
    }

    public void add(ArrayList<QuizContent> data) {
        this.quizList = data;
        notifyDataSetChanged();
    }

    public void clear() {
        quizList.clear();
    }
}
