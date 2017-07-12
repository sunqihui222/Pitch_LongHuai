package com.shtoone.liqing.mvp.presenter.laboratory;

import com.shtoone.liqing.mvp.contract.laboratory.LaboratoryContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.LaboratoryFragmentData;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;
import com.socks.library.KLog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/31.
 */

public class LaboratoryPresenter extends BasePresenter<LaboratoryContract.View> implements LaboratoryContract.Presenter{

    private String TAG = LaboratoryPresenter.class.getSimpleName();

    public LaboratoryPresenter(LaboratoryContract.View mView) {
        super(mView);
    }


    @Override
    public void requestLaboratoryFragmentData(String departType, String biaoshiid, String startTime, String endTime, String shebeibianhao) {

        HttpHelper.getInstance().initService().requestLabortoryData(departType,biaoshiid,startTime,endTime,shebeibianhao).enqueue(new Callback<LaboratoryFragmentData>() {
            @Override
            public void onResponse(Call<LaboratoryFragmentData> call, Response<LaboratoryFragmentData> response) {

                if (response.isSuccessful()) {
                    LaboratoryFragmentData data=response.body();
                    if (data.isSuccess()) {
                        if(data.getData().size()>0){
                            getView().responseLabortoryData(data);
                            getView().showContent();
                            KLog.e(TAG,"data=:"+data.toString());
                        }else {
                            getView().showEmpty();
                        }

                    }else {
                        getView().showError(new IOException());

                    }
                }else {
                    getView().showError(new IOException());
                }

            }

            @Override
            public void onFailure(Call<LaboratoryFragmentData> call, Throwable t) {

                getView().showError(t);
            }
        });
    }


    @Override
    public void start() {

    }
}
