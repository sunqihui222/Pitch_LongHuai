package com.shtoone.liqing.mvp.view.softeningpoint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.contract.laboratory.SofteningPointDetailContract;
import com.shtoone.liqing.mvp.model.bean.SofteningPointDetailBean;
import com.shtoone.liqing.mvp.presenter.laboratory.SofteningPointDetailPresenter;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.utils.ToastUtils;
import com.shtoone.liqing.widget.PageStateLayout;
import com.socks.library.KLog;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/6/5.
 */

public class SofteningPointDetailActivity extends BaseActivity<SofteningPointDetailContract.Presenter> implements SofteningPointDetailContract.View  {

    private static final String TAG = SofteningPointDetailActivity.class.getSimpleName();
    @BindView(R.id.toolbar_toolbar)
    Toolbar  toolbarToolbar;
    @BindView(R.id.softpoint_detail_date)
    TextView tv_testtime;
    @BindView(R.id.softpoint_detail_projectname)
    TextView tv_projectname;
    @BindView(R.id.softpoint_detail_number)
    TextView tv_detail_number;
    @BindView(R.id.softpoint_detail_name)
    TextView tv_detail_name;
    @BindView(R.id.softpoint_detail_part)
    TextView tv_detail_part;
    @BindView(R.id.softpoint_detail_desc)
    TextView tv_detail_desc;
    @BindView(R.id.softpoint_detail_single1)
    TextView tv_detail_single1;
    @BindView(R.id.softpoint_detail_single2)
    TextView tv_detail_single2;
    @BindView(R.id.softpoint_detail_standard)
    TextView tv_detail_standard;
    @BindView(R.id.softpoint_detail_average)
    TextView tv_detail_average;
    @BindView(R.id.ptr_soften_detail_fragment)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.psl_soften_detail_fragment)
    PageStateLayout pagestatelayout;
    private String f_guid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_softeningpoint_detail);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected SofteningPointDetailContract.Presenter createPresenter() {
        return new SofteningPointDetailPresenter(this);
    }

    private void initData() {
        initStateBar(toolbarToolbar);
        setToolbarTitle();
        initToolbarBackNavigation(toolbarToolbar);
        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(ptrframelayout);
        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        f_guid = getIntent().getStringExtra("f_guid");
        mPresenter.requestSofteningPointDetailData(f_guid);
        KLog.e("软化度f_guid="+f_guid);
    }

    @Override
    public void loadData() {
        super.loadData();
        KLog.e(TAG,"==========loadData=============");
    }

    @Override
    public void responseSofteningPointDetailData(SofteningPointDetailBean softeningPointDetailBean) {
        setData2View(softeningPointDetailBean);
        KLog.e(TAG,"softeningPointDetailBean"+softeningPointDetailBean.toString());
    }

    public void setData2View(SofteningPointDetailBean softeningPointDetailBean){
        List<SofteningPointDetailBean.DataEntity> data = softeningPointDetailBean.getData();
        tv_testtime.setText(data.get(0).getIs_testtime());
        tv_projectname.setText(data.get(0).getHeader3());
        tv_detail_number.setText(data.get(0).getHeader5());
        tv_detail_name.setText(data.get(0).getSHeader3());
        tv_detail_part.setText(data.get(0).getSHeader2());
        tv_detail_desc.setText(data.get(0).getSHeader4());
        tv_detail_single1.setText(data.get(0).getRuanhuadian1());
        tv_detail_single2.setText(data.get(0).getRuanhuadian2());
        tv_detail_standard.setText(data.get(0).getBiaoZhun1());
        tv_detail_average.setText(data.get(0).getAvgvalue1());
    }


    private void setToolbarTitle() {

        String toolBarName = getResources().getString(R.string.Lab);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.softeningpoint)+"详情");
        toolbarToolbar.setTitle(sb.toString());
        //        }
    }

    protected void initToolbarBackNavigation(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
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
    public boolean isCanDoRefresh() {
        //判断是哪种状态的页面，都让其可下拉
        if (pagestatelayout.isShowContent) {
            //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新

            return false;
        } else {
            return true;
        }
    }
}
