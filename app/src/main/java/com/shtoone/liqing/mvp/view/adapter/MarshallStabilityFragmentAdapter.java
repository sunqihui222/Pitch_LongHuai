package com.shtoone.liqing.mvp.view.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityBean;
import com.shtoone.liqing.widget.SlantedTextView;

/**
 * Created by Administrator on 2017/6/1.
 */
public class MarshallStabilityFragmentAdapter extends BaseQuickAdapter<MarshallStabilityBean.DataBean, BaseViewHolder> {

    private OnItemClickListener onItemClickListener;

    public MarshallStabilityFragmentAdapter() {
        super(R.layout.item_recyclerview_marshallstability, null);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MarshallStabilityBean.DataBean item) {
        holder.setText(R.id.tv_maxieer_date,item.getIs_testtime())
                .setText(R.id.tv_maxieer_part,item.getSHeader2())
                .setText(R.id.tv_maxieer_ioavg,item.getAvgvalue1()+"mm")
                .setText(R.id.tv_maxieer_between,item.getBiaoZhun1()+"mm"+"-"+item.getBiaoZhun2()+"mm")
                .setText(R.id.tv_maxieer_safeavg,item.getAvgvalue2()+"KN")
                .setText(R.id.tv_maxieer_standard,">="+item.getBiaoZhun3()+"KN")
                .setOnClickListener(R.id.cv_item_recyclerview_mashall_fragment, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v, holder.getLayoutPosition());
                    }
                });

        ((SlantedTextView) holder.getView(R.id.stv_quality)).setText(item.getIsQualified().equals("合格") ? "合格" : "不合格")
                .setSlantedBackgroundColor(item.getIsQualified().equals("合格") ? Color.parseColor("#2baf2b") : Color.RED );

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
