package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.R;
import com.ajsherrell.hyperbaricquiz.model.Category;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private static final String TAG = QuizAdapter.class.getSimpleName();

    private Context context;
    private QuizContent quizContent;
    private QuizAdapterOnClickHandler clickHandler;

    public interface QuizAdapterOnClickHandler {
        void onClick(Category category);
    }

    public QuizAdapter(Context context, QuizAdapterOnClickHandler clickHandler,
                       QuizContent quizContent) {
        this.context = context;
        this.clickHandler = clickHandler;
        this.quizContent = quizContent;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTv;
        ImageView imageView;

        public QuizViewHolder(View view) {
            super(view);
            titleTv = view.findViewById(R.id.category_list_tv);
            imageView = view.findViewById(R.id.category_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickHandler.onClick(quizContent.getCategory().get(position));
            Log.d(TAG, "onClick: !!!in title adapter at position " + position);
        }

    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_content, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, final int position) {
        String categoryTitle = quizContent.getCategory().get(position).getTitle();

        holder.titleTv.setText(categoryTitle);

        int imageResId = context.getResources().getIdentifier(categoryTitle, "drawable", context.getPackageName());
            Picasso.with(context)
                    .load(imageResId)
                    .placeholder(R.drawable.ic_stat_name)
                    .error(R.drawable.ic_stat_name)
                    .into(holder.imageView);

        Log.d(TAG, "onBindViewHolder: !!!this is the category image from quiz adapter" + imageResId);
    }

    @Override
    public int getItemCount() {
        return quizContent.getCategory() == null ? 0 : quizContent.getCategory().size();
    }

    public void add(QuizContent data) {
        this.quizContent = data;
        notifyDataSetChanged();
    }

    public void clear() {
        quizContent = new QuizContent();
    }
}
