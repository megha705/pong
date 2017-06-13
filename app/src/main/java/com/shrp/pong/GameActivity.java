package com.shrp.pong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by shriroop on 07-Jun-17.
 */

public class GameActivity extends Activity implements View.OnClickListener {

    GameView pongView;
    Button bStart;
    boolean started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        pongView = (GameView) findViewById(R.id.gvPong);
        //pongView.setVisibility(View.INVISIBLE);
        pongView.setTextView((TextView) findViewById(R.id.tvStatus));
        started = false;


        GameThread thread = pongView.getGameThread();
        thread.doStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameThread thread = pongView.getGameThread();
        thread.pauseGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameThread thread = pongView.getGameThread();
        if(started) {
            thread.resumeGame();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        started = false;
        SoundManager.cleanup();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStart:
                bStart.setVisibility(View.INVISIBLE);
                pongView.setVisibility(View.VISIBLE);
                GameThread thread = pongView.getGameThread();
                thread.doStart();
                started = true;
                break;
        }
    }
}
