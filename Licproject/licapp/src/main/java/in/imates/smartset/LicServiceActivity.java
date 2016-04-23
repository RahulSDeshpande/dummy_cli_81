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
public class LicServiceActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private GridView grid;

    String [] licitemname = {"Service Helpline","Surrender Value","Paidup Value",
            "Settelment Option","Backdating","Plan Comparison","Late Fee"};

    int [] licitemimage = {R.drawable.ii2_1,R.drawable.ii2_2,R.drawable.ii2_3,R.drawable.ii2_4,R.drawable.ii2_5,R.drawable.ii2_6,R.drawable.ii2_7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lic_servicing);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("LIC Servicing");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        LicServiceAdapter adapter = new LicServiceAdapter(LicServiceActivity.this,licitemname,licitemimage);
        grid = (GridView) findViewById(R.id.grid_lic_servicing);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 6:
                        startActivity(new Intent(LicServiceActivity.this,LateFee.class));
                        break;
                    case 1:
                        startActivity(new Intent(LicServiceActivity.this,SurrenderValue.class));
                        break;
                    case 2:
                        startActivity(new Intent(LicServiceActivity.this,PaidUpValue.class));
                        break;
                    case 4:
                        startActivity(new Intent(LicServiceActivity.this,BackDating.class));
                        break;
                    case 3:
                        startActivity(new Intent(LicServiceActivity.this,SettlementOption.class));
                        break;
                    case 0:
                        startActivity(new Intent(LicServiceActivity.this,ServiceHelpline.class));
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
