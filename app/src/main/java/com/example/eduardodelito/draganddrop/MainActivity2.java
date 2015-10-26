package com.example.eduardodelito.draganddrop;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * Created by eduardodelito on 7/13/15.
 */


public class MainActivity2 extends Activity implements View.OnTouchListener {

    private ImageView mImageView;
    private ViewGroup mRrootLayout;
    private int _xDelta;
    private int _yDelta;
    private ImageView thumbnail;
    private Button close;
    private int moveX = 336;
    private int moveY = 84;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Bitmap ic = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        Bitmap icResize = Bitmap.createScaledBitmap(ic, 50, 50, true);


        Bitmap parent = BitmapFactory.decodeResource(getResources(), R.drawable.floor);


        mRrootLayout = (ViewGroup) findViewById(R.id.root);
        thumbnail = (ImageView) findViewById(R.id.thumbnail);

        final RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) mRrootLayout.getLayoutParams();
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 50);

        int dstWidth = (int)lParams.width - (int)layoutParams.width;
        int dstHeight = (int)lParams.height - (int)layoutParams.height;


        Bitmap bigBitmap = Bitmap.createScaledBitmap(parent, dstWidth, dstHeight, true);
        Canvas canvas = new Canvas(bigBitmap);
        canvas.drawBitmap(icResize, moveX, moveY, new Paint());


        close = (Button) findViewById(R.id.button);


        thumbnail.setImageBitmap(bigBitmap);

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRrootLayout.setVisibility(View.VISIBLE);
                thumbnail.setVisibility(View.GONE);
                close.setVisibility(View.VISIBLE);

                layoutParams.leftMargin = moveX;
                layoutParams.topMargin = moveY;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;


                mImageView.setLayoutParams(layoutParams);

                mRrootLayout.invalidate();
            }
        });




        mImageView = (ImageView) mRrootLayout.findViewById(R.id.imageView);



        mImageView.setLayoutParams(layoutParams);
        mImageView.setOnTouchListener(this);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRrootLayout.setVisibility(View.GONE);
                thumbnail.setVisibility(View.VISIBLE);
                thumbnail.setImageBitmap(getBitmapFromView(mRrootLayout));
                close.setVisibility(View.GONE);

            }
        });


    }

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;



                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:

                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();

                moveX = X - _xDelta;
                moveY = Y - _yDelta;

                if(moveX < 0){
                    moveX = 0;
                }

                if(moveX > (mRrootLayout.getWidth() - mImageView.getWidth())){
                    moveX = mRrootLayout.getWidth() - mImageView.getWidth();
                }

                if(moveY < 0){
                    moveY = 0;
                }

                if(moveY > (mRrootLayout.getHeight() - mImageView.getHeight())){
                    moveY = mRrootLayout.getHeight() - mImageView.getHeight();
                }



                layoutParams.leftMargin = moveX;
                layoutParams.topMargin = moveY;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);



                ScaleAnimation scal = new ScaleAnimation(3f, 1f, 3f, 1f, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
                scal.setDuration(500);
                scal.setFillAfter(true);
                mImageView.setAnimation(scal);


                break;
        }
        mRrootLayout.invalidate();
        return true;
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.TRANSPARENT);
        view.draw(canvas);
        return returnedBitmap;
    }

}
