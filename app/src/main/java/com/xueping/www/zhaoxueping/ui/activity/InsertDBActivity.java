package com.xueping.www.zhaoxueping.ui.activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.base.mvp.BaseMvpToolbarActivity;
import com.xueping.www.zhaoxueping.base.mvp.impl.BasePresenterImpl;
import com.xueping.www.zhaoxueping.common.utils.EcnPreferences;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.common.view.bottomdialog.NormalPickerBsFragment;
import com.xueping.www.zhaoxueping.db.GreenDaoManager;
import com.xueping.www.zhaoxueping.db.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * 作者：Created by BarryDong on 2017/9/6.
 * 邮箱：barry.dong@tianyitechs.com
 */
@Route(path= MyRoute.InsertDBActivity)
public class InsertDBActivity extends BaseMvpToolbarActivity {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.add_avatar)
    ImageView addAvatar;

    private String pictureUrl;
    private User user= new User();
    private int REQUEST_CODE = 1000;

    @Override
    public BasePresenterImpl initPresenter() {
        return new BasePresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }
    private void initToolbar(){
        toolbarTitleTv.setText("添加学员");
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

    @OnClick({R.id.butt_add_avatar,R.id.add_person})
    public void OnViewClicked(View v){
        switch (v.getId()){
            case R.id.butt_add_avatar:
                final ArrayList<String> choosePhotoList = new ArrayList<>();
//                choosePhotoList.add(getString(R.string.take_photo));
                choosePhotoList.add("从相册选取");
                choosePhotoList.add("查看大图");
                NormalPickerBsFragment.OnItemClickListener headListener = new
                        NormalPickerBsFragment
                                .OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                switch (position) {
                                    case 0:
                                        //从相册选取
                                        //动态权限
                                        String cachePath = BoxingFileHelper.getCacheDir(InsertDBActivity.this);
                                        if (TextUtils.isEmpty(cachePath)) {
                                            ToastUtil.showToast("设备存储读取出错或暂不可用，请稍候重试");
                                            return;
                                        }
                                        Uri destUri = new Uri.Builder()
                                                .scheme("file")
                                                .appendPath(cachePath)
                                                .appendPath(String.format(Locale.US, "%s.png", System.currentTimeMillis()))
                                                .build();
                                        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                                                .withCropOption(new BoxingCropOption(destUri))
                                                .withMediaPlaceHolderRes(R.mipmap.ic_boxing_default_image)
                                                .needCamera(R.mipmap.ic_boxing_camera_white);//支持相机

                                        Boxing.of(singleCropImgConfig)
                                                .withIntent(InsertDBActivity.this, BoxingActivity.class)
                                                .start(InsertDBActivity.this, REQUEST_CODE);

                                        break;
                                    case 1:
                                        //查看大图
                                        if (TextUtils.isEmpty(user.getAvatarUrl())) {
                                            showMessage("图片为空");
                                            break;
                                        }
//                                        ARouter.getInstance().build(MyRoute.ImageActivity)
//                                                .withInt(Constants.EXTRA_IMAGE_TYPE, Constants.IMAGE_ACTIVITY_TYPE_URL)
//                                                .withString(Constants.EXTRA_IMAGE_PATH, user.getAvatar())
//                                                .withBoolean(Constants.EXTRA_IMAGE_IS_AVATAR, true)
//                                                .navigation();
//                                        break;
                                    default:
                                        break;
                                }
                            }
                        };
                NormalPickerBsFragment choosePhotoFragment = NormalPickerBsFragment.newInstance();
                choosePhotoFragment.setOnclickListener(choosePhotoList, false, "", headListener);
                choosePhotoFragment.show(getSupportFragmentManager(),
                        NormalPickerBsFragment.class
                                .getSimpleName());
                break;
            case R.id.add_person:
//                Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(etName.getText().toString().trim())){
                    user.setName(etName.getText().toString().trim());
                }else {
                    ToastUtil.showToast("姓名不能为空");
                    return;
                }
                if (!TextUtils.isEmpty(etCode.getText().toString().trim())){
                     user.setCode(etCode.getText().toString().trim());
                }else {
                    ToastUtil.showToast("卡号不能为空");
                    return;
                }
                User newsUser= GreenDaoManager.getInstance(this).getSession().getUserDao().load(etCode.getText().toString().trim());
                if (newsUser!=null){
                    ToastUtil.showToast("卡号已存在");
                    return;
                }
                if (!TextUtils.isEmpty(pictureUrl)){
                    user.setAvatarUrl(pictureUrl);
                }else {
                    ToastUtil.showToast("请添加头像");
                    return;
                }

                long currentTime = System.currentTimeMillis();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
                Date date = new Date(currentTime);
                Timber.e("haifeng,"+formatter.format(date));
                user.setTime(formatter.format(date));

                GreenDaoManager.getInstance(InsertDBActivity.this).getSession().getUserDao().insertOrReplace(user);
                EcnPreferences.putString(InsertDBActivity.this, "想不想看", "luoti");
                ToastUtil.showToast("添加成功");
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final ArrayList<BaseMedia> medias = Boxing.getResult(data);
            if (requestCode == REQUEST_CODE) {
                if (null == medias || medias.isEmpty()) {
                    return;
                }
                String path = medias.get(0).getPath();
                if (TextUtils.isEmpty(path)) {
                    return;
                }

                Glide.with(this).load(path).into(addAvatar);
                pictureUrl=path;
            }
        }
    }
}
