package com.xueping.www.zhaoxueping.base.mvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.common.view.LoadingDialog;


/**
 * mvp Fragment 基础类
 */
public abstract class BaseMvpFragment<V extends IBaseView, T extends BasePresenterImpl<V>> extends Fragment
        implements IBaseView {

    public T presenter;

    protected Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//        CustomImageLoader.getInstance().addGlideRequests(this);//申请图片加载功能
        presenter = initPresenter();
        presenter.attachView((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
//        CustomImageLoader.getInstance().removeGlideRequests(this);
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 初始化 任命者
     *
     * @return
     */
    public abstract T initPresenter();

    @Override
    public void showMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            ToastUtil.showToast(message);
        }
    }

    @Override
    public void showMessage(@StringRes int stringId) {
        if (!TextUtils.isEmpty(getString(stringId))) {
            ToastUtil.showToast(getString(stringId));
        }
    }

    @Override
    public void showLoadingDialog(@StringRes int stringId) {
        dialog = LoadingDialog.createLoadingDialog(getActivity(), getString(stringId));
        dialog.show();
    }

    @Override
    public void showLoadingDialog(String message) {
        dialog = LoadingDialog.createLoadingDialog(getActivity(), message);
        dialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

//    public void logout() {
//        User.deleteLoginUser();
//        final HintDialog hd = new HintDialog(getContext());
//        hd.show(getActivity().getString(R.string.logout), 3);
//        hd.setDialogButtonListener1(new HintDialog.DialogButtonListener1() {
//            @Override
//            public void onSureClick() {
//                hd.dismiss();
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.setAction(Constants.ACTION_TO_LOGIN);
//                startActivity(intent);
//            }
//        });
//    }
}
