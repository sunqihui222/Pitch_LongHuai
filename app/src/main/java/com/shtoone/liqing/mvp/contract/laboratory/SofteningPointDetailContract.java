package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.SofteningPointDetailBean;

/**
 * Created by Administrator on 2017/6/8.
 */

public interface SofteningPointDetailContract extends BaseContract{

    interface View extends BaseContract.View{
        void responseSofteningPointDetailData(SofteningPointDetailBean softeningPointDetailBean);
    }

    interface Presenter extends BaseContract.Presenter{
        void requestSofteningPointDetailData(String f_guid);
    }
}
