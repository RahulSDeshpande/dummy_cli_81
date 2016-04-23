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
 * Created by root on 5/1/16.
 */
public class ReminderServiceActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private GridView grid;

    String [] remitemname = {"LIC Customers","Groups","Create SMS",
            "Send SMS","Due Reminders","Fup Update","Backup/Restore","Setting"};

    int [] remitemimage = {R.drawable.ii4,R.drawable.ii4,R.drawable.ii4,R.drawable.ii4,
            R.drawable.ii4,R.drawable.ii4,R.drawable.ii4,R.drawable.ii4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_services);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reminder Services");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        ReminderServiceAdapter adapter = new ReminderServiceAdapter(ReminderServiceActivity.this,remitemname,remitemimage);
        grid = (GridView) findViewById(R.id.grid_reminder_services);

        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 7:
                        startActivity(new Intent(ReminderServiceActivity.this,ReminderSettingActivity.class));
                        break;
                   /* case 1:
                        startActivity(new Intent(PremiumCalculatorActivity.this,MainScreenActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(PremiumCalculatorActivity.this,MainScreenActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(PremiumCalculatorActivity.this,MainScreenActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(PremiumCalculatorActivity.this,MainScreenActivity.class));
                        break;
                    case 0:
                        startActivity(new Intent(PremiumCalculatorActivity.this,MainScreenActivity.class));
                        break;*/
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
