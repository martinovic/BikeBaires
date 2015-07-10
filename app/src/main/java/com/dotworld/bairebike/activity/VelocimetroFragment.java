package com.dotworld.bairebike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dotworld.bairebike.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class VelocimetroFragment extends Fragment {

    View rootView = null;
    TextView horaActual = null;
    TextView velocidad_actual = null;
    TextView velocidad_maxima = null;
    Timer timer = null;
    TimerTask timerTask = null;
    final Handler handler = new Handler();

    public VelocimetroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_velocimetro, container, false);
        horaActual = (TextView)rootView.findViewById(R.id.horaActual);
        velocidad_actual = (TextView)rootView.findViewById(R.id.velocidad_actual);
        velocidad_maxima = (TextView)rootView.findViewById(R.id.velocidad_maxima);

        startTimer();



        return rootView;
    }

    public void startTimer(){
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 1000,1000);
    }

    public void initializeTimerTask(){

        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
                        final String strDate = simpleDateFormat.format(calendar.getTime());
                        horaActual.setText(strDate);
                    }
                });
            }
        };
    }
}
