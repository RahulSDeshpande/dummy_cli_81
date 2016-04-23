package in.jigyasacodes.meme2demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CloudinaryTrial extends Activity {

	private FrameLayout fl;
	private ImageView ivCopy, ivBG;

	private Button btnImagePick, btnUploadImage;

	private boolean imagePickFLAG = true;

	private final int SELECT_PIC = 1;

	private InputStream mInputStream;
	private File mFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.cloudinary_trial_layout);

		fl = (FrameLayout) findViewById(R.id.fl);
		ivBG = (ImageView) findViewById(R.id.ivBG);
		ivCopy = (ImageView) findViewById(R.id.ivCopy);
		btnImagePick = (Button) findViewById(R.id.btnImagePick);
		btnUploadImage = (Button) findViewById(R.id.btnUploadImage);

		fl.setDrawingCacheEnabled(true);
		fl.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		// // ivCopy.setImageBitmap(fl.getDrawingCache());

		// // saveImage(fl.getDrawingCache());

		btnImagePick.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivityForResult(
						new Intent(Intent.ACTION_PICK).setType("image/*"),
						SELECT_PIC);
			}
		});

		btnUploadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				/*
				 * fl.setDrawingCacheEnabled(true);
				 * fl.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
				 * fl.buildDrawingCache();
				 * 
				 * ivCopy.setVisibility(View.VISIBLE);
				 * ivCopy.setImageBitmap(fl.getDrawingCache());
				 * 
				 * saveImage(fl.getDrawingCache(true));
				 */

				new UploadTask().execute(mInputStream);
			}
		});
	}

	class UploadTask extends AsyncTask<InputStream, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(final InputStream... INPUT_STREAM) {

			uploadImageToCloudinary(mInputStream);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);
		}
	}

	boolean uploadImageToCloudinary(final InputStream INPUT_STREAM) {

		Cloudinary cloudinary = new Cloudinary(
				"cloudinary://485558143258645:v3WhAEqDwIh_hmwOEfrKq6T2JSA@jigyasacodes-in");

		try {

			cloudinary
					.uploader()
					.upload("data:image/png;base64,iVBORw0KGgoAA\nAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAABlBMVEUAAAD///+l2Z/dAAAAM0l\nEQVR4nGP4/5/h/1+G/58ZDrAz3D/McH8yw83NDDeNGe4Ug9C9zwz3gVLMDA/A6\nP9/AFGGFyjOXZtQAAAAAElFTkSuQmCC",
							ObjectUtils.emptyMap());

			Toast.makeText(this, cloudinary.url().generate("jc_1.jpg"),
					Toast.LENGTH_LONG).show();

		} catch (IOException e) {

			Toast.makeText(this, "Error occurred while uploading your image..",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {

		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		switch (requestCode) {
		case SELECT_PIC:

			if (resultCode == RESULT_OK) {

				imagePickFLAG = false;
				btnUploadImage.setEnabled(true);
				// btnImagePickAndGenDrawingCache
				// .setText("Generate Drawing Cache");

				try {

					final Uri imageURI = imageReturnedIntent.getData();

					final InputStream is = getContentResolver()
							.openInputStream(imageURI);

					// File f = new
					// new UploadTask().execute(is);

					mInputStream = is;

					final Bitmap bmp = BitmapFactory.decodeStream(is);

					ivBG.setImageBitmap(bmp);
					Toast.makeText(this, "Image picking was successful !!",
							Toast.LENGTH_LONG).show();

					fl.setVisibility(View.VISIBLE);

				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

			} else {

				fl.setVisibility(View.INVISIBLE);
				btnUploadImage.setEnabled(false);
				imagePickFLAG = true;
				// btnImagePickAndGenDrawingCache.setText("Pick an Image again..");
				Toast.makeText(
						this,
						"Image picking was unsuccessful..!!\n\nPLEASE TRY AGAIN.",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	void saveImage(Bitmap origBmp) {

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

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}