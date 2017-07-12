package com.shtoone.liqing.mvp.view.pitch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.pitch.PitchProductQueryContract;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.model.bean.PitchProductQueryData;
import com.shtoone.liqing.mvp.presenter.pitch.PitchProductQueryPresenter;
import com.shtoone.liqing.mvp.view.adapter.OnItemClickListener;
import com.shtoone.liqing.mvp.view.adapter.PitchProductionqueryFragmentAdapter;
import com.shtoone.liqing.mvp.view.base.BaseFragment;
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
 * Author： hengzwd on 2017/3/16.
 * Email：hengzwdhengzwd@qq.com
 */

public class PitchProductQueryFragment extends BaseFragment<PitchProductQueryContract.Presenter> implements PitchProductQueryContract.View {

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
    private View footloading;
    private View footerror;
    private View footnoloading;
    private DepartmentBean departmentBean;
    private ParametersData parametersData = new ParametersData();
    private PitchProductionqueryFragmentAdapter madapter;
    private LinearLayoutManager linearLayoutManager;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private int lastVisibleItemPosition;
    private boolean isLoading;
    private List<PitchProductQueryData.DataEntity> list;
    private String TAG = PitchProductQueryFragment.class.getSimpleName();

    public static PitchProductQueryFragment newInstance(DepartmentBean departmentBean) {

        PitchProductQueryFragment fragment = new PitchProductQueryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("depatmentdate", departmentBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_productquery_pitch, container, false);
        footloading = inflater.inflate(R.layout.item_footer_loading, container, false);
        footerror = inflater.inflate(R.layout.item_footer_error, container, false);
        footnoloading = inflater.inflate(R.layout.item_footer_not_loading, container, false);
        Bundle bundle = getArguments();
        departmentBean = (DepartmentBean) bundle.getSerializable("depatmentdate");
        KLog.e(TAG,"departmentBean=:"+departmentBean.toString());
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initStateBar(toolbarToolbar);
        setToolbarTitle();
        initDate();
        return view;

    }

    private void initDate() {

        parametersData.departType = departmentBean.departtype;
        parametersData.biaoshiid = departmentBean.departmentID;
        parametersData.deviceType = Constants.TYPE_WATERSTABILITY;
        parametersData.fromTo = Constants.PITCHPRODUCTQUERYFRAGMENT;
        departmentBean.fromto = Constants.PITCHPRODUCTQUERYFRAGMENT;
        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(_mActivity, 56));
        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(ptrframelayout);
        initToolbarBackNavigation(toolbarToolbar);
        //mAdapter的实例化要放到最开始，因为在没有数据的时候，滑动会空指针异常，因为  if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
        madapter = new PitchProductionqueryFragmentAdapter();
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

//                if (dy > 5) {
//                    BaseApplication.bus.post(new EventData(ConstantsUtils.OVERPROOFFABHIDE));
//                } else if (dy < -5) {
//                    BaseApplication.bus.post(new EventData(ConstantsUtils.OVERPROOFFABSHOW));
//                }
            }
        });
        madapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                departmentBean.fromto = Constants.PITCHPRODUCTQUERYFRAGMENT;
                departmentBean.bianhao = madapter.getData().get(position).getBianhao();
                departmentBean.equipmentID=madapter.getData().get(position).getSbbh();
                ((PitchActivity) _mActivity).startDetailActivity(departmentBean);
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        Map<String, String> map = new HashMap<>();
        map.put("departType", departmentBean.departtype);
        map.put("biaoshiid", departmentBean.departmentID);
        map.put("startTime", DateUtils.ChangeTimeToLong(parametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(parametersData.endDateTime));
        map.put("shebeibianhao", parametersData.equipmentID);
        parametersData.currentPage = 1;
        map.put("pageNo", parametersData.currentPage + "");
        map.put("maxPageItems", "10");
        map.put("peifan", parametersData.models);
        KLog.e(TAG,"map=:"+map.toString());
        mPresenter.requestPitchProductQueryData(map);
    }


    @Override
    public void loadMore() {
        super.loadMore();
        Map<String, String> map = new HashMap<>();
        map.put("departType", departmentBean.departtype);
        map.put("biaoshiid", departmentBean.departmentID);
        map.put("startTime", DateUtils.ChangeTimeToLong(parametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(parametersData.endDateTime));
        map.put("shebeibianhao", parametersData.equipmentID);
        map.put("pageNo", parametersData.currentPage +1+ "");
        map.put("maxPageItems", "10");
        map.put("peifan", parametersData.models);
        mPresenter.loadMoreData(map);

    }

    private void setToolbarTitle() {
//        if (null != toolbarToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
        String toolBarName = getResources().getString(R.string.liqing);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.HistoryData));
        toolbarToolbar.setTitle(sb.toString());
//        }
    }

    @Override
    public void showloadingMore() {
        madapter.removeAllFooterView();
        madapter.addFooterView(footloading);
        madapter.notifyDataSetChanged();
    }

    @Override
    public void lodingMorecompleted() {//所有数据都加载完毕
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
            KLog.e(TAG,"throwable=:"+throwable);
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
    public void responseLoadMore(PitchProductQueryData pitchProductQueryData) {
        isLoading = false;
        if (pitchProductQueryData.getData().size()>0) {
            parametersData.currentPage++;
        }
        list.addAll(pitchProductQueryData.getData());
        madapter.removeAllFooterView();
        madapter.addData(pitchProductQueryData.getData());
        madapter.notifyDataSetChanged();
    }


    @Override
    public void responsePitchProductQueryData(PitchProductQueryData pitchProductQueryData) {

        isLoading = false;
        parametersData.currentPage = 1;
        list = pitchProductQueryData.getData();
        madapter.removeAllHeaderView();
        madapter.removeAllFooterView();
        madapter.setOverProofData(pitchProductQueryData);
        madapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateDepartment(EventData eventData) {
        KLog.e("updateDepartment:Subscribe");
        if (eventData.parametersBean != null && null != this.parametersData) {
            if (eventData.parametersBean.fromTo == Constants.PITCHPRODUCTQUERYFRAGMENT) {
                this.parametersData.startDateTime = eventData.parametersBean.startDateTime;
                this.parametersData.endDateTime = eventData.parametersBean.endDateTime;
                this.parametersData.equipmentID = eventData.parametersBean.equipmentID;
                this.parametersData.handleType = eventData.parametersBean.handleType;
                this.parametersData.alarmLevel = eventData.parametersBean.alarmLevel;
                this.parametersData.models = eventData.parametersBean.models;
                KLog.e(TAG,"parametersData=:"+parametersData.toString());
                ptrframelayout.autoRefresh(true);
            }
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
            KLog.e(TAG,"t=:"+t);
            ToastUtils.showToast(BaseApplication.mContext, "数据异常");
            pagestatelayout.showError();
        }
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

    @OnClick(R.id.fab)
    public void onClick() {
        parametersData.deviceType=Constants.TYPE_PITCH;
        ((PitchActivity) _mActivity).startDrawerActivity(parametersData, null);
    }


    @NonNull
    @Override
    protected PitchProductQueryContract.Presenter createPresenter() {
        return new PitchProductQueryPresenter(this);
    }


}
