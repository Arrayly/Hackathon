package project.hackathon;

import android.graphics.PorterDuff;
import android.view.Menu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import project.hackathon.animations.DepthPageTransformer;
import project.hackathon.fragments.BlankFragment1;
import project.hackathon.fragments.BlankFragment2;
import project.hackathon.fragments.BlankFragment3;
import project.hackathon.fragments.BlankFragment4;

public class DashboardActivity extends AppCompatActivity {

    private TabLayout tab_layout;

    private ViewPager mViewPager;

    private static final int[] TAB_ICONS = {R.drawable.ic_equalizer, R.drawable.ic_credit_card,
            R.drawable.ic_pie_chart_outline,R.drawable.ic_person};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initToolbar();
        initComponent();
    }

    private void initComponent() {
        tab_layout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.main_viewpager);

        //SetUp view pager
        //Connect state pager to view with special state pager adapter
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());

        if (mViewPager != null) {
            mViewPager.setPageTransformer(true, new DepthPageTransformer());
            mViewPager.setAdapter(pagerAdapter);

        }

        if (tab_layout != null) {
            tab_layout.setupWithViewPager(mViewPager);
            addTabIcons();
            setIconColors();
            tab_layout.addOnTabSelectedListener(new OnTabSelectedListener() {
                @Override
                public void onTabSelected(final Tab tab) {
                    int tabPos = tab.getPosition();
                    tab.getIcon()
                            .setColorFilter(getResources().getColor(R.color.light_blue_100), PorterDuff.Mode.SRC_IN);
                    Toast.makeText(DashboardActivity.this, "position" + tabPos, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onTabUnselected(final Tab tab) {
                    tab.getIcon()
                            .setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);

                }

                @Override
                public void onTabReselected(final Tab tab) {

                }
            });
        }
    }

    private void setIconColors() {
        // set icon color pre-selected
        tab_layout.getTabAt(0).getIcon()
                .setColorFilter(getResources().getColor(R.color.light_blue_100), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(1).getIcon()
                .setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(2).getIcon()
                .setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(3).getIcon()
                .setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
    }

    private void addTabIcons() {
        for (int x = 0; x < 4; x++) {
            tab_layout.getTabAt(x).setIcon(TAB_ICONS[x]);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon()
                .setColorFilter(getResources().getColor(R.color.light_blue_500), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
    }


    //State pager adapter for fragments
    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        SectionsPagerAdapter(final FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() { //Number of pages
            return 4;
        }

        @Override
        public Fragment getItem(final int position) { //fragment to be displayed
            switch (position) {
                case 0:
                    return new BlankFragment1();
                case 1:
                    return new BlankFragment2();
                case 2:
                    return new BlankFragment3();
                case 3:
                    return new BlankFragment4();
            }
            return null;
        }

    }

}
