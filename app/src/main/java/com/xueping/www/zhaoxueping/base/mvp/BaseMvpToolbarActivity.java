package com.xueping.www.zhaoxueping.base.mvp;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.umeng.message.PushAgent;

import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.common.utils.CommonUtils;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.common.view.LoadingDialog;

import butterknife.BindView;


/**
 * mvp 基类
 */
public abstract class BaseMvpToolbarActivity<V extends IBaseView, T extends BasePresenterImpl<V>> extends BaseActivity
        implements IBaseView {

    public T presenter;
    @BindView(R.id.toolbar_title_tv)
    protected TextView toolbarTitleTv;
    @BindView(R.id.toolbar_title_et)
    protected TextView toolbarTitleEt;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.root_layout)
    protected LinearLayout rootLayout;
    @BindView(R.id.divider)
    protected View divider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
//        CustomImageLoader.getInstance().addGlideRequests(this);//申请图片加载功能
//        PushAgent.getInstance(this).onAppStart();
        presenter = initPresenter();
        presenter.attachView((V) this);
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
        CommonUtils.setStatusBarColor(this);
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) {
            return;
        }
        rootLayout.addView(view, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
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

//    @Override
//    public void logout() {
//        User.deleteLoginUser();
//        final HintDialog hd = new HintDialog(this);
//        hd.show(getString(R.string.logout), 3);
//        hd.setDialogButtonListener1(new HintDialog.DialogButtonListener1() {
//            @Override
//            public void onSureClick() {
//                hd.dismiss();
//                Intent intent = new Intent(BaseMvpToolbarActivity.this, MainActivity.class);
//                intent.setAction(Constants.ACTION_TO_LOGIN);
//                startActivity(intent);
//            }
//        });
//    }
}
