package com.edgeasia.tes02;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

                xpos = (int) event.getX();
                ypos = (int) event.getY();

                SGD.onTouchEvent(event);
                if(!active) {
                    btnTag.setX(xpos);
                    btnTag.setY(ypos);
                }
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
                Log.i("scale", String.valueOf(btnTag.getWidth()) + ", " + String.valueOf(btnTag.getHeight()));
            }
            return true;
        }
    }

    private boolean active = false;
    private void tes() {
        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //active = (active == false) ? (active = true):(active = false);
                if(!active){
                    btnTag.setBackgroundColor(Color.GRAY);
                    active = true;
                }else {
                    btnTag.setBackgroundResource(android.R.drawable.btn_default);
                    active = false;
                }
            }
        });
    }

}
