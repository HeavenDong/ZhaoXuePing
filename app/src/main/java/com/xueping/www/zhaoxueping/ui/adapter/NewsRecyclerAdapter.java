package com.xueping.www.zhaoxueping.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.db.Actions;
import com.xueping.www.zhaoxueping.db.User;

import java.util.List;

/**
 * 作者：Created by BarryDong on 2017/9/11.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class NewsRecyclerAdapter extends BaseQuickAdapter<Actions, BaseViewHolder> {
    public NewsRecyclerAdapter(List<Actions> list) {
        super(R.layout.item_actions_layout, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Actions item) {
        helper.setText(R.id.tv_time, item.getActionTime());
        helper.setText(R.id.tv_name, item.getActionName()+":");
        helper.setText(R.id.tv_content, item.getActionContent());
    }
}
