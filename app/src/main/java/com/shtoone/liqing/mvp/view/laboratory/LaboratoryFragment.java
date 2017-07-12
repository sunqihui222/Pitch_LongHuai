package com.shtoone.liqing.mvp.view.laboratory;

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
import android.widget.Button;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.laboratory.LaboratoryContract;
import com.shtoone.liqing.mvp.model.LaboratoryFragmentData;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.presenter.laboratory.LaboratoryPresenter;
import com.shtoone.liqing.mvp.view.adapter.LaboratoryFragmentRecyclerViewAdapter;
import com.shtoone.liqing.mvp.view.adapter.OnItemClickListener;
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

import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/5/31.
 */
public class LaboratoryFragment extends BaseFragment<LaboratoryContract.Presenter> implements LaboratoryContract.View {

    private String TAG = LaboratoryFragment.class.getSimpleName();
    private Toolbar toolbarToolbar;
    private Button btn_test;
    private FloatingActionButton fab;
    private ParametersData mParametersData;
    private RecyclerView recyclerview;
    private PageStateLayout pagestatelayout;
    private PtrFrameLayout ptrframelayout;
    private DepartmentBean mDepartmentBean;

    private LinearLayoutManager linearLayoutManager;
    private LaboratoryFragmentRecyclerViewAdapter mAdapter;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private List<LaboratoryFragmentData.DataBean> dataEntityList;


    public static LaboratoryFragment newInstance() {
        return new LaboratoryFragment();
    }

    @NonNull
    @Override
    protected LaboratoryContract.Presenter createPresenter() {
        return new LaboratoryPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laboratory, container, false);
        if (savedInstanceState == null) {
            mDepartmentBean = (DepartmentBean) BaseApplication.mDepartmentData.clone();
            mDepartmentBean.fromto = Constants.LABORATORYFRAGMENT;
        } else {
            mDepartmentBean = (DepartmentBean) savedInstanceState.getSerializable("departmentData");
        }
        initView(view);
        initData();
        EventBus.getDefault().register(this);
        return view;
    }


    public void initView(View view) {
        toolbarToolbar = (Toolbar) view.findViewById(R.id.toolbar_toolbar);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        pagestatelayout = (PageStateLayout) view.findViewById(R.id.pagestatelayout);
        ptrframelayout = (PtrFrameLayout) view.findViewById(R.id.ptrframelayout);
    }

    public void initData() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        if (null != BaseApplication.parametersData) {
            mParametersData = (ParametersData) BaseApplication.parametersData.clone();
            KLog.e(TAG, mParametersData.toString());
            mParametersData.fromTo = Constants.LABORATORYFRAGMENT;
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParametersData.getTo = Constants.LABORATORYFRAGMENT;
                KLog.e(TAG, mParametersData.toString());
                mParametersData.departType = mDepartmentBean.departtype;
                mParametersData.biaoshiid = mDepartmentBean.departmentID;
//随便定义测试用的，不一定正确
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
        mAdapter = new LaboratoryFragmentRecyclerViewAdapter();

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
                departmentBeanItem.departmentID = dataEntityList.get(position).getBsid();
//                departmentBeanItem.departtype=mDepartmentBean.departtype;
                if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_OWNER)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_SECTION;
                } else if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_SECTION)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_PROJECT;
                } else if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_PROJECT)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_BHZ;
                } else if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_BHZ)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_BHZ;
                } else if (mDepartmentBean.departtype.equals(Constants.DEPARTTYPE_MANAGER)) {
                    departmentBeanItem.departtype = Constants.DEPARTTYPE_MANAGER;
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("departmentData", departmentBeanItem);
                Intent intent = new Intent(_mActivity, LaboratoryActivity.class);
                intent.putExtra("departmentData", bundle);
                startActivity(intent);
                KLog.e(TAG, "departmentBeanItem=:" + departmentBeanItem.toString());

//                startActivity(new Intent(_mActivity, PitchDetailActivity.class));
            }
        });

    }

    @Override
    public void loadData() {
        super.loadData();
        String startTime = DateUtils.ChangeTimeToLong(mParametersData.startDateTime);
        String endTime = DateUtils.ChangeTimeToLong(mParametersData.endDateTime);
        mPresenter.requestLaboratoryFragmentData(mDepartmentBean.departtype, mDepartmentBean.departmentID, startTime, endTime, mParametersData.equipmentID);
        KLog.e(TAG, "departtype=:" + mDepartmentBean.departtype);
    }

    @Override
    public void responseLabortoryData(LaboratoryFragmentData laboratoryFragmentData) {
        dataEntityList = laboratoryFragmentData.getData();
        KLog.e(TAG + ";" + laboratoryFragmentData.getData().size());
        mAdapter.removeAllHeaderView();
        mAdapter.setNewData(dataEntityList);
        KLog.e(TAG, "dataEntityList=:" + dataEntityList.toString());
        mAdapter.notifyDataSetChanged();
    }


    protected void initToolbarMenu(final Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.menu_hierarchy);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                KLog.e("---  LaboratoryFragment----onMenuItemClick---");
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        BaseApplication.mDepartmentData.fromto = Constants.LABORATORYFRAGMENT;
                        BaseApplication.mDepartmentData.funtype=Constants.TYPE_LABORTORY;
                        ((MainActivity) _mActivity).startDrawerActivity(null, BaseApplication.mDepartmentData);
                        break;
                }
                return true;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateDepartment(EventData eventData) {
        KLog.e("LaboratoryFragment:Subscribe");
        if (null != eventData.departmentData && null != this.mDepartmentBean) {
            if (eventData.departmentData.fromto == Constants.LABORATORYFRAGMENT) {
                this.mDepartmentBean.departmentName = eventData.departmentData.departmentName;
                this.mDepartmentBean.departmentID = eventData.departmentData.departmentID;
                this.mDepartmentBean.departtype = eventData.departmentData.departtype;
                this.mParametersData.equipmentID = eventData.departmentData.equipmentID;
                ptrframelayout.autoRefresh(true);
            }
        }
        if (eventData.parametersBean != null && null != this.mParametersData) {
            if (eventData.parametersBean.fromTo == Constants.LABORATORYFRAGMENT) {
                this.mParametersData.startDateTime = eventData.parametersBean.startDateTime;
                this.mParametersData.endDateTime = eventData.parametersBean.endDateTime;
                this.mParametersData.equipmentID = eventData.parametersBean.equipmentID;
                ptrframelayout.autoRefresh(true);
            }
        }

        KLog.e(TAG, "实验室mParametersData.equipmentID=:" + mParametersData.equipmentID);
    }

    private void setToolbarTitle() {
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer(toolBarName + " > ");
        sb.append(getString(R.string.laboratory));
        toolbarToolbar.setTitle(sb.toString());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


}
