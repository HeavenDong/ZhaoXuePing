package com.xueping.www.zhaoxueping.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpToolbarActivity;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.db.Actions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Created by EthanDong on 2018/3/14.
 **/
@Route(path = MyRoute.MessageDetailActivity)
public class MessageDetailActivity extends BaseMvpToolbarActivity {
    @Autowired(name = "item_message")
    Actions actions;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.time)
    TextView time;
    @Override
    public BasePresenterImpl initPresenter() {
        return new BasePresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        initToolbar();
        initView();
    }

    private void initToolbar(){
        toolbarTitleTv.setText(actions.getActionName());
        toolbar.setNavigationIcon(R.mipmap.toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        content.setText(actions.getActionContent());
        time.setText(actions.getActionTime());
    }
}
