package com.shtoone.liqing.mvp.contract.laboratory;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.AsphaltPenetrationBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public interface AsphaltPenetrationContract {

    interface View extends BaseContract.View {

        void  showloadingMore();
        void  lodingMorecompleted();
        void showLoadMoreError(Throwable throwable);
        void  responseLoadMore(AsphaltPenetrationBean asphaltPenetrationBean);
        void  responseAsphaltPenetrationBean(AsphaltPenetrationBean asphaltPenetrationBean);

    }

    interface Presenter extends BaseContract.Presenter {


        void requestAsphaltPenetrationBean(Map<String, String> map);
        void loadMoreData(Map<String, String> map);
    }

}
