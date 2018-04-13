package com.xueping.www.zhaoxueping.ui.fragment;

import android.os.Bundle;
import android.provider.SyncStateContract;
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
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.db.Actions;
import com.xueping.www.zhaoxueping.db.GreenDaoManager;
import com.xueping.www.zhaoxueping.db.User;
import com.xueping.www.zhaoxueping.ui.activity.AddNotificationsActivity;
import com.xueping.www.zhaoxueping.ui.adapter.NewsRecyclerAdapter;
import com.xueping.www.zhaoxueping.ui.presenter.HomePresenterImpl;
import com.xueping.www.zhaoxueping.ui.presenter.ShopPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Created by BarryDong on 2017/9/6.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class MessageFragment extends BaseMvpFragment{
    Unbinder unbinder;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    private List<Actions> list=new ArrayList<>();
    private NewsRecyclerAdapter adapter;

    @Override
    public ShopPresenterImpl initPresenter() {
        return new ShopPresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_message,null);
        unbinder= ButterKnife.bind(this, view);
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
        adapter = new NewsRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        //设置列表Item点击监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取点击条目的数据
                Actions item= (Actions) adapter.getData().get(position);
                //item点击进详情
//                ARouter.getInstance().build(MyRoute.MessageDetailActivity)
//                        .withObject("item_message", list)
//                        .navigation(getActivity(),100);

                ARouter.getInstance().build(MyRoute.MessageDetailActivity)
                        .withParcelable("item_message", item)
                        .navigation(getActivity(),100);
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

    private void getData() {
        list.clear();
        Actions actions=new Actions();
        actions.setActionName("公告");
        actions.setActionContent("欢迎各位同学，这里是电磁实验室考勤信息系统。"
                + "\n\n好男人就是我，我就赵雪平！！！"
                + "\n我之所以制作这个app，一是为了迎接即将到来的毕业；二是出于对互联网行业的热爱。"
                + "\n\n众里寻她千百度，蓦然回首，那人依旧对我不屑一顾。"
                + "\n我当年也是个痴情的种子，结果下了场雨……淹死了。"
                + "\n要是在欺负我,我就把你的名字写在内裤上，用屁崩死你。"
                + "\n杜蕾斯破产不是悲剧，杜蕾斯破了才是悲剧。"
                + "\n孔子曰，中午不睡，下午崩溃；孟子曰，孔子说的对。"


        );
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(currentTime);
        actions.setActionTime(formatter.format(date));
        list.add(actions);

        List<Actions> mDataList= GreenDaoManager.getInstance(getContext().getApplicationContext()).getSession().getActionsDao().loadAll();
        if (mDataList!=null&&mDataList.size()>0){
            list.addAll(mDataList);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
