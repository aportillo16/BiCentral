package com.example.reto4;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.squareup.picasso.Transformation;

public class PicassoCircleTransformation implements Transformation {

    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        float r = size / 2f;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        Paint paintBg = new Paint();
        paintBg.setAntiAlias(true);
        paint.setColor(0xFF141c26);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawCircle(r, r, r, paintBg);
        canvas.drawCircle(r, r, r - 2, paint);

        squaredBitmap.recycle();

        return bitmap;
    }

    @Override public String key() { return String.valueOf(Math.random()); }
}
