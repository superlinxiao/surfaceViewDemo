package com.linxiao.surfacedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description
 * Author lizheng
 * Create Data  2018\8\2 0002
 */
public class MyView extends View {

  private Paint mTempPaint;

  public MyView(Context context) {
    this(context,null);
  }

  public MyView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView();
  }

  private void initView() {
    mTempPaint = new Paint();
    mTempPaint.setColor(Color.BLUE);
    mTempPaint.setStyle(Paint.Style.STROKE);
    mTempPaint.setAntiAlias(true);
    mTempPaint.setStrokeWidth(5);
  }


  int num = 0;

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (num > 100 & num < 300) {
      canvas.drawCircle(200, 200, 100, mTempPaint);
    }else{
      //绘制背景
      canvas.drawColor(Color.RED);
    }

    num++;
    invalidate();
  }
}
