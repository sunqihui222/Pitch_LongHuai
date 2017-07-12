package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.SofteningPointBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */

public interface SofteningPointContract {

    interface View extends BaseContract.View {

        void  showloadingMore();
        void  lodingMorecompleted();
        void showLoadMoreError(Throwable throwable);
        void  responseLoadMore(SofteningPointBean softeningPointBean);
        void  responseSofteningPointBean(SofteningPointBean softeningPointBean);

    }

    interface Presenter extends BaseContract.Presenter {


         void requestSofteningPointBean(Map<String, String> map);
        void loadMoreData(Map<String, String> map);
    }

}
