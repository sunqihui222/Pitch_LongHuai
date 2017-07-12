package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.DuctilityBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public interface DuctilityContract {
    interface View extends BaseContract.View {

        void  showloadingMore();
        void  lodingMorecompleted();
        void showLoadMoreError(Throwable throwable);
        void  responseLoadMore(DuctilityBean ductilityBean);
        void  responseDuctilityBean(DuctilityBean ductilityBean);

    }

    interface Presenter extends BaseContract.Presenter {


        void requestDuctilityBean(Map<String, String> map);
        void loadMoreData(Map<String, String> map);
    }

}
