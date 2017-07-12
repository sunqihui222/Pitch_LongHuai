package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.MarshallStabilityContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/31.
 */
public class MarshallStabilityPresenter extends BasePresenter<MarshallStabilityContract.View> implements MarshallStabilityContract.Presenter {

    private static final String TAG = MarshallStabilityPresenter.class.getSimpleName();
    public MarshallStabilityPresenter(MarshallStabilityContract.View mView) {
        super(mView);
    }

    @Override
    public void requestMarshallFragmentData(Map map) {

        HttpHelper.getInstance().initService().requestMarshallStabilityBean(map).enqueue(new Callback<MarshallStabilityBean>() {
            @Override
            public void onResponse(Call<MarshallStabilityBean> call, Response<MarshallStabilityBean> response) {

                if (response.isSuccessful()) {
                    MarshallStabilityBean data=response.body();
                    if (data.isSuccess()) {
                        if(data.getData().size()>0){
                            getView().responseMarshallData(data);
                            getView().showContent();
                        }else{
                            getView().showEmpty();
                        }
                    }else{
                        getView().showError(new IOException());
                    }
                }else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<MarshallStabilityBean> call, Throwable t) {
                if(isViewAttached()){
                    getView().showError(t);
                }
            }
        });
    }


    @Override
    public void loadMoreData(Map<String, String> map) {

        HttpHelper.getInstance().initService().requestMarshallStabilityBean(map).enqueue(new Callback<MarshallStabilityBean>() {
            @Override
            public void onResponse(Call<MarshallStabilityBean> call, Response<MarshallStabilityBean> response) {
                if (response.isSuccessful()){
                    MarshallStabilityBean marshallStabilityMoreData=response.body();
                    if (marshallStabilityMoreData != null) {
                        if (marshallStabilityMoreData.getData().size()>0) {
                            getView().responseLoadMore(marshallStabilityMoreData);
                            KLog.e(TAG,"marshallStabilityMoreData="+marshallStabilityMoreData.getData().toString());
                        }else {
                            getView().lodingMorecompleted();
                        }
                    }else {
                        getView().showLoadMoreError(new IOException());
                    }

                }else {
                    getView().showLoadMoreError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<MarshallStabilityBean> call, Throwable t) {

                getView().showLoadMoreError(t);
            }
        });
    }


    @Override
    public void start() {

    }


}
