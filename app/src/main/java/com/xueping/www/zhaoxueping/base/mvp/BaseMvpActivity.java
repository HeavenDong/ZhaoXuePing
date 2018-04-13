package com.xueping.www.zhaoxueping.base.mvp;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.text.TextUtils;

//import com.umeng.message.PushAgent;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.common.utils.CommonUtils;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.common.view.LoadingDialog;


/**
 * mvp 基类
 */
public abstract class BaseMvpActivity<V extends IBaseView, T extends BasePresenterImpl<V>> extends BaseActivity
        implements IBaseView {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PushAgent.getInstance(this).onAppStart();
        presenter = initPresenter();
        presenter.attachView((V) this);
    }

    @Override
    public void setContentView(int layoutId) {
        super.setContentView(layoutId);
        CommonUtils.setStatusBarColor(this);
    }

    @Override
    protected void onDestroy() {
//        CustomImageLoader.getInstance().removeGlideRequests(this);
        if (this.isFinishing()) {
            dismissLoadingDialog();
        }
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
        dialog = LoadingDialog.createLoadingDialog(this, getString(stringId));
        dialog.show();
    }

    @Override
    public void showLoadingDialog(String message) {
        dialog = LoadingDialog.createLoadingDialog(this, message);
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
//        final HintDialog hd = new HintDialog(this);
//        hd.show(getString(R.string.logout), 3);
//        hd.setDialogButtonListener1(new HintDialog.DialogButtonListener1() {
//            @Override
//            public void onSureClick() {
//                hd.dismiss();
//                Intent intent = new Intent(BaseMvpActivity.this, MainActivity.class);
//                intent.setAction(Constants.ACTION_TO_LOGIN);
//                startActivity(intent);
//            }
//        });
//    }

}
