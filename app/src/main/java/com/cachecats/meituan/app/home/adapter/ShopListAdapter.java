package com.cachecats.meituan.app.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cachecats.meituan.R;
import com.cachecats.meituan.api.model.LotteryPic;
import com.cachecats.meituan.utils.CommonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by solo on 2018/1/29.
 */

public class ShopListAdapter extends BaseQuickAdapter<LotteryPic,BaseViewHolder> {

    private Context context;

    private int imageWidth = 0;
    public ShopListAdapter(Context context, int layoutResId, @Nullable List<LotteryPic> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        imageWidth = CommonUtils.getScreenWidth(context) / 2 - 4;
    }

    @Override
    protected void convert(BaseViewHolder holder, LotteryPic item) {
        int height = item.height * imageWidth / item.width;
        ImageView imageView = holder.getView(R.id.show_iv);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(imageWidth,height));
        Glide.with(context).load(item.highSrc)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(imageView);
        holder.setText(R.id.desc_tv, item.name);
    }
}
