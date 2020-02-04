package project.hackathon;

import android.graphics.PorterDuff;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import project.hackathon.animations.DepthPageTransformer;
import project.hackathon.fragments.BlankFragment3;
import project.hackathon.fragments.BlankFragment4;
import project.hackathon.fragments.HealthCheckFragment;
import project.hackathon.fragments.SummaryFragment;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private MenuItem prevMenuItem;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getWindow().setStatusBarColor(getResources().getColor(R.color.overlay_light_90));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initToolbar();
        initComponent();
    }

    private void initComponent() {
        navigation = findViewById(R.id.navigation);
        mViewPager = findViewById(R.id.main_viewpager);

        //SetUp view pager
        //Connect state pager to view with special state pager adapter
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());

        if (mViewPager != null) {
            mViewPager.setPageTransformer(true, new DepthPageTransformer());
            mViewPager.setAdapter(pagerAdapter);

        }

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_movie:
                        navigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_700));
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_music:
                        navigation.setBackgroundColor(getResources().getColor(R.color.pink_800));
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_books:
                        navigation.setBackgroundColor(getResources().getColor(R.color.grey_700));
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_newsstand:
                        mViewPager.setCurrentItem(3);
                        navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        return true;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset,
                    final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);



            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
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
                    return new SummaryFragment();
                case 1:
                    return new HealthCheckFragment();
                case 2:
                    return new BlankFragment3();
                case 3:
                    return new BlankFragment4();
            }
            return null;
        }

    }

}
