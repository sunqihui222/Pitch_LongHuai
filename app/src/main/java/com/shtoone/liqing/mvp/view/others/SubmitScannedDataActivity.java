package com.shtoone.liqing.mvp.view.others;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dd.CircularProgressButton;
import com.sdsmdg.tastytoast.TastyToast;
import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.contract.others.SubmitScannedDataContract;
import com.shtoone.liqing.mvp.presenter.others.SubmitScannedDataPresenter;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.utils.DateUtils;
import com.shtoone.liqing.utils.DensityUtils;
import com.shtoone.liqing.utils.ToastUtils;
import com.shtoone.liqing.widget.PageStateLayout;
import com.socks.library.KLog;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava.HttpException;

public class SubmitScannedDataActivity extends BaseActivity<SubmitScannedDataContract.Presenter> implements SubmitScannedDataContract.View {

    private static final String TAG = SubmitScannedDataActivity.class.getSimpleName();
    @BindView(R.id.tv_shebeibianhao)
    TextView equipmentNumber;
    @BindViews({R.id.rb_general, R.id.rb_modified, R.id.rb_above, R.id.rb_center, R.id.rb_below, R.id.rb_is_standard, R.id.rb_not_standard})
    List<RadioButton> radioButtonList;
    @BindViews({R.id.rg_pitch_type, R.id.rg_lay, R.id.rg_use_standard})
    List<RadioGroup> radioGroupList;
    @BindView(R.id.btn_submit)
    CircularProgressButton btnSubmit;
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.psl_produce_query_detail_fragment)
    PageStateLayout pslProduceQuery;

    private String shebeibianhao, pitchType, lay,isUseStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_scanneddata);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected SubmitScannedDataContract.Presenter createPresenter() {
        return new SubmitScannedDataPresenter(this);
    }

    private void initView() {
        Bundle bundle = getIntent().getBundleExtra("shebeibianhao");
        this.shebeibianhao =  bundle.getString("shebeibianhao");
        KLog.e(TAG,"shebeibianhao="+ this.shebeibianhao);
        initStateBar(toolbar);
        initToolbarBackNavigation(toolbar);
        setToolbar();

        //获取不到数据时，自定义页面展示
        pslProduceQuery.setPadding(0, 0, 0, DensityUtils.dp2px(this, 0));
        initPageStateLayout(pslProduceQuery);

        //获取默认值
        pitchType = radioButtonList.get(0).getText().toString();
        lay = radioButtonList.get(2).getText().toString();
        isUseStandard = String.valueOf(1);

        equipmentNumber.setText(this.shebeibianhao);//展示扫码获得的设备编号

        radioGroupList.get(0).setOnCheckedChangeListener(pitchTypeOnChecked);
        radioGroupList.get(1).setOnCheckedChangeListener(layOnChecked);
        radioGroupList.get(2).setOnCheckedChangeListener(useStandardOnChecked);
    }

    RadioGroup.OnCheckedChangeListener pitchTypeOnChecked = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_general:
                    pitchType = radioButtonList.get(0).getText().toString();
                    break;
                case R.id.rb_modified:
                    pitchType = radioButtonList.get(1).getText().toString();
                    break;
                default:
                    break;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener layOnChecked = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_above:
                    lay = radioButtonList.get(2).getText().toString();
                    break;
                case R.id.rb_center:
                    lay = radioButtonList.get(3).getText().toString();
                    break;
                case R.id.rb_below:
                    lay = radioButtonList.get(4).getText().toString();
                    break;
                default:
                    break;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener useStandardOnChecked = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_is_standard:
                    isUseStandard = String.valueOf(1);
                    break;
                case R.id.rb_not_standard:
                    isUseStandard = String.valueOf(0);
                    break;
                default:
                    break;
            }
        }
    };

    @OnClick(R.id.btn_submit)
    protected void btnOnclick(View v) {
        new MaterialDialog.Builder(this)
                .title("确认")
                .content("请问您确定填写无误并提交吗？")
                .positiveText("确定")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MaterialDialog progressDialog = new MaterialDialog.Builder(SubmitScannedDataActivity.this)
                                .title("提交")
                                .content("正在提交中，请稍等……")
                                .progress(true, 0)
                                .progressIndeterminateStyle(true)
                                .cancelable(false).build();
                        handleSurveySubmit(progressDialog);
                    }
                })
                .negativeText("放弃")
                .show();
    }

    private void handleSurveySubmit(final MaterialDialog progressDialog){
        progressDialog.show();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("shebeibianhao",shebeibianhao.trim());
        paramsMap.put("type",pitchType.trim());
        paramsMap.put("layer",lay.trim());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String handleTime = sdf.format(Calendar.getInstance().getTime());
        Log.e("提交时间",handleTime);
        paramsMap.put("createtime", DateUtils.ChangeTimeToLong(handleTime));
        paramsMap.put("biaoshi",isUseStandard.trim());
        mPresenter.submit(paramsMap);
        progressDialog.dismiss();
    }

    private void setToolbar(){
        toolbar.setTitle(getResources().getString(R.string.toolbar_name)+"数据调查");
    }

    //region SubmitScannedDataContract中View方法实现
    @Override
    public void submitSuccessfully(String message) {
        TastyToast.makeText(BaseApplication.mContext, message, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
    }

    @Override
    public void submitFailed(String message) {
        TastyToast.makeText(BaseApplication.mContext, message, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }
    //endregion

    //region BaseContract中View方法实现
    @Override
    public void showContent() {}

    @Override
    public void showError(Throwable t) {
        if (t instanceof ConnectException) {
            ToastUtils.showToast(BaseApplication.mContext, "网络异常,请检测网络");
            pslProduceQuery.showNetError();
        } else if (t instanceof HttpException) {
            ToastUtils.showToast(BaseApplication.mContext, "服务器异常");
            pslProduceQuery.showError();
        } else if (t instanceof SocketTimeoutException) {
            ToastUtils.showToast(BaseApplication.mContext, "连接超时");
            pslProduceQuery.showError();
        } else if (t instanceof JSONException) {
            ToastUtils.showToast(BaseApplication.mContext, "解析异常");
            pslProduceQuery.showError();
        } else {
            ToastUtils.showToast(BaseApplication.mContext, "数据异常");
            pslProduceQuery.showError();
        }
    }

    @Override
    public void showLoading() {}

    @Override
    public void showEmpty() {}
    //endregion
}
