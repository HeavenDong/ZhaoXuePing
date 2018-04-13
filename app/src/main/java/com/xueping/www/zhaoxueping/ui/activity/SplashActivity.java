package com.xueping.www.zhaoxueping.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xueping.www.zhaoxueping.App;
import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.base.MyRoute;
import com.xueping.www.zhaoxueping.common.utils.EcnPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path= MyRoute.SplashActivity)
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.look)
    TextView lookPictrue;
    @BindView(R.id.jump_splash_bg)
    TextView bgJumpSplash;
    Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide the title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);

        String pictrue = EcnPreferences.getString(this,"想不想看", "");
//        if (pictrue.equals("luoti")){
            lookPictrue.setVisibility(View.GONE);
//        }else {
//            lookPictrue.setVisibility(View.VISIBLE);
//        }

//        App app = (App) this.getApplication();
//        app.addActivity(this);
        App.getInstance().addActivity(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));

                ARouter.getInstance().build(MyRoute.MainActivity).navigation();
                finish();
////                        .withString(Constants.EXTRA_SHARE_TITLE, mTitle)
////                        .withString(Constants.EXTRA_SHARE_CONTENT, mContent)
////                        .withString(Constants.EXTRA_SHARE_ICON_URL, mIconUrl)
////                        .withString(Constants.EXTRA_SHARE_URL, mUrl)
//                        .navigation();
            }
        }, 3000);

        bgJumpSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(MyRoute.MainActivity).navigation();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=handler) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
