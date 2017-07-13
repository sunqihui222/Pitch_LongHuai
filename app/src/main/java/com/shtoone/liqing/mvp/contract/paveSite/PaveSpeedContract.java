package com.shtoone.liqing.mvp.contract.paveSite;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.PaveSpeedData;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface PaveSpeedContract extends BaseContract {

    interface View extends BaseContract.View{
        void responsePaveSpeedData(PaveSpeedData data);
        void responseLoadMore(PaveSpeedData data);
    }

    interface Presenter extends BaseContract.Presenter{

        void requestPaveSpeedData(Map map);
        void loadMoreData(Map map);
    }
}
