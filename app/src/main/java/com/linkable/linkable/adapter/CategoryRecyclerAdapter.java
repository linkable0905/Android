package com.linkable.linkable.adapter;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.linkable.linkable.Category;
import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.activity.BookListActivity;
import com.linkable.linkable.activity.RetrofitExService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.CategoryActivity.selected;
import static com.linkable.linkable.activity.MainActivity.URL;
import static com.linkable.linkable.activity.LoginActivity.token;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Category> listData = new ArrayList<>();

    @NonNull
    @Override
    public CategoryRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
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
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTextView;
        private ToggleButton starButton;

        ItemViewHolder(View itemView) {
            super(itemView);

            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            starButton = itemView.findViewById(R.id.starButton);
        }

        void onBind(final Category data, ItemViewHolder viewHolder) {
            starButton.setChecked(false);
            String[] nums = selected.split(" ");
            for (String i : nums) {
                if (Integer.parseInt(i) == data.getId()) {
                    Log.i("selectedid", i);
                    starButton.setChecked(true);
                }
            }

            categoryTextView.setText(data.getCategory());

            starButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("aaaa", "" + starButton.isChecked());
                    if (starButton.isChecked()) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
                        Call<Category> call = retrofitExService.categoryStar("Token " + token, data.getId()-1);
                        call.enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                if (response.isSuccessful()) {
                                    Log.i("register", "즐겨찾기 성공");
                                    starButton.setChecked(true);
                                } else {
                                    Log.i("plz", response.headers().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {
                                //Log.i("fail", "회원가입 실패");
                            }
                        });
                    }
                    else {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
                        Call call = retrofitExService.deleteCategoryStar("Token "+ token, data.getId()-1);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    Log.i("register", "즐겨찾기 삭제 성공");
                                    starButton.setChecked(false);
                                }
                                else {
                                    Log.i("plz",response.headers().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                //Log.i("fail", "회원가입 실패");
                            }
                        });
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), BookListActivity.class);
                    intent.putExtra("str", data.getCategory());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}