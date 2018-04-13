package com.xueping.www.zhaoxueping.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.common.utils.GlideCircleTransform;
import com.xueping.www.zhaoxueping.common.view.CircularImageView;
import com.xueping.www.zhaoxueping.db.User;

import java.io.File;
import java.util.List;

/**
 * 作者：Created by BarryDong on 2017/9/11.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class UserRecyclerAdapter extends BaseQuickAdapter<User, BaseViewHolder> {
    public UserRecyclerAdapter(List<User> list) {
        super(R.layout.item_user_layout, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_code,item.getCode());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_time,item.getTime());

        Glide.with(mContext).load(item.getAvatarUrl())
                .placeholder(R.mipmap.default_avatar)
                .error(R.mipmap.default_avatar)
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView) helper.getView(R.id.iv_head));
//        CircularImageView circularImageView=(CircularImageView)helper.getView(R.id.iv_head);

//        Glide.with(mContext).load("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg")
//                .placeholder(R.mipmap.default_avatar)
//                .error(R.mipmap.logo)
//                .into(circularImageView);
//        Glide.with(mContext).load("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg").into(circularImageView);
    }
}
