package com.myitschool.surfaceviewsimple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lophtpicker on 08.02.2017.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;


    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(getContext(), holder);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.requestStop();
        boolean retry = true;
        while(retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
        gameThread = null;
    }
}
