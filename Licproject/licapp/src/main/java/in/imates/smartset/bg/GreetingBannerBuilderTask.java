package in.imates.smartset.bg;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import in.imates.smartset.R;

/**
 * Created by root on 5/1/16.
 */
public class GreetingBannerBuilderTask extends AsyncTask {

    private Context ctx;

    public GreetingBannerBuilderTask(Context ctx) {

        this.ctx = ctx;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }

    private Bitmap processingBitmap(final Bitmap BITMAP) {

        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        Log.e("GreetingCategoryActivity.ProcessingBitmap()", "1");

        //try {
        //bm1 = BitmapFactory.decodeStream(getContentResolver()
        //.openInputStream(source1));

        bm1 = BITMAP;
        Bitmap.Config config = bm1.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }

        Log.e("GreetingCategoryActivity.ProcessingBitmap()", "2");

        newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(),
                config);
        Canvas newCanvas = new Canvas(newBitmap);

        Log.e("GreetingCategoryActivity.ProcessingBitmap()", "3");

        newCanvas.drawBitmap(bm1, 0, 0, null);

        Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4");

        SharedPreferences sharedPref = ctx.getSharedPreferences("lic_app_greeting_profile", Context.MODE_PRIVATE);

        String captionString = sharedPref.getString("name", "");   //editTextCaption.getText().toString();

        if (captionString != null || captionString.length() == 0) {

            Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.1");

            // Text 3
            Paint paintText3 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText3.setTextAlign(Paint.Align.LEFT);
            paintText3.setColor(Color.BLACK);
            paintText3.setTextSize(20);
            paintText3.setTypeface(Typeface.createFromAsset(
                    ctx.getAssets(), "fonts/Dosis-SemiBold.ttf"));
            paintText3.setStyle(Paint.Style.FILL);
            paintText3.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

            final String STR3 = sharedPref.getString("contact_num", "");

            Rect rectText3 = new Rect();

            boolean isTrue3 = true;

            Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.2");

            while (isTrue3) {

                Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.2.1");

                paintText3.getTextBounds(STR3, 0, STR3.length(), rectText3);

                if (bm1.getWidth() > rectText3.width()) {

                    Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.2.1.1");

                    // //
                    newCanvas.drawText(STR3, 160, bm1.getHeight() - 20
                                /*- (rectText3.height()) / 3 */, paintText3);
                    isTrue3 = false;
                    // //
                } else {

                    Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.2.1.2");

                    paintText3.setTextSize(paintText3.getTextSize() - 20);
                    Log.e("GreetingCategoryActivity - ProcessingBitmap() - try - if() - while() - else",
                            paintText3.getTextSize() + "");
                }
            }

            Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.3");

            // Text 2
            Paint paintText2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText2.setTextAlign(Paint.Align.LEFT);
            paintText2.setColor(Color.BLACK);
            paintText2.setTextSize(20);
            paintText2.setTypeface(Typeface.createFromAsset(
                    ctx.getAssets(), "fonts/Dosis-SemiBold.ttf"));
            paintText2.setStyle(Paint.Style.FILL);
            paintText2.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

            final String STR2 = sharedPref.getString("designation", "");

            Rect rectText2 = new Rect();

            boolean isTrue2 = true;

            while (isTrue2) {

                paintText2.getTextBounds(STR2, 0, STR2.length(), rectText2);

                if (bm1.getWidth() > rectText2.width()) {

                    // //
                    newCanvas.drawText(STR2, 160, bm1.getHeight()
                            - (rectText3.height() + 30)/* / 3 */, paintText2);
                    isTrue2 = false;
                    // //
                } else {

                    paintText2.setTextSize(paintText2.getTextSize() - 20);
                    Log.e("GreetingCategoryActivity - ProcessingBitmap() - try - if() - while() - else",
                            paintText2.getTextSize() + "");
                }
            }

            ////////////////////////////////////////////////////

            // Text 1
            Paint paintText1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText1.setTextAlign(Paint.Align.LEFT);
            paintText1.setColor(Color.BLACK);
            paintText1.setTextSize(30);
            paintText1.setTypeface(Typeface.createFromAsset(
                    ctx.getAssets(), "fonts/Dosis-SemiBold.ttf"));
            paintText1.setStyle(Paint.Style.FILL);
            paintText1.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

            Rect rectText1 = new Rect();
            boolean isTrue1 = true;

            while (isTrue1) {

                paintText1.getTextBounds(captionString, 0,
                        captionString.length(), rectText1);

                if (bm1.getWidth() > (rectText1.width() + 50)) {

                    // //
                    newCanvas.drawText(
                            captionString,
                            160,
                            bm1.getHeight()
                                    - (rectText3.height() + 20
                                    + rectText2.height() + 20),
                            paintText1);
                    isTrue1 = false;
                    // //
                } else {

                    paintText1.setTextSize(paintText1.getTextSize() - 4);
                    Log.e("GreetingCategoryActivity - ProcessingBitmap() - try - if() - while() - else",
                            paintText1.getTextSize() + "");
                }
            }

            Bitmap bmpImageProfile = BitmapFactory.decodeResource(
                    ctx.getResources(), R.drawable.smart_set_logo);
            //  bmpImageProfile.setHeight(80);
            //  bmpImageProfile.setHeight(80);

            int newBmpImageProfileHeight = (newBitmap.getHeight() - bmpImageProfile.getHeight()) - 15;
            int newBmpImageProfileWidth = /*(newBitmap.getWidth() - bmpImageProfile.getWidth()) - */15;
            newCanvas.drawBitmap(bmpImageProfile, newBmpImageProfileWidth, newBmpImageProfileHeight, null);

            Bitmap bmpImageWatermark = BitmapFactory.decodeResource(
                    ctx.getResources(), R.drawable.imates);
            //  bmpImageWatermark.setHeight(80);
            //  bmpImageWatermark.setHeight(80);

            int newBmpImageWatermarkHeight = 20;
            int newBmpImageWatermarkWidth = 20;
            newCanvas.drawBitmap(bmpImageWatermark, newBmpImageWatermarkWidth, newBmpImageWatermarkHeight, null);


            ////////////////////////////////////////////////////

				/*
                // Text 1
				Paint paintText1 = new Paint(Paint.ANTI_ALIAS_FLAG);
				paintText1.setTextAlign(Align.LEFT);
				paintText1.setColor(Color.BLACK);
				paintText1.setTextSize(120);
				paintText1.setTypeface(Typeface.createFromAsset(
						this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
				paintText1.setStyle(Style.FILL);
				paintText1.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

				Rect rectText1 = new Rect();
				boolean isTrue1 = true;

				//while (isTrue1) {

					paintText1.getTextBounds(captionString, 0,
							captionString.length(), rectText1);

					Toast.makeText(getApplicationContext(),
							"bm1.getWidth(): " + bm1.getWidth()+"\n\nrectText1.width(): "
									+rectText1.width()+"\n\npaintText1.setTextSize: "+paintText1.getTextSize(),
							Toast.LENGTH_LONG).show();

					if (bm1.getWidth() < rectText1.width()) {

						paintText1.setTextSize(convertToPixels(this, bm1.getWidth()) - 20);
					}
						// //

					Toast.makeText(getApplicationContext(),
							"bm1.getWidth(): " + bm1.getWidth()+"\n\nrectText1.width(): "
									+rectText1.width()+"\n\npaintText1.setTextSize: "+paintText1.getTextSize(),
							Toast.LENGTH_LONG).show();
						newCanvas.drawText(
								captionString,
								40,
								bm1.getHeight()
										- (rectText3.height() + 40
												+ rectText2.height() + 8),
								paintText1);
						isTrue1 = false;
						// //
					//} else {

						//paintText1.setTextSize(paintText1.getTextSize() - 20);
						//Log.e("GreetingCategoryActivity - ProcessingBitmap() - try - if() - while() - else",
								//paintText1.getTextSize() + "");
					//}
				//}
				 */

            Toast.makeText(ctx,
                    "drawText: " + captionString, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ctx, "Please fill Profile details first.",
                    Toast.LENGTH_LONG).show();
        }
        //} catch (FileNotFoundException e) {
        // TODO: handle exception
        //  e.printStackTrace();
        //}

        return newBitmap;
        // return saveImage(newBitmap);
    }
}
