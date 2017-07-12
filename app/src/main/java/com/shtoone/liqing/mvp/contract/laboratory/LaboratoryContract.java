package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.LaboratoryFragmentData;

/**
 * Created by Administrator on 2017/5/31.
 */

public interface LaboratoryContract {

    interface View extends BaseContract.View{
        void responseLabortoryData(LaboratoryFragmentData data);

    }

    interface Presenter extends BaseContract.Presenter{

        void requestLaboratoryFragmentData(String departType, String biaoshiid,String startTime, String endTime, String shebeibianhao);

    }
}
