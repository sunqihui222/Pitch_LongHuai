package com.shtoone.liqing.mvp.view.softeningpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.laboratory.SofteningPointContract;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.model.bean.SofteningPointBean;
import com.shtoone.liqing.mvp.presenter.laboratory.SoftentingPointPresenter;
import com.shtoone.liqing.mvp.view.adapter.OnItemClickListener;
import com.shtoone.liqing.mvp.view.adapter.SofteningPointFragmentAdapter;
import com.shtoone.liqing.mvp.view.base.BaseFragment;
import com.shtoone.liqing.mvp.view.laboratory.LaboratoryActivity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import retrofit2.adapter.rxjava.HttpException;


/**
 * Created by Administrator on 2017/5/31.
 */

public class SofteningPointFragment extends BaseFragment<SofteningPointContract.Presenter> implements SofteningPointContract.View {

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

    private ParametersData parametersData = new ParametersData();
    private DepartmentBean mDepartmentBean;
    private LinearLayoutManager linearLayoutManager;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private View footloading;
    private View footerror;
    private View footnoloading;
    private int lastVisibleItemPosition;
    private boolean isLoading;
    private SofteningPointFragmentAdapter madapter;
    private List<SofteningPointBean.DataEntity> list;
    private String TAG = SofteningPointFragment.class.getSimpleName();
    private String qualified;

    public static SofteningPointFragment newInstance(DepartmentBean departmentBean) {
        SofteningPointFragment fragment = new SofteningPointFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("depatmentdate", departmentBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_softeningpoint, container, false);
        footloading = inflater.inflate(R.layout.item_footer_loading, container, false);
        footerror = inflater.inflate(R.layout.item_footer_error, container, false);
        footnoloading = inflater.inflate(R.layout.item_footer_not_loading, container, false);
        Bundle bundle = getArguments();
        mDepartmentBean = (DepartmentBean) bundle.getSerializable("depatmentdate");
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setToolbarTitle();
        initDate();

        return view;
    }

    private void initDate() {
        KLog.e(TAG, "departmentBean=" + mDepartmentBean.toString());
        parametersData.departType = mDepartmentBean.departtype;
        parametersData.biaoshiid = mDepartmentBean.departmentID;
        parametersData.fromTo = Constants.SofteningPoingFragment;
        mDepartmentBean.fromto = Constants.SofteningPoingFragment;
        parametersData.deviceType = Constants.TYPE_RUANHUADIAN;
        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(_mActivity, 56));
        initPtrFrameLayout(ptrframelayout);

