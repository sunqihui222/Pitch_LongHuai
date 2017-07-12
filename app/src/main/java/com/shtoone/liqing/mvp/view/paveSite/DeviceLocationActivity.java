package com.shtoone.liqing.mvp.view.paveSite;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.mvp.contract.paveSite.DeviceLocationContract;
import com.shtoone.liqing.mvp.model.bean.DeviceLocationData;
import com.shtoone.liqing.mvp.model.bean.EquipmentData;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.presenter.paveSite.DeviceLocationPresenter;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.utils.AMapUtil;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.utils.ToastUtil;
import com.shtoone.liqing.utils.ToastUtils;
import com.shtoone.liqing.widget.PageStateLayout;
import com.socks.library.KLog;
import com.squareup.otto.Subscribe;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.adapter.rxjava.HttpException;

public class DeviceLocationActivity extends BaseActivity<DeviceLocationContract.Presenter> implements DeviceLocationContract.View {
    private LatLonPoint latLonPoint;
    private MapView mapView;
    private AMap aMap;
    private ProgressDialog progDialog = null;
    private GeocodeSearch geocoderSearch;
    private String addressName;
    private Marker regeoMarker;
    private PageStateLayout mPageStateLayout;
    private Toolbar mToolbar;
    private ParametersData mParametersData;
    private MaterialSpinner materialSpinner;

