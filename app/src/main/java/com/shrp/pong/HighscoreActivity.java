package com.shrp.pong;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by shriroop on 14-Jun-17.
 */

public class HighscoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_highscore);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Aclonica.ttf");
        TextView tv = (TextView) findViewById(R.id.tvHName);
        tv.setTypeface(typeface, Typeface.BOLD);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/ABeeZee-Regular.ttf");
        SharedPreferences prefs = getSharedPreferences("HIGHSCORE", MODE_PRIVATE);
        Map<String, ?> all = prefs.getAll();
        tv = (TextView) findViewById(R.id.tvNoScore);
        tv.setTypeface(typeface);
        if(all.size() == 0) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
            LinearLayout ll = (LinearLayout) findViewById(R.id.llScores);
            for(int i = 1; i <= 10; i++) {
                Object o = all.get("highscore" + i);
                if(o != null) {
                    String score = o.toString();
                    tv = new TextView(this);
                    tv.setTypeface(typeface);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    tv.setPadding(12, 12, 12, 12);
                    tv.setText(i + ". " + score + " secs");
                    ll.addView(tv);
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
