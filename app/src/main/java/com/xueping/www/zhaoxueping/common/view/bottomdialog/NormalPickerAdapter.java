package com.xueping.www.zhaoxueping.common.view.bottomdialog;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueping.www.zhaoxueping.R;

import java.util.List;

/**
 * 作者：Created by EthanDong on 2018/3/12.
 * 选择器适配器
 */
public class NormalPickerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public NormalPickerAdapter(List<String> data) {
        super(R.layout.item_normal_picker, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String item) {
        viewHolder.setText(R.id.tv_item_name, item);
    }

    @Override
    public void addData(int position, String data) {
        List<String> list = getData();
        if (null == list) {
            return;
        }
        if (list.contains(data)) {
            return;
        }
        super.addData(position, data);
    }
}