package com.cachecats.meituan.app.home.adapter;


import com.cachecats.meituan.R;
import com.cachecats.meituan.app.home.model.IconTitleModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by solo on 2018/1/11.
 */

public class LittleModuleAdapter extends BaseQuickAdapter<IconTitleModel, BaseViewHolder> {

    private List<IconTitleModel> list;

    public LittleModuleAdapter(int layoutResId, @Nullable List<IconTitleModel> data) {
        super(layoutResId, data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, IconTitleModel item) {
        //设置图片
        helper.setImageResource(R.id.iv_icon_title, item.getIconResource());
        //设置标题
        helper.setText(R.id.tv_icon_title, item.getTitle());
    }
}
