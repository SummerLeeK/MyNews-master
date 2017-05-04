package com.news.lee.mynews.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.news.lee.mynews.DaoMaster;
import com.news.lee.mynews.DaoSession;
import com.news.lee.mynews.Favorites;
import com.news.lee.mynews.FavoritesDao;
import com.news.lee.mynews.R;
import com.news.lee.mynews.activity.BaseActivity;
import com.news.lee.mynews.activity.DetailActivity;
import com.news.lee.mynews.adapter.CollectionAdapter;
import com.news.lee.mynews.adapter.MyBaseAdapter;
import com.news.lee.mynews.bean.ZhihuStories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by lee on 17-4-2.
 */

public class CollectionFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, CollectionAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private CollectionAdapter adapter;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tv_null;
    private LinearLayout linearLayout;

    private List<Favorites> mList = new ArrayList<>();

    private static final int ZHIHU_TYPE = 1;
    private static final int WECHAT_TYPE = 2;

    private static final String TAG_ZHIHU = "ZHIHU";
    private static final String TAG_WECHAT = "WECHAT";

    private DaoMaster master;
    private DaoMaster.DevOpenHelper helper;
    private DaoSession session;
    private FavoritesDao favoritesDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;

        root = inflater.inflate(R.layout.collection_item_view, null);
        recyclerView = (RecyclerView) root.findViewById(R.id.collection_recycle);
        tv_null = (TextView) root.findViewById(R.id.tv_null);
        linearLayout = (LinearLayout) root.findViewById(R.id.linear);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

                outRect.set(0,5,0,5);
            }
        });


        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, 1 << 2 | 1 << 3) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


                if (direction == ItemTouchHelper.LEFT) {

                    final int postion = viewHolder.getAdapterPosition();
                    final Favorites favorites = mList.get(postion);
                    DeleteData(favorites);
                    mList.remove(postion);
                    adapter.notifyItemRemoved(postion);
                    Snackbar.make(linearLayout, "删除成功～", Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mList.add(postion, favorites);
                            InsertData(favorites);
                            adapter.notifyDataSetChanged();

                        }
                    }).show();


                }


            }

            //处理动画
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    //滑动时改变 Item 的透明度，以实现滑动过程中实现渐变效果
                    if (dX < 0) {
                        final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                        viewHolder.itemView.setAlpha(alpha);
                        viewHolder.itemView.setTranslationX(dX);
                    }
                }

            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);

            }
        });

        adapter = new CollectionAdapter(mContext);

        adapter.setItemClickListener(this);

        recyclerView.setAdapter(adapter);


        touchHelper.attachToRecyclerView(recyclerView);

        if (mList.size() != 0) {
            adapter.setData(mList);
            tv_null.setVisibility(View.GONE);

        }


        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);


        return root;
    }

    private void InsertData(Favorites f) {

        favoritesDao.insert(f);
    }

    private void DeleteData(Favorites f) {

        favoritesDao.delete(f);
    }

    private void initData() {

        helper = new DaoMaster.DevOpenHelper(mContext, "favorites.db", null);
        master = new DaoMaster(helper.getWritableDatabase());
        session = master.newSession();
        favoritesDao = session.getFavoritesDao();
        mList = favoritesDao.loadAll();
    }


    @Override
    public void onRefresh() {

        initData();

        swipeRefreshLayout.setRefreshing(false);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        Favorites f = mList.get(position);
        if (f.getType() == ZHIHU_TYPE) {
            intent.putExtra("type", TAG_ZHIHU);
            intent.putExtra("title", f.getTitle());
            intent.putExtra("url", f.getId() + "");
            startActivity(intent);

        } else if (f.getType() == WECHAT_TYPE) {

            intent.putExtra("type", TAG_WECHAT);
            intent.putExtra("title", f.getTitle());
            intent.putExtra("url", f.getUrl());
            intent.putExtra("PicUrl",f.getImage());
            startActivity(intent);
        }


    }
}
