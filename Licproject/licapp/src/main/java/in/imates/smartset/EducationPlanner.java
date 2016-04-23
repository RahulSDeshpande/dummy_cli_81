package in.imates.smartset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by root on 26/12/15.
 */
public class EducationPlanner extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    EditText et_childage;
    EditText et_childcollegeage;
    EditText et_collegedur;
    EditText et_costoncollege;
    EditText et_rateofreturn;
    EditText et_inflationrate;
    AlertDialog.Builder builder,builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_planner);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Education Planner");
        et_childage = (EditText) findViewById(R.id.childage);
        et_childcollegeage = (EditText) findViewById(R.id.childcollegeage);
        et_collegedur = (EditText) findViewById(R.id.collegedur);
        et_costoncollege = (EditText) findViewById(R.id.costoncollege);
        et_rateofreturn = (EditText) findViewById(R.id.rateofreturn);
        et_inflationrate = (EditText) findViewById(R.id.inflationrate);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_childage.getText().toString().contentEquals("") || et_childcollegeage.getText().toString().equals("")
                        || et_collegedur.getText().toString().equals("") || et_costoncollege.getText().toString().equals("")
                        || et_rateofreturn.getText().toString().equals("") || et_inflationrate.getText().toString().equals("")) {

                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false).
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {

                    PremiumPlan.ChildAge = Double.parseDouble(et_childage.getText().toString());
                    PremiumPlan.CollegeYear = Double.parseDouble(et_childcollegeage.getText().toString());
                    PremiumPlan.EducationDuration = Double.parseDouble(et_collegedur.getText().toString());
                    PremiumPlan.CollegeDuration = PremiumPlan.CollegeYear + PremiumPlan.EducationDuration;
                    PremiumPlan.CollegeCost = Double.parseDouble(et_costoncollege.getText().toString());
                    PremiumPlan.ExpectedReturnRate = Double.parseDouble(et_rateofreturn.getText().toString());
                    PremiumPlan.ExpectedInflationRate = Double.parseDouble(et_inflationrate.getText().toString());
                    EducationPlanner.this.startActivityForResult(new Intent(EducationPlanner.this, EducationPlannerResult.class), 0);
                }
            }

        });


        btnBack = (Button) findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
