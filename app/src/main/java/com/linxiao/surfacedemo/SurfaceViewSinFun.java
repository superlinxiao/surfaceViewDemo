package com.linxiao.surfacedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Description
 * Author lizheng
 * Create Data  2018\8\2 0002
 */
public class SurfaceViewSinFun extends SurfaceView implements SurfaceHolder.Callback, Runnable {
  private SurfaceHolder mSurfaceHolder;
  //绘图的Canvas
  private Canvas mCanvas;
  //子线程标志位
  private boolean mIsDrawing;
  private int x = 0, y = 0;
  private Paint mPaint;
  private Path mPath;
  private Paint mTempPaint;

  public SurfaceViewSinFun(Context context) {
    this(context, null);
  }

  public SurfaceViewSinFun(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SurfaceViewSinFun(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    mPaint = new Paint();
    mPaint.setColor(Color.GREEN);
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
    while (mIsDrawing) {
      drawSomething();
      x += 1;
      y = (int) (100 * Math.sin(2 * x * Math.PI / 180) + 400);
      //加入新的坐标点
      mPath.lineTo(x, y);
    }
  }

  int num = 0;

  private void drawSomething() {
    try {
      //获得canvas对象
      mCanvas = mSurfaceHolder.lockCanvas();
      //通过下面的验证，发现surfaceHolder中持有的缓存不一定是2个，有时候也会是3个。
      //另外，num比较小的时候是测试不出来的，因为activity初始化需要一定的时候，num==0的时候，数据并没有写入到canvas中
      if (num == 10) {
        mCanvas.drawColor(Color.YELLOW);
      } else if (num == 11) {
        mCanvas.drawColor(Color.RED);
      } else if (num == 12) {
        mCanvas.drawColor(0xFFFF00FF);
      }
      //通过下面的测试，证明canvas中是保留上次绘制的内容的
      if (num > 100 & num < 300) {
        mCanvas.drawCircle(200, 200, 100, mTempPaint);
      } else {
        //绘制背景
//        mCanvas.drawColor(Color.RED);
      }
      //绘制路径
      mCanvas.drawPath(mPath, mPaint);
      num++;
    } catch (Exception e) {
      e.printStackTrace();
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
}


