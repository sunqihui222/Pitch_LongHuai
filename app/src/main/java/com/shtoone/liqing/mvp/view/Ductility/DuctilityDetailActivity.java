package com.shtoone.liqing.mvp.view.Ductility;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.HttpHelper;
import com.shtoone.liqing.mvp.model.bean.DuctilityDetailBean;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.widget.PageStateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/6/7.
 */

public class DuctilityDetailActivity extends BaseActivity {

    @BindView(R.id.ductility_detail_date)
    TextView tv_testtime;
    @BindView(R.id.ductility_detail_projectname)
    TextView  tv_projectname;
    @BindView(R.id.ductility_detail_number)
    TextView  tv_detail_number;
    @BindView(R.id.ductility_detail_name)
    TextView  tv_detail_name;
    @BindView(R.id.ductility_detail_part)
    TextView  tv_detail_part;
    @BindView(R.id.ductility_detail_desc)
    TextView  tv_detail_desc;
    @BindView(R.id.ductility_detail_single1)
    TextView  tv_detail_single1;
    @BindView(R.id.ductility_detail_standard)
    TextView tv_detail_standard;
    @BindView(R.id.ductility_detail_average)
    TextView tv_detail_average;
    @BindView(R.id.toolbar_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ptr_ductity_detail_fragment)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.psl_ductity_detail_fragment)
    PageStateLayout pagestatelayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_ductility_detail);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }


    private void initData() {
        initStateBar(mToolbar);
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(ptrframelayout);
        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        pagestatelayout.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        String f_guid = getIntent().getStringExtra("f_guid");
        HttpHelper.getInstance().initService().requestDuctilityDetail(f_guid).enqueue(new Callback<DuctilityDetailBean>() {
            @Override
            public void onResponse(Call<DuctilityDetailBean> call, Response<DuctilityDetailBean> response) {
                if (response.isSuccessful()){
                    DuctilityDetailBean body = response.body();
                    tv_testtime.setText(body.getData().get(0).getIS_TESTTIME());
                    tv_projectname.setText(body.getData().get(0).getHeader3());
                    tv_detail_number.setText(body.getData().get(0).getHeader5());
                    tv_detail_name.setText(body.getData().get(0).getSHeader3());
                    tv_detail_part.setText(body.getData().get(0).getSHeader2());
                    tv_detail_desc.setText(body.getData().get(0).getSHeader4());
                    String danzhi = body.getData().get(0).getYandu11()+"cm  "+body.getData().get(0).getYandu12()+"cm  "+body.getData().get(0).getYandu13()+"cm  ";
                    tv_detail_single1.setText(danzhi);
                    String standrs = body.getData().get(0).getBiaozhunzhi1() + "~" + body.getData().get(0).getBiaozhunzhi2();
                    tv_detail_standard.setText(standrs);
                    tv_detail_average.setText(body.getData().get(0).getAvgvalue1());
                }else{

                }
            }

            @Override
            public void onFailure(Call<DuctilityDetailBean> call, Throwable t) {

            }
        });
    }

    private void setToolbarTitle() {
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer( toolBarName+ " > ");
        sb.append(getString(R.string.yandu_detail));
        mToolbar.setTitle(sb.toString());
    }
}
