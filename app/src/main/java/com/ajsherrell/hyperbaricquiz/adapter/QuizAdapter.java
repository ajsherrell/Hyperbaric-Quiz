package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.Constants;
import com.ajsherrell.hyperbaricquiz.R;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private static final String TAG = QuizAdapter.class.getSimpleName();

    private Context mContext;
    private List<QuizContent> quizList;

    private Constants.ClickListener.OnItemClickListener onItemClickListener;

    public QuizAdapter(Context context, List<QuizContent> quizList,
                       Constants.ClickListener.OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.quizList = quizList;
        this.onItemClickListener = onItemClickListener;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        RecyclerView titleRecyclerView;
        ImageView imageView;
        TextView titleTv;
        TextView idTv;
        TextView questionTv;
        RadioButton optionA;
        RadioButton optionB;
        RadioButton optionC;
        TextView answer;

        public QuizViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.question_recycler_view);
            titleRecyclerView = view.findViewById(R.id.image_rv);
            imageView = view.findViewById(R.id.category_image);
            titleTv = view.findViewById(R.id.category_list_tv);
            idTv = view.findViewById(R.id.id);
            questionTv = view.findViewById(R.id.question);
            optionA = view.findViewById(R.id.radio_A);
            optionB = view.findViewById(R.id.radio_B);
            optionC = view.findViewById(R.id.radio_C);
            answer = view.findViewById(R.id.answer);
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
        holder.titleTv.setText(quizList.get(position).getTitle().get(position));
        holder.idTv.setText(quizList.get(position).getId());
        holder.questionTv.setText(quizList.get(position).getQuestion());
        holder.answer.setText(quizList.get(position).getAnswer());

        List<String> optionsArr;
        optionsArr = quizList.get(position).getOptions();
        Collections.shuffle(optionsArr);
        holder.optionA.setText(optionsArr.get(0));
        holder.optionB.setText(optionsArr.get(1));
        holder.optionC.setText(optionsArr.get(2));

        //get images
        String categoryTitle = (String) holder.titleTv.getText();
        String CATEGORY_IMAGE = String.valueOf(getImage(Integer.parseInt(categoryTitle)));
        if (!TextUtils.isEmpty(CATEGORY_IMAGE)) {
            Picasso.with(mContext)
                    .load(CATEGORY_IMAGE.trim())
                    .placeholder(R.drawable.ic_stat_name)
                    .error(R.drawable.ic_stat_name)
                    .into(holder.imageView);
        }
        holder.titleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) onItemClickListener.onItemClick(position);
            }
        });
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
