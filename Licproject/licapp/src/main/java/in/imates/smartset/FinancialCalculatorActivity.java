package in.imates.smartset;

import android.content.Context;
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
public class FinancialCalculatorActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private GridView grid;
    Context context;

    int [] fincalitemimage = {R.drawable.ii3_1,R.drawable.ii3_2,R.drawable.ii3_3,R.drawable.ii3_4,
            R.drawable.ii3_5,R.drawable.ii2_4,R.drawable.ii3_8,R.drawable.ii2_3,
            R.drawable.ii3_10,R.drawable.ii3_11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_calculator);

        String [] fincalitemname = getResources().getStringArray(R.array.fincalitemname);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Financial Calculator");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        FinancialCalculatorAdapter adapter = new FinancialCalculatorAdapter(FinancialCalculatorActivity.this,fincalitemname,fincalitemimage);
        grid = (GridView) findViewById(R.id.grid_financial_cal);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(FinancialCalculatorActivity.this,HumanLifeValue.class));
                        break;
                    case 1:
                        startActivity(new Intent(FinancialCalculatorActivity.this,RetirementPlanner.class));
                        break;
                    case 2:
                        startActivity(new Intent(FinancialCalculatorActivity.this,EducationPlanner.class));
                        break;
                    case 3:
                        startActivity(new Intent(FinancialCalculatorActivity.this,MarriagePlanner.class));
                        break;
                    case 4:
                        startActivity(new Intent(FinancialCalculatorActivity.this,InflationCalculator.class));
                        break;
                    case 5:
                        startActivity(new Intent(FinancialCalculatorActivity.this,FixedDeposite.class));
                        break;
                    case 6:
                        startActivity(new Intent(FinancialCalculatorActivity.this,EMICalculator.class));
                        break;
                    case 7:
                        startActivity(new Intent(FinancialCalculatorActivity.this,PrasentValueCalculator.class));
                        break;
                    case 8:
                        startActivity(new Intent(FinancialCalculatorActivity.this,HealthPlanner.class));
                        break;
                    case 9:
                        startActivity(new Intent(FinancialCalculatorActivity.this,HabitSaving.class));
                        break;


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
