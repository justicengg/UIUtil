package com.d.lib.ui.view.stroke;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.d.lib.ui.common.UIUtil;


/**
 * 镂空背景
 * Created by D on 2017/3/15.
 */
public class HoleBgView extends View {
    private int width;
    private int height;

    private Rect rect;
    private RectF rectF;
    private Paint paint;
    private int offsetX;//偏移
    private int offsetY;//偏移
    private int withrH;
    private int withrW;
    private float strokeWidth;

    public HoleBgView(Context context) {
        this(context, null);
    }

    public HoleBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HoleBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);//禁用硬件加速
        }
        offsetX = UIUtil.dip2px(context, 136.5f);
        offsetY = UIUtil.dip2px(context, 29.5f);
        withrH = UIUtil.dip2px(context, 35000);
        withrW = UIUtil.dip2px(context, 35014.3f);
        strokeWidth = withrH * 2 - UIUtil.dip2px(context, 34.5f);
        rect = new Rect();
        rectF = new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.parseColor("#000000"));
        paint.setAlpha(0xcc);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int offsetYF = height - offsetY;
        int left = offsetX - withrW;
        int top = offsetYF - withrH;
        int right = offsetX + withrW;
        int bottom = offsetYF + withrH;
        rect.set(left, top, right, bottom);
        rectF.set(rect);
        canvas.drawRoundRect(rectF, withrH, withrH, paint);//在原有矩形基础上，画成圆角矩形
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}