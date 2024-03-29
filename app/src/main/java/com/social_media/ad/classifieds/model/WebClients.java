

package com.social_media.ad.classifieds.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;


public class WebClients extends WebView implements View.OnTouchListener {
    private float clickX, clickY;

    public WebClients(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickX = event.getX();
            clickY = event.getY();
        }
        return false;
    }

    public float getClickX()
    {
        return clickX;
    }

    public float getClickY() {
        return clickY;
    }
}