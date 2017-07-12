package com.shtoone.liqing.mvp.contract.others;

import com.shtoone.liqing.mvp.contract.base.BaseContract;

import java.util.Map;

public interface SubmitScannedDataContract {
    interface View extends BaseContract.View {
        void submitSuccessfully(String message);
        void submitFailed(String message);

    }

    interface Presenter extends BaseContract.Presenter {
        void submit(Map<String, String> map);
    }

}
