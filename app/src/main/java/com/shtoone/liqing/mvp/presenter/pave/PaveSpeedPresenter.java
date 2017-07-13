package com.shtoone.liqing.mvp.presenter.pave;

import com.shtoone.liqing.mvp.contract.paveSite.PaveSpeedContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.PaveSpeedData;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PaveSpeedPresenter extends BasePresenter<PaveSpeedContract.View> implements PaveSpeedContract.Presenter{


    public PaveSpeedPresenter(PaveSpeedContract.View mView) {
        super(mView);
    }

    @Override
    public void requestPaveSpeedData(Map map) {

        HttpHelper.getInstance().initService().requestPaveSpeedData(map).enqueue(new Callback<PaveSpeedData>() {
            @Override
            public void onResponse(Call<PaveSpeedData> call, Response<PaveSpeedData> response) {

                if (response.isSuccessful()) {
                    PaveSpeedData data=response.body();
                    if (data.isSuccess()) {
                        if(isViewAttached()){
                            getView().responsePaveSpeedData(data);
                            getView().showContent();
                        }
                    }else {
                        getView().showError(new IOException());

                    }
                }else {
                    getView().showError(new IOException());
                }

            }

            @Override
            public void onFailure(Call<PaveSpeedData> call, Throwable t) {

                getView().showError(t);
            }
        });

    }

    @Override
    public void loadMoreData(Map map) {
        HttpHelper.getInstance().initService().requestPaveSpeedData(map).enqueue(new Callback<PaveSpeedData>() {
            @Override
            public void onResponse(Call<PaveSpeedData> call, Response<PaveSpeedData> response) {

                if (response.isSuccessful()) {
                    PaveSpeedData data=response.body();
                    if (data.isSuccess()) {
                        getView().responseLoadMore(data);
                        getView().showContent();
                    }else {
                        getView().showError(new IOException());

                    }
                }else {
                    getView().showError(new IOException());
                }

            }

            @Override
            public void onFailure(Call<PaveSpeedData> call, Throwable t) {

                getView().showError(t);
            }
        });

    }




    @Override
    public void start() {

    }
}
