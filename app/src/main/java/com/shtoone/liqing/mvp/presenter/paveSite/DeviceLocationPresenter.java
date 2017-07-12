package com.shtoone.liqing.mvp.presenter.paveSite;

import com.shtoone.liqing.mvp.contract.paveSite.DeviceLocationContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.DeviceLocationData;
import com.shtoone.liqing.mvp.model.bean.EquipmentData;
import com.shtoone.liqing.mvp.presenter.base.BasePresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceLocationPresenter extends BasePresenter<DeviceLocationContract.View> implements DeviceLocationContract.Presenter {
    public DeviceLocationPresenter(DeviceLocationContract.View mView) {
        super(mView);
    }

    @Override
    public void requestAllLocation(String departType, String biaoshiid) {
        HttpHelper.getInstance().initService().requestAllLocationData(departType, biaoshiid).enqueue(new Callback<DeviceLocationData>() {
            @Override
            public void onResponse(Call<DeviceLocationData> call, Response<DeviceLocationData> response) {
                DeviceLocationData deviceLocationData = response.body();
                if (response.isSuccessful()) {
                    if (deviceLocationData.isSuccess()) {
                        if (null != getView() && deviceLocationData.getData().size() > 0) {
                            getView().responseLocationData(response.body());
                        } else {
                            getView().showError(new IOException());
                        }
                    } else {
                        getView().setFailMessage("无定位坐标点");
                    }

                } else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<DeviceLocationData> call, Throwable t) {

                if (getView() != null) {
                    getView().showError(t);
                }
            }
        });
    }

    @Override
    public void requestEquipmentType(String departType, String biaoshiid, String shebeileixing) {
        HttpHelper.getInstance().initService().requestEquipment(departType, biaoshiid, shebeileixing).enqueue(new Callback<EquipmentData>() {
            @Override
            public void onResponse(Call<EquipmentData> call, Response<EquipmentData> response) {
                EquipmentData equipmentData = response.body();
                if (response.isSuccessful() && equipmentData.isSuccess()) {
                    if (null != getView() && equipmentData.getData().size() > 0) {
                        getView().responseEquipmentType(response.body());
                    } else {
                        getView().showError(new IOException());
                    }

                } else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<EquipmentData> call, Throwable t) {

                if (getView() != null) {
                    getView().showError(t);
                }
            }
        });
    }

    @Override
    public void requestLocation(String departType, String biaoshiid, String F_SBBH) {
        HttpHelper.getInstance().initService().requestLocationData(departType, biaoshiid, F_SBBH).enqueue(new Callback<DeviceLocationData>() {
            @Override
            public void onResponse(Call<DeviceLocationData> call, Response<DeviceLocationData> response) {
                DeviceLocationData deviceLocationData = response.body();
                if (response.isSuccessful()) {
                    if (deviceLocationData.isSuccess()) {
                        if (null != getView() && deviceLocationData.getData().size() > 0) {
                            getView().responseLocation(response.body());
                        } else {
                            getView().showError(new IOException());
                        }
                    } else {
                        getView().setFailMessage("无定位坐标点");
                    }

                } else {
                    getView().showError(new IOException());
                }
            }

            @Override
            public void onFailure(Call<DeviceLocationData> call, Throwable t) {

                if (getView() != null) {
                    getView().showError(t);
                }
            }
        });
    }

    @Override
    public void start() {

    }
}
