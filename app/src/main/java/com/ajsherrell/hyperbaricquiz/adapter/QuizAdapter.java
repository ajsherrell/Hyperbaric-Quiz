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

    private Context mContext;
    private List<QuizContent> quizList;

    // need a click listener interface
    public static final class ClickListener {
        public interface OnItemClickListener {
            void onItemClick(int position);
        }
    }

    private ClickListener.OnItemClickListener onItemClickListener;

    public QuizAdapter(Context context, List<QuizContent> quizList,
                       ClickListener.OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.quizList = quizList;
        this.onItemClickListener = onItemClickListener;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView recyclerView;
        ImageView imageView;
        TextView titleTextView;

        public QuizViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.image_rv);
            view.setOnClickListener(this);
            titleTextView = view.findViewById(R.id.category_list_tv);
            imageView = view.findViewById(R.id.category_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onItemClickListener.onItemClick(quizList.indexOf(position));
            Log.d(TAG, "onClick: this is onClick in Adapter at position: " + position);
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
        holder.titleTextView.setText((CharSequence) quizList.get(position).getTitle());
        String cTitle = (String) holder.titleTextView.getText();
        String CATEGORY_IMAGE = String.valueOf(getImage(Integer.parseInt(cTitle)));
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

    public int getImage(int image) {
        String category = null;
        switch (category) {
            case "Physics":
                image = R.drawable.physics;
                break;
            case "Pressure":
                image = R.drawable.pressure;
                break;
            default:
                Log.d(TAG, "getImage: no image!!!" + image);
                return 0;
        }
        return image; //TODO: test image log.
    }
}
