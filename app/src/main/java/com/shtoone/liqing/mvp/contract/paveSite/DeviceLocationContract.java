package com.shtoone.liqing.mvp.contract.paveSite;

import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.DeviceLocationData;
import com.shtoone.liqing.mvp.model.bean.EquipmentData;

public interface DeviceLocationContract {
    interface View extends BaseContract.View{
        void responseLocationData(DeviceLocationData deviceLocationData);
        void setFailMessage(String message);
        void responseEquipmentType(EquipmentData equipmentData);
        void responseLocation(DeviceLocationData deviceLocationData);
    }

    interface Presenter extends BaseContract.Presenter{
        void requestAllLocation(String departType, String biaoshiid);
        void requestEquipmentType(String departType, String biaoshiid, String shebeileixing);
        void requestLocation(String departType, String biaoshiid, String F_SBBH);
    }
}
