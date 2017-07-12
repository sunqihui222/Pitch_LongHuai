package com.shtoone.liqing.mvp.view.laboratory;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.contract.laboratory.MarshallStabDetailContract;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityDetailBean;
import com.shtoone.liqing.mvp.presenter.laboratory.MarshallStabDetailPresenter;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.utils.ToastUtils;
import com.shtoone.liqing.widget.PageStateLayout;
import com.shtoone.liqing.widget.ui.MyMarkerView;
import com.socks.library.KLog;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/6/2.
 */
public class MarshallStabDetailActivity extends BaseActivity<MarshallStabDetailContract.Presenter> implements MarshallStabDetailContract.View {

    private static final String TAG = MarshallStabDetailActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private  PtrFrameLayout ptrframelayout;
    private PageStateLayout pagestatelayout;
    private NestedScrollView nestedscroll;
    private TextView sysDate;
    private TextView projectName;
    private TextView projectPart;
    private TextView sampleNum;
    private TextView sampleName;
    private TextView sampleDec;
    private TextView flowVauleEver;
    private TextView flowStandardVaule;
    private TextView flowSingleVaule1;
    private TextView flowSingleVaule2;
    private TextView flowSingleVaule3;
    private TextView flowSingleVaule4;
    private TextView flowSingleVaule5;
    private TextView flowSingleVaule6;
    private TextView stabilityEver;
    private TextView stabSingleVaule1;
    private TextView stabSingleVaule2;
    private TextView stabSingleVaule3;
    private TextView stabSingleVaule4;
    private TextView stabSingleVaule5;
    private TextView stabSingleVaule6;
    private TextView stabStandardVaule;

    private LineChart lineChartMarshallDetail;
    private String f_guid;



