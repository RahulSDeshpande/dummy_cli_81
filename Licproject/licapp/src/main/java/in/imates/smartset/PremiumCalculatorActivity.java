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
public class PremiumCalculatorActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private GridView grid;

    String [] premitemname = {"Endownment Plans","MoneyBack Plans","Children Plans",
            "Term Assurance","Pension Plans","Health Plans"};

    int [] premitemimage = {R.drawable.ii2_3,R.drawable.ii2_7,R.drawable.ii11_3,R.drawable.ii11_4,R.drawable.ii3_2,R.drawable.ii3_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_calculator);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Premium Calculator");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        PremiumCalculatorAdapter adapter = new PremiumCalculatorAdapter(PremiumCalculatorActivity.this,premitemname,premitemimage);
        grid = (GridView) findViewById(R.id.grid_premium_calculator);

        grid.setAdapter(adapter);

          grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

               /* switch (position){
                    case 6:
                        startActivity(new Intent(PremiumCalculatorActivity.this,MainScreenActivity.class));
                        break;
                    case 1:
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
                        break;
                }*/
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
