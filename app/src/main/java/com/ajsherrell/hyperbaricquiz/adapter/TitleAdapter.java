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
import com.ajsherrell.hyperbaricquiz.model.Titles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

    private static final String TAG = TitleAdapter.class.getSimpleName();
    private Context context;
    private List<Titles> titles;

    // need a click listener interface
    public static final class ClickListener {
        public interface OnItemClickListener {
            void onItemClick(int position);
        }
    }

    private ClickListener.OnItemClickListener onItemClickListener;

    public TitleAdapter(Context context, List<Titles> titles,
                        ClickListener.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.titles = titles;
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
    public void onBindViewHolder(@NonNull TitleAdapter.TitleViewHolder holder, int position) {
        holder.titleTv.setText(titles.get(position).getTitle());

        String categoryTitle = (String) holder.titleTv.getText();
        String CATEGORY_IMAGE = String.valueOf(getImage(Integer.parseInt(categoryTitle)));
        if (!TextUtils.isEmpty(CATEGORY_IMAGE)) {
            Picasso.with(context)
                    .load(CATEGORY_IMAGE.trim())
                    .placeholder(R.drawable.ic_stat_name)
                    .error(R.drawable.ic_stat_name)
                    .into(holder.imageView);
        }
        Log.d(TAG, "onBindViewHolder: !!!this is the category image from title adapter" + CATEGORY_IMAGE);
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    public void add(List<Titles> data) {
        this.titles = data;
    }

    public void clear() {
        titles.clear();;
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
            onItemClickListener.onItemClick(titles.indexOf(position));
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
