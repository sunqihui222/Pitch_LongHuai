package com.shtoone.liqing.mvp.contract.others;


import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.RegisterBean;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public interface RegisterContract {
    interface View extends BaseContract.View {
        void registerSuccessfully(RegisterBean mRegisterBean);

        void registerFailed(String message);

    }

    interface Presenter extends BaseContract.Presenter {
        void register(String machineCode, String phoneBrand, String phoneSysVersion, String phoneModel);
    }

}
