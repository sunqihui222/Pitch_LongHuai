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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.laboratory.MarshallStabilityContract;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.MarshallStabilityBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.presenter.laboratory.MarshallStabilityPresenter;
import com.shtoone.liqing.mvp.view.adapter.MarshallStabilityFragmentAdapter;
import com.shtoone.liqing.mvp.view.adapter.OnItemClickListener;
import com.shtoone.liqing.mvp.view.base.BaseFragment;
import com.shtoone.liqing.utils.DateUtils;
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

import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/5/31.
 */
public class MarshallStabilityFragment extends BaseFragment<MarshallStabilityContract.Presenter> implements MarshallStabilityContract.View {

    private static final String TAG = MarshallStabilityFragment.class.getSimpleName();
    private Toolbar mToolbar;
    private FloatingActionButton fab;
    private boolean isRegistered = false;
    private View view;
    private ParametersData mParametersData= new ParametersData();
    private RecyclerView recyclerview;
    private PageStateLayout pagestatelayout;
    private PtrFrameLayout ptrframelayout;


    private View footloading;
    private View footerror;
    private View footnoloading;
    private ParametersData parametersData=new ParametersData();
    private DepartmentBean departmentBean;
    private boolean isLoading;
    private List<MarshallStabilityBean.DataBean> list;
    private MarshallStabilityFragmentAdapter madapter;
    private LinearLayoutManager linearLayoutManager;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private int lastVisibleItemPosition;


    @NonNull
    @Override
    protected MarshallStabilityContract.Presenter createPresenter() {
        return new MarshallStabilityPresenter(this);
    }

    public static MarshallStabilityFragment newInstance(DepartmentBean departmentBean) {
        KLog.e("-------MarshallStabilityFragment-----");
        MarshallStabilityFragment fragment = new MarshallStabilityFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("depatmentdate", departmentBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_marshall_stability, container, false);
        footloading=inflater.inflate(R.layout.item_footer_loading,container,false);
        footerror=inflater.inflate(R.layout.item_footer_error,container,false);
        footnoloading=inflater.inflate(R.layout.item_footer_not_loading,container,false);
        Bundle bundle = getArguments();
        departmentBean = (DepartmentBean) bundle.getSerializable("depatmentdate");
        EventBus.getDefault().register(this);
        initView(view);
        initData();
        return view;
    }

    public void initView(View view){

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_toolbar);
        recyclerview= (RecyclerView) view.findViewById(R.id.recyclerview);
        ptrframelayout = (PtrFrameLayout) view.findViewById(R.id.ptrframelayout);
        pagestatelayout = (PageStateLayout) view.findViewById(R.id.pagestatelayout);

    }

    private void initData() {
        KLog.e(TAG,"departmentBean="+departmentBean.toString());
        parametersData.departType = departmentBean.departtype;
        parametersData.biaoshiid = departmentBean.departmentID;
        parametersData.fromTo = Constants.MAESHALLFRAGMENT;
        departmentBean.fromto = Constants.MAESHALLFRAGMENT;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KLog.e(TAG, parametersData.toString());
                parametersData.fromTo = Constants.MAESHALLFRAGMENT;
                ((LaboratoryActivity) _mActivity).startDrawerActivity(parametersData, null);
            }
        });

        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        initPtrFrameLayout(ptrframelayout);

        //mAdapter的实例化要放到最开始，因为在没有数据的时候，滑动会空指针异常，因为  if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
        madapter = new MarshallStabilityFragmentAdapter();
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
                KLog.e(TAG,"------setOnItemClickListener-----");
                String f_guid = madapter.getData().get(position).getF_GUID();
                jump2MarshallStabDetailActivity(f_guid);
            }
        });

    }

    @Override
    public void loadData() {
        super.loadData();
        Map<String,String> map=new HashMap<>();
        map.put("departType",departmentBean.departtype);
        map.put("biaoshiid",departmentBean.departmentID);
        map.put("startTime", DateUtils.ChangeTimeToLong(parametersData.startDateTime));
        map.put("endTime", DateUtils.ChangeTimeToLong(parametersData.endDateTime));
        map.put("shebeibianhao",parametersData.equipmentID);
        map.put("isQualified",parametersData.level);
        parametersData.currentPage=1;
        map.put("pageNo",parametersData.currentPage+"");
        map.put("maxPageItems","15");
        KLog.e(TAG,"map=:"+map.toString());
        mPresenter.requestMarshallFragmentData(map);
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
        map.put("isQualified",parametersData.level);
        map.put("pageNo", parametersData.currentPage +1+ "");
        map.put("maxPageItems", "15");
        mPresenter.loadMoreData(map);

    }

    @Override
    public void responseMarshallData(MarshallStabilityBean marshallStabilityData) {

        isLoading=false;
        parametersData.currentPage=1;
        list=marshallStabilityData.getData();
        madapter.removeAllHeaderView();
        madapter.removeAllFooterView();
        madapter.setNewData(marshallStabilityData.getData());
        madapter.notifyDataSetChanged();
    }

    @Override
    public void responseLoadMore(MarshallStabilityBean marshallStabilityMoreData) {
        isLoading = false;
        if (marshallStabilityMoreData.getData().size()>0) {
            parametersData.currentPage++;
        }
        list.addAll(marshallStabilityMoreData.getData());
        madapter.removeAllFooterView();
//        madapter.addData(marshallStabilityMoreData.getData());
        KLog.e(TAG,"marshallStabilityMoreData=:"+marshallStabilityMoreData.getData().toString());
        madapter.notifyDataSetChanged();
    }

    public void jump2MarshallStabDetailActivity(String f_guid){
        KLog.e(TAG,"----jump2MarshallStabDetailActivity----");
        Intent intent = new Intent(_mActivity, MarshallStabDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("f_guid", f_guid);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void setToolbarTitle() {
//        if (null != toolbarToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
//        StringBuffer sb = new StringBuffer("广东云湛高速公路" + " > ");
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.marshall_list));
        mToolbar.setTitle(sb.toString());
//        }
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateDepartment(EventData eventData) {
        KLog.e("updateDepartment:Subscribe");
        KLog.e(eventData.parametersBean.fromTo);
        if (eventData.parametersBean != null && null != this.parametersData) {
            if (eventData.parametersBean.fromTo == Constants.MAESHALLFRAGMENT) {
                this.parametersData.startDateTime = eventData.parametersBean.startDateTime;
                this.parametersData.endDateTime = eventData.parametersBean.endDateTime;
                this.parametersData.equipmentID = eventData.parametersBean.equipmentID;
                this.parametersData.level=eventData.parametersBean.level;
                KLog.e(TAG,"马歇尔界面parametersData="+parametersData.toString());
                ptrframelayout.autoRefresh(true);
            }
        }
        if (eventData.position == Constants.MAESHALLFRAGMENT) {
            ptrframelayout.autoRefresh(true);
        }
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
