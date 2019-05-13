package com.linkable.linkable.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkable.linkable.Category;
import com.linkable.linkable.Data;
import com.linkable.linkable.R;

import java.util.ArrayList;

public class GaugeRecyclerAdapter extends RecyclerView.Adapter<GaugeRecyclerAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Category> listData = new ArrayList<>();

    static public String gaugeList = "";

    @NonNull
    @Override
    public GaugeRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gauge_item, parent, false);
        return new GaugeRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaugeRecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(listData.get(i), itemViewHolder);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        Log.i("qwer", "" + listData);
        return listData.size();
    }

    public void addItem(Category data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private SeekBar seekBar;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.gaugeCategoryTextView);
            seekBar = itemView.findViewById(R.id.gaugeBar);
        }

        void onBind(final Category data, ItemViewHolder viewHolder) {
            textView.setText(data.getCategory());
        }
    }
}
