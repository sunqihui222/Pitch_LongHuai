package com.shtoone.liqing.mvp.view.paveSite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.contract.paveSite.PaveSiteContract;
import com.shtoone.liqing.mvp.view.base.BaseFragment;
import com.shtoone.liqing.mvp.view.endpress.EndpressTempActivity;
import com.shtoone.liqing.mvp.view.pave.PaveTemptureActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaveSiteFragment extends BaseFragment<PaveSiteContract.Presenter> implements PaveSiteContract.View {
    @BindViews({R.id.cv_pave_speed, R.id.cv_pave_site})
    List<CardView> cardViewList;
    //    CardView cvLocation, cvNianYa, cvPaveSite;
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbarToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pave_site, container, false);
        ButterKnife.bind(this, view);
        initToolbarNavigationMenu(toolbarToolbar);
        setToolbarTitle();
        return view;
    }

    public static PaveSiteFragment newInstance() {
        return new PaveSiteFragment();
    }

    private void setToolbarTitle() {
        String toolBarName = getResources().getString(R.string.toolbar_name);
        StringBuffer sb = new StringBuffer(toolBarName + " > ");
        sb.append(getString(R.string.pavesite));
        toolbarToolbar.setTitle(sb.toString());
    }


    @OnClick(R.id.cv_pave_speed)
    void cv2OnClick(){
        startActivity(new Intent(_mActivity, EndpressTempActivity.class));
    }

    @OnClick(R.id.cv_pave_site)
    void cv3OnClick(){
        startActivity(new Intent(_mActivity, PaveTemptureActivity.class));
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

    @NonNull
    @Override
    protected PaveSiteContract.Presenter createPresenter() {
        return null;
    }
}
