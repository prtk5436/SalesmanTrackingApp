package com.example.mysts.design;

import android.view.Menu;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysts.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class  MBottomBar extends AppCompatActivity {
    private BottomAppBar bottom_bar;
    private FloatingActionButton bottom_bar_fab;
    private OvershootInterpolator overshootInterpolator;

    public MBottomBar(View view) {
        setType(view);
    }

    private void setType(View view) {
        overshootInterpolator = new OvershootInterpolator();
        bottom_bar = view.findViewById(R.id.bottom_bar);
        bottom_bar_fab = view.findViewById(R.id.bottom_bar_fab);
    }

    public FloatingActionButton getFab() {
        return bottom_bar_fab;
    }

    public BottomAppBar getBottomBar() {
        return bottom_bar;
    }

    public void fabAnimation(boolean isOpenMenu2) {
        int speed = 550;
        if (isOpenMenu2) {
            bottom_bar_fab.animate().setInterpolator(overshootInterpolator).rotation(45f).setDuration(speed).start();
        } else {
            bottom_bar_fab.animate().setInterpolator(overshootInterpolator).rotation(0f).setDuration(speed).start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
