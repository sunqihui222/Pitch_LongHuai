package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.MarshallStabDetailContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityDetailBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/2.
 */

public class MarshallStabDetailPresenter extends BasePresenter<MarshallStabDetailContract.View> implements MarshallStabDetailContract.Presenter {


    public MarshallStabDetailPresenter(MarshallStabDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void requestMarshallDetailData(Map map) {

        HttpHelper.getInstance().initService().requestMarshallStabilityDetailBean(map).enqueue(new Callback<MarshallStabilityDetailBean>() {
            @Override
            public void onResponse(Call<MarshallStabilityDetailBean> call, Response<MarshallStabilityDetailBean> response) {

                if (response.isSuccessful()) {
                    MarshallStabilityDetailBean marshallDetaildata=response.body();
                    if (marshallDetaildata.isSuccess()) {
                        getView().responseMarshallDetailData(marshallDetaildata);
                        getView().showContent();
                    }else {
                        getView().showError(new IOException());

                    }
                }else {
                    getView().showError(new IOException());
                }

            }

            @Override
            public void onFailure(Call<MarshallStabilityDetailBean> call, Throwable t) {

                getView().showError(t);
            }
        });
    }

    @Override
    public void start() {

    }

}
