package com.xueping.www.zhaoxueping.ui.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpFragment;
import com.xueping.www.zhaoxueping.common.utils.CommonUtils;
import com.xueping.www.zhaoxueping.db.Actions;
import com.xueping.www.zhaoxueping.db.GreenDaoManager;
import com.xueping.www.zhaoxueping.db.User;
import com.xueping.www.zhaoxueping.ui.adapter.NewsRecyclerAdapter;
import com.xueping.www.zhaoxueping.ui.adapter.UserRecyclerAdapter;
import com.xueping.www.zhaoxueping.ui.presenter.HomePresenterImpl;
import com.xueping.www.zhaoxueping.ui.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * 作者：Created by BarryDong on 2017/9/6.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class HomeFragment extends BaseMvpFragment<HomeView,HomePresenterImpl> implements HomeView{
    Unbinder unbinder;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    private List<User> list=new ArrayList<>();
    private UserRecyclerAdapter adapter;
    @Override
    public HomePresenterImpl initPresenter() {
        return new HomePresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.setStatusBarColor(getActivity());
        initView();
    }
    private void initView() {
        //创建默认的线性LayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        //创建并设置Adapter
        adapter = new UserRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        //设置列表Item点击监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取点击条目的数据
                User item= (User) adapter.getData().get(position);
                //item点击进详情
//                ARouter.getInstance().build(MyRoute.MessageDetailActivity)
//                        .withObject("item_message", list)
//                        .navigation(getActivity(),100);

                ARouter.getInstance().build(MyRoute.DetailsActivity)
                        .withString("code", item.getCode())
                        .navigation(getActivity(),200);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (getActivity() == null) {
                return;
            }
            getData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getData() {
        List<User> mDataList= GreenDaoManager.getInstance(getContext().getApplicationContext()).getSession().getUserDao().loadAll();
        if (mDataList==null||mDataList.size()<=0){
            tvEmpty.setVisibility(View.VISIBLE);
        }else {
            tvEmpty.setVisibility(View.GONE);
            list.clear();
            list.addAll(mDataList);
            adapter.notifyDataSetChanged();
        }
    }
}
