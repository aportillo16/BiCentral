package com.example.reto4;

import com.squareup.picasso.Transformation;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

public class PicassoRoundedCornerTransformation implements Transformation {

    private float mRadius;

    public PicassoRoundedCornerTransformation(float radius) {
        this.mRadius = radius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        float mMargin = 0.0f;
        float right = width - mMargin;
        float bottom = height - mMargin;
        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint);

        source.recycle();

        return bitmap;
    }

    @Override public String key() { return String.valueOf(Math.random()); }
}