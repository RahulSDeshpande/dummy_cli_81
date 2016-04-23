package in.jigyasacodes.meme2demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnLoadImage1;
	TextView textSource1;
	EditText editTextCaption;
	Button btnProcessing;
	ImageView imageResult;

	final int RQS_IMAGE1 = 1;
	Uri source1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnLoadImage1 = (Button) findViewById(R.id.loadImage1);
		textSource1 = (TextView) findViewById(R.id.sourceUri1);
		editTextCaption = (EditText) findViewById(R.id.caption);
		btnProcessing = (Button) findViewById(R.id.processing);
		imageResult = (ImageView) findViewById(R.id.source);

		btnLoadImage1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Intent intent = new Intent(Intent.ACTION_PICK,
				// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// startActivityForResult(intent, RQS_IMAGE1);

				startActivityForResult(
						new Intent(Intent.ACTION_PICK).setType("image/*"),
						RQS_IMAGE1);
			}
		});

		btnProcessing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (source1 != null) {
					Bitmap processedBitmap = processingBitmap();
					if (processedBitmap != null) {
						imageResult.setImageBitmap(processedBitmap);
						
						MainActivity.this.saveImage(processedBitmap);
						Toast.makeText(getApplicationContext(), "Done.\n\nImage saved in SD Card !!",
								Toast.LENGTH_LONG).show();

					} else {
						Toast.makeText(getApplicationContext(),
								"Something wrong in processing!",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Select both image!", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case RQS_IMAGE1:
				source1 = data.getData();
				textSource1.setText(source1.toString());
				break;
			}
		}
	}

	private Bitmap processingBitmap() {
		
		Bitmap bm1 = null;
		Bitmap newBitmap = null;

		try {
			bm1 = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(source1));
			Config config = bm1.getConfig();
			if (config == null) {
				config = Bitmap.Config.ARGB_8888;
			}

			newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(),
					config);
			Canvas newCanvas = new Canvas(newBitmap);

			newCanvas.drawBitmap(bm1, 0, 0, null);

			String captionString = editTextCaption.getText().toString();

			if (captionString != null) {

				// Text 3
				Paint paintText3 = new Paint(Paint.ANTI_ALIAS_FLAG);
				paintText3.setTextAlign(Align.LEFT);
				paintText3.setColor(Color.BLACK);
				paintText3.setTextSize(80);
				paintText3.setTypeface(Typeface.createFromAsset(
						this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
				paintText3.setStyle(Style.FILL);
				paintText3.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

				final String STR3 = "+91 988 139 5270";

				Rect rectText3 = new Rect();

				boolean isTrue3 = true;

				while (isTrue3) {

					paintText3.getTextBounds(STR3, 0, STR3.length(), rectText3);

					if (bm1.getWidth() > rectText3.width()) {

						// //
						newCanvas.drawText(STR3, 20, bm1.getHeight() - 20
								/*- (rectText3.height()) / 3 */, paintText3);
						isTrue3 = false;
						// //
					} else {

						paintText3.setTextSize(paintText3.getTextSize() - 20);
						Log.e("MainActivity - ProcessingBitmap() - try - if() - while() - else",
								paintText3.getTextSize() + "");
					}
				}

				// Text 2
				Paint paintText2 = new Paint(Paint.ANTI_ALIAS_FLAG);
				paintText2.setTextAlign(Align.LEFT);
				paintText2.setColor(Color.BLACK);
				paintText2.setTextSize(80);
				paintText2.setTypeface(Typeface.createFromAsset(
						this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
				paintText2.setStyle(Style.FILL);
				paintText2.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

				final String STR2 = "Developmet Officer LIC, Pune";

				Rect rectText2 = new Rect();

				boolean isTrue2 = true;

				while (isTrue2) {

					paintText2.getTextBounds(STR2, 0, STR2.length(), rectText2);

					if (bm1.getWidth() > rectText2.width()) {

						// //
						newCanvas.drawText(STR2, 20, bm1.getHeight()
								- (rectText3.height() + 50)/* / 3 */, paintText2);
						isTrue2 = false;
						// //
					} else {

						paintText2.setTextSize(paintText2.getTextSize() - 20);
						Log.e("MainActivity - ProcessingBitmap() - try - if() - while() - else",
								paintText2.getTextSize() + "");
					}
				}
				
				////////////////////////////////////////////////////
				
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

				while (isTrue1) {

					paintText1.getTextBounds(captionString, 0,
							captionString.length(), rectText1);

					if (bm1.getWidth() > (rectText1.width() + 50)) {

						// //
						newCanvas.drawText(
								captionString,
								20,
								bm1.getHeight()
										- (rectText3.height() + 40
												+ rectText2.height() + 40),
								paintText1);
						isTrue1 = false;
						// //
					} else {

						paintText1.setTextSize(paintText1.getTextSize() - 4);
						Log.e("MainActivity - ProcessingBitmap() - try - if() - while() - else",
								paintText1.getTextSize() + "");
					}
				}

				
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
						//Log.e("MainActivity - ProcessingBitmap() - try - if() - while() - else",
								//paintText1.getTextSize() + "");
					//}
				//}
				 */
				
				Toast.makeText(getApplicationContext(),
						"drawText: " + captionString, Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), "Caption empty!",
						Toast.LENGTH_LONG).show();
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return newBitmap;
		// return saveImage(newBitmap);
	}

	private BitmapDrawable writeTextOnDrawable(int drawableId, String text) {

		Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId)
				.copy(Bitmap.Config.ARGB_8888, true);

		Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTypeface(tf);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(convertToPixels(this, 11));

		Rect textRect = new Rect();
		paint.getTextBounds(text, 0, text.length(), textRect);

		Canvas canvas = new Canvas(bm);

		// If the text is bigger than the canvas , reduce the font size
		if (textRect.width() >= (canvas.getWidth() - 4))
			// the padding on either sides is considered as 4, so as to
			// appropriately fit in the text
			paint.setTextSize(convertToPixels(this, 7));
		// Scaling needs to be used for different dpi's

		// Calculate the positions
		int xPos = (canvas.getWidth() / 2) - 2;
		// -2 is for regulating the x position offset

		// "- ((paint.descent() + paint.ascent()) / 2)" is the distance from the
		// baseline to the center.
		int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint
				.ascent()) / 2));

		canvas.drawText(text, xPos, yPos, paint);

		return new BitmapDrawable(getResources(), bm);
	}

	public static int convertToPixels(Context context, int nDP) {
		final float conversionScale = context.getResources()
				.getDisplayMetrics().density;

		return (int) ((nDP * conversionScale) + 0.5f);

	}

	Bitmap saveImage(Bitmap origBmp) {

		File myDir = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/$MEME1/");
		myDir.mkdirs();

		// Random generator = new Random();
		// int n = 10000;
		// n = generator.nextInt(n);

		Calendar c = Calendar.getInstance();
		String fname = "MEME1_" + c.getTimeInMillis() + ".png";

		File file = new File(myDir, fname);

		if (file.exists())

			file.delete();

		try {

			FileOutputStream out = new FileOutputStream(file);

			/*
			 * // NEWLY ADDED CODE STARTS HERE [ Canvas canvas = new
			 * Canvas(origBmp);
			 * 
			 * Paint paint = new Paint(); paint.setColor(Color.WHITE); // Text
			 * Color paint.setStrokeWidth(12); // Text Size
			 * paint.setXfermode(new
			 * PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); // Text
			 * Overlapping Pattern
			 * 
			 * // some more settings...
			 * 
			 * canvas.drawBitmap(origBmp, 0, 0, paint);
			 * canvas.drawText("Testing...", 10, 10, paint); // NEWLY ADDED CODE
			 * ENDS HERE ]
			 */
			origBmp.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();

			Toast.makeText(getApplicationContext(),
					"Image saved successfully !!", Toast.LENGTH_LONG).show();

		} catch (Exception e) {

			e.printStackTrace();

			Toast.makeText(getApplicationContext(), "Something went wrong !!",
					Toast.LENGTH_LONG).show();
		}

		return origBmp;
	}
}