package project.hackathon;

import android.app.Activity;
import android.os.Build;
import android.transition.Fade;
import android.transition.Transition;
import android.util.DisplayMetrics;

public class HelperClass {

    public static final String FADE_TRANSITION = "fade_tans";

    public static int getSoftButtonsBarSizePort(Activity activity) {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public static Transition fadeTranisitonObject(){
        android.transition.Fade enterTransition = new Fade();
        enterTransition.setDuration(300);

        return enterTransition;

    }

}
