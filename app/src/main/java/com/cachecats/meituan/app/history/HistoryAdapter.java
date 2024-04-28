package com.cachecats.meituan.app.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.cachecats.meituan.R;
import com.cachecats.meituan.api.model.LotteryResp;
import com.cachecats.meituan.api.model.WinningNumberInfo;
import com.cachecats.meituan.utils.CommonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by solo on 2018/1/29.
 */

public class HistoryAdapter extends BaseQuickAdapter<LotteryResp,BaseViewHolder> {

    private Context context;

    public HistoryAdapter(Context context, int layoutResId, @Nullable List<LotteryResp> data) {
        super(layoutResId, data);
        this.context = context;
    }

    TextView[] tvNumberList = new TextView[7];
    TextView[] tvInfoList = new TextView[7];
    @Override
    protected void convert(BaseViewHolder holder, LotteryResp item) {
        holder.setText(R.id.tvShortNumber, String.valueOf(item.shortIssueNumber));
        holder.setText(R.id.tvDate, String.valueOf(item.lotteryDateStr));

        tvNumberList[0] = holder.getView(R.id.tvNumber1);
        tvNumberList[1] = holder.getView(R.id.tvNumber2);
        tvNumberList[2] = holder.getView(R.id.tvNumber3);
        tvNumberList[3] = holder.getView(R.id.tvNumber4);
        tvNumberList[4] = holder.getView(R.id.tvNumber5);
        tvNumberList[5] = holder.getView(R.id.tvNumber6);
        tvNumberList[6] = holder.getView(R.id.tvNumber7);

        tvInfoList[0] = holder.getView(R.id.tvInfo1);
        tvInfoList[1] = holder.getView(R.id.tvInfo2);
        tvInfoList[2] = holder.getView(R.id.tvInfo3);
        tvInfoList[3] = holder.getView(R.id.tvInfo4);
        tvInfoList[4] = holder.getView(R.id.tvInfo5);
        tvInfoList[5] = holder.getView(R.id.tvInfo6);
        tvInfoList[6] = holder.getView(R.id.tvInfo7);

        setData(tvNumberList,tvInfoList,item.winningNumberInfo);
    }

    @SuppressLint("SetTextI18n")
    private void setData(TextView[] tvNumberList, TextView[] tvInfoList, List<WinningNumberInfo> winningNumberInfo){
        for (int i = 0; i < 7; i++) {
            WinningNumberInfo info = winningNumberInfo.get(i);
            TextView tvNumber = tvNumberList[i];
            tvNumber.setText(info.codeStr);
            tvNumber.setBackgroundResource(getResIdByColor(info.waveColor));
            tvInfoList[i].setText(info.zodiac+"/"+info.fiveElementStr);
        }
    }


    public int getResIdByColor(int waveColor) {
        if (waveColor == 1){
            return R.drawable.number_bg_red;
        }else if (waveColor == 2){
            return R.drawable.number_bg_blue;
        }
        return R.drawable.number_bg_green;
    }
}
