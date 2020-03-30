package com.example.watermonitoringiot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONObject;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class MainTdsGraphActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart lineChart;
    ArrayList<QualityMstr> qualityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tds_graph);


        String url=Url.url+"/seminar/index.php?op=get_quality_data&user_id=1";
        StringRequest rs=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj= new JSONObject(response);
                    JSONArray jarr =obj.getJSONArray("tds");
                    for(int i=0;i<jarr.length();i++) {
                        JSONObject jsonObject = jarr.getJSONObject(i);
                        QualityMstr qualityMstr = new QualityMstr();

                        qualityMstr.setId(jsonObject.getInt("id"));
                        qualityMstr.setTds(jsonObject.getInt("tds"));


                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        Date date = dateFormat.parse(jsonObject.getString("date"));

                        qualityMstr.setDate(date);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

                        Long ltime = timeFormat.parse(jsonObject.getString("time")).getTime();
                        Time time = new Time(ltime);
                        qualityMstr.setTime(time);

                        qualityList.add(qualityMstr);

                    }
                    Toast.makeText(MainTdsGraphActivity.this, "success", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.fillInStackTrace().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        })
        {

        };
        RequestQueue rq= Volley.newRequestQueue(this);
        rq.add(rs);



        lineChart = findViewById(R.id.chart1);
        lineChart.setOnChartGestureListener(MainTdsGraphActivity.this);
        lineChart.setOnChartValueSelectedListener(MainTdsGraphActivity.this);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Entry> yValue = new ArrayList<>();
        for (QualityMstr qualityMstr :qualityList)
        {

        }
        yValue.add(new Entry(0,90f));
        yValue.add(new Entry(1,90f));
        yValue.add(new Entry(2,90f));
        yValue.add(new Entry(3,90f));
        yValue.add(new Entry(4,90f));


        LineDataSet set1=new LineDataSet(yValue,"Data Set");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets =new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);


    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

