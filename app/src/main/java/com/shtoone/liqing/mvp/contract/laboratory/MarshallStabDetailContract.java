package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityDetailBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */

public interface MarshallStabDetailContract extends BaseContract{

    interface View extends BaseContract.View{
        void responseMarshallDetailData(MarshallStabilityDetailBean marshallDetaildata);
    }

    interface Presenter extends BaseContract.Presenter{
        void requestMarshallDetailData(Map map);
    }
}
