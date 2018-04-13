package com.xueping.www.zhaoxueping.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.githang.statusbar.StatusBarCompat;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpFragment;
import com.xueping.www.zhaoxueping.common.utils.CommonUtils;
import com.xueping.www.zhaoxueping.ui.presenter.MePresenterImpl;
import com.xueping.www.zhaoxueping.ui.view.MeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：Created by BarryDong on 2017/9/6.
 * 邮箱：barry.dong@tianyitechs.com
 */
@Route(path= MyRoute.MeFragment)
public class MeFragment extends BaseMvpFragment<MeView,MePresenterImpl>implements View.OnClickListener{
    Unbinder unbinder;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @Override
    public MePresenterImpl initPresenter() {
        return new MePresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.setStatusBarColor(getActivity());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor();
//            Glide.with(getContext()).load("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg")
//                    .placeholder(R.mipmap.default_avatar)
//                    .error(R.mipmap.default_avatar)
//                    .transform(new GlideCircleTransform(getContext())).into(ivAvatar);
        } else {
            CommonUtils.setStatusBarColor(getActivity());
        }
    }

    private void setStatusBarColor() {
        if (getActivity() == null) {
            return;
        }
        StatusBarCompat.setStatusBarColor(getActivity(), Color.parseColor("#83b7fe"));
    }

    @OnClick({R.id.add_user,R.id.query_user,R.id.add_message})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.query_user:
                ARouter.getInstance().build(MyRoute.DragSwipeListActivity).navigation();
                break;
            case R.id.add_user:
                ARouter.getInstance().build(MyRoute.InsertDBActivity).navigation();
//////                        .withString(Constants.EXTRA_SHARE_TITLE, mTitle)
//////                        .withString(Constants.EXTRA_SHARE_CONTENT, mContent)
//////                        .withString(Constants.EXTRA_SHARE_ICON_URL, mIconUrl)
//////                        .withString(Constants.EXTRA_SHARE_URL, mUrl)
//                        .navigation();
                break;
            case R.id.add_message:
                ARouter.getInstance().build(MyRoute.AddNotificationsActivity).navigation();
//                startActivity(new Intent(getActivity(),DragSwipeListActivity.class));
                break;

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
