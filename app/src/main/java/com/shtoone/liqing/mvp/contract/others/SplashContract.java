package com.shtoone.liqing.mvp.contract.others;

import com.shtoone.liqing.mvp.contract.base.BaseContract;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public interface SplashContract {
    interface View extends BaseContract.View {
        void go2LoginOrGuide();

        void go2Main();

        void go2ScanQR();
    }

    interface Presenter extends BaseContract.Presenter {
        void checkLogin();

        void checkUpdate();
    }

}
