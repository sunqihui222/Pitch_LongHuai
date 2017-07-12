package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */

public interface MarshallStabilityContract extends BaseContract{

    interface View extends BaseContract.View{

        void showloadingMore();

        void lodingMorecompleted();

        void showLoadMoreError(Throwable throwable);

        void responseMarshallData(MarshallStabilityBean marshallStabilityData);
        void responseLoadMore(MarshallStabilityBean marshallStabilityMoreData);
    }

    interface Presenter extends BaseContract.Presenter{
        void requestMarshallFragmentData(Map map);
        void loadMoreData(Map<String, String> map);
    }
}
