package com.cachecats.meituan.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.cachecats.meituan.MyApplication;
import com.cachecats.meituan.R;
import com.cachecats.meituan.app.discover.DiscoverFragment;
import com.cachecats.meituan.app.home.HomeFragment;
import com.cachecats.meituan.app.mine.MineFragment;
import com.cachecats.meituan.app.nearby.NearbyFragment;
import com.cachecats.meituan.app.order.OrderFragment;
import com.cachecats.meituan.base.BaseActivity;
import com.cachecats.meituan.base.BaseFragment;
import com.cachecats.meituan.di.components.DaggerActivityComponent;
import com.cachecats.meituan.di.modules.ActivityModule;
import com.cachecats.meituan.widget.bottomtab.CustomBottomTabWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabWidget)
    CustomBottomTabWidget tabWidget;
    private List<BaseFragment> fragmentList;

    @BindView(R.id.top_menu)
    ImageView topMenu;
    View menuView ;
    PopupWindow popupWindow ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);

        //初始化
        init();
    }

    private void init() {
        //构造Fragment的集合
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearbyFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MineFragment());
        //初始化CustomBottomTabWidget
        tabWidget.init(getSupportFragmentManager(), fragmentList);

        topMenu.setOnClickListener(v -> showTopMenu());
        menuView = LayoutInflater.from(this).inflate(R.layout.home_top_menu,null);
    }

    private void showTopMenu(){
        if (popupWindow == null){
            popupWindow = new PopupWindow(menuView, ViewGroup.LayoutParams.WRAP_CONTENT
            ,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            popupWindow.showAsDropDown(topMenu,-100,0);
            addMenuListener();
        }else {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    private void addMenuListener(){
        if (popupWindow==null){
            return;
        }
        popupWindow.setOnDismissListener(() -> popupWindow = null);
        LinearLayout llyMenu = menuView.findViewById(R.id.llyMenu);
        for (int i = 0; i < llyMenu.getChildCount(); i++) {
            View view = llyMenu.getChildAt(i);
            int finalI = i;
            view.setOnClickListener(v -> {
                popupWindow.dismiss();
                popupWindow = null;
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url",urls[finalI]);
                MainActivity.this.startActivity(intent);
            });
        }
    }

    String[] urls = {
            "http://107.149.219.98:8081/live?lotteryType=1",//开奖直播
            "http://107.149.219.98:8081/history?lotteryType=1",//开奖历史
            "http://107.149.219.98:8081/pic-lib?lotteryType=1",//六合图库
            "http://107.149.219.98:8081/charts?lotteryType=1",//走势图
            "http://107.149.219.98:8081/ch-nums?lotteryType=1",//跳马助手
            "http://107.149.219.98:8081/fushi?lotteryType=1",//复式计算
            "https://dh.2130d9.com:2130/",//app下载
    };
}
