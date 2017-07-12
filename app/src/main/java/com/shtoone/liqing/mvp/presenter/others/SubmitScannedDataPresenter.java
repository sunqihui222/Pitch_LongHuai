package com.shtoone.liqing.mvp.presenter.others;

import com.shtoone.liqing.mvp.contract.others.SubmitScannedDataContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.UploadResponseBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;
import com.socks.library.KLog;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SubmitScannedDataPresenter extends BasePresenter<SubmitScannedDataContract.View> implements SubmitScannedDataContract.Presenter {


    public SubmitScannedDataPresenter(SubmitScannedDataContract.View mView) {
        super(mView);
    }

    @Override
    public void submit(Map<String, String> map) {
        KLog.e("设备编号" + map.get("shebeibianhao") + " 沥青类型" + map.get("type")
                + " 层面" + map.get("layer") + " 提交时间" + map.get("createtime") + " 标识" + map.get("biaoshi"));
        HttpHelper.getInstance().initService().uploadPitchData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UploadResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().submitFailed("提交失败");
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(UploadResponseBean uploadResponseBean) {
                        if (uploadResponseBean.isSuccess()) {
                            getView().submitSuccessfully("提交成功");
                        } else {
                            getView().submitFailed("提交失败");
                        }
                    }
                });
    }

    @Override
    public void start() {

    }
}
