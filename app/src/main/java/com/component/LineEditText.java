package com.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import org.xml.sax.Parser;

/**
 * Created by 扬洋 on 2015/7/20.
 */
public class LineEditText extends EditText {
    // 画笔 用来画下划线
    private Paint paint;
    private Rect mRect;

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#FF45C01A"));
        // 开启抗锯齿 较耗内存
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int count = getLineCount();
        Rect r = mRect;
        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, r);
            canvas.drawLine(r.left, baseline + 10, r.right, baseline + 10, paint);
        }

        super.onDraw(canvas);
    }

}