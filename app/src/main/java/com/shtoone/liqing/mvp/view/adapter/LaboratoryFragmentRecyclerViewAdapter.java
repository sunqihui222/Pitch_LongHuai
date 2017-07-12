package com.shtoone.liqing.mvp.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.model.LaboratoryFragmentData;

/**
 * Created by Administrator on 2017/6/6.
 */
public class LaboratoryFragmentRecyclerViewAdapter extends BaseQuickAdapter<LaboratoryFragmentData.DataBean, BaseViewHolder> {

    private OnItemClickListener onItemClickListener;

    public LaboratoryFragmentRecyclerViewAdapter() {
        super(R.layout.item_recyclerview_laboratory_fragment, null);
    }


    @Override
    protected void convert(final BaseViewHolder holder, LaboratoryFragmentData.DataBean item) {
        holder.setText(R.id.tv_organization_item_recyclerview_laboratory_fragment, item.getBanhezhanminchen())
                .setText(R.id.tv_maxier,item.getCountmxe())
                .setText(R.id.tv_ruanhuadian,item.getCountrhd())
                .setText(R.id.tv_zhenrudu,item.getCountzrd())
                .setText(R.id.tv_yandu,item.getCountyd())
                .setOnClickListener(R.id.cv_item_recyclerview_laboratory_fragment, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(holder.getConvertView(),holder.getLayoutPosition());
                    }
                });
//        KLog.e(TAG,"========convert===========");

    }

    public void setonitemclickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }
}
