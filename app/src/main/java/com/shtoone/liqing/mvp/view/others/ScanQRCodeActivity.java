package com.shtoone.liqing.mvp.view.others;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.common.Constants;
import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.utils.SharedPreferencesUtils;
import com.socks.library.KLog;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanQRCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final String TAG = ScanQRCodeActivity.class.getSimpleName();
    public static final int REQUEST_IMAGE = 0;
    private QRCodeView mQRCodeView;
    private ImageView iv_back;
    private Gson mGson;
    Toolbar toolbarToolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        initView();
        initStateBar(toolbarToolbar);
        initToolbarMenu(toolbarToolbar);
//        initToolbarBackNavigation(toolbarToolbar);
        setToolbarTitle();
    }

    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }

    private void initView() {
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        toolbarToolbar= (Toolbar) findViewById(R.id.toolbar_toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mQRCodeView.startCamera();
        mQRCodeView.startSpot();
        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQRCodeView.stopCamera();
        mQRCodeView.stopSpot();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        mQRCodeView.startSpot();
        handleQRCode(result);
    }

    private void handleQRCode(String result) {
        Toast.makeText(ScanQRCodeActivity.this, result, Toast.LENGTH_SHORT).show();
        KLog.e(TAG,"result=:"+result);
        Bundle bundle = new Bundle();
        bundle.putString("shebeibianhao", result);
        Intent mIntent=new Intent(this,SubmitScannedDataActivity.class);
        mIntent.putExtra("shebeibianhao",bundle);
        startActivity(mIntent);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }


    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        mQRCodeView.closeFlashlight();
        finish();
    }

    private void go2Login() {
        //用户名和密码设为空，这样在点击重新登录后，即使没有登录，下次启动由于闪屏页的验证是需要用户名和密码，所以跳转到登录页面。
        SharedPreferencesUtils.put(BaseApplication.mContext, Constants.USERNAME, "");
        SharedPreferencesUtils.put(BaseApplication.mContext, Constants.PASSWORD, "");

        Intent mIntent = new Intent(this, LaunchActivity.class);
        //目的是为了通知LaunchActivity此时应该启动loginfragment，而不是闪屏页。
        mIntent.putExtra(Constants.FROM_TO, Constants.FROM_MAIN);
        startActivity(mIntent);
        finish();
    }

    protected void initToolbarMenu(final Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.menu_scan_relogin);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                KLog.e("---onMenuItemClick---");
                switch (item.getItemId()) {
                    case R.id.scan_relogin:
                        go2Login();
                        break;
                    case R.id.menu_sign_out:
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    private void setToolbarTitle(){
        toolbarToolbar.setTitle(getResources().getString(R.string.scan));
    }
}