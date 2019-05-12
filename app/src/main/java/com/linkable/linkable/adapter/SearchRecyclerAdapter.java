package com.linkable.linkable.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkable.linkable.Data;
import com.linkable.linkable.R;

import java.util.ArrayList;
import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ItemViewHolder> {
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public SearchRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new SearchRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(listData.get(i),itemViewHolder, i);
    }


    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        Log.i("qwer", "" + listData);
        return listData.size();
    }

    public void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        //if (listData.size() < 5)
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.bookTitleTextView);
            textView2 = itemView.findViewById(R.id.bookAuthorTextView);
            imageView = itemView.findViewById(R.id.bookImage);
        }

        void onBind(final Data data,ItemViewHolder viewHolder, final int index) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getAutor());
            Glide.with(viewHolder.imageView.getContext()).load(data.getImagesource()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra("index",index);
                    v.getContext().startActivity(intent);*/
                }
            });

        }
    }
}
