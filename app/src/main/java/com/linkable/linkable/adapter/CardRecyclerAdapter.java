package com.linkable.linkable.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.linkable.linkable.Data;
import com.linkable.linkable.R;

import java.util.ArrayList;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public CardRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(listData.get(i), itemViewHolder);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        Log.i("qwer", "" + listData);
        return listData.size();
    }

    public void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cardImageView);
        }

        void onBind(final Data data, ItemViewHolder viewHolder) {
            Glide.with(viewHolder.imageView.getContext()).load(data.getImagesource()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra("bookid", data.getNode());
                    intent.putExtra("title", textView1.getText().toString());
                    intent.putExtra("author", textView2.getText().toString());
                    intent.putExtra("imageurl", data.getImagesource());
                    intent.putExtra("description", data.getDescription());
                    v.getContext().startActivity(intent);
                    */
                }
            });
        }
    }
}
