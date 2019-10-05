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
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private static final String TAG = QuizAdapter.class.getSimpleName();

    private Context context;
    private List<QuizContent> quizList;
    private QuizAdapterOnClickHandler clickHandler;

    public interface QuizAdapterOnClickHandler {
        void onClick(ArrayList<QuizContent> clickedCategory);
    }

    public QuizAdapter(Context context, List<QuizContent> quizList,
                       QuizAdapterOnClickHandler clickHandler) {
        this.context = context;
        this.quizList = quizList;
        this.clickHandler = clickHandler;
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
            clickHandler.onClick(quizList.get(position));
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
        QuizContent content = quizList.get(position);

        holder.titleTv.setText(content.getTitle());

        String categoryTitle = (String) holder.titleTv.getText();
        //String CATEGORY_IMAGE = String.valueOf(getImage(Integer.parseInt(categoryTitle)));
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
        return quizList == null ? 0 : quizList.size();
    }

    public void add(ArrayList<QuizContent> data) {
        this.quizList = data;
        notifyDataSetChanged();
    }

    public void clear() {
        quizList.clear();
    }

    //get images by category
    public int getImage(int image) {
        String category = null;
        switch (category) {
            case "physics":
                image = R.drawable.physics;
                break;
            case "pressure":
                image = R.drawable.pressure;
                break;
            default:
                Log.d(TAG, "getImage: no image!!!" + image);
                return 0;
        }
        return image; //TODO: test image log.
    }

}
