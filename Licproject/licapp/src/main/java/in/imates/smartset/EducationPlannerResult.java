package in.imates.smartset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 30/12/15.
 */
public class EducationPlannerResult extends AppCompatActivity {

    double Age;
    double Amount;
    double Annually;
    double Duration;
    String InflationAdjustedCost;
    double MoneyToSaveNow;
    double Total;
    String YEAR = "year";
    RelativeLayout footerlayout;
    double value;
    Toolbar mToolbar;


    public EducationPlannerResult() {
        Total = 0.0d;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_planner_result);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Education Planner Result");

        HashMap<String, String> mapsummary;
        Object[] objArr;
           this.footerlayout = (RelativeLayout) findViewById(R.id.footerlayout);
        ArrayList<HashMap<String, String>> fillmenuSetting = new ArrayList();
        Log.e("EducationDuration", String.valueOf(PremiumPlan.EducationDuration));
        for (int i = 1; ((double) i) <= PremiumPlan.EducationDuration; i++) {
            if (i == 1) {
                this.Age = PremiumPlan.CollegeYear;
                double Duration1 = this.Age - PremiumPlan.ChildAge;
                this.Amount = PremiumPlan.CollegeCost * Math.pow(1.0d + (PremiumPlan.ExpectedInflationRate / 100.0d), Duration1);
                this.value = this.Amount * PremiumPlan.EducationDuration;
                this.MoneyToSaveNow = this.value / Math.pow(1.0d + (PremiumPlan.ExpectedReturnRate / 100.0d), Duration1);
                this.Annually = ((this.value * PremiumPlan.ExpectedReturnRate) / 1200.0d) / ((1.0d + (PremiumPlan.ExpectedReturnRate / 1200.0d)) * (Math.pow(1.0d + (PremiumPlan.ExpectedReturnRate / 1200.0d), 12.0d * Duration1) - 1.0d));
            } else if (this.Age == 0.0d) {
                this.Age = 0.0d;
            } else if (this.Age > PremiumPlan.CollegeDuration - 2.0d) {
                this.Age = 0.0d;
            } else {
                this.Age += 1.0d;
            }
            this.InflationAdjustedCost = "Inflation Adjusted Cost for " + i + YEAR;
            if (i == 10) {
                this.InflationAdjustedCost = "Inflation Adjusted Cost for n year";
            }
            if (this.Age <= 0.0d) {
                this.Duration = 0.0d;
            } else {
                this.Duration = this.Age - PremiumPlan.ChildAge;
            }
            if (this.Duration <= 0.0d) {
                this.Amount = 0.0d;
            } else {
                this.Amount = PremiumPlan.CollegeCost * Math.pow(1.0d + (PremiumPlan.ExpectedInflationRate / 100.0d), this.Duration);
            }
            this.Total += this.Amount;
            mapsummary = new HashMap();
            objArr = new Object[1];
            objArr[0] = Double.valueOf(this.Age);
            mapsummary.put("Age", String.format("%.0f", objArr));
            mapsummary.put("InflationCost", this.InflationAdjustedCost);
            objArr = new Object[1];
            objArr[0] = Double.valueOf(this.Amount);
            mapsummary.put("Amount", String.format("%.0f", objArr));
            fillmenuSetting.add(mapsummary);
        }
        mapsummary = new HashMap();
        mapsummary.put("Total", "Total cost required (Rs.)");
        objArr = new Object[1];
        objArr[0] = Double.valueOf(this.Total);
        mapsummary.put("TotalValue", String.format("%.3f", objArr));
        mapsummary.put("Lumpsum", "Money to save now (Rs.)(LUMPSUM /SINGLE)");
        objArr = new Object[1];
        objArr[0] = Double.valueOf(this.MoneyToSaveNow);
        mapsummary.put("LumpsumValue", String.format("%.3f", objArr));
        mapsummary.put("Annually", "Money to save now (Rs.)(MONTHLY)");
        objArr = new Object[1];
        objArr[0] = Double.valueOf(this.Annually);
        mapsummary.put("AnnuallyValue", String.format("%.3f", objArr));
        fillmenuSetting.add(mapsummary);
        ((ListView) findViewById(R.id.lstEducationResult)).setAdapter(new SpecialAdapter(getApplicationContext(), fillmenuSetting, R.layout.activity_education_item, new String[]{"Age", "InflationCost", "Amount", "Total", "TotalValue", "Lumpsum", "LumpsumValue", "Annually", "AnnuallyValue"}, new int[]{R.id.Age, R.id.InflationCost, R.id.Amount, R.id.Total, R.id.TotalValue, R.id.Lumpsum, R.id.LumpsumValue, R.id.Annually, R.id.AnnuallyValue}));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
