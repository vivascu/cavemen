package com.cavemen.inception.ui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import com.cavemen.inception.R;

import java.io.InputStream;

/**
 * Created by ggodonoga on 13/04/2014.
 */
public class AnimatedCaveman extends View {

    Movie movie = null;
    long movieStart;
    private int gifId;

    public AnimatedCaveman(Context context) {
        super(context);
        initializeView();
    }

    public AnimatedCaveman(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public AnimatedCaveman(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    private void initializeView() {
        if (gifId == 0) {
            gifId = R.drawable.animated_cavemen;
        }
        if (gifId != 0) {
            InputStream is = getContext().getResources().openRawResource(gifId);
            movie = Movie.decodeStream(is);
            movieStart = 0;
            this.invalidate();
        }
    }

    public void setGIFResource(int resId) {
        this.gifId = resId;
        initializeView();
    }

    public int getGIFResource() {
        return this.gifId;
    }

    private void setAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AnimatedCaveman, 0, 0);
            String gifSource = a.getString(R.styleable.AnimatedCaveman_src);
            //little workaround here. Who knows better approach on how to easily get resource id - please share
            String sourceName = Uri.parse(gifSource).getLastPathSegment().replace(".gif", "");
            setGIFResource(getResources().getIdentifier(sourceName, "drawable", getContext().getPackageName()));
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        if (movieStart == 0) { // first time
            movieStart = now;
        }
        if (movie != null) {
            int relTime = (int) ((now - movieStart) % movie.duration());
            movie.setTime(relTime);
            movie.draw(canvas, getWidth() - movie.width(), getHeight() - movie.height());
            this.invalidate();
        }
    }
}
