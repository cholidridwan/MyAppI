package com.edgeasia.tes02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = (RelativeLayout) findViewById(R.id.canvas);

        final Button btnTag = new Button(this);
        btnTag.setText("Button");
        btnTag.setId(1);
        canvas.addView(btnTag);

        canvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int xpos=(int) event.getX();
                int ypos=(int) event.getY();

                Log.i("coo", String.valueOf(xpos) + "x" + String.valueOf(ypos));
                btnTag.setX(xpos);
                btnTag.setY(ypos);

                return true;
            }
        });
    }

}
