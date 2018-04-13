package com.xueping.www.zhaoxueping.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xueping.www.zhaoxueping.App;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpToolbarActivity;
import com.xueping.www.zhaoxueping.common.utils.TabEntity;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.ui.fragment.HomeFragment;
import com.xueping.www.zhaoxueping.ui.fragment.MeFragment;
import com.xueping.www.zhaoxueping.ui.fragment.MessageFragment;
import com.xueping.www.zhaoxueping.ui.presenter.MainPresenterImpl;
import com.xueping.www.zhaoxueping.ui.view.IMainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path= MyRoute.MainActivity)
public class MainActivity extends BaseMvpToolbarActivity<IMainView, MainPresenterImpl> implements IMainView {
    //标题
    @BindView(R.id.toolbar_title_tv)
    protected TextView toolbarTitleTv;
    //容器
    @BindView(R.id.fl_body)
    FrameLayout flBody;
    //底部导航栏
    @BindView(R.id.tl_bottom_bar)
    CommonTabLayout tlBottomBar;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] tags;
    private String[] mTitles;
    //未选中时icon
    private int[] mTabNormal = {R.mipmap.seeit_normal_2x,
//            R.mipmap.loveit_normal_2x,
            R.mipmap.shopit_normal_2x,
            R.mipmap.me_normal_2x};
    // 选中时icon
    private int[] mTabPressed = {R.mipmap.seeit_pressed_2x,
//            R.mipmap.loveit_pressed_2x,
            R.mipmap.shopit_pressed_2x,R.mipmap.me_pressed_2x};
    private Fragment homeFragment,showFragment,shopFragment,meFragment;
    private FragmentManager fragmentManager;

    private long exitTime;//记录第二次返回键点击时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getInstance().addActivity(this);
        initView();
    }

    @Override
    public MainPresenterImpl initPresenter() {
        return new MainPresenterImpl();
    }

    private void initView() {
        //标记fragment
        tags=new String[]{"home",
//                "show",
                "news","me"};
        // Tab页面标题
        mTitles = getResources().getStringArray(R.array.tab_name);

        fragmentManager = getSupportFragmentManager();
        homeFragment=new HomeFragment();
//        showFragment=new ShowFragment();
        shopFragment=new MessageFragment();
        meFragment=new MeFragment();
        mFragments.add(homeFragment);
//        mFragments.add(showFragment);
        mFragments.add(shopFragment);
        mFragments.add(meFragment);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mTabPressed[i], mTabNormal[i]));
        }
        // 填充布局
        tlBottomBar.setTabData(mTabEntities, this, R.id.fl_body, mFragments);

        tlBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position){
                    case 0:
                        toolbar.setVisibility(View.VISIBLE);
                        toolbarTitleTv.setText(mTitles[0]);
                        break;
                    case 1:
                        toolbar.setVisibility(View.VISIBLE);
                        toolbarTitleTv.setText(mTitles[1]);
                        break;
//                    case 2:
//                        toolbar.setVisibility(View.VISIBLE);
//                        toolbarTitleTv.setText(mTitles[2]);
//                        break;
                    case 2:
                        toolbar.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {}
        });

        // 默认首页选中
        tlBottomBar.setCurrentTab(0);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText(mTitles[0]);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
           if ((System.currentTimeMillis()-exitTime)>2000){
               ToastUtil.showToast("再按一次退出");
               exitTime=System.currentTimeMillis();
           }else {
               App.getInstance().onTerminate();
           }
           return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
