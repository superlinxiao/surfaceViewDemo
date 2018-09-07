package com.linxiao.surfacedemo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Description
 * Author lizheng
 * Create Data  2018\9\7 0007
 */
public class MySurfaceTexture extends TextureView {

  public MySurfaceTexture(Context context) {
    super(context);
  }

  public MySurfaceTexture(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MySurfaceTexture(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void init() {
    this.setSurfaceTextureListener(new SurfaceTextureListener() {

      @Override
      public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        try {
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {

      }

      @Override
      public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return true;
      }

      @Override
      public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

      }
    });
  }

}
