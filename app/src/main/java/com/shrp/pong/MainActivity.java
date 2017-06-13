package com.shrp.pong;

import android.app.Activity;
import android.content.Intent;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.bStartGame);
        b.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStartGame:
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                this.startActivity(i);
        }
    }
}