        initToolbarBackNavigation(toolbarToolbar);
        madapter = new SofteningPointFragmentAdapter();
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        SlideInLeftAnimationAdapter mSlideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(madapter);
        mSlideInLeftAnimationAdapter.setDuration(500);
        mSlideInLeftAnimationAdapter.setInterpolator(new OvershootInterpolator(0.5f));
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(mSlideInLeftAnimationAdapter);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(mScaleInAnimationAdapter);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //还有一个不完美的地方就是当规定的item个数时，最后一个item在屏幕外滑到可见时，其底部没有footview，这点以后再解决。
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == madapter.getItemCount() && list.size() >= 4) {

                    if (!isLoading) {
                        isLoading = true;
                        recyclerview.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KLog.e("第" + parametersData.currentPage + "页");
                                loadMore();
                                KLog.e("上拉加载更多mParametersData.currentPage=" + parametersData.currentPage);
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

            }
        });

        madapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(_mActivity, SofteningPointDetailActivity.class);
                String f_guid = madapter.getData().get(position).getF_GUID();
                intent.putExtra("f_guid", f_guid);
                startActivity(intent);

            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        Map<String, String> map = new HashMap<>();
        map.put("departType", mDepartmentBean.departtype);
        map.put("startTime", DateUtils.ChangeTimeToLong(parametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(parametersData.endDateTime));
        map.put("biaoshiid", mDepartmentBean.departmentID);
        map.put("F_SBBH", parametersData.equipmentID);
        map.put("isQualified", parametersData.level);
        parametersData.currentPage = 1;
        map.put("pageNo", parametersData.currentPage + "");
        map.put("maxPageItems", "15");
        mPresenter.requestSofteningPointBean(map);
    }


    @Override
    public void loadMore() {
        super.loadMore();
        Map<String, String> map = new HashMap<>();
        map.put("departType", mDepartmentBean.departtype);
        map.put("startTime", DateUtils.ChangeTimeToLong(parametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(parametersData.endDateTime));
        map.put("biaoshiid", mDepartmentBean.departmentID);
        map.put("F_SBBH", parametersData.equipmentID);
        map.put("isQualified", parametersData.level);
        map.put("pageNo", parametersData.currentPage + 1 + "");
        map.put("maxPageItems", "15");
        mPresenter.loadMoreData(map);

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
    public void showloadingMore() {

        madapter.removeAllFooterView();
        madapter.addFooterView(footloading);
        madapter.notifyDataSetChanged();
    }

    @Override
    public void lodingMorecompleted() {

        madapter.removeAllFooterView();
        madapter.addFooterView(footnoloading);
        madapter.notifyDataSetChanged();

    }

    @Override
    public void showLoadMoreError(Throwable throwable) {
        isLoading = false;
        if (throwable instanceof ConnectException) {
            ToastUtils.showToast(BaseApplication.mContext, "网络异常,请检测网络");
            madapter.removeAllFooterView();
            madapter.addFooterView(footerror);
        } else if (throwable instanceof HttpException) {
            ToastUtils.showToast(BaseApplication.mContext, "服务器异常");
            madapter.removeAllFooterView();
            madapter.addFooterView(footerror);
        } else if (throwable instanceof SocketTimeoutException) {
            ToastUtils.showToast(BaseApplication.mContext, "连接超时");
            madapter.removeAllFooterView();
            madapter.addFooterView(footerror);
        } else if (throwable instanceof JSONException) {
            ToastUtils.showToast(BaseApplication.mContext, "解析异常");
            madapter.removeAllFooterView();
            madapter.addFooterView(footerror);
        } else {
            ToastUtils.showToast(BaseApplication.mContext, "数据异常");
            madapter.removeAllFooterView();
            madapter.addFooterView(footerror);
        }
        madapter.notifyDataSetChanged();
        recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                madapter.removeAllFooterView();
                madapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    @Override
    public void responseLoadMore(SofteningPointBean softeningPointBean) {
        isLoading = false;
        if (softeningPointBean.getData().size() > 0) {
            parametersData.currentPage++;
        }
        list.addAll(softeningPointBean.getData());
        madapter.removeAllFooterView();
        madapter.addData(softeningPointBean.getData());
        madapter.notifyDataSetChanged();
    }

    @Override
    public void responseSofteningPointBean(SofteningPointBean softeningPointBean) {

        isLoading = false;
        parametersData.currentPage = 1;
        list = softeningPointBean.getData();
        madapter.removeAllHeaderView();
        madapter.removeAllFooterView();
        // madapter.setSofteningPointData(softeningPointBean);
        madapter.setNewData(softeningPointBean.getData());
        madapter.notifyDataSetChanged();

    }

    @NonNull
    @Override
    protected SofteningPointContract.Presenter createPresenter() {
        return new SoftentingPointPresenter(this);
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

    @OnClick(R.id.fab)
    public void onClick() {
        parametersData.fromTo = Constants.SofteningPoingFragment;
        ((LaboratoryActivity) _mActivity).startDrawerActivity(parametersData, null);
    }

    private void setToolbarTitle() {
        //        if (null != toolbarToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
        String toolBarName = getResources().getString(R.string.Lab);
        StringBuffer sb = new StringBuffer(toolBarName + " > ");
        sb.append(getString(R.string.softeningpoint));
        toolbarToolbar.setTitle(sb.toString());
        //        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateDepartment(EventData eventData) {
        KLog.e("updateDepartment:Subscribe");
        KLog.e(eventData.parametersBean.fromTo);
        if (eventData.parametersBean != null && null != this.parametersData) {
            if (eventData.parametersBean.fromTo == Constants.SofteningPoingFragment) {
                this.parametersData.startDateTime = eventData.parametersBean.startDateTime;
                this.parametersData.endDateTime = eventData.parametersBean.endDateTime;
                this.parametersData.equipmentID = eventData.parametersBean.equipmentID;
                this.parametersData.level = eventData.parametersBean.level;
                ptrframelayout.autoRefresh(true);
                Log.e("level", eventData.parametersBean.level);
            }
        }
        if (eventData.position == Constants.SofteningPoingFragment) {
            ptrframelayout.autoRefresh(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
