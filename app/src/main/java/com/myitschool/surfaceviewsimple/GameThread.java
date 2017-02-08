package com.myitschool.surfaceviewsimple;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by lophtpicker on 08.02.2017.
 */

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Bitmap bitmap;
    private volatile boolean running = true;

    public GameThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vzhuh_picture);
    }

    public boolean requestStop() {
        return running = false;
    }

    @Override
    public void run() {
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.RED);

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.FILL);

        Random random = new Random();
        Paint paint = new Paint();

        float x = 100f;
        float y = 100f;
        float w = bitmap.getWidth() / 2f;

        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();

            float xVelocity = (random.nextInt() - 0.5f) * 10f;
            float yVelocity = (random.nextInt() - 0.5f) * 10f;

            if ( x <= w || (x >= canvas.getWidth() - w) )
                xVelocity = -xVelocity;
            if ( y <= w || (y >= canvas.getWidth() - w) )
                yVelocity = -yVelocity;

            x += xVelocity;
            y += yVelocity;

            try {
                canvas.drawRect(0, 0, canvas.getHeight(), canvas.getWidth(), backgroundPaint);

                canvas.drawBitmap(bitmap, x - w, x - w, paint);
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

        }

    }

}
