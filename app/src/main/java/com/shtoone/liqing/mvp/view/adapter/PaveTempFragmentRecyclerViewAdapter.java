package com.shtoone.liqing.mvp.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.model.PaveTemptureData;

/**
 * Created by Administrator on 2017/6/5.
 */

public class PaveTempFragmentRecyclerViewAdapter extends BaseQuickAdapter<PaveTemptureData.DataBean, BaseViewHolder> {

    public PaveTempFragmentRecyclerViewAdapter() {
        super(R.layout.item_recyclerview_pave_temp, null);
    }

    @Override
    protected void convert(final BaseViewHolder holder, PaveTemptureData.DataBean item) {
        holder.setText(R.id.tv_shebeimingcheng_recyclerview_pave_temp_fragment,item.getTmpno())
                .setText(R.id.tv_time_item_recyclerview_pave_temp_fragment,item.getTmpshijian())
                .setText(R.id.tv_wendu_item_recyclerview_pave_temp_fragment,item.getTmpdata());
    }
}
