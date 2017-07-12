package com.shtoone.liqing.mvp.view.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.model.bean.SofteningPointBean;
import com.shtoone.liqing.widget.SlantedTextView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class SofteningPointFragmentAdapter extends BaseQuickAdapter<SofteningPointBean.DataEntity,BaseViewHolder>{

    private SofteningPointBean.DataEntity mDataEntity;
    private OnItemClickListener           onItemClickListener;

    public SofteningPointFragmentAdapter() {
        super(R.layout.item_recycleview_softeningpoint, null);
    }

    @Override
    protected void convert(final BaseViewHolder holder, SofteningPointBean.DataEntity item) {
            holder.setText(R.id.softeningpoint_testtime,"试验时间"+":"+item.getIs_testtime())
                    .setText(R.id.softeningpoint_header5,"样品编号"+":"+item.getHeader5())
                    .setText(R.id.softeningpoint_SHeader2,"部位"+":"+item.getSHeader2())
                    .setText(R.id.softeningpoint_avgvalue1,"平均值"+":"+item.getAvgvalue1())
                    .setText(R.id.softeningpoint_biaoZhun1,"标准值"+":"+item.getBiaoZhun1())
                    .setText(R.id.softeningpoint_ruanhuadian1,"单值1"+":"+item.getRuanhuadian1())
                    .setText(R.id.softeningpoint_ruanhuadian2,"单值2"+":"+item.getRuanhuadian2())
                    .setOnClickListener(R.id.cv_item_recyclerview_softeningpoint_fragment,new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v, holder.getLayoutPosition());
                        }
                    });

        ((SlantedTextView) holder.getView(R.id.stv_isqualified)).setText(item.getIsQualified().equals("合格") ? "合格" : "不合格")
                .setSlantedBackgroundColor(item.getIsQualified().equals("合格") ? Color.parseColor("#2baf2b") : Color.RED );


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    public void setSofteningPointData(SofteningPointBean softeningPointData){
//        setNewData(softeningPointData.getData());
//    }
//
//    @Override
//    public void setNewData(List<SofteningPointBean.DataEntity> data) {
//        super.setNewData(data);
//    }
}
