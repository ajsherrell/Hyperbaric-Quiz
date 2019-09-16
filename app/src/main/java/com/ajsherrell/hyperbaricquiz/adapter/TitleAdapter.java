package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.Constants;
import com.ajsherrell.hyperbaricquiz.R;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.ajsherrell.hyperbaricquiz.model.Titles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

    private static final String TAG = TitleAdapter.class.getSimpleName();
    private QuizContent content;
    private Context context;

    private Constants.ClickListener.OnItemClickListener onItemClickListener;

    public TitleAdapter(QuizContent content,
                        Constants.ClickListener.OnItemClickListener onItemClickListener) {
        this.content = content;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TitleAdapter.TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list, parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleAdapter.TitleViewHolder holder, final int position) {
        holder.titleTv.setText(content.getTitle().get(position));
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
        Log.d(TAG, "onBindViewHolder: !!!this is the category image from title adapter" + CATEGORY_IMAGE);
    }

    @Override
    public int getItemCount() {
        return content == null ? 0 : content.getTitle().size();
    }

    public void add(QuizContent data) {
        this.content = data;
        notifyDataSetChanged();
    }

    public void clear() {
        content.getTitle().clear();
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView recyclerView;
        ImageView imageView;
        public TextView titleTv;

        public TitleViewHolder(@NonNull View v) {
            super(v);
            recyclerView = v.findViewById(R.id.image_rv);
            titleTv = v.findViewById(R.id.category_list_tv);
            imageView = v.findViewById(R.id.category_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onItemClickListener.onItemClick(content.getTitle().indexOf(position));
            Log.d(TAG, "onClick: !!!in title adapter at position " + position);
        }
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
