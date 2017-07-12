package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.SofteningPointContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.SofteningPointBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/1.
 */

public class SoftentingPointPresenter extends BasePresenter<SofteningPointContract.View>implements SofteningPointContract.Presenter{


    public SoftentingPointPresenter(SofteningPointContract.View mView) {
        super(mView);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestSofteningPointBean(Map<String, String> map) {

        getView().showLoading();
        HttpHelper.getInstance().initService().requestSofteningPoint(map).enqueue(new Callback<SofteningPointBean>() {
            @Override
            public void onResponse(Call<SofteningPointBean> call, Response<SofteningPointBean> response) {
                if (response.isSuccessful()) {
                    SofteningPointBean softeningPointBean = response.body();
                    if(softeningPointBean.isSuccess()){
                        if(softeningPointBean.getData().size()>0){
                            getView().responseSofteningPointBean(response.body());
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
            public void onFailure(Call<SofteningPointBean> call, Throwable t) {
                if (getView() != null) {
                    getView().showError(t);
                }

            }
        });


    }

    @Override
    public void loadMoreData(Map<String, String> map) {
        getView().showloadingMore();
        HttpHelper.getInstance().initService().requestSofteningPoint(map).enqueue(new Callback<SofteningPointBean>() {
            @Override
            public void onResponse(Call<SofteningPointBean> call, Response<SofteningPointBean> response) {
                if (response.isSuccessful()) {
                    SofteningPointBean softteningPointbean = response.body();
                    if (softteningPointbean!=null){
                        if (softteningPointbean.getData().size()>0){
                            getView().responseLoadMore(softteningPointbean);

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
            public void onFailure(Call<SofteningPointBean> call, Throwable t) {
                getView().showLoadMoreError(t);

            }
        });

    }
}
