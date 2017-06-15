package com.shrp.pong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by shriroop on 07-Jun-17.
 */

public class GameActivity extends Activity implements View.OnClickListener {

    GameView pongView;
    boolean started;
    PowerManager.WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ABeeZee-Regular.ttf");

        pongView = (GameView) findViewById(R.id.gvPong);
        TextView tv = (TextView) findViewById(R.id.tvStatus);
        tv.setTypeface(typeface);
        pongView.setTextView(tv);
        tv = (TextView) findViewById(R.id.tvTime);
        tv.setTypeface(typeface);
        pongView.setTimeTextView(tv);
        started = false;

        GameThread thread = pongView.getGameThread();
        thread.doStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameThread thread = pongView.getGameThread();
        thread.pauseGame();
        if(wl != null && wl.isHeld()) {
            wl.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "game");
        wl.acquire();
        GameThread thread = pongView.getGameThread();
        if(started) {
            thread.resumeGame();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        started = false;
        if(wl != null) {
            if(wl.isHeld())
                wl.release();
        }
        SoundManager.cleanup();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
