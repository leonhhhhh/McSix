package com.cachecats.meituan.di.components;

import com.cachecats.meituan.app.HistoryActivity;
import com.cachecats.meituan.app.MainActivity;
import com.cachecats.meituan.app.WebViewActivity;
import com.cachecats.meituan.app.home.HomeFragment;
import com.cachecats.meituan.di.modules.ActivityModule;
import com.cachecats.meituan.di.scopes.Scopes;

import dagger.Component;

/**
 * Created by solo on 2018/1/10.
 */

@Scopes.Activity
@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(HomeFragment fragment);
    void inject(HistoryActivity activity);
    void inject(WebViewActivity activity);

}