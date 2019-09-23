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

    private Context context;
    private List<QuizContent> quizList;

    private Constants.ClickListener.OnItemClickListener onItemClickListener;

    public QuizAdapter(Context context, List<QuizContent> quizList,
                       Constants.ClickListener.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.quizList = quizList;
        this.onItemClickListener = onItemClickListener;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView recyclerView;
        TextView titleTv;
        TextView idTv;
        TextView questionTv;
        RadioButton optionA;
        RadioButton optionB;
        RadioButton optionC;
        TextView answer;
        ImageView imageView;

        public QuizViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.image_rv);
            titleTv = view.findViewById(R.id.category_name);
            idTv = view.findViewById(R.id.id);
            questionTv = view.findViewById(R.id.question);
            optionA = view.findViewById(R.id.radio_A);
            optionB = view.findViewById(R.id.radio_B);
            optionC = view.findViewById(R.id.radio_C);
            answer = view.findViewById(R.id.answer);
            imageView = view.findViewById(R.id.category_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onItemClickListener.onItemClick(position);
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

        holder.titleTv.setText(content.getTitles());
        holder.idTv.setText(content.getId());
        holder.questionTv.setText(content.getQuestion());
        holder.answer.setText(content.getAnswer());

        List<String> optionsArr;
        optionsArr = content.getOptions();
        Collections.shuffle(optionsArr);
        holder.optionA.setText(optionsArr.get(0));
        holder.optionB.setText(optionsArr.get(1));
        holder.optionC.setText(optionsArr.get(2));

        String categoryTitle = (String) holder.titleTv.getText();
        String CATEGORY_IMAGE = String.valueOf(getImage(Integer.parseInt(categoryTitle)));
        if (!TextUtils.isEmpty(CATEGORY_IMAGE)) {
            Picasso.with(context)
                    .load(CATEGORY_IMAGE.trim())
                    .placeholder(R.drawable.ic_stat_name)
                    .error(R.drawable.ic_stat_name)
                    .into(holder.imageView);
        }
        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) onItemClickListener.onItemClick(position);
            }
        });
        Log.d(TAG, "onBindViewHolder: !!!this is the category image from quiz adapter" + CATEGORY_IMAGE);
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

    //get titles
    public List<String[]> getTitleArr(List title) {
        String category = null;
        switch (category) {
            case "physics":
                title.toArray(new String[]{category});//TODO: fix this.
                break;
            case "pressure":
                title.toArray(new String[]{category});
                break;
            default:
                Log.d(TAG, "getTitleArr: this is the title array " + title);
                return null;
        }
        return title;

    }
}
