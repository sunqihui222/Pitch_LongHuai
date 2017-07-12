package com.shtoone.liqing.mvp.view.others;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.mvp.contract.others.SplashContract;
import com.shtoone.liqing.mvp.presenter.others.SplashPresenter;
import com.shtoone.liqing.mvp.view.base.BaseFragment;
import com.shtoone.liqing.utils.SharedPreferencesUtils;
import com.shtoone.liqing.widget.CircleTextProgressbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class SplashFragment extends BaseFragment<SplashContract.Presenter> implements SplashContract.View {

    private static final String TAG = SplashFragment.class.getSimpleName();
    //目的是为了判断网络请求时，用户是否退出
    private boolean isExit;
    private boolean isFirstentry;

    @BindView(R.id.ctp_skip)
    CircleTextProgressbar ctpSkip;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @NonNull
    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctpSkip.setOutLineColor(Color.TRANSPARENT);
        ctpSkip.setInCircleColor(Color.parseColor("#99000000"));
        ctpSkip.setProgressColor(Color.RED);
        ctpSkip.setProgressLineWidth(3);
        ctpSkip.setTimeMillis(Constants.DEFAULT_TIMEOUT * 1000);
        ctpSkip.setText(Constants.DEFAULT_TIMEOUT + "s");
        ctpSkip.setCountdownProgressListener(1, new CircleTextProgressbar.OnCountdownProgressListener() {
            int intTime = Constants.DEFAULT_TIMEOUT;
            double temp = 100.0 / Constants.DEFAULT_TIMEOUT;

            @Override
            public void onProgress(int what, int progress) {
                if (((int) (progress % temp)) == 0) {

                    if (ctpSkip != null) {
                        ctpSkip.setText((--intTime) + "s");
                    }
                }
            }
        });
        ctpSkip.start();
        initData();
    }

    private void initData() {
        isFirstentry = (boolean) SharedPreferencesUtils.get(BaseApplication.mContext, Constants.ISFIRSTENTRY, true);
        //严格按照流程来，Presenter层的代码应该在View层代码的必要数据加载完成之后才调用
        mPresenter.checkLogin();
        mPresenter.checkUpdate();
    }

    @OnClick(R.id.ctp_skip)
    public void onClick() {
    }

    @Override
    public void go2LoginOrGuide() {
        if (isExit) {
            return;
        }
        if (isFirstentry) {
            start(GuideFragment.newInstance());
//            _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));

        } else {
            start(LoginFragment.newInstance(Constants.FROM_SPLASH));
//            _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));

        }
    }

    @Override
    public void go2Main() {
        if (isExit) {
            return;
        }
        _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));
        //start(MainFragment.newInstance());
        _mActivity.finish();
    }

    //登录跳转到扫描界面
    public void go2ScanQR(){
        //Android6.0以上动态请求打开相机权限
        if (ContextCompat.checkSelfPermission(_mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(_mActivity, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            _mActivity.startActivity(new Intent(_mActivity, ScanQRCodeActivity.class));
            _mActivity.finish();
        }
    }

    /*
    使用原生方式Android6.0以上动态请求打开相机权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(_mActivity, ScanQRCodeActivity.class));
            } else {
                // Permission Denied
                Toast.makeText(_mActivity, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onDestroy() {
        isExit = true;
        super.onDestroy();
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }
}
