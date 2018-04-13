package com.xueping.www.zhaoxueping.common.view.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xueping.www.zhaoxueping.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：Created by EthanDong on 2018/3/12.
 * list:要选择的内容列表
 * showTitle:是否展示标题
 * title:标题内容
 * listener：item点击监听
 * */
public class NormalPickerBsFragment extends BottomSheetDialogFragment {
    Unbinder unbinder;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.recy_header)
    RecyclerViewHeader recyHeader;//compile 'com.bartoszlipinski.recyclerviewheader:library:1.2.0'
    @BindView(R.id.popu_title)
    TextView popuTitle;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private OnItemClickListener onItemClickListener;
    private List<String> names;

    protected Dialog dialog;

    protected View rootView;
    private boolean showTitle;
    private String title;

    public void setOnclickListener(List<String> list, boolean showTitle, String title, OnItemClickListener listener) {
        names = list;
        this.showTitle=showTitle;
        this.title=title;
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Context context;

    public static NormalPickerBsFragment newInstance() {
        return new NormalPickerBsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new MyBottomSheetDialog(getContext(), getTheme());
    }

    @NonNull
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        if (rootView == null) {
            //缓存下来的View 当为空时才需要初始化 并缓存
            rootView = View.inflate(context, R.layout.fragment_normal_picker_bottom_sheet, null);
        }
        //防止重复利用时造成的view has parent的bug
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (null != parent) {
            parent.removeView(rootView);
        }
        dialog.setContentView(rootView);

        unbinder = ButterKnife.bind(this, rootView);

        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.design_bottom_sheet);
        if (showTitle){
            popuTitle.setVisibility(View.VISIBLE);
            popuTitle.setText(title);
        }else {
            popuTitle.setVisibility(View.GONE);
        }
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(linearLayout);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dialog.dismiss();
                    //设置BottomSheetBehavior状态为STATE_COLLAPSED
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        ((View) rootView.getParent()).setBackgroundColor(getResources().getColor(android.R.color
                .transparent));

        if (null == onItemClickListener) {
            dismiss();
            return;
        }

        if (null == names || names.isEmpty()) {
            dismiss();
            return;
        }

        context = getContext();

        if (null == context) {
            dismiss();
            return;
        }

        NormalPickerAdapter adapter = new NormalPickerAdapter(names);
        // 页面列表
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new RecyclerViewDivider(LinearLayoutManager.VERTICAL, context
                .getResources()
                .getColor(R.color.color_divider_dark), context));
        recyHeader.attachTo(rv,true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        dismiss();
    }
}