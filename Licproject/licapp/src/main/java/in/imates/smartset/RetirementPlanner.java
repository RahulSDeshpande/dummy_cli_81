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

/**
 * Created by root on 26/12/15.
 */
public class RetirementPlanner extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    EditText et_name;
    EditText et_age;
    EditText et_retirement_age;
    EditText et_currentexp;
    EditText et_inflationrate;
    EditText et_monthlysaving;
    EditText et_rateofreturn;
    EditText et_rateofinterest;
    Button btnCalculate;
    AlertDialog.Builder builder,builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirement_planner);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Retirement Planner Calculator");
        et_name = (EditText) findViewById(R.id.txtName);
        et_age = (EditText) findViewById(R.id.txtAge);
        et_retirement_age = (EditText) findViewById(R.id.txtretirementage);
        et_currentexp = (EditText) findViewById(R.id.currentexp);
        et_inflationrate = (EditText) findViewById(R.id.inflation);
        et_monthlysaving = (EditText) findViewById(R.id.monthlysaving);
        et_rateofreturn = (EditText) findViewById(R.id.rateofreturn);
        et_rateofinterest = (EditText) findViewById(R.id.rateofinterest);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().equals("") || et_age.getText().toString().equals("")
                        || et_retirement_age.getText().toString().equals("") || et_currentexp.getText().toString().equals("")
                        || et_inflationrate.getText().toString().equals("") || et_monthlysaving.getText().toString().equals("")
                        || et_rateofreturn.getText().toString().equals("") || et_rateofinterest.getText().toString().equals("")) {
                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {
                    String Name = et_name.getText().toString();
                    double d2 = Double.parseDouble(et_age.getText().toString());
                    double d6 = Double.parseDouble(et_retirement_age.getText().toString());
                    double d3 = Double.parseDouble(et_currentexp.getText().toString());
                    double d4 = Double.parseDouble(et_inflationrate.getText().toString());
                    double d1 = Double.parseDouble(et_monthlysaving.getText().toString());
                    double d = Double.parseDouble(et_rateofreturn.getText().toString());
                    double d5 = Double.parseDouble(et_rateofinterest.getText().toString());
                    d2 = d6 - d2;
                    d3 = (d3 * 12D * Math.pow(1.0D + d4 / 100D, d2)) / (d5 / 100D);
                    d1 = (12D * d1 * (Math.pow(1.0D + d / 100D, d2) * 1.0D - 1.0D) * (1.0D + d / 100D)) / (d / 100D);
                    d4 = d3 - d1;
                    d = (d4 * d) / 1200D / ((1.0D + d / 1200D) * (Math.pow(1.0D + d / 1200D, 12D * d2) - 1.0D));
                    final String display = (new StringBuilder("Dear, ")).append(Name).append(" Lump sum requirement for this amount is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d3)
                    })).append("\n\nAmount you will accumulate with current saving is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d1)
                    })).append("\n\nShort fall in amount is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d4)
                    })).append("\n\nExtra amount you need to save per month is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d)
                    })).toString();
                    builder.setMessage(display).setCancelable(false).setPositiveButton("Close", new android.content.DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }

                    }).setNegativeButton("Share", new android.content.DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialoginterface, int i) {
                            Intent intent = new Intent("android.intent.action.SEND");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.TEXT", display);
                            startActivity(Intent.createChooser(intent, "Share With.."));
                        }


                    });
                    builder.create().show();
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
