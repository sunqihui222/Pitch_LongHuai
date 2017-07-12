package com.shtoone.liqing.mvp.view.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.model.bean.AsphaltPenetrationBean;
import com.shtoone.liqing.widget.SlantedTextView;

import java.util.List;


/**
 * Created by Administrator on 2017/6/6.
 */

public class AsphaltPenetrationFragmentAdapter extends BaseQuickAdapter<AsphaltPenetrationBean.DataEntity,BaseViewHolder> {

    private OnItemClickListener onItemClickListener;

    public AsphaltPenetrationFragmentAdapter() {
        super(R.layout.item_recycleview_asphaltpenetration, null);
    }

    @Override
    protected void convert(final BaseViewHolder holder, AsphaltPenetrationBean.DataEntity item) {

        holder.setText(R.id.asphaltpenetration_testtime,"试验时间"+":"+item.getIs_testtime())
                .setText(R.id.asphaltpenetration_header5,"样品编号"+":"+item.getHeader5())
                .setText(R.id.asphaltpenetration_SHeader2,"部位"+":"+item.getSHeader2())
                .setText(R.id.asphaltpenetration_avgvalue1,"平均值"+":"+(item.getAvgvalue1().equals("") ? "0"+"mm" : item.getAvgvalue1()+"mm"))
                .setText(R.id.asphaltpenetration_biaoZhun1,"标准值"+":"+(item.getBiaoZhun1().equals("") ? "0"+"mm" : item.getBiaoZhun1()+"mm")+"~"+(item.getBiaoZhun2().equals("") ? "0"+"mm" : item.getBiaoZhun2()+"mm"))
                .setText(R.id.asphaltpenetration_ruanhuadian1,"单值1~3"+":"+item.getZhenrudu().replace(",","mm  ")+"mm")
                .setOnClickListener(R.id.cv_item_recyclerview_asphaltpenetration_fragment,new View.OnClickListener(){
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

    public void setAsphaltPenetrationData(AsphaltPenetrationBean asphaltPenetrationData){
        asphaltPenetrationData.getData();
        setNewData(asphaltPenetrationData.getData());
    }

    @Override
    public void setNewData(List<AsphaltPenetrationBean.DataEntity> data) {
        super.setNewData(data);
    }
}
