package com.shtoone.liqing.mvp.view.pitch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.pitch.PitchContract;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.model.bean.PitchFragmentData;
import com.shtoone.liqing.mvp.presenter.pitch.PitchPresenter;
import com.shtoone.liqing.mvp.view.adapter.OnItemClickListener;
import com.shtoone.liqing.mvp.view.adapter.PitchFragmentRecyclerViewAdapter;
import com.shtoone.liqing.mvp.view.base.BaseFragment;
import com.shtoone.liqing.mvp.view.others.MainActivity;
import com.shtoone.liqing.utils.DateUtils;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.utils.ToastUtils;
import com.shtoone.liqing.widget.PageStateLayout;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import retrofit2.adapter.rxjava.HttpException;


/**
 * Author： hengzwd on 2017/3/8.
 * Email：hengzwdhengzwd@qq.com
 */

public class PitchFragment extends BaseFragment<PitchContract.Presenter> implements PitchContract.View {


    private String TAG = PitchFragment.class.getSimpleName();
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbarToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.pagestatelayout)
    PageStateLayout pagestatelayout;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private LinearLayoutManager linearLayoutManager;
    private PitchFragmentRecyclerViewAdapter mAdapter;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private ParametersData mParametersData;
    private DepartmentBean mDepartmentBean;
    private List<PitchFragmentData.DataEntity> dataEntityList;
    private boolean isRegistered = false;

    public static PitchFragment newInstance() {
        return new PitchFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pitch, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        if (savedInstanceState == null) {
            mDepartmentBean = (DepartmentBean) BaseApplication.mDepartmentData.clone();
            KLog.e(TAG,"mDepartmentBean1111=:"+mDepartmentBean.toString());
            mDepartmentBean.fromto=Constants.PITCHFRAGMENT;
        } else {
            mDepartmentBean = (DepartmentBean) savedInstanceState.getSerializable("departmentData");
            KLog.e(TAG,"mDepartmentBean2222=:"+mDepartmentBean.toString());
        }

        initData();
        return view;
    }

    private void initData() {

        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        if (null != BaseApplication.parametersData) {
            mParametersData = (ParametersData) BaseApplication.parametersData.clone();
            KLog.e(TAG, mParametersData.toString());
            mParametersData.fromTo = Constants.PITCHFRAGMENT;
            mParametersData.deviceType = Constants.TYPE_PITCH;
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                KLog.e(TAG, mParametersData.toString());
                mParametersData.getTo = Constants.PITCHFRAGMENT;
                mParametersData.departType = mDepartmentBean.departtype;
                mParametersData.biaoshiid = mDepartmentBean.departmentID;
                mParametersData.deviceType = Constants.TYPE_PITCH;
                KLog.e(TAG,"departType=:"+mParametersData.departType);
                ((MainActivity) _mActivity).startDrawerActivity(mParametersData, null);
            }
        });

        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(_mActivity, 56));
        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(ptrframelayout);

        initToolbarMenu(toolbarToolbar);
        initToolbarNavigationMenu(toolbarToolbar);
        setToolbarTitle();


        //mAdapter的实例化要放到最开始，因为在没有数据的时候，滑动会空指针异常，因为  if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
        mAdapter = new PitchFragmentRecyclerViewAdapter();

        SlideInLeftAnimationAdapter mSlideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(mAdapter);
        mSlideInLeftAnimationAdapter.setDuration(500);
        mSlideInLeftAnimationAdapter.setInterpolator(new OvershootInterpolator(0.5f));
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(mSlideInLeftAnimationAdapter);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(mScaleInAnimationAdapter);
        mAdapter.setonitemclickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DepartmentBean departmentBeanItem = new DepartmentBean();
                departmentBeanItem.departmentID = dataEntityList.get(position).getBsId();
                if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_OWNER)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_SECTION;

                } else if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_SECTION)){
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_PROJECT;
                } else if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_PROJECT)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_BHZ;
                }
                else if(mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_BHZ)){
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_BHZ;
                }else if(mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_MANAGER)){
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_MANAGER;
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("departmentData", departmentBeanItem);
                Intent intent = new Intent(_mActivity, PitchActivity.class);
                //为了调试沥青报告界面
//                Intent intent = new Intent(_mActivity, ScanQRCodeActivity.class);
                intent.putExtra("departmentData", bundle);
                startActivity(intent);
                KLog.e(TAG,"departmentBeanItem=:"+departmentBeanItem.toString());

//                startActivity(new Intent(_mActivity, PitchDetailActivity.class));
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("departmentData", mDepartmentBean);
    }

    @Override
    public void loadData() {
        super.loadData();
        String startTime = DateUtils.ChangeTimeToLong(mParametersData.startDateTime);
        String endTime = DateUtils.ChangeTimeToLong(mParametersData.endDateTime);
        mPresenter.requestTotalProductData(mDepartmentBean.departtype, mDepartmentBean.departmentID, startTime, endTime, mParametersData.equipmentID);
        KLog.e(TAG,"departtype=:"+mDepartmentBean.departtype);
    }

    @Override
    public void responseTotalProductData(PitchFragmentData PitchFragmentData) {
        dataEntityList = PitchFragmentData.getData();
        KLog.e(TAG + ";" + PitchFragmentData.getData().size());
        mAdapter.removeAllHeaderView();
        mAdapter.setNewData(PitchFragmentData.getData());
        mAdapter.notifyDataSetChanged();
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

    @NonNull
    @Override
    protected PitchContract.Presenter createPresenter() {
        return new PitchPresenter(this);
    }


    protected void initToolbarMenu(final Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.menu_hierarchy);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                KLog.e("---onMenuItemClick---");
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        BaseApplication.mDepartmentData.fromto = Constants.PITCHFRAGMENT;
                        BaseApplication.mDepartmentData.funtype=Constants.TYPE_PITCH;
                        ((MainActivity) _mActivity).startDrawerActivity(null, BaseApplication.mDepartmentData);
                        break;
                }
                return true;
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateDepartment(EventData eventData) {
        KLog.e("updateDepartment:Subscribe");
        //订阅从OrganizationFragment传递过来的参数的变化
        if (null != eventData.departmentData && null != this.mDepartmentBean) {
            if (eventData.departmentData.fromto == Constants.PITCHFRAGMENT) {
                this.mDepartmentBean.departmentName = eventData.departmentData.departmentName;
                this.mDepartmentBean.departmentID = eventData.departmentData.departmentID;
                this.mDepartmentBean.departtype = eventData.departmentData.departtype;
                this.mParametersData.equipmentID = eventData.departmentData.equipmentID;
                KLog.e(TAG,"mDepartmentBean.departtype=:"+mDepartmentBean.departtype);
                ptrframelayout.autoRefresh(true);
            }
        }
        //订阅从Parametersfragment传递过来的参数的变化
        if (eventData.parametersBean != null && null != this.mParametersData) {
            if (eventData.parametersBean.fromTo == Constants.PITCHFRAGMENT) {
                this.mParametersData.startDateTime = eventData.parametersBean.startDateTime;
                this.mParametersData.endDateTime = eventData.parametersBean.endDateTime;
                this.mParametersData.equipmentID = eventData.parametersBean.equipmentID;
                ptrframelayout.autoRefresh(true);
            }
        }
    }

    private void setToolbarTitle() {
//        if (null != toolbarToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
//        StringBuffer sb = new StringBuffer("广东云湛高速公路" + " > ");
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.liqing));
        toolbarToolbar.setTitle(sb.toString());
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean isCanDoRefresh() {
        //判断是哪种状态的页面，都让其可下拉
        if (pagestatelayout.isShowContent) {
            //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新
            if (null != recyclerview) {
                if (recyclerview.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager lm = (LinearLayoutManager) recyclerview.getLayoutManager();
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

}
