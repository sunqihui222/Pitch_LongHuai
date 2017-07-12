package com.shtoone.liqing.mvp.view.endpress;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.sdsmdg.tastytoast.TastyToast;
import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.pave.PaveTemptureContract;
import com.shtoone.liqing.mvp.model.PaveTemptureData;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.presenter.pave.PaveTempturePresenter;
import com.shtoone.liqing.mvp.view.adapter.PaveTempFragmentRecyclerViewAdapter;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.mvp.view.others.DrawerActivity;
import com.shtoone.liqing.mvp.view.pave.PaveTemptureActivity;
import com.shtoone.liqing.utils.DateUtils;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.utils.ToastUtils;
import com.shtoone.liqing.widget.PageStateLayout;
import com.shtoone.liqing.widget.ui.CustomLinearLayoutManager;
import com.shtoone.liqing.widget.ui.MyMarkerView;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import retrofit2.adapter.rxjava.HttpException;

import static com.shtoone.liqing.BaseApplication.parametersData;

/**
 * Created by Administrator on 2017/6/5.
 */

public class EndpressTempActivity extends BaseActivity<PaveTemptureContract.Presenter> implements PaveTemptureContract.View ,View.OnTouchListener,GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener{

    private static final String TAG = PaveTemptureActivity.class.getSimpleName();
    private boolean isRegistered = false;

    private Toolbar mToolbar;
    private NestedScrollView mNestedScrollView;
    private PageStateLayout mPageStateLayout;
    private PtrFrameLayout mPtrFrameLayout;
    private FloatingActionButton fab;
    private RecyclerView mRecyclerView;
    private LineChart lineChart;
    private Gson mGson;
    private ParametersData mParametersData;

    private LinearLayoutManager mLinearLayoutManager;
    private PaveTempFragmentRecyclerViewAdapter mAdapter;
    private boolean isLoading;
    private int lastVisibleItemPosition;
    private List<PaveTemptureData.DataBean> listData;
    private static final int FLING_MIN_DISTANCE = 20;
    private static final int FLING_MIN_VELOCITY = 200;
    private ViewFlipper mFlipper;
    private GestureDetector mGestureDetector;
    private TextView tv_pave_temp_normal;

    @NonNull
    @Override
    protected PaveTemptureContract.Presenter createPresenter() {
        return new PaveTempturePresenter(this);
    }

    public static EndpressTempActivity newInstance() {
        KLog.e(TAG,"--------------newInstance-------------");
        return new EndpressTempActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pave_temp);
        initView();
        initData();
        EventBus.getDefault().register(this);
    }

    public void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_pave_temp_detail_fragment);
        mPageStateLayout = (PageStateLayout) findViewById(R.id.psl_pave_temp_detail_fragment);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_tanpu_wendu_fragment);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nsv_pave_temp_detail_fragment);
        mFlipper= (ViewFlipper) findViewById(R.id.tanpuwendu_Flipper);
        lineChart= (LineChart) findViewById(R.id.lineChart_pave_temp_detail);
