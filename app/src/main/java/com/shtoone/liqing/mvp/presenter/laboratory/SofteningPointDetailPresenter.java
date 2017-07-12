package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.SofteningPointDetailContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.SofteningPointDetailBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;
import com.socks.library.KLog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/8.
 */

public class SofteningPointDetailPresenter extends BasePresenter<SofteningPointDetailContract.View> implements SofteningPointDetailContract.Presenter{

    private static final String TAG = SofteningPointDetailPresenter.class.getSimpleName();
    public SofteningPointDetailPresenter(SofteningPointDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void requestSofteningPointDetailData(String f_guid) {
        HttpHelper.getInstance().initService().requestSofteningPointDetail(f_guid).enqueue(new Callback<SofteningPointDetailBean>() {
            @Override
            public void onResponse(Call<SofteningPointDetailBean> call, Response<SofteningPointDetailBean> response) {
                if (response.isSuccessful()) {
                    SofteningPointDetailBean softeningPointDetailBean = response.body();
                    if (softeningPointDetailBean.isSuccess()) {
                        getView().responseSofteningPointDetailData(softeningPointDetailBean);
                        getView().showContent();
                        KLog.e(TAG,"软化度详情=:"+softeningPointDetailBean.toString());
                    }else {
                        getView().showError(new IOException());
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<SofteningPointDetailBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
