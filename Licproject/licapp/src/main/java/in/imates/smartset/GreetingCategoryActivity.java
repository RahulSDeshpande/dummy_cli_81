package in.imates.smartset;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by root on 24/12/15.
 */
public class GreetingCategoryActivity extends AppCompatActivity {

    private final String[] itemname = {"LIC Plans", "Festivals", "Birthdays",
            "Anniversaries", "Quotes", "LIC Career", "Miscellaneous", "Profile"};
    private final String[] IMAGE_NAMES = {"/1.jpg", "/1.jpg", "/1.jpg", "/1.jpg"};
    private final String[] CATEGORY_SUB_URL = {"/lic_plans", "/festivals", "/birthdays",
            "/anniversaries", "/quotes", "/lic_career", "/misc", "profile"};
    private Toolbar mToolbar;
    private GridView grid;
    private int intGreetingCategoryPosition = -1;
    private String strGreetingCategory = null;
    private String strGreetingBaseUrl = null;

    private ImageView ivGreeting;
    private Button btnGreetingShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_greeting_category);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        strGreetingBaseUrl = this.getIntent().getStringExtra("greeting_base_url");
        intGreetingCategoryPosition = this.getIntent().getIntExtra("greeting_category_position", -1);
        strGreetingCategory = this.getIntent().getStringExtra("greeting_category");
        getSupportActionBar().setTitle("SmartSet > " + strGreetingCategory);

        GreetingCategoryAdapter adapter = new GreetingCategoryAdapter(
                GreetingCategoryActivity.this, IMAGE_NAMES, strGreetingBaseUrl + CATEGORY_SUB_URL[intGreetingCategoryPosition]);
        grid = (GridView) findViewById(R.id.grid_view_greeting_category);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(GreetingCategoryActivity.this, "CLICKED @ position #" + position, Toast.LENGTH_LONG).show();

                //Intent intent = new Intent(GreetingCategoryActivity.this, GreetingCategoryActivity.class);
                //intent.putExtra("greeting_category", itemname[position]);
                //intent.putExtra("greeting_base_url", GREETING_BASE_URL);
                //startActivity(intent);


                ImageView iv = ((ImageView) view.findViewById(R.id.grid_image));
                final Bitmap BITMAP = ((BitmapDrawable) iv.getDrawable()).getBitmap();

                showGreetingDialog(processingBitmap(BITMAP));
                //  saveImage(processingBitmap(BITMAP));
            }
        });
    }

    private void showGreetingDialog(final String FILE_NAME) {

        final Dialog DIALOG = new Dialog(GreetingCategoryActivity.this);

        DIALOG.setContentView(R.layout.dialog_greeting_share);
        DIALOG.setTitle("Your Greet Card");

        ImageView ivGreeting = (ImageView) DIALOG.findViewById(R.id.ivGreeting);
        ivGreeting.setImageBitmap(BMP);

        Button btnGreetingShare = (Button) DIALOG.findViewById(R.id.btnGreetingShare);

        btnGreetingShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                shareGreeting(FILE_NAME);
                DIALOG.dismiss();
            }
        });

        DIALOG.show();

    }

    private void shareGreeting(final String FILE_NAME) {

        Intent intentShare = new Intent(Intent.ACTION_SEND);

        intentShare.setType("image/*");

        File greetingFileToBeShared = new File(FILE_NAME);

        Uri uri = Uri.fromFile(greetingFileToBeShared);
        intentShare.putExtra(Intent.EXTRA_STREAM, uri);
        //  intentShare.putExtra(Intent.EXTRA_TEXT, uri.toString());

        startActivity(Intent.createChooser(intentShare, "SmartSet: Share Greeting via"));
    }

    private String processingBitmap(final Bitmap BITMAP) {

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

        SharedPreferences sharedPref = getSharedPreferences("lic_app_greeting_profile", Context.MODE_PRIVATE);

        String captionString = sharedPref.getString("name", "");   //editTextCaption.getText().toString();

        if (captionString != null || captionString.length() == 0) {

            Log.e("GreetingCategoryActivity.ProcessingBitmap()", "4.1");

            // Text 3
            Paint paintText3 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText3.setTextAlign(Paint.Align.LEFT);
            paintText3.setColor(Color.BLACK);
            paintText3.setTextSize(20);
            paintText3.setTypeface(Typeface.createFromAsset(
                    this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
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
                    this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
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
                    this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
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

            final String strGreetingUri = this.getSharedPreferences(
                    "lic_app_greeting_profile", Context.MODE_PRIVATE)
                    .getString("greeting_uri", "");

            if (false/*strGreetingUri.length() > 0*/) {

                Log.e("if(strGreetingUri.length() > 0) 1",
                        "strGreetingUri:" + strGreetingUri);

                Uri uriGreeting = Uri.parse(strGreetingUri);

                //  Bitmap bmpImageProfile = BitmapFactory.decodeResource(
                //  GreetingCategoryActivity.this.getResources(), R.drawable.smart_set_logo);
                Bitmap bmpImageProfile = null;

                try {

                    bmpImageProfile = BitmapFactory.decodeStream(getContentResolver()
                            .openInputStream(uriGreeting));
                    Log.e("if(strGreetingUri.length() > 0) 2",
                            "strGreetingUri:" + strGreetingUri);

                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                }

                //  bmpImageProfile.setHeight(80);
                //  bmpImageProfile.setHeight(80);

                int newBmpImageProfileHeight = (newBitmap.getHeight() - bmpImageProfile.getHeight()) - 15;
                int newBmpImageProfileWidth = /*(newBitmap.getWidth() - bmpImageProfile.getWidth()) - */15;
                newCanvas.drawBitmap(bmpImageProfile, newBmpImageProfileWidth, newBmpImageProfileHeight, null);
            }

            Bitmap bmpImageWatermark = BitmapFactory.decodeResource(
                    GreetingCategoryActivity.this.getResources(), R.drawable.imates);
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

            Toast.makeText(getApplicationContext(),
                    "drawText: " + captionString, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill Profile details first.",
                    Toast.LENGTH_LONG).show();
        }
        //} catch (FileNotFoundException e) {
        // TODO: handle exception
        //  e.printStackTrace();
        //}

        //  return newBitmap;
        return saveImage(newBitmap);
    }

    private Bitmap BMP;
    private String bmpFileName;

    private String saveImage(Bitmap origBmp) {

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
                    "Image saved successfully @ location '"
                            + fname + "'",
                    Toast.LENGTH_LONG).show();

            BMP = origBmp;
            bmpFileName = fname;


        } catch (Exception e) {

            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "Something went wrong !!",
                    Toast.LENGTH_LONG).show();
        }

        //  return origBmp;

        return fname;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}