package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.AsphaltPenetrationContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.AsphaltPenetrationBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/6.
 */

public class AsphaltPenetrationPresenter extends BasePresenter<AsphaltPenetrationContract.View> implements AsphaltPenetrationContract.Presenter{

    private String TAG = AsphaltPenetrationPresenter.class.getSimpleName();
    public AsphaltPenetrationPresenter(AsphaltPenetrationContract.View mView) {
        super(mView);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestAsphaltPenetrationBean(Map<String, String> map) {

        getView().showLoading();
        HttpHelper.getInstance().initService().requestAsphaltPenetration(map).enqueue(new Callback<AsphaltPenetrationBean>() {
            @Override
            public void onResponse(Call<AsphaltPenetrationBean> call, Response<AsphaltPenetrationBean> response) {
                if (response.isSuccessful()) {
                    AsphaltPenetrationBean asphaltPenetrationBean = response.body();
                    if (asphaltPenetrationBean.isSuccess()) {
                        if(asphaltPenetrationBean.getData().size()>0){
                            getView().responseAsphaltPenetrationBean(asphaltPenetrationBean);
                            getView().showContent();
                        }else{
                            getView().showEmpty();
                        }
                    }else {
                        getView().showEmpty();
                    }
                    KLog.e(TAG,"针入度数据="+response.body());
                }else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<AsphaltPenetrationBean> call, Throwable t) {
                if (getView() != null) {
                    getView().showError(t);
                }

            }
        });


    }

    @Override
    public void loadMoreData(Map<String, String> map) {

        getView().showloadingMore();
        HttpHelper.getInstance().initService().requestAsphaltPenetration(map).enqueue(new Callback<AsphaltPenetrationBean>() {
            @Override
            public void onResponse(Call<AsphaltPenetrationBean> call, Response<AsphaltPenetrationBean> response) {
                if (response.isSuccessful()) {
                    AsphaltPenetrationBean asphaltpenetrationbean = response.body();
                    if (asphaltpenetrationbean!=null){
                        if (asphaltpenetrationbean.getData().size()>0){
                            getView().responseLoadMore(asphaltpenetrationbean);

                        }else {
                            getView().lodingMorecompleted();
                        }
                    }else {
                        getView().showLoadMoreError(new IOException());

                    }
                }else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<AsphaltPenetrationBean> call, Throwable t) {
                getView().showLoadMoreError(t);

            }
        });

    }
}
