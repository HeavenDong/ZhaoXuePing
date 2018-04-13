package com.xueping.www.zhaoxueping.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpToolbarActivity;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.common.utils.GlideCircleTransform;
import com.xueping.www.zhaoxueping.db.GreenDaoManager;
import com.xueping.www.zhaoxueping.db.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Created by BarryDong on 2017/9/14.
 * 邮箱：barry.dong@tianyitechs.com
 */
@Route(path= MyRoute.DetailsActivity)
public class DetailsActivity extends BaseMvpToolbarActivity{
    @BindView(R.id.iv_avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.identity)
    TextView identity;
    @BindView(R.id.description)
    TextView description;

    @Override
    public BasePresenterImpl initPresenter() {
        return new BasePresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_details);
        ButterKnife.bind(this);

       String code= getIntent().getStringExtra("code");
        Log.e("haifeng","code:"+code);

        User currentUser= GreenDaoManager.getInstance(this).getSession().getUserDao().load(code);
        initToolbar();
       initView(currentUser);
    }
    private void initToolbar(){
        toolbarTitleTv.setText("学员信息");
        toolbar.setNavigationIcon(R.mipmap.toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView(User user) {
        if (null==user){
            return;
        }
        Glide.with(this).load(user.getAvatarUrl())
                .placeholder(R.mipmap.default_avatar)
                .error(R.mipmap.default_avatar)
                .transform(new GlideCircleTransform(DetailsActivity.this)).into(avatar);
        if (!TextUtils.isEmpty(user.getName())){
            name.setText(user.getName());
        }
        if (!TextUtils.isEmpty(user.getCode())){
            code.setText(user.getCode());
        }
//        if (user.getGender()==0){
//            gender.setText("女");
//        }else if (user.getGender()==1){
//            gender.setText("男");
//        }else {
//            gender.setText("性别填写错误");
//        }
//        if (!TextUtils.isEmpty(user.getMobile())){
//            mobile.setText(user.getMobile());
//        }
//        if (!TextUtils.isEmpty(user.getIdentity())){
//            identity.setText(user.getIdentity());
//        }
//        if (!TextUtils.isEmpty(user.getDescription())){
//            description.setText(user.getDescription());
//        }


    }
}
