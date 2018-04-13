package com.xueping.www.zhaoxueping.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.base.swipelist.MainAdapter;
import com.xueping.www.zhaoxueping.common.utils.CommonUtils;
import com.xueping.www.zhaoxueping.db.User;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.Collections;
import java.util.List;

/**
 * 作者：Created by BarryDong on 2017/9/13.
 * 邮箱：barry.dong@tianyitechs.com
 */
@Route(path= MyRoute.DragSwipeListActivity)
public class DragSwipeListActivity extends BaseDragActivity {
    private MainAdapter mAdapter;
    private List<User> mDataList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtils.setStatusBarColor(this);
        initToolbar();
        mAdapter = getAdapter();
        mDataList = getDataList();
    }

    @Override
    public BasePresenterImpl initPresenter() {
        return new BasePresenterImpl();
    }

    private void initToolbar(){
        toolbarTitleTv.setText("我的学生");
        toolbar.setNavigationIcon(R.mipmap.toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected OnItemMoveListener getItemMoveListener() {
        // 监听拖拽和侧滑删除，更新UI和数据源。
        return onItemMoveListener;
    }

    /**
     * 监听拖拽和侧滑删除，更新UI和数据源。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            // 不同的ViewType不能拖拽换位置。
            if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;

            // 添加了HeadView时，通过ViewHolder拿到的position都需要减掉HeadView的数量。
            int fromPosition = srcHolder.getAdapterPosition() - getRecyclerView().getHeaderItemCount();
            int toPosition = targetHolder.getAdapterPosition() - getRecyclerView().getHeaderItemCount();

            Collections.swap(mDataList, fromPosition, toPosition);
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;// 返回true表示处理了并可以换位置，返回false表示你没有处理并不能换位置。
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            int position = srcHolder.getAdapterPosition() - getRecyclerView().getHeaderItemCount();
            if (position < 0) return; // 因为添加了HeadView，这里的HeadView是第0个，减掉后肯定是负数，所以不许删除。

            mDataList.remove(position);
            mAdapter.notifyItemRemoved(position);
            Toast.makeText(DragSwipeListActivity.this, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
        }
    };
}
