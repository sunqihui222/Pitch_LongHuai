package com.shtoone.liqing.mvp.presenter.pave;

import com.shtoone.liqing.mvp.contract.pave.PaveTemptureContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.PaveTemptureData;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/31.
 */

public class PaveTempturePresenter extends BasePresenter<PaveTemptureContract.View> implements PaveTemptureContract.Presenter{


    public PaveTempturePresenter(PaveTemptureContract.View mView) {
        super(mView);
    }

    @Override
    public void requestPaveTemptureData(Map map) {

        HttpHelper.getInstance().initService().requestPaveTempData(map).enqueue(new Callback<PaveTemptureData>() {
            @Override
            public void onResponse(Call<PaveTemptureData> call, Response<PaveTemptureData> response) {

                if (response.isSuccessful()) {
                    PaveTemptureData data=response.body();
                    if (data.isSuccess()) {
                        if(isViewAttached()){
                            getView().responsePaveTempData(data);
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
            public void onFailure(Call<PaveTemptureData> call, Throwable t) {

                getView().showError(t);
            }
        });

    }

    @Override
    public void loadMoreData(Map map) {
        HttpHelper.getInstance().initService().requestPaveTempData(map).enqueue(new Callback<PaveTemptureData>() {
            @Override
            public void onResponse(Call<PaveTemptureData> call, Response<PaveTemptureData> response) {

                if (response.isSuccessful()) {
                    PaveTemptureData data=response.body();
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
            public void onFailure(Call<PaveTemptureData> call, Throwable t) {

                getView().showError(t);
            }
        });

    }


    @Override
    public void start() {

    }
}
