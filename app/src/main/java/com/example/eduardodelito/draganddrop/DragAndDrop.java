package com.example.eduardodelito.draganddrop;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AbsoluteLayout.LayoutParams;

public class DragAndDrop extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ImageView img = null;
    private AbsoluteLayout aLayout;
    int status = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);
        aLayout = (AbsoluteLayout) findViewById(R.id.absLayout);

        img = (ImageView) findViewById(R.id.imageView);

//sa.setFillAfter(true);

        img.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                status = 1;

                return false;
            }
        });
        aLayout.setOnTouchListener(new OnTouchListener() {

                                       @Override
                                       public boolean onTouch(View v, MotionEvent event) {
                                           // TODO Auto-generated method stub

         if (status == 1) // any event from down and move
           {


             LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (int) event.getX() - img.getWidth() / 2, (int) event.getY() - img.getHeight() / 2);
             img.setLayoutParams(lp);
             ScaleAnimation scal = new ScaleAnimation(0.3f, 0.6f, 0.3f, 0.6f, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
             scal.setDuration(500);
             scal.setFillAfter(true);
             img.setAnimation(scal);

           }
         if (event.getAction() == MotionEvent.ACTION_UP) {
                status = 0;

               img.setBackgroundColor(Color.TRANSPARENT);

          }


//                                           LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (int) event.getX() - img.getWidth() / 2, (int) event.getY() - img.getHeight() / 2);
//                                           img.setLayoutParams(lp);
//                                           ScaleAnimation scal = new ScaleAnimation(0.3f, 0.6f, 0.3f, 0.6f, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
//                                           scal.setDuration(500);
//                                           scal.setFillAfter(true);
//                                           img.setAnimation(scal);


                                           return true;
                                       }
                                   }

        );


    }

}

