package com.shtoone.liqing.mvp.view.laboratory;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.shtoone.liqing.BaseApplication;
import com.shtoone.liqing.R;
import com.shtoone.liqing.event.EventData;
import com.shtoone.liqing.mvp.contract.base.BaseContract;
import com.shtoone.liqing.mvp.model.bean.DepartmentBean;
import com.shtoone.liqing.mvp.model.bean.ParametersData;
import com.shtoone.liqing.mvp.view.Ductility.DuctilityFragment;
import com.shtoone.liqing.mvp.view.asphaltpenetration.AsphaltPenetrationFragment;
import com.shtoone.liqing.mvp.view.base.BaseActivity;
import com.shtoone.liqing.mvp.view.others.DrawerActivity;
import com.shtoone.liqing.mvp.view.softeningpoint.SofteningPointFragment;
import com.socks.library.KLog;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/5/31.
 */
public class LaboratoryActivity extends BaseActivity {

    private static final String TAG = LaboratoryActivity.class.getSimpleName();
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private AHBottomNavigation bottomNavigation;
    private int bottomNavigationPreposition = 0;
    private SupportFragment[] mFragments = new SupportFragment[4];
    private FrameLayout fl_container;
    private DepartmentBean departmentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory);

        Intent intent =getIntent();
        Bundle bundle=intent.getBundleExtra("departmentData");
        departmentBean= (DepartmentBean) bundle.getSerializable("departmentData");
        KLog.e(TAG,"departmentBean=:"+departmentBean.toString());

        if (savedInstanceState == null) {
            mFragments[0] = MarshallStabilityFragment.newInstance(departmentBean);
            mFragments[1] = SofteningPointFragment.newInstance(departmentBean);
            mFragments[2] = AsphaltPenetrationFragment.newInstance(departmentBean);
            mFragments[3] = DuctilityFragment.newInstance(departmentBean);
            int showPosition = 0;
            loadMultipleRootFragment(R.id.fl_container_laboratory_activity, showPosition, mFragments[0], mFragments[1], mFragments[2],mFragments[3]);
//            loadMultipleRootFragment(R.id.fl_container_laboratory_activity, showPosition, mFragments[0]);
        } else {
            mFragments[0] = findFragment(MarshallStabilityFragment.class);
            mFragments[1] = findFragment(SofteningPointFragment.class);
            mFragments[2] = findFragment(AsphaltPenetrationFragment.class);
            mFragments[3] = findFragment(DuctilityFragment.class);
        }

        initView();
        initData();
    }


    private void initView() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation_laboratory_activity);
        fl_container = (FrameLayout) findViewById(R.id.fl_container_laboratory_activity);
    }

    public void initData() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.marshall_stability, R.drawable.ic_search_white_18dp, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.softening_point, R.drawable.ic_overproof, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.asphalt_penetration, R.drawable.ic_statistic, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.ductility, R.drawable.ic_statistic, R.color.white);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setColored(true);
        bottomNavigation.setForceTint(false);
        bottomNavigation.setAccentColor(getResources().getColor(R.color.base_color));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.gray));
        bottomNavigation.setForceTitlesDisplay(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, final boolean wasSelected) {
                showHideFragment(mFragments[position], mFragments[bottomNavigationPreposition]);
                bottomNavigationPreposition = position;
                if (wasSelected) {
                    BaseApplication.bus.post(new EventData(position));
                }

                fl_container.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!wasSelected && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int cx = (fl_container.getLeft() + fl_container.getRight()) / 2;
                            int cy = fl_container.getBottom();
                            int radius = Math.max(fl_container.getWidth(), fl_container.getHeight());
                            Animator mAnimator = ViewAnimationUtils.createCircularReveal(fl_container, cx, cy, 0, radius);
                            mAnimator.setDuration(300);
                            mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                            mAnimator.start();
                        }
                    }
                });

            }
        });

    }

    public void startDrawerActivity(@Nullable ParametersData mParametersData, @Nullable DepartmentBean departmentData) {
        Intent intent = new Intent(this, DrawerActivity.class);
        Bundle bundle = new Bundle();
        if (mParametersData != null && departmentData == null) {
            bundle.putSerializable("mparametersData", mParametersData);
        } else if (mParametersData == null && departmentData != null) {
            bundle.putSerializable("departmentData", departmentData);
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }


}
