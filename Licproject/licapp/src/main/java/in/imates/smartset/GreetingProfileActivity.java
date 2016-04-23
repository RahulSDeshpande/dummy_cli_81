package in.imates.smartset;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by root on 24/12/15.
 */
public class GreetingProfileActivity extends AppCompatActivity {

    private final int SELECT_PHOTO = 1;
    private Toolbar mToolbar;
    private EditText etProfileName, etProfileDesignation, etProfileContactNum, etProfileEmailId, etProfileWebsite;
    private Button btnProfilePicPhoto, btnProfileSave;
    private ImageView ivProfilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_greeting_profile);

        initUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentImageReturned) {

        super.onActivityResult(requestCode, resultCode, intentImageReturned);

        switch (requestCode) {

            case SELECT_PHOTO:

                if (resultCode == RESULT_OK) {

                    try {

                        final Uri URI_IMAGE = intentImageReturned.getData();
                        final InputStream IMAGE_STREAM = getContentResolver().openInputStream(URI_IMAGE);
                        final Bitmap BMP_SELECTED_IMAGE = BitmapFactory.decodeStream(IMAGE_STREAM);
                        ivProfilePhoto.setImageBitmap(BMP_SELECTED_IMAGE);

                        SharedPreferences sharedPref = getSharedPreferences("lic_app_greeting_profile", Context.MODE_PRIVATE);
                        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();

                        sharedPrefEditor.putString("greeting_uri", intentImageReturned.toString());
                        sharedPrefEditor.apply();

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }

                }
        }
    }

    private void initUI() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        etProfileName = (EditText) findViewById(R.id.etProfileName);
        etProfileDesignation = (EditText) findViewById(R.id.etProfileDesignation);
        etProfileContactNum = (EditText) findViewById(R.id.etProfileContactNum);
        etProfileEmailId = (EditText) findViewById(R.id.etProfileEmailId);
        etProfileWebsite = (EditText) findViewById(R.id.etProfileWebsite);

        btnProfilePicPhoto = (Button) findViewById(R.id.btnProfilePickPhoto);
        btnProfileSave = (Button) findViewById(R.id.btnProfileSave);

        ivProfilePhoto = (ImageView) findViewById(R.id.ivProfilePhoto);

        SharedPreferences sharedPref = getSharedPreferences("lic_app_greeting_profile", Context.MODE_PRIVATE);
        etProfileName.setText(sharedPref.getString("name", ""));
        etProfileDesignation.setText(sharedPref.getString("designation", ""));
        etProfileContactNum.setText(sharedPref.getString("contact_num", ""));
        etProfileEmailId.setText(sharedPref.getString("email_id", ""));
        etProfileWebsite.setText(sharedPref.getString("website", ""));

        btnProfilePicPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentPhotoPicker = new Intent(Intent.ACTION_PICK);
                intentPhotoPicker.setType("image/*");
                startActivityForResult(intentPhotoPicker, SELECT_PHOTO);

            }
        });

        btnProfileSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (etProfileName.getText().toString().trim().length() > 0
                        &&
                        etProfileContactNum.getText().toString().trim().length() > 0) {

                    SharedPreferences sharedPref = getSharedPreferences("lic_app_greeting_profile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();

                    sharedPrefEditor.putString("name", etProfileName.getText().toString().trim());
                    sharedPrefEditor.putString("designation", etProfileDesignation.getText().toString().trim());
                    sharedPrefEditor.putString("contact_num", etProfileContactNum.getText().toString().trim());
                    sharedPrefEditor.putString("email_id", etProfileEmailId.getText().toString().trim());
                    sharedPrefEditor.putString("website", etProfileWebsite.getText().toString().trim());

                    sharedPrefEditor.apply();
                }
            }
        });
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