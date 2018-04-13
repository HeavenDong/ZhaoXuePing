package com.xueping.www.zhaoxueping.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpFragment;
import com.xueping.www.zhaoxueping.common.utils.CommonUtils;
import com.xueping.www.zhaoxueping.ui.presenter.ShowPresenterImpl;
import com.xueping.www.zhaoxueping.ui.view.ShowView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * 作者：Created by BarryDong on 2017/9/6.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class ShowFragment extends BaseMvpFragment<ShowView,ShowPresenterImpl> implements ShowView{
    Unbinder unbinder;
    @Override
    public ShowPresenterImpl initPresenter() {
        return new ShowPresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, null);
        unbinder= ButterKnife.bind(this, view);
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
            Timber.e("显示");
        }else {
            Timber.e("隐藏");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
