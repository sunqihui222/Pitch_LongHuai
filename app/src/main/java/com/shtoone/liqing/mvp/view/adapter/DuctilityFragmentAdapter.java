package com.shtoone.liqing.mvp.view.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.model.bean.DuctilityBean;
import com.shtoone.liqing.widget.SlantedTextView;

import java.util.List;


/**
 * Created by Administrator on 2017/6/6.
 */

public class DuctilityFragmentAdapter extends BaseQuickAdapter<DuctilityBean.DataEntity,BaseViewHolder> {
    private OnItemClickListener onItemClickListener;

    public DuctilityFragmentAdapter() {
        super(R.layout.item_recycleview_ductility, null);

    }

    @Override
    protected void convert(final BaseViewHolder holder, DuctilityBean.DataEntity item) {
        holder.setText(R.id.ductility_testtime,"试验时间"+":"+item.getIS_TESTTIME())
                .setText(R.id.ductility_header5,"样品编号"+":"+item.getHeader5())
                .setText(R.id.ductility_SHeader2,"部位"+":"+item.getSHeader2())
                .setText(R.id.ductility_avgvalue1,"平均值"+":"+(item.getAvgvalue1().equals("") ? "0"+"mm" : item.getAvgvalue1()+"mm"))
                .setText(R.id.ductility_biaoZhun1,"标准值"+":"+(item.getBiaozhunzhi1().equals("") ? "0"+"mm" : item.getBiaozhunzhi1()+"mm")+"~"+(item.getBiaozhunzhi2().equals("") ? "0"+"mm" : item.getBiaozhunzhi2()+"mm"))
                .setText(R.id.ductility_ruanhuadian1,"单值1~3"+":"+(item.getYandu11().equals("") ? "0"+"mm" : item.getYandu11()+"mm")+"  "+(item.getYandu12().equals("") ? "0"+"mm" : item.getYandu12()+"mm")+"  "+(item.getYandu13().equals("") ? "0"+"mm" : item.getYandu13()+"mm"))
                .setOnClickListener(R.id.cv_item_recyclerview_ductility_fragment,new View.OnClickListener(){
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

    public void setDuctilityData(DuctilityBean ductilityData){
        ductilityData.getData();
        setNewData(ductilityData.getData());

    }

    @Override
    public void setNewData(List<DuctilityBean.DataEntity> data) {
        super.setNewData(data);
    }
}
