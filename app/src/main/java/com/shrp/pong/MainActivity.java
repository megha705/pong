package com.shrp.pong;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    Button gameButton, bHighScore;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Aclonica.ttf");

        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setTypeface(typeface, Typeface.BOLD);
        gameButton = (Button) findViewById(R.id.bStartGame);
        gameButton.setOnClickListener(MainActivity.this);
        bHighScore = (Button) findViewById(R.id.bHighScore);
        bHighScore.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStartGame:
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                this.startActivity(i);
                break;
            case R.id.bHighScore:
                i = new Intent(MainActivity.this, HighscoreActivity.class);
                this.startActivity(i);
                break;
        }
    }
}
