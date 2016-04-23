package in.imates.smartset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by root on 24/12/15.
 */
public class GreetingMainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private GridView grid;

    private final String GREETING_BASE_URL = "http://res.cloudinary.com/polygonpost/image/upload/licapp-dev/greeting";

    private final String[] itemname = {"LIC Plans", "Festivals", "Birthdays",
            "Anniversaries", "Quotes", "LIC Career", "Miscellaneous", "Profile"};

    final int[] itemimage = {R.drawable.ii5_1, R.drawable.ii5_2, R.drawable.ii5_3, R.drawable.ii3_4, R.drawable.ii2_1,
            R.drawable.ii5_6, R.drawable.ii5_7, R.drawable.ii5_8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_greeting_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SmartSet > Greeting");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        MainScreenAdapter adapter = new MainScreenAdapter(GreetingMainActivity.this, itemname, itemimage);
        grid = (GridView) findViewById(R.id.grid_view_greeting);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent;

                if(position == 7) {

                    intent = new Intent(GreetingMainActivity.this, GreetingProfileActivity.class);
                }
                else
                {
                    Toast.makeText(GreetingMainActivity.this,
                            "CLICKED @ position #" + position +
                                    "greeting_category: " + itemname[position] +
                                    "\ngreeting_base_url: " + GREETING_BASE_URL,
                            Toast.LENGTH_LONG).show();

                    intent = new Intent(GreetingMainActivity.this, GreetingCategoryActivity.class);
                    intent.putExtra("greeting_category_position", position);
                    intent.putExtra("greeting_category", itemname[position]);
                    intent.putExtra("greeting_base_url", GREETING_BASE_URL);

                }
                startActivity(intent);

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