    private List<String> equipmentIDs;
    private List<String> equipmentNames;
    private boolean isRegistered = false;
    //    private BHZEquipment mBHZEquipment;
    private DeviceLocationData deviceLocationData;
    private DeviceLocationData allDeviceLocationData;
    private List<DeviceLocationData.DataBean> allDataBeen;
    private List<DeviceLocationData.DataBean> dataBeen;
    private MarkerOptions markerOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_location);
        mParametersData = (ParametersData) BaseApplication.parametersData.clone();
        mParametersData.departType = BaseApplication.mDepartmentData.departtype;
        mParametersData.biaoshiid = BaseApplication.mDepartmentData.departmentID;
        if (!isRegistered) {
            BaseApplication.bus.register(this);
            isRegistered = true;
        }
        initView();
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        initMap();
        mPresenter.requestAllLocation(mParametersData.departType, mParametersData.biaoshiid);
        mPresenter.requestEquipmentType(mParametersData.departType, mParametersData.biaoshiid, "5");
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.map_location);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        materialSpinner = (MaterialSpinner) findViewById(R.id.ms_select_equipment_dialog1);
        mPageStateLayout = (PageStateLayout) findViewById(R.id.psl_concrete_main_activity);
        initStateBar(mToolbar);
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        initPageStateLayout(mPageStateLayout);
        mPageStateLayout.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        allDataBeen = new ArrayList<>();
        dataBeen = new ArrayList<>();
        materialSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void setToolbarTitle() {
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer(toolBarName + " > ");
        sb.append(getString(R.string.pavesite) + " > ");
        sb.append(getString(R.string.monitor)).trimToSize();
        mToolbar.setTitle(sb.toString());
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            regeoMarker = aMap.addMarker(new MarkerOptions().anchor(1.0f, 1.0f)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }

        aMap.setOnMarkerClickListener(markerListener);

        //设置中心点和缩放比例
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(gaodeLatLng(new LatLng(21.77379, 110.785585))));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        //绑定信息窗点击事件
//        aMap.setOnInfoWindowClickListener(listener);

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(searchListener);
        progDialog = new ProgressDialog(this);
    }

    @Override
    public void loadData() {
        super.loadData();
        mPresenter.requestAllLocation(mParametersData.departType, mParametersData.biaoshiid);
        mPresenter.requestEquipmentType(mParametersData.departType, mParametersData.biaoshiid, "5");
    }

    AMap.OnMarkerClickListener markerListener = new AMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            if (aMap != null) {
                marker.showInfoWindow();
            }
            return true;
        }
    };

    GeocodeSearch.OnGeocodeSearchListener searchListener = new GeocodeSearch.OnGeocodeSearchListener() {
        @Override
        public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
            dismissDialog();
            if (rCode == AMapException.CODE_AMAP_SUCCESS) {
                if (result != null && result.getRegeocodeAddress() != null
                        && result.getRegeocodeAddress().getFormatAddress() != null) {
                    addressName = result.getRegeocodeAddress().getFormatAddress()
                            + "附近";
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            AMapUtil.convertToLatLng(latLonPoint), 15));
                    regeoMarker.setPosition(AMapUtil.convertToLatLng(latLonPoint));
                    ToastUtil.show(DeviceLocationActivity.this, addressName);
                } else {
                    ToastUtil.show(DeviceLocationActivity.this, R.string.no_result);
                }
            } else {
                ToastUtil.showerror(DeviceLocationActivity.this, rCode);
            }
        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

        }
    };

    private LatLng gaodeLatLng(LatLng latlng) {
        CoordinateConverter converter = new CoordinateConverter(DeviceLocationActivity.this);
        // CoordType.GPS 待转换坐标类型
        converter.from(CoordinateConverter.CoordType.GPS);
        // sourceLatLng待转换坐标点 LatLng类型
        converter.coord(latlng);
        // 执行转换操作
        return converter.convert();
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
            KLog.e("equipment选择第：" + i + "个");
            if (i >= 0) {
                aMap.clear();
            }
            if (i >= 0) {
                mParametersData.equipmentID = equipmentIDs.get(i);
                KLog.e("equipmentIDs[i]:" + equipmentIDs.get(i));
                mPresenter.requestLocation(mParametersData.departType, mParametersData.biaoshiid, mParametersData.equipmentID);

            } else if (i == -1) {
                mParametersData.equipmentID = "";
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在获取地址");
        progDialog.show();
    }

    //region 基本activity里的方法
    @Override
    public void setFailMessage(String message) {
        ToastUtils.showToast(BaseApplication.mContext, message);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable t) {
        if (t instanceof ConnectException) {
            ToastUtils.showToast(BaseApplication.mContext, "网络异常,请检测网络");
            mPageStateLayout.showNetError();
        } else if (t instanceof HttpException) {
            ToastUtils.showToast(BaseApplication.mContext, "服务器异常");
            mPageStateLayout.showError();
        } else if (t instanceof SocketTimeoutException) {
            ToastUtils.showToast(BaseApplication.mContext, "连接超时");
            mPageStateLayout.showError();
        } else if (t instanceof JSONException) {
            ToastUtils.showToast(BaseApplication.mContext, "解析异常");
            mPageStateLayout.showError();
        } else {
            ToastUtils.showToast(BaseApplication.mContext, "数据异常");
            mPageStateLayout.showError();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected DeviceLocationContract.Presenter createPresenter() {
        return new DeviceLocationPresenter(this);
    }
    //endregion

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    //region 处理所有定位点
    @Override
    public void responseLocationData(DeviceLocationData deviceLocationData) {
        allDataBeen.addAll(deviceLocationData.getData());
        initAllPoint();
    }

    private void initAllPoint() {
        LatLng latlng;
        for (int i = 0; i < allDataBeen.size(); i++) {
            latlng = new LatLng(Double.parseDouble(allDataBeen.get(i).getBeiwei()), Double.parseDouble(allDataBeen.get(i).getDonjin()));
            toGaode(latlng, allDataBeen.get(i).getBanhezhanminchen(), allDataBeen.get(i).getDongjinbeiwei());
        }
    }

    private void toGaode(LatLng latlng, String banhezhan, String dongjinbeiwei) {
        LatLng desLatLng = gaodeLatLng(latlng);
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(desLatLng)
                .title(banhezhan)
                .snippet(dongjinbeiwei)
                .draggable(true);
        aMap.addMarker(markerOption);
    }
    //endregion

    //设备列表
    @Override
    public void responseEquipmentType(EquipmentData equipmentData) {
        setEquipmentQueryView(equipmentData);
    }

    private void setEquipmentQueryView(EquipmentData equipmentData) {
        equipmentNames = new ArrayList<>();
        equipmentIDs = new ArrayList<>();
        for (EquipmentData.DataEntity temp : equipmentData.getData()) {
            equipmentNames.add(temp.getBanhezhanminchen());
            equipmentIDs.add(temp.getGprsbianhao());
        }

        ArrayAdapter<String> equipmentsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, equipmentNames);
        equipmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materialSpinner.setAdapter(equipmentsAdapter);

        for (int i = 0; i < equipmentIDs.size(); i++) {
            if (mParametersData.equipmentID.equals(equipmentIDs.get(i))) {
                materialSpinner.setSelection(i + 1);
                KLog.e("默认：" + (i + 1) + "个");
            }
        }
    }

    //region 处理指定定位点
    @Override
    public void responseLocation(DeviceLocationData deviceLocationData) {
        dataBeen.clear();
        dataBeen.addAll(deviceLocationData.getData());
        initPoint();
    }

    private void initPoint() {
        for (int i = 0; i < dataBeen.size(); i++) {
            LatLng latlng = new LatLng(Double.parseDouble(dataBeen.get(i).getBeiwei()), Double.parseDouble(dataBeen.get(i).getDonjin()));
            CoordinateConverter converter = new CoordinateConverter(DeviceLocationActivity.this);
            // CoordType.GPS 待转换坐标类型
            converter.from(CoordinateConverter.CoordType.GPS);
            // sourceLatLng待转换坐标点 LatLng类型
            converter.coord(latlng);
            // 执行转换操作
            LatLng desLatLng = converter.convert();
            latLonPoint = new LatLonPoint(desLatLng.latitude,desLatLng.longitude);
            getAddress(latLonPoint);
            regeoMarker = aMap.addMarker(new MarkerOptions().anchor(1.0f, 1.0f)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(dataBeen.get(0).getBanhezhanminchen())
                    .snippet(dataBeen.get(0).getDongjinbeiwei()));
            regeoMarker.showInfoWindow();
        }
    }
    //endregion

    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
        showDialog();
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
    }

    @Subscribe
    public void updateSearch(ParametersData mParametersData) {
        if (mParametersData != null) {
            if (mParametersData.fromTo == Constants.DEVICELOCATIONACTIVITY) {
                this.mParametersData.startDateTime = mParametersData.startDateTime;
                this.mParametersData.endDateTime = mParametersData.endDateTime;
                this.mParametersData.equipmentID = mParametersData.equipmentID;
                this.mParametersData.userGroupID = mParametersData.userGroupID;
                this.mParametersData.departType = mParametersData.departType;
                this.mParametersData.biaoshiid = mParametersData.biaoshiid;
                KLog.e("mParametersData:" + mParametersData.startDateTime);
                KLog.e("mParametersData:" + mParametersData.endDateTime);
                KLog.e("mParametersData:" + mParametersData.equipmentID);
                KLog.e("mParametersData:" + mParametersData.userGroupID);
            }
        }
    }
}
