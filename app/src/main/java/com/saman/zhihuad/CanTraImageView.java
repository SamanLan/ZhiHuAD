package com.saman.zhihuad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * <b>Project:</b> ${file_name}<br>
 * <b>Create Date:</b> 2017/12/5 12:07<br>
 * <b>Author:</b> Saman<br>
 * <b>Description:</b> <br>
 */

public class CanTraImageView extends android.support.v7.widget.AppCompatImageView {

    private int minHeight;
    private float traPercent;

    public CanTraImageView(Context context) {
        super(context);
    }

    public CanTraImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        minHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int w = getWidth();
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);
        canvas.save();
        canvas.translate(0, -(h - minHeight) * traPercent);
        super.onDraw(canvas);
        canvas.restore();
    }

    public void setTraPercent(int childHeight, int parentHeight, int orientation) {
        if (getDrawable() == null) {
            return;
        }
        float traPercent;
        if (orientation == 0) {
            traPercent = childHeight * 1f / (parentHeight - minHeight);
        } else {
            traPercent = (parentHeight - childHeight - minHeight) * 1f / (parentHeight - minHeight);
        }
        this.traPercent = traPercent;
        if (this.traPercent <= 0) {
            this.traPercent = 0;
        }
        if (this.traPercent >= 1) {
            this.traPercent = 1;
        }
        invalidate();
    }
}
