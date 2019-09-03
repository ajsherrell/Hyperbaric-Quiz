package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.R;
import com.ajsherrell.hyperbaricquiz.model.Titles;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

    private static final String TAG = TitleAdapter.class.getSimpleName();
    private Context context;
    private List<Titles> titles;

    public TitleAdapter(Context context, List<Titles> titles) {
        this.context = context;
        this.titles = titles;
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
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;

        public TitleViewHolder(@NonNull View v) {
            super(v);
            titleTv = v.findViewById(R.id.category_list_tv);
        }
    }
}
