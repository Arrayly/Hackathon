package project.hackathon.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.progresviews.ProgressLine;
import com.app.progresviews.ProgressWheel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import project.hackathon.DashboardActivity;
import project.hackathon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    ProgressWheel mProgressWheel;
    ProgressLine lineSteps,LineCalories,lineActive;
    private LineChart chart;
    private FloatingActionButton fab;



    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        mProgressWheel = view.findViewById(R.id.wheelprogress);

        lineSteps = view.findViewById(R.id.progress_line_steps);
        LineCalories = view.findViewById(R.id.progress_line_calories);
        lineActive = view.findViewById(R.id.progress_line_acttiveMin);



        chart = view.findViewById(R.id.chart);
        fab = view.findViewById(R.id.summary_fab);




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgressWheel.setPercentage(220);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lineSteps.setmPercentage(85);
            }
        },500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LineCalories.setmPercentage(70);
            }
        },700);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lineActive.setmPercentage(60);
            }
        },800);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Pulse).pivot(100,220).duration(1000).playOn(fab);
            }
        },100);

        chart.setViewPortOffsets(0, 0, 0, 0);
        chart.setBackgroundColor(getResources().getColor(R.color.blue_600));


        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(false);

        // enable scaling and dragging
        chart.setDragEnabled(false);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        chart.getAxisRight().setEnabled(false);

        chart.getLegend().setEnabled(false);

        chart.animateXY(1500, 1500);

        // don't forget to refresh the drawing
        chart.invalidate();

        setData(20,5);


        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });

    }


    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();


        values.add(new Entry(0,2));
        values.add(new Entry(1,5));
        values.add(new Entry(2,10));
        values.add(new Entry(3,13));
        values.add(new Entry(4,8));
        values.add(new Entry(5,9));
        values.add(new Entry(6,15));
        values.add(new Entry(7,29));
        values.add(new Entry(8,35));
        values.add(new Entry(9,30));
        values.add(new Entry(10,29));
        values.add(new Entry(11,28));
        values.add(new Entry(12,37));
        values.add(new Entry(13,40));
        values.add(new Entry(14,41));
        values.add(new Entry(15,42));
        values.add(new Entry(16,43));
        values.add(new Entry(17,44));
        values.add(new Entry(18,46));
        values.add(new Entry(19,47));
        values.add(new Entry(20,49));



//        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() + (range + 1)) + 20;
//            values.add(new Entry(i, val));
//        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            chart.setData(data);
        }
    }
}
