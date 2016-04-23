package in.imates.smartset;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by root on 24/12/15.
 */
public class GreetingBannerActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private GridView grid;

    private final String[] itemname = {"LIC Plans", "Festivals", "Birthdays",
            "Anniversaries", "Quotes", "LIC Career", "Miscellaneous", "Profile"};

    private final String[] IMAGE_NAMES = {"/1.jpg", "/1.jpg", "/1.jpg", "/1.jpg"};

    private final String[] CATEGORY_SUB_URL = {"/lic_plans", "/festivals", "/birthdays",
            "/anniversaries", "/quotes", "/lic_career", "/misc", "profile"};

    private int intGreetingCategoryPosition = -1;
    private String strGreetingCategory = null;
    private String strGreetingBaseUrl = null;

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
                GreetingBannerActivity.this, IMAGE_NAMES, strGreetingBaseUrl + CATEGORY_SUB_URL[intGreetingCategoryPosition]);
        grid = (GridView) findViewById(R.id.grid_view_greeting_category);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(GreetingBannerActivity.this, "CLICKED @ position ######" + position, Toast.LENGTH_LONG).show();

                //Intent intent = new Intent(GreetingCategoryActivity.this, GreetingCategoryActivity.class);
                //intent.putExtra("greeting_category", itemname[position]);
                //intent.putExtra("greeting_base_url", GREETING_BASE_URL);
                //startActivity(intent);

                ImageView iv = ((ImageView) view.findViewById(R.id.grid_image));
                final Bitmap BITMAP = ((BitmapDrawable) iv.getDrawable()).getBitmap();

                processingBitmap(BITMAP);

            }
        });
    }

    private Bitmap processingBitmap(final Bitmap BITMAP) {

        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        Log.e("GreetingBannerActivity.ProcessingBitmap()", "1");

        //try {
        //bm1 = BitmapFactory.decodeStream(getContentResolver()
        //.openInputStream(source1));

        bm1 = BITMAP;
        Bitmap.Config config = bm1.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }

        Log.e("GreetingBannerActivity.ProcessingBitmap()", "2");

        newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(),
                config);
        Canvas newCanvas = new Canvas(newBitmap);

        Log.e("GreetingBannerActivity.ProcessingBitmap()","3");

        newCanvas.drawBitmap(bm1, 0, 0, null);

        String captionString = "Rahul S Deshpandeeeeeee";   //editTextCaption.getText().toString();

        Log.e("GreetingBannerActivity.ProcessingBitmap()","4");

        if (captionString != null) {

            Log.e("GreetingBannerActivity.ProcessingBitmap()","4.1");

            // Text 3
            Paint paintText3 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText3.setTextAlign(Paint.Align.LEFT);
            paintText3.setColor(Color.BLACK);
            paintText3.setTextSize(40);
            paintText3.setTypeface(Typeface.createFromAsset(
                    this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
            paintText3.setStyle(Paint.Style.FILL);
            paintText3.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

            final String STR3 = "+91 988 139 5270";

            Rect rectText3 = new Rect();

            boolean isTrue3 = true;

            Log.e("GreetingBannerActivity.ProcessingBitmap()","4.2");

            while (isTrue3) {

                Log.e("GreetingBannerActivity.ProcessingBitmap()","4.2.1");

                paintText3.getTextBounds(STR3, 0, STR3.length(), rectText3);

                if (bm1.getWidth() > rectText3.width()) {

                    Log.e("GreetingBannerActivity.ProcessingBitmap()","4.2.1.1");

                    // //
                    newCanvas.drawText(STR3, 200, bm1.getHeight() - 20
                                /*- (rectText3.height()) / 3 */, paintText3);
                    isTrue3 = false;
                    // //
                } else {

                    Log.e("GreetingBannerActivity.ProcessingBitmap()","4.2.1.2");

                    paintText3.setTextSize(paintText3.getTextSize() - 20);
                    Log.e("MainActivity - ProcessingBitmap() - try - if() - while() - else",
                            paintText3.getTextSize() + "");
                }
            }

            Log.e("GreetingBannerActivity.ProcessingBitmap()","4.3");

            // Text 2
            Paint paintText2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText2.setTextAlign(Paint.Align.LEFT);
            paintText2.setColor(Color.BLACK);
            paintText2.setTextSize(40);
            paintText2.setTypeface(Typeface.createFromAsset(
                    this.getAssets(), "fonts/Dosis-SemiBold.ttf"));
            paintText2.setStyle(Paint.Style.FILL);
            paintText2.setShadowLayer(6f, 4f, 4f, Color.DKGRAY);

            final String STR2 = "Developmet Officer LIC, Pune";

            Rect rectText2 = new Rect();

            boolean isTrue2 = true;

            while (isTrue2) {

                paintText2.getTextBounds(STR2, 0, STR2.length(), rectText2);

                if (bm1.getWidth() > rectText2.width()) {

                    // //
                    newCanvas.drawText(STR2, 200, bm1.getHeight()
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
            paintText1.setTextAlign(Paint.Align.LEFT);
            paintText1.setColor(Color.BLACK);
            paintText1.setTextSize(60);
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
                            200,
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
        //} catch (FileNotFoundException e) {
        // TODO: handle exception
        //  e.printStackTrace();
        //}

        return newBitmap;
        // return saveImage(newBitmap);
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