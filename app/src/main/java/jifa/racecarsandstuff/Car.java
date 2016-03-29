package jifa.racecarsandstuff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Joe on 14/03/2016.
 */
public class Car {
    public Bitmap graphics;
    public Rect scaleRect;
    public Bitmap image;
    public int xPos, yPos, width, height, angleDeg, indWidth, indHeight;

    public Car(View view, int xp, int yp){
        xPos = xp;
        yPos = yp;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inScaled = false;
        graphics = BitmapFactory.decodeResource(view.getResources(), R.drawable.graphics, options);

        indWidth = graphics.getWidth() / 8;
        indHeight = graphics.getHeight() / 6;

        scaleRect = new Rect(0, 0, indWidth*4, indHeight*4);
        image = Bitmap.createBitmap(indWidth * 4, indHeight * 4, Bitmap.Config.ARGB_4444);
        Canvas imageCanv = new Canvas(image);
        width = image.getWidth();
        height = image.getHeight();

        Paint paint = new Paint();
        paint.setFilterBitmap(false);
        paint.setAntiAlias(false);
        paint.setDither(false);

        for(int x = 0; x < 2; x++){
            for(int y = 0; y < 2; y++) {
                Rect rect = new Rect(indWidth*y, indHeight*x, indWidth*y + indWidth, indHeight*x + indHeight);
                Rect rect1 = new Rect(y*indWidth+(indWidth*4), x*indHeight, y*indWidth+(indWidth*5), x*indHeight+indHeight);
                imageCanv.drawBitmap(graphics, rect1, rect, paint);
            }
        }
    }

    public void draw(Canvas canvas){
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.translate(xPos - indWidth*8, yPos - indHeight*8);
        canvas.scale(10, 10);
        canvas.rotate(angleDeg, width/4, height/4);
        canvas.drawBitmap(image, null, scaleRect, null);
        canvas.restore();
    }
}
