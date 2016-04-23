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

/**
 * Created by root on 24/12/15.
 */
public class MainScreenActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private GridView grid;

    String[] itemname = {"Premium Calculator", "Lic Servicing", "Financial Calculator",
            "Reminder Service", "Greetings", "Online Services"};

    int[] itemimage = {R.drawable.ii1, R.drawable.ii2, R.drawable.ii3, R.drawable.ii4, R.drawable.ii5,
            R.drawable.ii6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SmartSet");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        MainScreenAdapter adapter = new MainScreenAdapter(MainScreenActivity.this, itemname, itemimage);
        grid = (GridView) findViewById(R.id.gridmainscreen);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position) {
                    case 1:
                        startActivity(new Intent(MainScreenActivity.this, LicServiceActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainScreenActivity.this, FinancialCalculatorActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainScreenActivity.this, GreetingMainActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainScreenActivity.this, OnlineServicesActivity.class));
                        break;
                    case 0:
                        startActivity(new Intent(MainScreenActivity.this, PremiumCalculatorActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainScreenActivity.this, ReminderServiceActivity.class));
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
