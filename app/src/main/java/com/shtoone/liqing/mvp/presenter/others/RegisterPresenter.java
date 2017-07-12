package com.shtoone.liqing.mvp.presenter.others;


import com.shtoone.liqing.mvp.contract.others.RegisterContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.RegisterBean;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;
import com.socks.library.KLog;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private static final String TAG = RegisterPresenter.class.getSimpleName();

    public RegisterPresenter(RegisterContract.View mView) {
        super(mView);
    }


    @Override
    public void start() {

    }

    @Override
    public void register(String machineCode, String phoneBrand, String phoneSysVersion, String phoneModel) {

        mRxManager.add(
                HttpHelper.getInstance().initService().register(machineCode, phoneBrand, phoneSysVersion, phoneModel)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new RxSubscriber<RegisterBean>() {
                            @Override
                            public void _onNext(RegisterBean registerBean) {

                                if (0 == registerBean.getStatus()) {
                                    getView().registerSuccessfully(registerBean);

                                    KLog.e("register::" + registerBean.getRegCode());
                                } else {
                                    getView().registerFailed(registerBean.getDescription());
                                }

                            }
                        })
        );
    }
}
