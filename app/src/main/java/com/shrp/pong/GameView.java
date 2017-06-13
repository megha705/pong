package com.shrp.pong;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.view.SurfaceHolder.Callback;
import android.widget.TextView;

/**
 * Created by shriroop on 06-Jun-17.
 */

public class GameView extends SurfaceView implements Callback {

    private Context context;
    private SurfaceHolder holder;
    private TextView status;

    private GameThread _thread;


    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.holder = getHolder();
        holder.addCallback(this);
        this.context = context;
        setFocusable(true);

        _thread = createNewGameThread();
    }

    public void setTextView(TextView tv) {
        this.status = tv;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread.setRunning(true);
        try {
            _thread.start();
        } catch (Exception ex) {
            _thread = createNewGameThread();
            _thread.start();
            _thread.setRunning(true);
        }
    }

    private GameThread createNewGameThread() {
        return new GameThread(holder, context, new Handler(){
            @Override
            public void handleMessage(Message m) {
                Bundle data = m.getData();
                if(data.containsKey("visibility")) {
                    status.setVisibility(m.getData().getInt("visibility"));
                    status.setText(m.getData().getString("score"));
                }
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        _thread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        _thread.setRunning(false);
        boolean running = true;
        while(running) {
            try {
                _thread.join();
                running = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public GameThread getGameThread() {
        return _thread;
    }

    @Override
    public void onWindowFocusChanged(boolean focus) {
        if(!focus) {
            _thread.pause();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        for(int i = 0; i < event.getPointerCount(); i++) {
            if(i == 0) {
                x1 = event.getX(i);
                y1 = event.getY(i);
            }
            if(i == 1) {
                x2 = event.getX(i);
                y2 = event.getY(i);
            }
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                _thread.setBatPosition(x1, y1, x2, y2);
        }
        return true;
    }
}
