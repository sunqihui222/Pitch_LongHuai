package com.shtoone.liqing.mvp.contract.pave;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.PaveTemptureData;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */

public interface PaveTemptureContract extends BaseContract{

    interface View extends BaseContract.View{
        void responsePaveTempData(PaveTemptureData data);
        void responseLoadMore(PaveTemptureData data);
    }

    interface Presenter extends BaseContract.Presenter{
        void requestPaveTemptureData(Map map);
        void loadMoreData(Map map);
    }
}