//        tv_pave_temp_normal = (TextView) view.findViewById(R.id.tv_pave_temp_normal);
        mGestureDetector = new GestureDetector(this, this);
        mFlipper.setLongClickable(true);
        mFlipper.setOnTouchListener(this);
        mRecyclerView.setOnTouchListener(this);
        KLog.e(TAG,"-------initView--------");
    }

    private void initData() {
        mParametersData = (ParametersData) parametersData.clone();
        mParametersData.departType = BaseApplication.mDepartmentData.departtype;
        mParametersData.biaoshiid=BaseApplication.mDepartmentData.departmentID;
        mParametersData.equipmentID=BaseApplication.mDepartmentData.equipmentID;
        mParametersData.deviceType = Constants.TYPE_PAVE;
        mParametersData.fromTo = Constants.EDDPRESSTEMPFRAGMENT;
        initStateBar(mToolbar);
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        initPageStateLayout(mPageStateLayout);
        mPageStateLayout.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        KLog.e(TAG,"-------initData--------");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KLog.e(TAG, mParametersData.toString());
                mParametersData.fromTo = Constants.EDDPRESSTEMPFRAGMENT;
                startDrawerActivity(mParametersData, null);
            }
        });

    }

    @Override
    public void loadData() {
        super.loadData();
        Map<String,String> map=new HashMap<>();
        map.put("departType",mParametersData.departType);
        map.put("biaoshiid",mParametersData.biaoshiid);
        map.put("startTime", DateUtils.ChangeTimeToLong(mParametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(mParametersData.endDateTime));
        map.put("tmpno",mParametersData.equipmentID);
//        map.put("tmpno","yzgstp06_01");
        mParametersData.currentPage=1;
        map.put("pageNo",mParametersData.currentPage+"");
        map.put("maxPageItems","15");
        KLog.e(TAG,"map=:"+map.toString());
        mPresenter.requestPaveTemptureData(map);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        Map<String,String> map=new HashMap<>();
        map.put("departType",mParametersData.departType);
        map.put("biaoshiid",mParametersData.biaoshiid);
        map.put("startTime", DateUtils.ChangeTimeToLong(mParametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(mParametersData.endDateTime));
        map.put("tmpno",mParametersData.equipmentID);
//        map.put("tmpno","yzgstp06_01");
        map.put("pageNo", mParametersData.currentPage+"");
        map.put("maxPageItems","15");
        KLog.e(TAG,"map=:"+map.toString());
        mPresenter.loadMoreData(map);
    }

    @Override
    public void responsePaveTempData(PaveTemptureData data) {

        listData=data.getData();
        setViewData();
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
        setChart();
        setChartData();
        KLog.e(TAG,"-------responsePaveTempData------");
    }

    @Override
    public void responseLoadMore(PaveTemptureData data) {
        listData=data.getData();
        setViewData();
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
        setChart();
        setChartData();
        KLog.e(TAG,"-------responseLoadMore------");
    }

    private void setViewData() {

//        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        //设置动画与适配器
        mAdapter = new PaveTempFragmentRecyclerViewAdapter();
        SlideInLeftAnimationAdapter mSlideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(mAdapter);
        mSlideInLeftAnimationAdapter.setFirstOnly(true);
        mSlideInLeftAnimationAdapter.setDuration(500);
        mSlideInLeftAnimationAdapter.setInterpolator(new OvershootInterpolator(.5f));
        ScaleInAnimationAdapter mScaleInAnimationAdapter = new ScaleInAnimationAdapter(mSlideInLeftAnimationAdapter);
        mScaleInAnimationAdapter.setFirstOnly(true);
        mRecyclerView.setAdapter(mScaleInAnimationAdapter);
        KLog.e(TAG,"======setViewData=====");
    }

    private void setChart() {
        KLog.e(TAG,"-------setChart------");
        lineChart.setDescription("");
        lineChart.setDrawGridBackground(true);
        lineChart.setNoDataTextDescription("暂无数据表……");
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.animateX(1500);
        lineChart.getAxisRight().setEnabled(false);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        lineChart.setMarkerView(mv);

        Typeface tf = Typeface.createFromAsset(this.getAssets(), "OpenSans-Light.ttf");

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinValue(0f);
        leftAxis.removeAllLimitLines();
        leftAxis.setTypeface(tf);
        leftAxis.setTextColor(Color.RED);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setTypeface(tf);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(10f);

        Legend l = lineChart.getLegend();
        l.setTypeface(tf);
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
//        l.setXEntrySpace(4f);
////        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);//设置图例的位置
//        l.setTextSize(11f);//设置文字大小
//        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
//        l.setFormSize(9f); // 设置Form的大小
//        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
    }

    private void setChartData() {
        ArrayList<String> xVals = new ArrayList<String>();
        //   String[] x = data.getX();
        for (int i = 0; i < listData.size(); i++) {
            xVals.add(listData.get(i).getTmpshijian());
        }

        ArrayList<Entry> yVals0 = new ArrayList<Entry>();

        for (int i = 0; i < listData.size(); i++) {

            yVals0.add(new Entry(Float.parseFloat(listData.get(i).getTmpdata()), i));
        }

        LineDataSet mLineDataSet0 = new LineDataSet(yVals0, "曲线图");

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
        mLineDataSet0.setDrawFilled(true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(mLineDataSet0);
        KLog.e(TAG,"摊铺温度dataSets=:"+dataSets.toString());
        LineData data = new LineData(xVals, dataSets);
        lineChart.setData(data);
        KLog.e(TAG,"data=:"+data.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateSearch(EventData eventData) {
        KLog.e("updateDepartment:Subscribe");
        KLog.e(eventData.parametersBean.fromTo);
        if (eventData.parametersBean != null && null != this.mParametersData) {
            if (eventData.parametersBean.fromTo == Constants.EDDPRESSTEMPFRAGMENT) {
                this.mParametersData.startDateTime = eventData.parametersBean.startDateTime;
                this.mParametersData.endDateTime = eventData.parametersBean.endDateTime;
                this.mParametersData.equipmentID = eventData.parametersBean.equipmentID;
                mPtrFrameLayout.autoRefresh(true);
            }
        }
        if (eventData.position == Constants.EDDPRESSTEMPFRAGMENT) {
            mPtrFrameLayout.autoRefresh(true);
        }
    }

    public void startDrawerActivity(@Nullable ParametersData mParametersData, @Nullable DepartmentBean departmentData) {
        Intent intent = new Intent(this, DrawerActivity.class);
        Bundle bundle = new Bundle();
        if (mParametersData != null && departmentData == null) {
            bundle.putSerializable("mparametersData", mParametersData);
        } else if (mParametersData == null && departmentData != null) {
            bundle.putSerializable("departmentData", departmentData);
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean isCanDoRefresh() {
        //判断是哪种状态的页面，都让其可下拉
        if (mPageStateLayout.isShowContent) {
            //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新
            if (null != mRecyclerView) {
                if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager lm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    int position = lm.findFirstVisibleItemPosition();
                    if (position >= 0) {
                        if (lm.findViewByPosition(position).getTop() > 0 && position == 0) {
                            return true;
                        }
                    }
                }
            } else {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        KLog.e(TAG,"===========onFling==========");
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            mFlipper.setInAnimation(inFromRightAnimation());
            mFlipper.setOutAnimation(outToLeftAnimation());
            mFlipper.showNext();
            int t = Integer.valueOf(mParametersData.currentPage) + 1;
            mParametersData.currentPage = t;
            KLog.e(TAG,"右滑currentPage=:"+mParametersData.currentPage);
            loadMore();
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Integer index = Integer.valueOf(mParametersData.currentPage);
            KLog.e(TAG,"index=:"+index);
            if (index - 1 <= 0) {
                mParametersData.currentPage = 1;
                KLog.e(TAG,"左滑currentPage=:"+mParametersData.currentPage);
//                refresh();
                TastyToast.makeText(this, "当前已是第一页!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            } else {
                mFlipper.setInAnimation(inFromLeftAnimation());
                mFlipper.setOutAnimation(outToRightAnimation());
                mFlipper.showPrevious();
                mParametersData.currentPage = (index - 1);
                KLog.e(TAG,"左滑currentPage=:"+mParametersData.currentPage);
                try {
                    loadMore();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }


    protected Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, +1f, Animation.RELATIVE_TO_PARENT,
                0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(300);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    protected Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                -1f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(300);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT,
                0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(300);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    protected Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(300);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setToolbarTitle() {
//        if (null != toolbarToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
//        StringBuffer sb = new StringBuffer("广东云湛高速公路" + " > ");
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.pavesite) + " > ");
        sb.append(getString(R.string.endpress_temp));
        mToolbar.setTitle(sb.toString());
//        }
    }

    @Override
    public void showContent() {
        mPageStateLayout.showContent();
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
        mPageStateLayout.showLoading();
    }

    @Override
    public void showEmpty() {
        mPageStateLayout.showEmpty();
    }

}