    @Override
    protected MarshallStabDetailContract.Presenter createPresenter() {
        return new MarshallStabDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marshallstab_detail);
        initView();
        initData();
    }

    public void initView(){

        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        ptrframelayout= (PtrFrameLayout) findViewById(R.id.ptr_marshall_detail_fragment);
        pagestatelayout  = (PageStateLayout) findViewById(R.id.psl_marshall_detail_fragment);
        nestedscroll= (NestedScrollView) findViewById(R.id.nsv_marshall_detail_fragment);
        sysDate = (TextView) findViewById(R.id.maxieer_detail_date);
        projectName = (TextView) findViewById(R.id.maxieer_detail_projectname);
        projectPart = (TextView) findViewById(R.id.maxieer_detail_part);
        sampleNum = (TextView) findViewById(R.id.maxieer_detail_number);
        sampleName = (TextView) findViewById(R.id.maxieer_detail_name);
        sampleDec = (TextView) findViewById(R.id.maxieer_detail_desc);
        flowVauleEver = (TextView) findViewById(R.id.maxieer_detail_liuzhi_pingjun);
        flowStandardVaule = (TextView) findViewById(R.id.maxieer_detail_liuzhi_between);
        flowSingleVaule1 = (TextView) findViewById(R.id.maxieer_detail_liuzhi_danzhi1);
        flowSingleVaule2 = (TextView) findViewById(R.id.maxieer_detail_liuzhi_danzhi2);
        flowSingleVaule3 = (TextView) findViewById(R.id.maxieer_detail_liuzhi_danzhi3);
        flowSingleVaule4 = (TextView) findViewById(R.id.maxieer_detail_liuzhi_danzhi4);
        flowSingleVaule5 = (TextView) findViewById(R.id.maxieer_detail_liuzhi_danzhi5);
        flowSingleVaule6 = (TextView) findViewById(R.id.maxieer_detail_liuzhi_danzhi6);

        stabilityEver = (TextView) findViewById(R.id.maxieer_detail_wendingdu_pingjun);
        stabSingleVaule1 = (TextView) findViewById(R.id.maxieer_detail_wendingdu_danzhi1);
        stabSingleVaule2 = (TextView) findViewById(R.id.maxieer_detail_wendingdu_danzhi2);
        stabSingleVaule3 = (TextView) findViewById(R.id.maxieer_detail_wendingdu_danzhi3);
        stabSingleVaule4 = (TextView) findViewById(R.id.maxieer_detail_wendingdu_danzhi4);
        stabSingleVaule5 = (TextView) findViewById(R.id.maxieer_detail_wendingdu_danzhi5);
        stabSingleVaule6 = (TextView) findViewById(R.id.maxieer_detail_wendingdu_danzhi6);
        stabStandardVaule = (TextView) findViewById(R.id.maxieer_detail_wendingdu_between);

        lineChartMarshallDetail= (LineChart) findViewById(R.id.lineChart_marshall_detail);
        initStateBar(mToolbar);
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);


    }

    public void initData(){

        Intent intent = getIntent();
        f_guid = intent.getStringExtra("f_guid");
        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(ptrframelayout);
    }

    @Override
    public void loadData() {
        super.loadData();
        Map<String, String> map = new HashMap<>();
        map.put("F_GUID", f_guid);
        mPresenter.requestMarshallDetailData(map);
    }


    @Override
    public void responseMarshallDetailData(MarshallStabilityDetailBean marshallDetaildata) {

        List<MarshallStabilityDetailBean.DataBean> data = marshallDetaildata.getData();
        final MarshallStabilityDetailBean.DataBean dataBean = data.get(0);
        setData2View(dataBean);
        setChartData(getXslist(dataBean)[0],getYslist(dataBean)[0]);
        for(int i=0;i<getFlowSingleVaule(dataBean).length;i++){
            if(i==0){
                flowSingleVaule1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChartData(getXslist(dataBean)[0],getYslist(dataBean)[0]);
                    }
                });
            }
            if(i==1){
                flowSingleVaule2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChartData(getXslist(dataBean)[1],getYslist(dataBean)[1]);
                    }
                });

            }
            if(i==2){
                flowSingleVaule3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChartData(getXslist(dataBean)[2],getYslist(dataBean)[2]);
                    }
                });

            }
            if(i==3){
                flowSingleVaule4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChartData(getXslist(dataBean)[3],getYslist(dataBean)[3]);
                    }
                });
            }

            if(i==4){
                flowSingleVaule5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChartData(getXslist(dataBean)[4],getYslist(dataBean)[4]);
                    }
                });
            }

            if(i==5){
                flowSingleVaule6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChartData(getXslist(dataBean)[5],getYslist(dataBean)[5]);
                    }
                });
            }
        }
        setChart();

    }

    private void setData2View(MarshallStabilityDetailBean.DataBean dataBean) {

        sysDate.setText(dataBean.getIs_testtime());
        projectName.setText(dataBean.getHeader3());
        projectPart.setText(dataBean.getSHeader2());
        sampleNum.setText(dataBean.getHeader5());
        sampleName.setText(dataBean.getSHeader3());
        sampleDec.setText(dataBean.getSHeader4());
        flowVauleEver.setText(dataBean.getAvgvalue1());
        flowStandardVaule.setText(dataBean.getBiaoZhun1()+"-"+dataBean.getBiaoZhun2());
        flowSingleVaule1.setText(TextUtils.isEmpty(getFlowSingleVaule(dataBean)[0])?"":getFlowSingleVaule(dataBean)[0]);
        flowSingleVaule2.setText(TextUtils.isEmpty(getFlowSingleVaule(dataBean)[1])?"":getFlowSingleVaule(dataBean)[1]);
        flowSingleVaule3.setText(TextUtils.isEmpty(getFlowSingleVaule(dataBean)[2])?"":getFlowSingleVaule(dataBean)[2]);
        flowSingleVaule4.setText(TextUtils.isEmpty(getFlowSingleVaule(dataBean)[3])?"":getFlowSingleVaule(dataBean)[3]);
        flowSingleVaule5.setText(TextUtils.isEmpty(getFlowSingleVaule(dataBean)[4])?"":getFlowSingleVaule(dataBean)[4]);
        flowSingleVaule6.setText(TextUtils.isEmpty(getFlowSingleVaule(dataBean)[5])?"":getFlowSingleVaule(dataBean)[5]);
        stabilityEver.setText(dataBean.getAvgvalue2());
        stabSingleVaule1.setText(TextUtils.isEmpty(getStabSingleVaule(dataBean)[0])?"":getStabSingleVaule(dataBean)[0]);
        stabSingleVaule2.setText(TextUtils.isEmpty(getStabSingleVaule(dataBean)[1])?"":getStabSingleVaule(dataBean)[1]);
        stabSingleVaule3.setText(TextUtils.isEmpty(getStabSingleVaule(dataBean)[2])?"":getStabSingleVaule(dataBean)[2]);
        stabSingleVaule4.setText(TextUtils.isEmpty(getStabSingleVaule(dataBean)[3])?"":getStabSingleVaule(dataBean)[3]);
        stabSingleVaule5.setText(TextUtils.isEmpty(getStabSingleVaule(dataBean)[4])?"":getStabSingleVaule(dataBean)[4]);
        stabSingleVaule6.setText(TextUtils.isEmpty(getStabSingleVaule(dataBean)[5])?"":getStabSingleVaule(dataBean)[5]);
        stabStandardVaule.setText(">="+dataBean.getBiaoZhun3());

    }


    private void setChart() {

        lineChartMarshallDetail.setDescription("");
        lineChartMarshallDetail.setDrawGridBackground(true);
        lineChartMarshallDetail.setNoDataTextDescription("暂无数据表……");
        lineChartMarshallDetail.setTouchEnabled(true);
        lineChartMarshallDetail.setDragEnabled(true);
        lineChartMarshallDetail.setScaleEnabled(true);
        lineChartMarshallDetail.setPinchZoom(true);
        lineChartMarshallDetail.animateX(1500);
        lineChartMarshallDetail.getAxisRight().setEnabled(false);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        lineChartMarshallDetail.setMarkerView(mv);

        Typeface tf = Typeface.createFromAsset(this.getAssets(), "OpenSans-Light.ttf");

        YAxis leftAxis = lineChartMarshallDetail.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinValue(0f);
        leftAxis.removeAllLimitLines();
        leftAxis.setTypeface(tf);
        leftAxis.setTextColor(Color.RED);

        XAxis xAxis = lineChartMarshallDetail.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setTypeface(tf);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(10f);


        Legend l = lineChartMarshallDetail.getLegend();
        l.setTypeface(tf);
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setXEntrySpace(4f);
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);//设置图例的位置
        l.setTextSize(11f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(9f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
    }

    private void setChartData(String xsData,String ysData) {
        ArrayList<String> xVals = new ArrayList<String>();
        String[] stabVauleList = getStabVauleList(xsData);
        //   String[] x = data.getX();
        for (int i = 0; i < stabVauleList.length; i++) {
            xVals.add(stabVauleList[i]);
        }

        ArrayList<Entry> yVals0 = new ArrayList<Entry>();
        String[] flowVauleList = getFlowVauleList(ysData);
        for (int i = 0; i < flowVauleList.length; i++) {
            try {
                yVals0.add(new Entry(Float.parseFloat(flowVauleList[i]), i));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        LineDataSet mLineDataSet0 = new LineDataSet(yVals0, "关系图");

        if (Utils.getSDKInt() >= 18) {
            Drawable drawable0 = ContextCompat.getDrawable(this, R.drawable.fade_red);
            mLineDataSet0.setFillDrawable(drawable0);

        }
        //设置样式
        mLineDataSet0.enableDashedLine(10f, 5f, 0f);
        mLineDataSet0.enableDashedHighlightLine(10f, 5f, 0f);
        mLineDataSet0.setColor(Color.BLACK);
        mLineDataSet0.setCircleColor(Color.BLUE);
        mLineDataSet0.setLineWidth(1f);
        mLineDataSet0.setCircleRadius(0f);
        mLineDataSet0.setHighLightColor(Color.BLACK);
        mLineDataSet0.setDrawCircleHole(true);
        mLineDataSet0.setValueTextSize(7f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(mLineDataSet0);
        KLog.e(TAG,"dataSets="+dataSets.toString());
        KLog.e(TAG,"xVals="+xVals.toString());
        LineData data = new LineData(xVals, dataSets);
        lineChartMarshallDetail.setData(data);
        lineChartMarshallDetail.invalidate();
        KLog.e(TAG,"data=:"+data.toString());
    }


    //获取流值集合
    public String[] getFlowSingleVaule(MarshallStabilityDetailBean.DataBean dataBean){
        String liuzhi = dataBean.getLiuzhi();
        String[] flowSingleVaule = liuzhi.split("&");
        return flowSingleVaule;
    }

    //获取稳定值集合
    public String[] getStabSingleVaule(MarshallStabilityDetailBean.DataBean dataBean){
        String wendingzhi = dataBean.getLiuzhi();
        String[] stabSingleVaule = wendingzhi.split("&");
        return stabSingleVaule;
    }

    //获取y轴值，对应点击每个流值显示的图标y轴数据
    public String[] getYslist(MarshallStabilityDetailBean.DataBean dataBean){
        String f_yskylz = dataBean.getF_YSKYLZ();
        String[] flowVauleList = f_yskylz.split("&");
        return flowVauleList;
    }

    //获取x轴值，对应点击每个流值显示的图标x轴数据
    public String[] getXslist(MarshallStabilityDetailBean.DataBean dataBean){
        String f_yskyxb = dataBean.getF_YSKYXB();
        String[] stabVauleList = f_yskyxb.split("&");
        return stabVauleList;
    }

    //
    public String[] getFlowVauleList(String flowVaule){
        String[] xsList=new String[100];
        for(int i=0;i<flowVaule.toString().length();i++){
            xsList = flowVaule.toString().split(";");
        }
        return xsList;
    }

    public String[] getStabVauleList(String stabVaule){
        String[] ysList=new String[100];
        for(int i=0;i<stabVaule.toString().length();i++){
            ysList = stabVaule.toString().split(";");
        }
        return ysList;
    }

    private void setToolbarTitle() {
//        if (null != toolbarToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
//        StringBuffer sb = new StringBuffer("广东云湛高速公路" + " > ");
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.marshall_detail));
        mToolbar.setTitle(sb.toString());
//        }
    }

    @Override
    public boolean isCanDoRefresh() {
        //判断是哪种状态的页面，都让其可下拉
        if (pagestatelayout.isShowContent) {
            //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新

            if (nestedscroll.getScrollY() == 0) {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void showContent() {
        pagestatelayout.showContent();
    }

    @Override
    public void showError(Throwable t) {
        if (t instanceof ConnectException) {
            ToastUtils.showToast(BaseApplication.mContext, "网络异常,请检测网络");
            pagestatelayout.showNetError();
        } else if (t instanceof HttpException) {
            ToastUtils.showToast(BaseApplication.mContext, "服务器异常");
            pagestatelayout.showError();
        } else if (t instanceof SocketTimeoutException) {
            ToastUtils.showToast(BaseApplication.mContext, "连接超时");
            pagestatelayout.showError();
        } else if (t instanceof JSONException) {
            ToastUtils.showToast(BaseApplication.mContext, "解析异常");
            pagestatelayout.showError();
        } else {
            ToastUtils.showToast(BaseApplication.mContext, "数据异常");
            pagestatelayout.showError();
        }
    }

    @Override
    public void showLoading() {
        pagestatelayout.showLoading();
    }

    @Override
    public void showEmpty() {
        pagestatelayout.showEmpty();
    }

}
