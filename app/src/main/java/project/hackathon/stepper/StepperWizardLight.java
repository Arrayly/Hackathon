package project.hackathon.stepper;

import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.chip.Chip;
import project.hackathon.R;

public class StepperWizardLight extends AppCompatActivity {


    private ViewPager viewPager;

    private static final int MAX_STEP = 6;

    private SectionsPagerAdapterTwo myViewPagerAdapter;

    private Chip mChip;

    private LottieAnimationView mAnimationView, progress;

    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper_wizard_light);

        viewPager = findViewById(R.id.view_pager);
        mChip = findViewById(R.id.btn_next);
        mAnimationView = findViewById(R.id.lottie_anim);

        mTextView = findViewById(R.id.analyzing_textVIew);

        getWindow().setStatusBarColor(getResources().getColor(R.color.collapse_toolbar_contracted));

        bottomProgressDots(0);

        myViewPagerAdapter = new SectionsPagerAdapterTwo(getSupportFragmentManager());

        if (viewPager != null) {
            viewPager.setAdapter(myViewPagerAdapter);
        }

        mChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                    if (current == MAX_STEP - 1) {
                        mChip.setChipBackgroundColorResource(R.color.green_700);
                        mChip.setText("D O N E");
                    }
                } else {
                    showFinishAnimation();
                }
            }
        });

        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset,
                    final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                bottomProgressDots(position);

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
    }

    private void showFinishAnimation() {
        if (mAnimationView != null) {
//            YoYo.with(Techniques.FadeIn).duration(300).playOn(mAnimationView);
            mAnimationView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.VISIBLE);
        }

        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);

        YoYo.with(Techniques.FadeOut).duration(500).playOn(relativeLayout);
        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(mTextView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
                customType(StepperWizardLight.this, "fadein-to-fadeout");
            }
        }, 4000);

    }


    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.orange_400), PorterDuff.Mode.SRC_IN);
        }
    }

    private class SectionsPagerAdapterTwo extends FragmentStatePagerAdapter {

        SectionsPagerAdapterTwo(final FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() { //Number of pages
            return 6;
        }

        @Override
        public Fragment getItem(final int position) { //fragment to be displayed
            switch (position) {
                case 0:
                    return new StepperFragment4();
                case 1:
                    return new StepperFragment1();
                case 2:
                    return new StepperFragment2();
                case 3:
                    return new StepperFragment3();

                case 4:
                    return new StepperFragment5();

                case 5:
                    return new StepperFragment6();
            }
            return null;
        }
    }
}
