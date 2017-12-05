package com.saman.zhihuad;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int type = 0;
    private int orientation;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientation = getIntent().getIntExtra("index", 0);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = ((RecyclerView) findViewById(R.id.recycler));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TestAdapter(new AlpahCallBack() {
            @Override
            public void call() {
                type = 1;
                recyclerView.animate().alpha(0).setDuration(400).setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        recyclerView.setVisibility(View.GONE);
                    }
                }).start();
            }
        }));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                int endPosition = linearLayoutManager.findLastVisibleItemPosition();
                for (int i = firstPosition; i <= endPosition; i++) {
                    RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                    if (holder instanceof AlphaHolder) {
                        View view = holder.itemView;
                        CanTraImageView canTraImageView = ((AlphaHolder) holder).canTraImageView;
                        canTraImageView.setTraPercent(view.getTop(), linearLayoutManager.getHeight(), orientation);
                    }
                }
            }
        });
    }

    private class TestAdapter extends RecyclerView.Adapter {

        private AlpahCallBack callBack;

        public TestAdapter(AlpahCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == 1 ? new AlphaHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.alpha_item, parent, false)) :
                    new NormalHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.normal_item, parent, false));
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof AlphaHolder) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callBack != null) {
                            callBack.call();
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 40;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 8 ? 1 : 0;
        }
    }

    private class NormalHolder extends RecyclerView.ViewHolder {

        public NormalHolder(View itemView) {
            super(itemView);
        }
    }

    private class AlphaHolder extends RecyclerView.ViewHolder {

        CanTraImageView canTraImageView;

        public AlphaHolder(View itemView) {
            super(itemView);
            canTraImageView = (CanTraImageView) itemView.findViewById(R.id.item);
        }
    }

    private interface AlpahCallBack {
        void call();
    }

    @Override
    public void onBackPressed() {
        if (type == 1) {
            type = 0;
            recyclerView.animate().alpha(1).setDuration(400).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    recyclerView.setVisibility(View.VISIBLE);
                }

            }).start();
        } else {
            super.onBackPressed();
        }
    }
}
