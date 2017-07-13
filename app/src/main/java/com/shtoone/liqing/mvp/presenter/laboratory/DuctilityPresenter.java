package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.DuctilityContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.DuctilityBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/6.
 */

public class DuctilityPresenter extends BasePresenter<DuctilityContract.View> implements DuctilityContract.Presenter{


    public DuctilityPresenter(DuctilityContract.View mView) {
        super(mView);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestDuctilityBean(Map<String, String> map) {

        getView().showLoading();
        HttpHelper.getInstance().initService().requestDuctility(map).enqueue(new Callback<DuctilityBean>() {
            @Override
            public void onResponse(Call<DuctilityBean> call, Response<DuctilityBean> response) {
                if (response.isSuccessful()) {
                    DuctilityBean ductilityBean = response.body();
                    if(ductilityBean.isSuccess()){
                        if(ductilityBean.getData().size()>0){
                            getView().responseDuctilityBean(response.body());
                            getView().showContent();
                        }else{
                            getView().showEmpty();
                        }
                    }else{
                        getView().showEmpty();
                    }
                }else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<DuctilityBean> call, Throwable t) {
                if (getView() != null) {
                    getView().showError(t);
                }

            }
        });

    }

    @Override
    public void loadMoreData(Map<String, String> map) {

    }
}
