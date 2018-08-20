package com.linxiao.surfacedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.nfc.Tag;
import android.os.SystemClock;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Locale;

/**
 * Description
 * Author lizheng
 * Create Data  2018\8\2 0002
 */
public class SurfaceViewSinFun extends SurfaceView implements SurfaceHolder.Callback, Runnable {
  private static final String TAG = "touch_event";
  private SurfaceHolder mSurfaceHolder;
  //绘图的Canvas
  private Canvas mCanvas;
  //子线程标志位
  private boolean mIsDrawing;
  private int x = 0, y = 0;
  private Paint mPaint;
  private Path mPath;
  private Paint mTempPaint;
  private StringBuilder stringBuilder;

  public SurfaceViewSinFun(Context context) {
    this(context, null);
  }

  public SurfaceViewSinFun(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SurfaceViewSinFun(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    mPaint = new Paint();
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(5);

    mTempPaint = new Paint();
    mTempPaint.setColor(Color.BLUE);
    mTempPaint.setStyle(Paint.Style.STROKE);
    mTempPaint.setAntiAlias(true);
    mTempPaint.setStrokeWidth(5);

    mPath = new Path();
    //路径起始点(0, 100)
    mPath.moveTo(0, 100);
    initView();
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    mIsDrawing = true;
    new Thread(this).start();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    mIsDrawing = false;
  }

  @Override
  public void run() {
//    while (mIsDrawing) {
//      drawSomething();
//      x += 1;
//      y = (int) (100 * Math.sin(2 * x * Math.PI / 180) + 400);
//      //加入新的坐标点
//      mPath.lineTo(x, y);
//    }
  }

  int num = 0;

  private void drawSomething() {
    try {
      //获得canvas对象
      mCanvas = mSurfaceHolder.lockCanvas();
      //通过下面的测试，证明canvas中是保留上次绘制的内容的
      if (num > 100 & num < 300) {
        mCanvas.drawCircle(200, 200, 100, mTempPaint);
      } else {
        //绘制背景
        mCanvas.drawColor(Color.RED);
      }
      //绘制路径
      mCanvas.drawPath(mPath, mPaint);
      num++;
    } catch (Exception e) {

    } finally {
      if (mCanvas != null) {
        //释放canvas对象并提交画布
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
      }
    }
  }

  /**
   * 初始化View
   */
  private void initView() {
    mSurfaceHolder = getHolder();
    mSurfaceHolder.addCallback(this);
    setFocusable(true);
    setKeepScreenOn(true);
    setFocusableInTouchMode(true);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    StringBuilder stringBuilder = new StringBuilder();
    int actionMasked = event.getActionMasked();
    stringBuilder.append("action code: ")
        .append(actionMasked)
        .append("  ");
    switch (actionMasked) {
      case MotionEvent.ACTION_DOWN: {
        stringBuilder.append("单点按下");
        break;
      }
      case MotionEvent.ACTION_UP: {
        stringBuilder.append("单点抬起");
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        stringBuilder.append("单点移动");
        break;
      }
      case MotionEvent.ACTION_CANCEL: {
        stringBuilder.append("触摸动作取消");
        break;
      }
      case MotionEvent.ACTION_OUTSIDE: {
        stringBuilder.append("触摸动作超出边界");
        break;
      }
      case MotionEvent.ACTION_POINTER_DOWN: {
        stringBuilder.append("多点按下");
        break;
      }
      case MotionEvent.ACTION_POINTER_UP: {
        stringBuilder.append("多点抬起");
        break;
      }
    }
    stringBuilder.append("  ");
    for (int i = 0; i < event.getPointerCount(); i++) {
      float x = event.getX(i);
      float y = event.getY(i);
      stringBuilder.append(String.format(Locale.getDefault(), "%d：(%f,%f)", i, x, y)).append("  ");
    }

    Log.d(TAG, stringBuilder.toString());
    return true;
  }
}


