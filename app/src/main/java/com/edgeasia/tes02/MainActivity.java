package com.edgeasia.tes02;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout canvas;
    private ScaleGestureDetector SGD;
    private float scale = 1f;
    private Button btnTag;
    private int xpos;
    private int ypos;
    private int width = 264;
    private int height = 144;
    private boolean active = false;
    private int click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SGD = new ScaleGestureDetector(this,new ScaleListener());

        canvas = (RelativeLayout) findViewById(R.id.canvas);

        btnTag = new Button(this);
        btnTag.setText("Button");
        btnTag.setId(1);
        canvas.addView(btnTag);

        canvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SGD.onTouchEvent(event);
                return true;
            }
        });

        tes();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            if(active) {
                btnTag.setWidth((int) (width * scale));
                btnTag.setHeight((int) (height * scale));
                //Log.i("scale", String.valueOf(btnTag.getWidth()) + ", " + String.valueOf(btnTag.getHeight()));
            }
            return true;
        }
    }

    private void tes() {
        btnTag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!active){
                    xpos = (int) event.getRawX();
                    ypos = (int) event.getRawY();
                    btnTag.setX(xpos - btnTag.getWidth()/2);
                    btnTag.setY(ypos - btnTag.getHeight()*2);
                }
                return false;
            }
        });

        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        click = 0;
                    }
                };

                if (click == 1) {
                    //Single click
                    handler.postDelayed(r, 250);
                } else if (click == 2) {
                    //Double click
                    click = 0;
                    if(!active){
                        btnTag.setBackgroundColor(Color.GRAY);
                        active = true;
                    }else {
                        btnTag.setBackgroundResource(android.R.drawable.btn_default);
                        active = false;
                    }
                }
            }
        });
    }

}
