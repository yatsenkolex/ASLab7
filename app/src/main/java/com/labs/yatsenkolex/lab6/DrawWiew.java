package com.labs.yatsenkolex.lab6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

class DrawView extends View {

    Paint paint;
    Path path;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float mid = getWidth() / 2;
        float min = Math.min(getWidth() / 2, getHeight());
        float half = min / 2;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        // star
        mid = mid - half;
        path.reset();
        paint.setColor(Color.rgb(00, 00, 00));
        // top left
        path.moveTo(mid + half * 0.5f, half * 0.84f + 500f);
        // top right
        path.lineTo(mid + half * 1.5f, half * 0.84f + 500f);
        // bottom left
        path.lineTo(mid + half * 0.68f, half * 1.45f + 500f);
        // top tip
        path.lineTo(mid + half * 1.0f, half * 0.5f + 500f);
        // bottom right
        path.lineTo(mid + half * 1.32f, half * 1.45f + 500f);
        // top left
        path.lineTo(mid + half * 0.5f, half * 0.84f + 500f);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawPath(path, paint);
        canvas.restore();
        path.close();

        super.onDraw(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            invalidate();
            return true;
        }
    }
}
