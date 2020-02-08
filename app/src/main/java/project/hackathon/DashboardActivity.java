package project.hackathon;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rhexgomez.typer.roboto.TyperRoboto;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import project.hackathon.animations.DepthPageTransformer;
import project.hackathon.fragments.ImproveFragment;
import project.hackathon.fragments.ProfileFragment;
import project.hackathon.fragments.HealthCheckFragment;
import project.hackathon.fragments.SummaryFragment;

public class DashboardActivity extends AppCompatActivity {

    public static final int HEALTH_REQUEST_CODE = 5;

    private BottomNavigationView navigation;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private MenuItem prevMenuItem;

    private ViewPager mViewPager;

    private KonfettiView mKonfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); // Make the bottom navigation bar translucent


        getWindow().setStatusBarColor(getResources().getColor(R.color.complete_white));

        //@color/grey_20

        getWindow().setNavigationBarColor(getResources().getColor(R.color.grey_20));

        initToolbar();
        initComponent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showInfoDialog();
            }
        }, 5000);

    }

    private void showInfoDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_info);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }

    private void initToolbar() {
        Toolbar toolbar =  findViewById(R.id.toolbar_dash);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initComponent() {
        navigation = findViewById(R.id.navigation);
        mViewPager = findViewById(R.id.main_viewpager);
        mKonfettiView = findViewById(R.id.viewKonfetti);

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
//                        navigation.setBackgroundColor(getResources().getColor(R.color.green_400));
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_music:
//                        navigation.setBackgroundColor(getResources().getColor(R.color.pink_800));
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_books:
//                        navigation.setBackgroundColor(getResources().getColor(R.color.blue_500));
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_newsstand:
                        mViewPager.setCurrentItem(3);
//                        navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
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
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);


            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
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
                    return new ImproveFragment();
                case 3:
                    return new ProfileFragment();
            }
            return null;
        }

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == HEALTH_REQUEST_CODE) {
                Toast.makeText(this, "Awesome!", Toast.LENGTH_SHORT).show();
                showDialogCongrat();
                showKonfetti();

            }
        }
    }

    private void showKonfetti() {
        mKonfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, mKonfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }

    private void showDialogCongrat() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_achievement_congrat);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.findViewById(R.id.bt_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(0);
                }
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(DashboardActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
