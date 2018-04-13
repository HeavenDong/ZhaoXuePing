package com.xueping.www.zhaoxueping.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpToolbarActivity;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.db.Actions;
import com.xueping.www.zhaoxueping.db.GreenDaoManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * 作者：Created by EthanDong on 2018/3/13.
 **/

@Route(path = MyRoute.AddNotificationsActivity)
public class AddNotificationsActivity extends BaseMvpToolbarActivity {
    @BindView(R.id.announcement)
    CheckBox announcement;
    @BindView(R.id.notice)
    CheckBox notice;
    @BindView(R.id.article)
    CheckBox article;
    @BindView(R.id.et_content)
    EditText etContent;

    @Override
    public BasePresenterImpl initPresenter() {
        return new BasePresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addmessage);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar(){
        toolbarTitleTv.setText("发布通知");
        toolbar.setNavigationIcon(R.mipmap.toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {

    }

    @OnClick({R.id.article,R.id.notice,R.id.announcement,R.id.insert_notice})
    public void OnViewClicked(View view){
        switch (view.getId()){
            case R.id.announcement:
                notice.setChecked(false);
                article.setChecked(false);
                break;
            case R.id.notice:
                announcement.setChecked(false);
                article.setChecked(false);
                break;
            case R.id.article:
                notice.setChecked(false);
                announcement.setChecked(false);
                break;
            case R.id.insert_notice:
                if (!notice.isChecked()&&!announcement.isChecked()&&!article.isChecked()){
                    ToastUtil.showToast("请选择消息性质");
                    return;
                }
                if (TextUtils.isEmpty(etContent.getText().toString().trim())){
                    ToastUtil.showToast("请输入消息内容");
                    return;
                }
                Actions actions=new Actions();
                if (announcement.isChecked()){
                    actions.setActionName("公告");
                }else if (notice.isChecked()){
                    actions.setActionName("通知");
                }else if (article.isChecked()){
                    actions.setActionName("推荐文章");
                }

                actions.setActionContent(etContent.getText().toString().trim());

                long currentTime = System.currentTimeMillis();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date(currentTime);
                actions.setActionTime(formatter.format(date));

                GreenDaoManager.getInstance(AddNotificationsActivity.this).getSession().getActionsDao().insertOrReplace(actions);
                ToastUtil.showToast("发布成功");
                finish();
                break;
        }
    }
}
